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

import java.util.List;

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
    }
    // Dondon, 26, 50000, Jr. Software Engineer
    // Marvin, 25, 70000, Jr. Software Engineer
    // Alejandro, 26, 50000, Sr. Software Engineer
    @Test
    @DisplayName("" +
            "Given employees with the setup above " +
            "When getAllEmployeesEarningMoreThanAmount(amount) is executed with amount = 60000d " +
            "Then result should return marvin, alejandro")
    public void getAllEmployeesEarningMoreThanAmount() {
        //ARRANGE
        Double amount = 60000d;
        //ACT
        List<Employee> result = employeeService.getAllEmployeesEarningMoreThanAmount(amount);
        //ASSERT
        List<Employee> expected = List.of(marvin, alejandro);
        assertEquals(expected, result);
    }

    // Dondon, 26, 50000, Jr. Software Engineer
    // Marvin, 25, 70000, Jr. Software Engineer
    // Alejandro, 26, 50000, Sr. Software Engineer
    @Test
    @DisplayName("" +
            "Given employees with the setup above " +
            "When getAllEmployeesExceedingAge(age) is executed with age = 25 " +
            "Then result should return dondon, marvin")
    public void getAllEmployeesExceedingAge() {
        //ARRANGE
        int age = 25;
        //ACT
        List<Employee> result = employeeService.getAllEmployeesExceedingAge(age);
        //ASSERT
        List<Employee> expected = List.of(dondon, marvin);
        assertEquals(expected, result);

    }

    // Dondon, 26, 50000, Jr. Software Engineer
    // Marvin, 25, 70000, Jr. Software Engineer
    // Alejandro, 26, 50000, Sr. Software Engineer
    @Test
    @DisplayName("" +
            "Given employees with the setup above " +
            "When getAllEmployeesWithMatchingPosition(position) is executed with position = Jr. Software Engineer " +
            "Then result should return dondon, marvin")
    public void getAllEmployeesWithMatchingPosition() {
        //ARRANGE
        String position = "Jr. Software Engineer";
        //ACT
        List<Employee> result = employeeService.getAllEmployeesWithMatchingPosition(position);
        //ASSERT
        List<Employee> expected = List.of(dondon, marvin);
        assertEquals(expected, result);
    }

    // Dondon, 26, 50000, Jr. Software Engineer
    // Marvin, 25, 70000, Jr. Software Engineer
    // Alejandro, 26, 50000, Sr. Software Engineer
    @Test
    @DisplayName("" +
            "Given employees with the setup above " +
            "When getEmployeeWithHighestSalary() is executed " +
            "Then result should return alejandro")
    public void getEmployeeWithHighestSalary() {
        //ARRANGE
        //ACT
        Employee result = employeeService.getEmployeeWithHighestSalary();
        //ASSERT
        assertEquals(alejandro, result);
    }
}
