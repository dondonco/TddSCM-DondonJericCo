package com.academy.exercise.repository;

import com.academy.exercise.model.Employee;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Test
    @DisplayName("" +
            "Given the interface EmployeeRepository that extends JpaRepository<Employee, Long> " +
            "When testFindAll is executed, employeeRepository will save the List.of(dondon, marvin, alejandro) " +
            "Then the result will return dondon, marvin, alejandro")
    public void testFindAll() {
        //ARRANGE
        Employee dondon = new Employee("Dondon", 26, 50000d, "Jr. Software Engineer");
        Employee marvin = new Employee("Marvin", 27, 70000d, "Jr. Software Engineer");
        Employee alejandro = new Employee("Alejandro", 25, 120000d, "Sr. Software Engineer");
        //ACT
        List<Employee> employees = employeeRepository.saveAll(List.of(dondon, marvin, alejandro));
        //ASSERT
        assertThat(employeeRepository.findAll()).containsAll(employees);

    }
}
