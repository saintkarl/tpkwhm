package com.banvien.tpk.core.dto;

import java.util.Date;

/**
 * Created by KhanhChu on 12/9/2017.
 */
public class SalesPerformanceBean extends AbstractBean<SalePerformanceDTO> {
    private Date fromDate;
    private Date toDate;
    private boolean showLessBuy = false;

    public SalesPerformanceBean() {
        this.pojo = new SalePerformanceDTO();
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public boolean isShowLessBuy() {
        return showLessBuy;
    }

    public void setShowLessBuy(boolean showLessBuy) {
        this.showLessBuy = showLessBuy;
    }
}
