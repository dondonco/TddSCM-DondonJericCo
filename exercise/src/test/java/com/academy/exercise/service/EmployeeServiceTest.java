package com.academy.exercise.service;

import com.academy.exercise.model.Employee;
import com.academy.exercise.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {


    private Employee dondon = new Employee("Dondon", 26, 50000d, "Jr. Software Engineer");
    private Employee marvin = new Employee("Marvin", 27, 70000d, "Jr. Software Engineer");
    private Employee alejandro = new Employee("Alejandro", 25, 120000d, "Sr. Software Engineer");

    @Mock
    private EmployeeRepository employeeRepository;
    @InjectMocks
    private EmployeeService employeeService;

    @BeforeEach
    public void setup() {
        List<Employee> employees = List.of(dondon, marvin, alejandro);
        when(employeeRepository.findAll()).thenReturn(employees);
        when(employeeService.getAllEmployeesEarningMoreThanAmount(60000d)).thenReturn(List.of(marvin, alejandro));
    }
    @Test
    @DisplayName("" +
            "Given " +
            "When " +
            "Then ")
    public void getAllEmployeesEarningMoreThanAmount() {
        //ARRANGE
        Double amount = 60000d;
        //ACT
        List<Employee> result = employeeService.getAllEmployeesEarningMoreThanAmount(amount);
        //ASSERT
        List<Employee> expected = List.of(marvin, alejandro);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("" +
            "Given " +
            "When " +
            "Then ")
    public void getAllEmployeesExceedingAge() {
        //ARRANGE
        int age = 25;
        //ACT
        List<Employee> result = employeeService.getAllEmployeesExceedingAge(age);
        //ASSERT
        List<Employee> expected = List.of(dondon, marvin);
        assertEquals(expected, result);

    }

    @Test
    @DisplayName("" +
            "Given " +
            "When " +
            "Then ")
    public void getAllEmployeesWithMatchingPosition() {
        //ARRANGE
        String position = "Jr. Software Engineer";
        //ACT
        List<Employee> result = employeeService.getAllEmployeesWithMatchingPosition(position);
        //ASSERT
        List<Employee> expected = List.of(dondon, marvin);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("" +
            "Given " +
            "When " +
            "Then ")
    public void getEmployeeWithHighestSalary() {
        //ARRANGE
        //ACT
        Employee result = employeeService.getEmployeeWithHighestSalary();
        //ASSERT
        assertEquals(alejandro, result);
    }
}
