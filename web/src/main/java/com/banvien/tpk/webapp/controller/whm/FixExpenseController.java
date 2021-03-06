package com.banvien.tpk.webapp.controller.whm;

import com.banvien.tpk.core.Constants;
import com.banvien.tpk.core.domain.FixExpense;
import com.banvien.tpk.core.dto.FixExpenseBean;
import com.banvien.tpk.core.exception.DuplicateException;
import com.banvien.tpk.core.exception.ObjectNotFoundException;
import com.banvien.tpk.core.service.FixExpenseService;
import com.banvien.tpk.webapp.util.RequestUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class FixExpenseController extends ApplicationObjectSupport {
    private transient final Log log = LogFactory.getLog(getClass());

    @Autowired
    private FixExpenseService fixExpenseService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
	}
    
    @RequestMapping("/whm/fixexpense/edit.html")
	public ModelAndView edit(@ModelAttribute(Constants.FORM_MODEL_KEY) FixExpenseBean bean, BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView("/whm/fixexpense/edit");

		String crudaction = bean.getCrudaction();
		FixExpense pojo = bean.getPojo();
		if(StringUtils.isNotBlank(crudaction) && crudaction.equals("insert-update")) {
			try {

				if(!bindingResult.hasErrors()) {
					if(pojo.getFixExpenseID() != null && pojo.getFixExpenseID() > 0) {
						this.fixExpenseService.updateItem(bean);
                        mav = new ModelAndView("redirect:/whm/fixexpense/list.html?isUpdate=true");
                    } else {
						this.fixExpenseService.addNew(bean);
                        mav = new ModelAndView("redirect:/whm/fixexpense/list.html?isAdd=true");
                    }
                    return mav;
                }
			}catch (ObjectNotFoundException oe) {
				logger.error(oe.getMessage(), oe);
                mav.addObject("alertType","error");
                mav.addObject("messageResponse", this.getMessageSourceAccessor().getMessage("database.exception.keynotfound"));
			}catch (DuplicateException de) {
				logger.error(de.getMessage());
                mav.addObject("alertType","error");
                mav.addObject("messageResponse", this.getMessageSourceAccessor().getMessage("database.exception.duplicate"));
			}catch(Exception e) {
				logger.error(e.getMessage(), e);
                mav.addObject("alertType","error");
                mav.addObject("messageResponse", this.getMessageSourceAccessor().getMessage("general.exception.msg"));
			}
		}
        if(!bindingResult.hasErrors()&& bean.getPojo().getFixExpenseID() != null && bean.getPojo().getFixExpenseID() > 0) {
            try {
                FixExpense itemObj = this.fixExpenseService.findById(pojo.getFixExpenseID());
                bean.setPojo(itemObj);
            }
            catch (Exception e) {
                logger.error("Could not found news " + bean.getPojo().getFixExpenseID(), e);
            }
        }

		mav.addObject(Constants.FORM_MODEL_KEY, bean);
        return mav;
	}


    @RequestMapping(value={"/whm/fixexpense/list.html"})
    public ModelAndView list(FixExpenseBean bean,HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/whm/fixexpense/list");
		if(StringUtils.isNotBlank(bean.getCrudaction()) && bean.getCrudaction().equals(Constants.ACTION_DELETE)) {
			Integer totalDeleted = 0;
			try {
				totalDeleted = fixExpenseService.deleteItems(bean.getCheckList());
				mav.addObject("totalDeleted", totalDeleted);
			}catch (Exception e) {
				log.error(e.getMessage(), e);
                mav.addObject("alertType","error");
                mav.addObject(Constants.MESSAGE_RESPONSE_MODEL_KEY, this.getMessageSourceAccessor().getMessage("database.multipledelete.exception"));
			}
		}
        executeSearch(bean, request);
        mav.addObject(Constants.LIST_MODEL_KEY, bean);
        showAlert(mav,bean.getIsAdd(),bean.getIsUpdate(),bean.getIsError());
        return mav;
    }

    private void executeSearch(FixExpenseBean bean, HttpServletRequest request) {
        RequestUtil.initSearchBean(request, bean);
        Object[] results = this.fixExpenseService.search(bean);
        bean.setListResult((List<FixExpense>)results[1]);
        bean.setTotalItems(Integer.valueOf(results[0].toString()));
    }

    private void showAlert(ModelAndView mav,Boolean isAdd, Boolean isUpdate, Boolean isError){
        if(isAdd){
            mav.addObject("alertType","success");
            mav.addObject(Constants.MESSAGE_RESPONSE_MODEL_KEY, this.getMessageSourceAccessor().getMessage("database.add.successful"));
        }else if(isUpdate){
            mav.addObject("alertType","success");
            mav.addObject(Constants.MESSAGE_RESPONSE_MODEL_KEY, this.getMessageSourceAccessor().getMessage("database.update.successful"));
        }else if(isError){
            mav.addObject("alertType","error");
            mav.addObject(Constants.MESSAGE_RESPONSE_MODEL_KEY, this.getMessageSourceAccessor().getMessage("error.occur"));
        }
    }
}
