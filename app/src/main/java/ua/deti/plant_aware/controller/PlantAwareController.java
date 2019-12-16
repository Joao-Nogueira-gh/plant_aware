package ua.deti.plant_aware.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.deti.plant_aware.login.Login;
import ua.deti.plant_aware.register.Register;
import java.util.*;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import ua.deti.plant_aware.repository.*;
import ua.deti.plant_aware.model.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.query.Criteria;
import static org.springframework.data.mongodb.core.query.Criteria.where;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Timestamp;
import java.util.*;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
        model.addAttribute("all_plants", plantRep.findAll(Plant.class));

        // TEMOS DE DAR FETCH AO USER DESTA SESSAO
        // DAR FETCH AS PLANTAS DO USER
        // FOR EACH PLANTA
        // PASS EVERY DATA WE HAVE ABOUT IT


        model.addAttribute("ideal_water", plantRep.findAll(Plant.class));
        return "index-4";
    }



    @PostMapping("/register")
    String registerUser(@ModelAttribute User user){
        // this.plantRep.findOne(user.getUsername());
        System.out.println("i hate this\n\n");
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
    //Login
    @GetMapping("/login")
    public String loginForm(Model model){
      model.addAttribute("login", new Login());
      return "login";
    }

    @PostMapping("/login")
  public String loginSubmit(@ModelAttribute Login login) {
    User u=userRep.findOne(new Query(where("username").is(login.getUsername()).and("password").is(login.getPassword())), User.class);
    if (u==null){
        System.out.println("User not in database");
        return "login";
      }
    return "index-4";
  }

  //Register

  @GetMapping("/register")
  public String registerForm(Model model){
    model.addAttribute("registo", new Register());
    return "register_v2";

  }
  @PostMapping("/registo")
  @ResponseBody
public String registerSubmit(@ModelAttribute Register registo) {
  User u = new User(registo.getUsername(),registo.getPassword(),registo.getEmail());
  userRep.save(u);
  System.out.println(u.getUsername());
  System.out.println("User inserido");
  return "User Registado";
}

  @GetMapping("/users")
  @ResponseBody
  List<User> all() {
      return userRep.findAll(User.class);
  }


  @GetMapping("/chart")
  public String getChart(Model model){
    model.addAttribute("all_info", plantRep.findAll(Plant.class));
    return "chart";
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
