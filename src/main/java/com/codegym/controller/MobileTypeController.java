package com.codegym.controller;

//import com.codegym.model.Employee;
import com.codegym.model.MobileType;
import com.codegym.model.Producer;
//import com.codegym.service.IEmployeeService;
import com.codegym.service.IMobileTypeService;
import com.codegym.service.IProducerService;
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

@Controller
public class MobileTypeController {
    @Autowired
    private IMobileTypeService mobileTypeService;

    @Autowired
    private IProducerService producerService;

    @ModelAttribute("producer")
    public Iterable<Producer> producers () {
        return producerService.findAll();
    }



    @GetMapping("/admin/list-mobileType")
    public ModelAndView showListMobileType() {
        Iterable<MobileType> mobileTypes = mobileTypeService.findAll();
        ModelAndView modelAndView = new ModelAndView("mobileType/list");
        modelAndView.addObject("mobileTypes", mobileTypes);
        return modelAndView;
    }

    @GetMapping("/admin/create-mobileType")
    public ModelAndView showCreateMobileType() {
        ModelAndView modelAndView = new ModelAndView("mobileType/create");
        modelAndView.addObject("mobileTypes", new MobileType());
        return modelAndView;
    }

    @PostMapping("/admin/save-mobileType")
    public ModelAndView saveMobileType(@Valid @ModelAttribute ("mobileTypes") MobileType mobileType, BindingResult bindingResult) {
        new MobileType().validate(mobileType, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("mobileType/create");
            return modelAndView;
        } else {
            mobileTypeService.save(mobileType);
            ModelAndView modelAndView = new ModelAndView("redirect:/admin/list-mobileType");
            modelAndView.addObject("mobileTypes", new MobileType());
            return modelAndView;
        }
    }

    @GetMapping("/admin/edit-mobileType/{id}")
    public ModelAndView showEditFormMobileType(@PathVariable ("id") Long id) {
        MobileType mobileType = mobileTypeService.findById(id);
        ModelAndView modelAndView = new ModelAndView("mobileType/edit");
        modelAndView.addObject("mobileTypes", mobileType);
        return modelAndView;
    }

    @PostMapping("/admin/update-mobileType")
    public ModelAndView updateMobileType(@Valid @ModelAttribute ("mobileTypes") MobileType mobileType, BindingResult bindingResult) {
        new MobileType().validate(mobileType, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("mobileType/edit");
            return modelAndView;
        } else {
            mobileTypeService.save(mobileType);
            ModelAndView modelAndView = new ModelAndView("redirect:/admin/list-mobileType");
            modelAndView.addObject("mobileTypes", new MobileType());
            modelAndView.addObject("message", "Updated successfully");
            return modelAndView;
        }
    }

    @GetMapping ("/admin/delete-mobileType/{id}")
    public ModelAndView showDeleteFormMobileType(@PathVariable ("id") Long id) {
        MobileType mobileType = mobileTypeService.findById(id);
        ModelAndView modelAndView = new ModelAndView("mobileType/delete");
        modelAndView.addObject("mobileTypes", mobileType);
        return modelAndView;
    }

    @PostMapping("/admin/remove-mobileType")
    public String deleteProducer(MobileType mobileType) {
        mobileTypeService.remove(mobileType.getMobileTypeId());
        return "redirect:/admin/list-mobileType";
    }
}
