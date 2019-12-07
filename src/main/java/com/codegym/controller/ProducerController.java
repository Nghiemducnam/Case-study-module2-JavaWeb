package com.codegym.controller;

//import com.codegym.model.Employee;
import com.codegym.model.Producer;
//import com.codegym.service.IEmployeeService;
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
public class ProducerController {
    @Autowired
    private IProducerService producerService;


    @GetMapping("/admin/list-producer")
    public ModelAndView showListProducer() {
        Iterable<Producer> producers = producerService.findAll();
        ModelAndView modelAndView = new ModelAndView("producer/list");
        modelAndView.addObject("producers", producers);
        return modelAndView;
    }

    @GetMapping("/admin/create-producer")
    public ModelAndView showCreateProducer() {
        ModelAndView modelAndView = new ModelAndView("producer/create");
        modelAndView.addObject("producers", new Producer());
        return modelAndView;
    }

    @PostMapping("/admin/save-producer")
    public ModelAndView saveProducer(@Valid @ModelAttribute ("producers") Producer producer, BindingResult bindingResult) {
        new Producer().validate(producer, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("producer/create");
            return modelAndView;
        } else {
            producerService.save(producer);
            ModelAndView modelAndView = new ModelAndView("redirect:/admin/list-producer");
            modelAndView.addObject("producer", new Producer());
            modelAndView.addObject("message", "You have just created a new Producer!");
            return modelAndView;
        }
    }

    @GetMapping("/admin/edit-producer/{id}")
    public ModelAndView showEditFormProducer(@PathVariable ("id") Long id) {
        Producer producer = producerService.findById(id);
        ModelAndView modelAndView = new ModelAndView("producer/edit");
        modelAndView.addObject("producers", producer);
        return modelAndView;
    }

    @PostMapping("/admin/update-producer")
    public ModelAndView updateProducer(@Valid @ModelAttribute ("producers")  Producer producer, BindingResult bindingResult) {
        new Producer().validate(producer, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("producer/edit");
            return modelAndView;
        } else {
            producerService.save(producer);
            ModelAndView modelAndView = new ModelAndView("producer/edit");
            modelAndView.addObject("producers", new Producer());
            modelAndView.addObject("message", "Updated successfully");
            return modelAndView;
        }
    }

    @GetMapping ("/admin/delete-producer/{id}")
    public ModelAndView showDeleteFormProducer(@PathVariable ("id") Long id) {
        Producer producer = producerService.findById(id);
        ModelAndView modelAndView = new ModelAndView("producer/delete");
        modelAndView.addObject("producers", producer);
        return modelAndView;
    }

    @PostMapping("/admin/remove-producer")
    public String deleteProducer(Producer producer) {
        producerService.remove(producer.getProducerId());
        return "redirect:/admin/list-producer";
    }
}
