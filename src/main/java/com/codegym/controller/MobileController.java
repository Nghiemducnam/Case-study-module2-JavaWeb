package com.codegym.controller;

import com.codegym.model.Employee;
import com.codegym.model.Mobile;
import com.codegym.model.MobileType;
import com.codegym.model.Producer;
import com.codegym.service.IEmployeeService;
import com.codegym.service.IMobileService;
import com.codegym.service.IMobileTypeService;
import com.codegym.service.IProducerService;
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
public class MobileController {
    @Autowired
    private IMobileTypeService mobileTypeService;

    @Autowired
    private IProducerService producerService;

    @Autowired
    private IMobileService mobileService;

    @ModelAttribute("producer")
    public Iterable<Producer> producers () {
        return producerService.findAll();
    }

    @ModelAttribute("mobileType")
    public Iterable<MobileType> mobileTypes () {
        return mobileTypeService.findAll();
    }


//
//    @GetMapping("/admin/list-mobile")
//    public ModelAndView showListMobile(Pageable pageable) {
//        Page<Mobile> mobiles = mobileService.findAll(pageable);
//        ModelAndView modelAndView = new ModelAndView("mobile/list");
//        modelAndView.addObject("mobiles", mobiles);
//        return modelAndView;
//    }

    @GetMapping("/admin/list-mobile")
    public ModelAndView listEmployee(@RequestParam("s") Optional<String> s, @PageableDefault(size = 10, sort = "mobilePrice") Pageable pageable){
        Page<Mobile> mobiles;
        if(s.isPresent()){
            mobiles = mobileService.findAllByMobileName(s.get(),pageable);
        }else{
            mobiles = mobileService.findAll(pageable);
        }

        ModelAndView modelAndView = new ModelAndView("mobile/list");
        modelAndView.addObject("mobiles", mobiles);
        return modelAndView;
    }

    @GetMapping("/admin/create-mobile")
    public ModelAndView showCreateMobile() {
        ModelAndView modelAndView = new ModelAndView("mobile/create");
        modelAndView.addObject("mobiles", new Mobile());
        return modelAndView;
    }

    @PostMapping("/admin/save-mobile")
    public ModelAndView saveMobile(@Valid @ModelAttribute ("mobiles") Mobile mobile, BindingResult bindingResult) {
        new Mobile().validate(mobile, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("mobile/create");
            return modelAndView;
        } else {
            mobileService.save(mobile);
            ModelAndView modelAndView = new ModelAndView("redirect:/admin/list-mobile");
            modelAndView.addObject("mobiles", new Mobile());
            return modelAndView;
        }
    }

    @GetMapping("/admin/edit-mobile/{id}")
    public ModelAndView showEditFormMobile(@PathVariable ("id") Long id) {
        Mobile mobile = mobileService.findById(id);
        ModelAndView modelAndView = new ModelAndView("mobile/edit");
        modelAndView.addObject("mobiles", mobile);
        return modelAndView;
    }

    @PostMapping("/admin/update-mobile")
    public ModelAndView updateMobile(@Valid @ModelAttribute ("mobiles") Mobile mobile, BindingResult  bindingResult) {
        new Mobile().validate(mobile, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("mobile/edit");
            return modelAndView;
        } else {
            mobileService.save(mobile);
            ModelAndView modelAndView = new ModelAndView("mobile/edit");
            modelAndView.addObject("mobiles", new Mobile());
            modelAndView.addObject("message", "Updated successfully");
            return modelAndView;
        }
    }

    @GetMapping ("/admin/delete-mobile/{id}")
    public ModelAndView showDeleteFormMobile(@PathVariable ("id") Long id) {
        Mobile mobile = mobileService.findById(id);
        ModelAndView modelAndView = new ModelAndView("mobile/delete");
        modelAndView.addObject("mobiles", mobile);
        return modelAndView;
    }

    @PostMapping("/admin/remove-mobile")
    public String deleteMobile(Mobile mobile) {
        mobileService.remove(mobile.getMobileId());
        return "redirect:/admin/list-mobile";
    }

    @GetMapping("/user/searchByMobileType")
    public ModelAndView getMobileType(@RequestParam("search") Long mobileTypeId, Pageable pageable) {
        Page<Mobile> mobiles;
        if (mobileTypeId == -1) {
            mobiles = mobileService.findAll(pageable);
        } else {
            MobileType mobileType = mobileTypeService.findById(mobileTypeId);
            mobiles = mobileService.findAllByMobileType(mobileType, pageable);
        }

        ModelAndView modelAndView = new ModelAndView("/mobile/list");
        modelAndView.addObject("mobiles",mobiles);
        modelAndView.addObject("search",mobileTypeId);
        return modelAndView;
    }


    @GetMapping("/user/searchByProducer")
    public ModelAndView getM(@RequestParam("search1") Long producerId , Pageable pageable) {
        Page<Mobile> mobiles;
        if (producerId == -1) {
            mobiles = mobileService.findAll(pageable);
        } else {
            Producer producer = producerService.findById(producerId);
            mobiles = mobileService.findAllByProducer(producer, pageable);
        }

        ModelAndView modelAndView = new ModelAndView("/mobile/list");
        modelAndView.addObject("mobiles",mobiles);
        modelAndView.addObject("search1",producerId);
        return modelAndView;
    }

    @GetMapping ("/admin/detail-mobile/{id}")
    public ModelAndView showDetailFormMobile(@PathVariable ("id") Long id) {
        Mobile mobile = mobileService.findById(id);
        ModelAndView modelAndView = new ModelAndView("mobile/detail");
        modelAndView.addObject("mobiles", mobile);
        return modelAndView;
    }
}
