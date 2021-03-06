package com.banvien.tpk.core.dto;

import com.banvien.tpk.core.domain.Customer;
import com.banvien.tpk.core.domain.User;
import com.banvien.tpk.core.util.HTMLGeneratorUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by KhanhChu on 20/11/2017.
 */
public class SalePerformanceDTO implements Serializable {
    private User salesman;
    private Map<String, SalesByDateDTO> salesByDates = new HashMap<String, SalesByDateDTO>();
    private Double totalWeight = 0d;
    private int totalCustomer = 0;
    private Map<Customer, Double> customerConsumption = new HashMap<Customer, Double>();
    private Map<Customer, Boolean> lessBuyCustomer = new HashMap<Customer, Boolean>();
    private List<Customer> wontBuyCustomer = new ArrayList<Customer>();

    public SalePerformanceDTO() {
    }

    public SalePerformanceDTO(User createdBy) {
        salesman = createdBy;
    }

    public User getSalesman() {
        return salesman;
    }

    public void setSalesman(User salesman) {
        this.salesman = salesman;
    }

    public Map<String, SalesByDateDTO> getSalesByDates() {
        return salesByDates;
    }

    public void setSalesByDates(Map<String, SalesByDateDTO> salesByDates) {
        this.salesByDates = salesByDates;
    }

    public Double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(Double totalWeight) {
        this.totalWeight = totalWeight;
    }

    public int getTotalCustomer() {
        return totalCustomer;
    }

    public void setTotalCustomer(int totalCustomer) {
        this.totalCustomer = totalCustomer;
    }

    public Map<Customer, Double> getCustomerConsumption() {
        return customerConsumption;
    }

    public void setCustomerConsumption(Map<Customer, Double> customerConsumption) {
        this.customerConsumption = customerConsumption;
    }

    public String getConsumptionHTML(){
        return HTMLGeneratorUtil.createConsumptionHTML(customerConsumption);
    }

    public Map<Customer, Boolean> getLessBuyCustomer() {
        return lessBuyCustomer;
    }

    public void setLessBuyCustomer(Map<Customer, Boolean> lessBuyCustomer) {
        this.lessBuyCustomer = lessBuyCustomer;
    }

    public List<Customer> getWontBuyCustomer() {
        return wontBuyCustomer;
    }

    public void setWontBuyCustomer(List<Customer> wontBuyCustomer) {
        this.wontBuyCustomer = wontBuyCustomer;
    }
}
