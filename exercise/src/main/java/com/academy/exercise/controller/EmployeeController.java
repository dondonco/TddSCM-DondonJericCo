package com.academy.exercise.controller;

import com.academy.exercise.model.Employee;
import com.academy.exercise.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/amount/{amount}")
    public List<Employee> getAllEmployeesEarningMoreThanAmount(@PathVariable Double amount) {
        return employeeService.getAllEmployeesEarningMoreThanAmount(amount);
    }

    @GetMapping("/age/{age}")
    public List<Employee> getAllEmployeesExceedingAge(@PathVariable int age) {
        return employeeService.getAllEmployeesExceedingAge(age);
    }

    @GetMapping("/position/{position}")
    public List<Employee> getAllEmployeesWithMatchingPosition(@PathVariable String position) {
        return employeeService.getAllEmployeesWithMatchingPosition(position);
    }

    @GetMapping("/salary")
    public Employee getEmployeeWithHighestSalary() {
        return employeeService.getEmployeeWithHighestSalary();
    }
}
