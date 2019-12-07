package com.codegym.model;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "bill")
public class Bill implements Validator {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long billId;
    @NotNull
    private Long billNumber;
    @NotEmpty
    private String billDate;
    private boolean billStatus;

    @ManyToOne
    @JoinColumn(name = "employeeId")
    private Employee employee;

    @OneToMany (targetEntity = BillDetail.class)
    private Set<BillDetail> billDetails;

    public Bill() {
    }

    public Bill(Long billNumber, String billDate, boolean billStatus, Employee employee, Set<BillDetail> billDetails) {
        this.billNumber = billNumber;
        this.billDate = billDate;
        this.billStatus = billStatus;
        this.employee = employee;
        this.billDetails = billDetails;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public Long getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(Long billNumber) {
        this.billNumber = billNumber;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public boolean isBillStatus() {
        return billStatus;
    }

    public void setBillStatus(boolean billStatus) {
        this.billStatus = billStatus;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Set<BillDetail> getBillDetails() {
        return billDetails;
    }

    public void setBillDetails(Set<BillDetail> billDetails) {
        this.billDetails = billDetails;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Bill.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
