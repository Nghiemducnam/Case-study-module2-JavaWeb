package com.codegym.model;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@Table(name = "payment")
public class Payment implements Validator {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long paymentId;
    @NotEmpty
    private String paymentCode;
    @NotEmpty
    private String paymentDescription;
    @NotEmpty
    private String paymentStatus;

    @OneToMany (targetEntity = BillDetail.class)
    private Set<BillDetail> billDetails;

    public Payment() {
    }

    public Payment(@NotEmpty String paymentCode, @NotEmpty String paymentDescription, @NotEmpty String paymentStatus, Set<BillDetail> billDetails) {
        this.paymentCode = paymentCode;
        this.paymentDescription = paymentDescription;
        this.paymentStatus = paymentStatus;
        this.billDetails = billDetails;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }

    public String getPaymentDescription() {
        return paymentDescription;
    }

    public void setPaymentDescription(String paymentDescription) {
        this.paymentDescription = paymentDescription;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Set<BillDetail> getBillDetails() {
        return billDetails;
    }

    public void setBillDetails(Set<BillDetail> billDetails) {
        this.billDetails = billDetails;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Payment.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
