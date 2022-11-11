package com.academy.exercise.service;

import com.academy.exercise.model.Employee;
import com.academy.exercise.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    public List<Employee> getAllEmployeesEarningMoreThanAmount(Double amount) {
        return employeeRepository.findAll()
                .stream()
                .filter(employee -> employee.getSalary() > amount)
                .toList();
    }

    public List<Employee> getAllEmployeesExceedingAge(int age) {
        return employeeRepository.findAll()
                .stream()
                .filter(employee -> employee.getAge() > age)
                .toList();
    }

    public List<Employee> getAllEmployeesWithMatchingPosition(String position) {
        return employeeRepository.findAll()
                .stream()
                .filter(employee -> employee.getPosition().equals(position))
                .toList();
    }

    public Employee getEmployeeWithHighestSalary() {
        return employeeRepository.findAll()
                .stream()
                .max(Comparator.comparing(Employee::getSalary))
                .get();
    }
}
