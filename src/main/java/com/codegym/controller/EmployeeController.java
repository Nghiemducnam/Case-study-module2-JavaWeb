package com.codegym.controller;

import com.codegym.model.Employee;
import com.codegym.service.IEmployeeService;
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
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;

//    @Autowired
//    private IPhoneService phoneService;
//
//    @ModelAttribute("categories")
//    public Iterable<Category> categories () {
//        return categoryService.findAll();
//    }


//    @GetMapping("/list-employee")
//    public ModelAndView showList(@RequestParam ("search") Optional<String> search,
//                                 @PageableDefault(size = 5, direction = Sort.Direction.ASC)
//                                         Pageable pageable) {
//        Page<Phone> phones;
//        if (search.isPresent()) {
//            phones = phoneService.findAllByPhoneName(search.get(),pageable);
//        } else {
//            phones = phoneService.findAll(pageable);
//        }
//        ModelAndView modelAndView = new ModelAndView("phone/list");
//        modelAndView.addObject("phones", phones);
//        return modelAndView;
//    }


    @GetMapping("/admin/list-employee")
    public ModelAndView showListEmployee() {
        Iterable<Employee> employees = employeeService.findAll();
        ModelAndView modelAndView = new ModelAndView("employee/list");
        modelAndView.addObject("employees", employees);
        return modelAndView;
    }

    @GetMapping("/admin/create-employee")
    public ModelAndView showCreateEmployee() {
        ModelAndView modelAndView = new ModelAndView("employee/create");
        modelAndView.addObject("employees", new Employee());
        return modelAndView;
    }

    @PostMapping("/admin/save-employee")
    public ModelAndView saveMaterial(@Valid @ModelAttribute ("employees") Employee employee, BindingResult bindingResult) {
        new Employee().validate(employee, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("employee/create");
            return modelAndView;
        } else {
            employeeService.save(employee);
            ModelAndView modelAndView = new ModelAndView("redirect:/admin/list-employee");
            modelAndView.addObject("employees", new Employee());
            return modelAndView;
        }
    }

    @GetMapping("/admin/edit-employee/{id}")
    public ModelAndView showEditFormEmployee(@PathVariable Long id) {
        Employee employee = employeeService.findById(id);
        ModelAndView modelAndView = new ModelAndView("employee/edit");
        modelAndView.addObject("employees", employee);
        return modelAndView;
    }

    @PostMapping("/admin/update-employee")
    public ModelAndView updatePhone(@Valid @ModelAttribute("employees") Employee employee, BindingResult bindingResult) {
        new Employee().validate(employee, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("employee/edit");
            return modelAndView;
        } else {
            employeeService.save(employee);
            ModelAndView modelAndView = new ModelAndView("redirect:/admin/list-employee");
            modelAndView.addObject("employees", new Employee());
            modelAndView.addObject("message", "Updated successfully");
            return modelAndView;
        }
    }

    @GetMapping ("/admin/delete-employee/{id}")
    public ModelAndView showDeleteFormEmployee(@PathVariable ("id") Long id) {
        Employee employee = employeeService.findById(id);
        ModelAndView modelAndView = new ModelAndView("employee/delete");
        modelAndView.addObject("employees", employee);
        return modelAndView;
    }

    @PostMapping("/admin/remove-employee")
    public String deleteEmployee(Employee employee) {
        employeeService.remove(employee.getEmployeeId());
        return "redirect:/admin/list-employee";
    }
}
