package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.model.Employee;
import com.codegym.model.Producer;
import com.codegym.service.ICustomerService;
import com.codegym.service.IEmployeeService;
import com.codegym.service.IProducerService;
import com.codegym.validators.PhoneNumberValidator;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class CustomerController {
    @Autowired
    private ICustomerService customerService;


    @GetMapping("/admin/list-customer")
    public ModelAndView showListCustomer() {
        Iterable<Customer> customers = customerService.findAll();
        ModelAndView modelAndView = new ModelAndView("customer/list");
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

    @GetMapping("/admin/create-customer")
    public ModelAndView showCreateCustomer() {
        ModelAndView modelAndView = new ModelAndView("customer/create");
        modelAndView.addObject("customers", new Customer());
        return modelAndView;
    }

    @PostMapping("/admin/save-customer")
    public ModelAndView saveCustomer(@Valid @ModelAttribute ("customers") Customer customer, BindingResult bindingResult) {
//        PhoneNumberValidator validator = new PhoneNumberValidator();
//        validator.validate(customer, bindingResult);
        new Customer().validate(customer, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("customer/create");
            return modelAndView;
        } else {
            customerService.save(customer);
            ModelAndView modelAndView = new ModelAndView("redirect:/admin/list-customer");
            modelAndView.addObject("customers", new Customer());
            return modelAndView;
        }
    }



    @GetMapping("/admin/edit-customer/{id}")
    public ModelAndView showEditFormCustomer(@PathVariable Long id) {
        Customer customer = customerService.findById(id);
        ModelAndView modelAndView = new ModelAndView("customer/edit");
        modelAndView.addObject("customers", customer);
        return modelAndView;
    }

    @PostMapping("/admin/edit-customer")
    public ModelAndView updateCustomer(@Valid @ModelAttribute ("customers")Customer customer, BindingResult bindingResult) {
        new Customer().validate(customer, bindingResult);
        if(bindingResult.hasFieldErrors()){
            ModelAndView modelAndView = new ModelAndView("customer/edit");
            return modelAndView;
        }
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("customer/edit");
        modelAndView.addObject("customers", new Customer());
        modelAndView.addObject("message", "Updated successfully");
        return modelAndView;
    }

    @GetMapping ("/admin/delete-customer/{id}")
    public ModelAndView showDeleteFormCustomer(@PathVariable ("id") Long id) {
        Customer customer = customerService.findById(id);
        ModelAndView modelAndView = new ModelAndView("customer/delete");
        modelAndView.addObject("customers", customer);
        return modelAndView;
    }

    @PostMapping("/admin/remove-customer")
    public String deleteCustomer(Customer customer) {
        customerService.remove(customer.getCustomerId());
        return "redirect:/admin/list-customer";
    }
}
