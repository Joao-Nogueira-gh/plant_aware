package ua.deti.plant_aware.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;

import ua.deti.plant_aware.repository.*;
import ua.deti.plant_aware.IController;
import ua.deti.plant_aware.PlantAwareApplication;
import ua.deti.plant_aware.model.*;

@Controller
@RequestMapping("/")
public class PlantAwareController {

    @Autowired
    private final UserRepository userRep;
    private final PlantRepository plantRep;

    PlantAwareController(UserRepository userRep, PlantRepository plantRep) {
        this.userRep = userRep;
        this.plantRep = plantRep;
        // this.loadEmployeeOnDB();
    }

    @RequestMapping("/")
    String index() {
        return "index-4";
    }

    @GetMapping("/usersfixes")
    List<User> all() {
        return userRep.findAll();
    }

    @GetMapping("/login")
    String login() {
        return "login-register";
    }

    @GetMapping("/register")
    String register() {
        return "register";
    }


    // public User getUser(String user){
    //     return userRep.getUser(user);
    // }

    // @GetMapping("/employees/{id}")
    // public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId)
    //     throws ResourceNotFoundException {
    //     Employee employee = employeeRepository.findById(employeeId)
    //       .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
    //     return ResponseEntity.ok().body(employee);
    // }
    // @GetMapping("/employees/email")
    // @ResponseBody
    // public String findByEmailId(@RequestParam(required = false, name = "email") String emailId)
    //     throws ResourceNotFoundException {
    //     Employee employee = employeeRepository.findByEmailId(emailId);
    //       //.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this email :: " + emailId));
    //     return employee.toString();
    // }

    // @PostMapping("/employees")
    // public Employee createEmployee(@Valid @RequestBody Employee employee) {
    //     return employeeRepository.save(employee);
    // }

    // @PutMapping("/employees/{id}")
    // public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
    //      @Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
    //     Employee employee = employeeRepository.findById(employeeId)
    //     .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

    //     employee.setEmailId(employeeDetails.getEmailId());
    //     employee.setLastName(employeeDetails.getLastName());
    //     employee.setFirstName(employeeDetails.getFirstName());
    //     final Employee updatedEmployee = employeeRepository.save(employee);
    //     return ResponseEntity.ok(updatedEmployee);
    // }

    // @DeleteMapping("/employees/{id}")
    // public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
    //      throws ResourceNotFoundException {
    //     Employee employee = employeeRepository.findById(employeeId)
    //    .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

    //     employeeRepository.delete(employee);
    //     Map<String, Boolean> response = new HashMap<>();
    //     response.put("deleted", Boolean.TRUE);
    //     return response;
    // }
}
