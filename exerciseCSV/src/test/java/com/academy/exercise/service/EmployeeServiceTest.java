package com.academy.exercise.service;

import com.academy.exercise.model.Employee;
import com.academy.exercise.repository.EmployeeRepository;
import com.academy.exercise.repository.EmployeeRepositoryFromCSV;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    private Employee dondon = new Employee("Dondon",26,50000d,"Jr. Software Engineer");
    private Employee marvin = new Employee("Marvin",27,70000d,"Jr. Software Engineer");
    private Employee alejandro = new Employee("Alejandro",25,160000d, "Sr. Software Engineer");

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @BeforeEach
    public void setup() throws IOException {
        Mockito.when(employeeRepository.getAllEmployee())
                .thenReturn(new EmployeeRepositoryFromCSV().getAllEmployee());
    }

    @Test
    @DisplayName("Given employees from the file employee_data.csv " +
            "WHEN getAllEmployeesThatAreEarningMoreThanAmount is executed with input 65000 " +
            "THEN result should return marvin, alejandro")
    public void getAllEmployeesThatAreEarningMoreThanAmount() throws IOException {
        //ARRANGE
        Double amount = 65000d;

        //ACT
        List<Employee> employeeList = employeeService.getAllEmployeesThatAreEarningMoreThanAmount(amount);

        //ASSERT
        assertThat(employeeList).contains(marvin, alejandro);

    }
    @Test
    @DisplayName("Given employees from the file employee_data.csv " +
            "WHEN getAllEmployeesExceedingAge is executed with age 25 " +
            "THEN result should return dondon, marvin")
    public void getAllEmployeesExceedingAge() throws IOException {
        //ARRANGE
        int age = 25;

        //ACT
        List<Employee> employeeList = employeeService.getAllEmployeesExceedingAge(age);

        //ASSERT
        assertThat(employeeList).contains(dondon, marvin);

    }
    @Test
    @DisplayName("Given employees from the file employee_data.csv " +
            "WHEN getAllEmployeesWithMatchingPosition is executed with position Jr. Software Engineer " +
            "THEN result should return dondon, marvin")
    public void getAllEmployeesWithMatchingPosition() throws IOException {
        //ARRANGE
        String position = "Jr. Software Engineer";
        //ACT
        List<Employee> employeeList = employeeService.getAllEmployeesWithMatchingPosition(position);

        //ASSERT
        assertThat(employeeList).contains(dondon, marvin);

    }
    @Test
    @DisplayName("Given employees from the file employee_data.csv " +
            "WHEN getEmployeeWithHighestSalary is executed " +
            "THEN result should return alejandro")
    public void getEmployeeWithHighestSalary() throws IOException {
        //ARRANGE
        //ACT
        Employee employee = employeeService.getEmployeeWithHighestSalary();

        //ASSERT
        assertEquals(alejandro, employee);

    }
}