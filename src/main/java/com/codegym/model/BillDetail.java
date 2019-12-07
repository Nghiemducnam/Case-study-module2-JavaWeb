package com.codegym.model;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "billDetail")
public class BillDetail implements Validator {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long billDetailId;
    @NotNull
    @Min(1)
    private Long mobileNumber;
    @NotNull
    @Min(1)
    private Long priceEach;

    private Long totalPrice;
    @NotNull
    private Date billDetailDate;
    private boolean billDetailStatus;


    @OneToMany (targetEntity = Mobile.class)
    private Set<Mobile> mobiles;

    @ManyToOne
    @JoinColumn(name = "billId")
    private Bill bill;

    @ManyToOne
    @JoinColumn (name = "customerId")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "paymentId")
    private Payment payment;

    public BillDetail() {
    }

    public BillDetail(Long mobileNumber, Long priceEach, Long totalPrice, Date billDetailDate, boolean billDetailStatus, Set<Mobile> mobiles, Bill bill, Customer customer, Payment payment) {
        this.mobileNumber = mobileNumber;
        this.priceEach = priceEach;
        this.totalPrice = totalPrice;
        this.billDetailDate = billDetailDate;
        this.billDetailStatus = billDetailStatus;
        this.mobiles = mobiles;
        this.bill = bill;
        this.customer = customer;
        this.payment = payment;
    }

    public Long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(Long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Long getTotalPrice() {
        return mobileNumber * priceEach;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getBillDetailId() {
        return billDetailId;
    }

    public void setBillDetailId(Long billDetailId) {
        this.billDetailId = billDetailId;
    }

    public Long getBillDetailNumber() {
        return mobileNumber;
    }

    public void setBillDetailNumber(Long billDetailNumber) {
        this.mobileNumber = billDetailNumber;
    }

    public Long getPriceEach() {
        return priceEach;
    }

    public void setPriceEach(Long priceEach) {
        this.priceEach = priceEach;
    }

    public Date getBillDetailDate() {
        return billDetailDate;
    }

    public void setBillDetailDate(Date billDetailDate) {
        this.billDetailDate = billDetailDate;
    }

    public boolean isBillDetailStatus() {
        return billDetailStatus;
    }

    public void setBillDetailStatus(boolean billDetailStatus) {
        this.billDetailStatus = billDetailStatus;
    }

    public Set<Mobile> getMobiles() {
        return mobiles;
    }

    public void setMobiles(Set<Mobile> mobiles) {
        this.mobiles = mobiles;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return BillDetail.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
