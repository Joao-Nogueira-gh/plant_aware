package ua.deti.plant_aware.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ua.deti.plant_aware.repository.*;
import ua.deti.plant_aware.model.*;

@Controller
@RequestMapping("/")
public class PlantAwareController {

    @Autowired
    private final UserRepository userRep;
    @Autowired
    private final PlantRepository plantRep;

    PlantAwareController(UserRepository userRep, PlantRepository plantRep) {
        this.userRep = userRep;
        this.plantRep = plantRep;
        // this.loadEmployeeOnDB();
    }



    /**
     * 
     * 
     * Main Page
     * Dashboard
     * 
     * Needs to pass along data collected in the last 24 for each user plant
     * And data from the last 7 days. All averaged together.
     * 
     */
    @RequestMapping("/")
    String index(Model model) {
        List<User> l = plantRep.findAll(User.class);
        User u = new User();

        for (User utilizador : l) {
            System.out.println(utilizador);
            if(utilizador.getUsername().equals("Plant_Lover99"))
            {
                u = utilizador;
                break;
            }
        }
        System.out.println(u);
        System.out.println(u.getPlants());
        model.addAttribute("avg_happ", u.averageHappiness());
        model.addAttribute("all_plants", u.getPlants());
        // model.addAllAttributes(attributeValues)

        // TEMOS DE DAR FETCH AO USER DESTA SESSAO
        // DAR FETCH AS PLANTAS DO USER
        // FOR EACH PLANTA
        // PASS EVERY DATA WE HAVE ABOUT IT


        // model.addAttribute("ideal_water", plantRep.findAll(Plant.class));
        return "index-4";
    }


    /**
     * 
     * 
     * Login GET
     * Passes along error messages if needed
     * 
     */
    @GetMapping("/login")
    String login() {
        return "login-register";
    }



    @GetMapping("/register")
    String register(Model model) {
        model.addAttribute("user", new User());

        return "register";
    }

    @PostMapping("/register")
    String registerUser(@ModelAttribute User user){
        return "login-register";
    }



    /**
     * 
     * 
     * Database operations:
     * Mostly for raw data display
     * 
     */
    @GetMapping("/all_plants")
    @ResponseBody
    List<Plant> all_plants() {
        return plantRep.findAll(Plant.class);
    }

    @GetMapping("/plant_db")
    public String all_plants(Model model) {
        model.addAttribute("all_plants", plantRep.findAll(Plant.class));
        return "plant_";
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
