package com.banvien.tpk.core.service;

import com.banvien.tpk.core.domain.BookProduct;
import com.banvien.tpk.core.domain.BookProductBill;
import com.banvien.tpk.core.domain.Importproduct;
import com.banvien.tpk.core.domain.User;
import com.banvien.tpk.core.dto.BookProductBillBean;
import com.banvien.tpk.core.dto.SalePerformanceDTO;
import com.banvien.tpk.core.dto.SalesPerformanceBean;
import com.banvien.tpk.core.exception.ObjectNotFoundException;

import java.util.List;
import java.util.Map;


public interface BookProductBillService extends GenericService<BookProductBill,Long> {
    String saveOrUpdateBookingBill(List<Long> bookedProductIDs, Map<Long, Long> mapSaleWarehouse, Long loginID, Long billID) throws ObjectNotFoundException;

    Object[] search(BookProductBillBean bean);

    Integer deleteItems(String[] checkList);

    void updateBookProductBill(Long billID, Long customerID,Long loginID,String des) throws ObjectNotFoundException;

    void updateStatus(Long bookProductBillID, Integer confirmed, Long loginID);

    void updateReject(String note, Long bookProductBillID, Long loginUserId);

    void updateConfirm(Long bookProductBillID, Long loginUserId) throws ObjectNotFoundException;

    BookProductBill saveOrUpdateBillInfo(BookProductBillBean bean) throws Exception;

    String getLatestBookBillNumber();

    BookProductBill updatePrice(BookProductBillBean bean);

    List<BookProduct> findByBill(Long bookProductBillID);

    List<Importproduct> findProductInBookBill(Long bookProductBillID);

    List<SalePerformanceDTO> salesPerformance(SalesPerformanceBean bean);

    Map<Long,User> findBookedUser(List<Long> productIds);

    boolean checkAllowConfirm(Long bookProductBillID);
}