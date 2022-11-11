package com.academy.exercise.controller;

import com.academy.exercise.model.Employee;
import com.academy.exercise.repository.EmployeeRepository;
import com.academy.exercise.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private EmployeeController employeeController;
    @MockBean
    private EmployeeService employeeService;

    private Employee dondon = new Employee("Dondon", 26, 50000d, "Jr. Software Engineer");
    private Employee marvin = new Employee("Marvin", 27, 70000d, "Jr. Software Engineer");
    private Employee alejandro = new Employee("Alejandro", 25, 120000d, "Sr. Software Engineer");


    @BeforeEach
    public void setup() {
    }

    @Test
    @DisplayName("" +
            "Given a get request with mapping of /employees/amount/60000," +
            "When employeeController execute employeeService.getAllEmployeesEarningMoreThanAmount(60000d) " +
            "Then response should return json of marvin, alejandro with status 200")
    public void getAllEmployeesEarningMoreThanAmount() throws Exception {
        //ARRANGE
        Double amount = 60000d;
        List<Employee> result = List.of(marvin, alejandro);
       //ACT
        when(employeeService.getAllEmployeesEarningMoreThanAmount(amount)).thenReturn(result);
        //ASSERT
        mockMvc.perform(MockMvcRequestBuilders.get("/employees/amount/60000")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Marvin"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].age").value(27))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].salary").value(70000))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].position").value("Jr. Software Engineer"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Alejandro"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].age").value(25))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].salary").value(120000))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].position").value("Sr. Software Engineer"))
                .andExpect(status().isOk());
        //assertEquals(200, code);
    }


    @Test
    @DisplayName("" +
            "Given a get request with mapping of /employees/age/25," +
            "When employeeController execute employeeService.getAllEmployeesExceedingAge(25) " +
            "Then response should return json of dondon, marvin with status 200")
    public void getAllEmployeesExceedingAge() throws Exception {
        //ARRANGE
        int age = 25;
        List<Employee> result = List.of(dondon, marvin);
        //ACT
        when(employeeService.getAllEmployeesExceedingAge(age)).thenReturn(result);
        //ASSERT
        mockMvc.perform(MockMvcRequestBuilders.get("/employees/age/25")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Dondon"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].age").value(26))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].salary").value(50000))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].position").value("Jr. Software Engineer"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Marvin"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].age").value(27))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].salary").value(70000))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].position").value("Jr. Software Engineer"))
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("" +
            "Given a get request with mapping of /employees/position/Jr. Software Engineer," +
            "When employeeController execute employeeService.getAllEmployeesWithMatchingPosition(position) " +
            "Then response should return json of dondon, marvin with status 200")
    public void getAllEmployeesWithMatchingPosition() throws Exception {
        //ARRANGE
        String position = "Jr. Software Engineer";
        List<Employee> result = List.of(dondon, marvin);
        //ACT
        when(employeeService.getAllEmployeesWithMatchingPosition(position)).thenReturn(result);
        //ASSERT

        mockMvc.perform(MockMvcRequestBuilders.get("/employees/position/Jr. Software Engineer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Dondon"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].age").value(26))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].salary").value(50000))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].position").value("Jr. Software Engineer"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Marvin"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].age").value(27))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].salary").value(70000))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].position").value("Jr. Software Engineer"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("" +
            "Given a get request with mapping of /employees/salary," +
            "When employeeController execute employeeService.getEmployeeWithHighestSalary() " +
            "Then response should return json of alejandro with status 200")
    public void getEmployeeWithHighestSalary() throws Exception {
        //ARRANGE
        //ACT
        when(employeeService.getEmployeeWithHighestSalary()).thenReturn(alejandro);
        //ASSERT
        mockMvc.perform(MockMvcRequestBuilders.get("/employees/salary")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Alejandro"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(25))
                .andExpect(MockMvcResultMatchers.jsonPath("$.salary").value(120000))
                .andExpect(MockMvcResultMatchers.jsonPath("$.position").value("Sr. Software Engineer"))
                .andExpect(status().isOk());

    }
}
