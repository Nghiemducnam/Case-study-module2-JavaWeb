package com.codegym.controller;

import com.codegym.model.Bill;
import com.codegym.model.Employee;
import com.codegym.service.IBillService;
import com.codegym.service.IEmployeeService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class BillController {
    @Autowired
    private IBillService billService;

    @Autowired
    private IEmployeeService employeeService;

    @ModelAttribute("employee")
    public Iterable<Employee> employees () {
        return employeeService.findAll();
    }


//    @GetMapping("/list-bill")
//    public ModelAndView showList(@RequestParam ("search") Optional<String> search,
//                                 @PageableDefault(size = 5, direction = Sort.Direction.ASC)
//                                         Pageable pageable) {
//        Page<Bill> bills;
//        if (search.isPresent()) {
//            bills = billService.findAllByEmployee(,pageable);
//        } else {
//            phones = phoneService.findAll(pageable);
//        }
//        ModelAndView modelAndView = new ModelAndView("phone/list");
//        modelAndView.addObject("phones", phones);
//        return modelAndView;
//    }

    //    @GetMapping("/list-bill")
//    public ModelAndView searchByEmployee (@RequestParam("search") Long employeeId, Pageable pageable) {
//        Page<Bill> bills;
//        if (employeeId == -1) {
//            bills = billService.findAll(pageable);
//        } else {
//            Employee employee = employeeService.findById(employeeId);
//            bills = billService.findAllByEmployee(employee, pageable);
//        }
//
//        ModelAndView modelAndView = new ModelAndView("bill/list");
//        modelAndView.addObject("bills",bills);
//        modelAndView.addObject("s",employeeId);
//        return modelAndView;
//    }
    @GetMapping("/admin/list-bill")
    public ModelAndView showListBill(@PageableDefault(size = 10) Pageable pageable) {
        Page<Bill> bills = billService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("bill/list");
        modelAndView.addObject("bills", bills);
        return modelAndView;
    }


    @GetMapping("/admin/create-bill")
    public ModelAndView showCreateBill() {
        ModelAndView modelAndView = new ModelAndView("bill/create");
        modelAndView.addObject("bills", new Bill());
        return modelAndView;
    }

    @PostMapping("/admin/save-bill")
    public ModelAndView saveBill(@Valid @ModelAttribute ("bills") Bill bill, BindingResult bindingResult) {
        new Bill().validate(bill, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("bill/create");
            return modelAndView;
        } else {
            billService.save(bill);
            ModelAndView modelAndView = new ModelAndView("redirect:/admin/list-bill");
            modelAndView.addObject("bills", new Bill());
            return modelAndView;
        }
    }

    @GetMapping("/admin/edit-bill/{id}")
    public ModelAndView showEditFormBill(@PathVariable ("id") Long id) {
        Bill bill = billService.findById(id);
        ModelAndView modelAndView = new ModelAndView("bill/edit");
        modelAndView.addObject("bills", bill);
        return modelAndView;
    }

    @PostMapping("/admin/update-bill")
    public ModelAndView updateBill(@Valid @ModelAttribute Bill bill, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("bill/edit");
            return modelAndView;
        } else {
            billService.save(bill);
            ModelAndView modelAndView = new ModelAndView("bill/edit");
            modelAndView.addObject("bills", bill);
            modelAndView.addObject("message", "Updated successfully");
            return modelAndView;
        }
    }

    @GetMapping ("/admin/delete-bill/{id}")
    public ModelAndView showDeleteFormBill(@PathVariable ("id") Long id) {
        Bill bill = billService.findById(id);
        ModelAndView modelAndView = new ModelAndView("bill/delete");
        modelAndView.addObject("bills", bill);
        return modelAndView;
    }

    @PostMapping("/admin/remove-bill")
    public String deleteBill(Bill bill) {
        billService.remove(bill.getBillId());
        return "redirect:/admin/list-bill";
    }
}
