package com.banvien.tpk.core.domain;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 * User: Chu Quoc Khanh
 * Date: 11/03/14
 * Time: 22:50
 * To change this template use File | Settings | File Templates.
 */
public class ImportMaterialBillLog implements Serializable {
    private Long importMaterialBillLogID;
    private String note;
    private Integer status;
    private Timestamp createdDate;
    private User createdBy;
    private Importmaterialbill importmaterialbill;

    public Long getImportMaterialBillLogID() {
        return importMaterialBillLogID;
    }

    public void setImportMaterialBillLogID(Long importMaterialBillLogID) {
        this.importMaterialBillLogID = importMaterialBillLogID;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public Importmaterialbill getImportmaterialbill() {
        return importmaterialbill;
    }

    public void setImportmaterialbill(Importmaterialbill importmaterialbill) {
        this.importmaterialbill = importmaterialbill;
    }
}
