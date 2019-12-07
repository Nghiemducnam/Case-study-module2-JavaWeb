package com.codegym.model;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@Table (name = "customer")
public class Customer implements Validator {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long customerId;
    @NotEmpty
    private String customerName;

    private String customerPhone;
    @NotEmpty
    @Email
    private String customerEmail;

    @OneToMany (targetEntity = BillDetail.class)
    private Set<BillDetail> billDetails;

    public Customer() {
    }

    public Customer(@NotEmpty String customerName, @NotEmpty String customerPhone, @NotEmpty @Email String customerEmail, Set<BillDetail> billDetails) {
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.customerEmail = customerEmail;
        this.billDetails = billDetails;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public Set<BillDetail> getBillDetails() {
        return billDetails;
    }

    public void setBillDetails(Set<BillDetail> billDetails) {
        this.billDetails = billDetails;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Customer.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Customer customer = (Customer) target;
        String number = customer.getCustomerPhone();
        ValidationUtils.rejectIfEmpty(errors,"customerPhone","number.empty");
        if(number.length() <10 || number.length()>11 ){
            errors.rejectValue("customerPhone","number.length");
        }
        if(!number.startsWith("0")){
            errors.rejectValue("customerPhone", "number.startsWith");
        }
        if (!number.matches("(^$|[0-9]*$)")){
            errors.rejectValue("customerPhone", "number.matches");
        }

    }
    }

