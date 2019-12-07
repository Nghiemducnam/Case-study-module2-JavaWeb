package com.codegym.validators;

import com.codegym.model.Customer;
import com.codegym.model.Mobile;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class PhoneNumberValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(Mobile.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Customer customer = (Customer) target;
        String phoneNumber = customer.getCustomerPhone();

        if(phoneNumber == null){
            errors.rejectValue("customerPhone","phoneNumber.empty","Phone number is empty!");
        }else {
            if(phoneNumber.isEmpty()){
                errors.rejectValue("customerPhone","phoneNumber.empty","Phone number is empty!");
            }
            if(!phoneNumber.startsWith("0")){
                errors.rejectValue("customerPhone","phoneNumber.StartWithZero","Phone number have to start with zero");
            }
        }

    }
}