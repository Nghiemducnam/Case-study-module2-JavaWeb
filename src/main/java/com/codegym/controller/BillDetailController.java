package com.codegym.controller;

import com.codegym.model.*;
import com.codegym.service.*;
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
public class BillDetailController {
    @Autowired
    private IBillService billService;

    @Autowired
    private IBillDetailService billDetailService;

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IpaymentService paymentService;

    @Autowired
    private IMobileService mobileService;

    @ModelAttribute("customer")
    public Iterable<Customer> customers () {
        return customerService.findAll();
    }

    @ModelAttribute("payment")
    public Iterable<Payment> payments () {
        return paymentService.findAll();
    }

    @ModelAttribute("bill")
    public Iterable<Bill> bills () {
        return billService.findAll();
    }



//    @GetMapping("/list-billDetail")
//    public ModelAndView showListBillDetail(@RequestParam ("search") Optional<String> search,
//                                 @PageableDefault(size = 5, direction = Sort.Direction.ASC)
//                                         Pageable pageable) {
//        Page<BillDetail> billDetails;
//        if (search.isPresent()) {
//            billDetails = billDetailService.findAll(pageable);
//        } else {
//            billDetails = billDetailService.findAll(pageable);
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
    @GetMapping("/admin/list-billDetail")
    public ModelAndView showListBillDetail() {
        Iterable<BillDetail> billDetails = billDetailService.findAll();
        ModelAndView modelAndView = new ModelAndView("billDetail/list");
        modelAndView.addObject("billDetails", billDetails);
        return modelAndView;
    }


    @GetMapping("/admin/create-billDetail")
    public ModelAndView showCreateBillDetail() {
        ModelAndView modelAndView = new ModelAndView("billDetail/create");
        modelAndView.addObject("billDetails", new BillDetail());
        return modelAndView;
    }

    @PostMapping("/admin/save-billDetail")
    public ModelAndView saveBillDetail(@Valid @ModelAttribute ("billDetails") BillDetail billDetail, BindingResult bindingResult) {
        new BillDetail().validate(billDetail, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("billDetail/create");
            return modelAndView;
        } else {
            billDetailService.save(billDetail);
            ModelAndView modelAndView = new ModelAndView("redirect:/admin/list-billDetail");
            modelAndView.addObject("billDetails", new BillDetail());
            return modelAndView;
        }
    }

    @GetMapping("/admin/edit-billDetail/{id}")
    public ModelAndView showEditFormBillDetail(@PathVariable Long id) {
        BillDetail billDetail = billDetailService.findById(id);
        ModelAndView modelAndView = new ModelAndView("billDetail/edit");
        modelAndView.addObject("billDetails", billDetail);
        return modelAndView;
    }

    @PostMapping("/admin/update-billDetail")
    public ModelAndView updateBillDetail(@Valid @ModelAttribute BillDetail billDetail, BindingResult bindingResult) {
        new BillDetail().validate(billDetail, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("billDetail/edit");
            return modelAndView;
        } else {
            billDetailService.save(billDetail);
            ModelAndView modelAndView = new ModelAndView("billDetail/edit");
            modelAndView.addObject("billDetails", billDetail);
            modelAndView.addObject("message", "Updated successfully");
            return modelAndView;
        }
    }

    @GetMapping ("/admin/delete-billDetail/{id}")
    public ModelAndView showDeleteFormBillDetail(@PathVariable ("id") Long id) {
        BillDetail billDetail = billDetailService.findById(id);
        ModelAndView modelAndView = new ModelAndView("billDetail/delete");
        modelAndView.addObject("billDetails", billDetail);
        return modelAndView;
    }

    @PostMapping("/admin/remove-billDetail")
    public String deleteBillDetail(BillDetail billDetail) {
        billDetailService.remove(billDetail.getBillDetailId());
        return "redirect:/admin/list-billDetail";
    }
}
