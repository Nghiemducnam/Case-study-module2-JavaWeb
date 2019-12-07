package com.codegym.model;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table (name = "employee")
public class Employee implements Validator {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long employeeId;
    @NotEmpty
    private String employeeName;
    @NotEmpty
    private String employeePhone;
    @NotEmpty
    @Email
    private String employeeEmail;
    @Size(max = 30, min = 6)
    private String employeeUser;
    @Size(min = 6, max = 15)
    private String employeePassword;

    @OneToMany (targetEntity = Bill.class)
    private Set<Bill> bills;

    public Employee() {
    }

    public Employee(@NotEmpty String employeeName, @NotEmpty String employeePhone, @NotEmpty String employeeEmail, @Size(max = 30, min = 6) String employeeUser, @Size(min = 6, max = 15) String employeePassword, Set<Bill> bills) {
        this.employeeName = employeeName;
        this.employeePhone = employeePhone;
        this.employeeEmail = employeeEmail;
        this.employeeUser = employeeUser;
        this.employeePassword = employeePassword;
        this.bills = bills;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeePhone() {
        return employeePhone;
    }

    public void setEmployeePhone(String employeePhone) {
        this.employeePhone = employeePhone;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getEmployeeUser() {
        return employeeUser;
    }

    public void setEmployeeUser(String employeeUser) {
        this.employeeUser = employeeUser;
    }

    public String getEmployeePassword() {
        return employeePassword;
    }

    public void setEmployeePassword(String employeePassword) {
        this.employeePassword = employeePassword;
    }

    public Set<Bill> getBills() {
        return bills;
    }

    public void setBills(Set<Bill> bills) {
        this.bills = bills;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Employee.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Employee employee = (Employee) target;
        String number = employee.getEmployeePhone();
        ValidationUtils.rejectIfEmpty(errors,"employeePhone","number.empty");
        if(number.length() <10 || number.length()>11 ){
            errors.rejectValue("employeePhone","number.length");
        }
        if(!number.startsWith("0")){
            errors.rejectValue("employeePhone", "number.startsWith");
        }
        if (!number.matches("(^$|[0-9]*$)")){
            errors.rejectValue("employeePhone", "number.matches");
        }

    }
    }

