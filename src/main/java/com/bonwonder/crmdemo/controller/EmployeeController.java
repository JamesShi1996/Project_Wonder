package com.bonwonder.crmdemo.controller;

import com.bonwonder.crmdemo.entity.Employee;
import com.bonwonder.crmdemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/list")
    public String listEmployees(Model model, @RequestParam(value = "sort", required = false) Integer sort){
        List<Employee> employeeList;

        if(sort == null){
            employeeList = employeeService.findAll();
        }else{
            employeeList = employeeService.sort(sort);
        }

        model.addAttribute("employees", employeeList);

        return "list-employees";
    }

    @GetMapping("/search")
    public String search(Model model, @RequestParam("keyword") String keyword){

        List<Employee> employeeList = employeeService.search(keyword);

        model.addAttribute("employees", employeeList);
        model.addAttribute("keyword", keyword);

        return "list-employees";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model){
        Employee employee = new Employee();

        model.addAttribute("employee", employee);

        return "employee-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("employee") Employee employee){
        employeeService.save(employee);
        return "redirect:/employees/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForAdd(Model model, @RequestParam("employeeId") int id){
        Employee employee = employeeService.findById(id);
        model.addAttribute("employee", employee);
        return "employee-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId") int id){
        employeeService.deleteById(id);
        return "redirect:/employees/list";
    }
}
