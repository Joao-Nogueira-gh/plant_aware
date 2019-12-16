package ua.deti.plant_aware.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.deti.plant_aware.login.Login;
import ua.deti.plant_aware.register.Register;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.query.Criteria;
import static org.springframework.data.mongodb.core.query.Criteria.where;


import ua.deti.plant_aware.repository.*;
import ua.deti.plant_aware.model.*;
import static org.springframework.data.mongodb.core.query.Criteria.where;

import org.springframework.data.mongodb.core.query.Query;

@Controller
@RequestMapping("/")
public class PlantAwareController {

    @Autowired
    private final PlantRepository plantRep;
    private String logged_user;

    PlantAwareController(PlantRepository plantRep) {
        this.plantRep = plantRep;
        this.logged_user = "";
    }

    /**
     * 
     * 
     * Main Page Dashboard
     * 
     * Needs to pass along data collected in the last 24 for each user plant And
     * data from the last 7 days. All averaged together.
     * 
     */
    @RequestMapping("/")
    String index(Model model) {

        // String now = Long.toString(System.currentTimeMillis() / 1000l);
        // String dayAgo = Long.toString((System.currentTimeMillis() - 3600*24*1000l) / 1000l);
        // String weekAgo =  Long.toString((System.currentTimeMillis() - 7*3600*24*1000l) / 1000l);

        User u=plantRep.findOne(new Query(where("username").is(this.logged_user)), User.class);

        System.out.println(u);
        System.out.println(u.getPlants());
        Query q = new Query();
        // q.fields().elemMatch("plants", Criteria.where("temp.0").lt(now).gt(dayAgo));
        q.fields().include("plants");
        List<Plant> plantas = plantRep.find(q, Plant.class);
        System.out.println(plantas);

        if(u == null){
            return "index-4";
        }

        System.out.println(u);
        System.out.println(u.getPlants());



        model.addAttribute("welcome_str", "Welcome, Plant_Lover99!");
        model.addAttribute("avg_happ", u.averageHappiness());
        model.addAttribute("all_plants", u.getPlants());
        model.addAttribute("username", u.getUsername());


        // Warnings should have a timestamp and should be sorted by it
        model.addAttribute("warnings", u.getWarnings());


        ArrayList<Chart_Data> data = new ArrayList<>();
        for (Plant p : u.getPlants()) {

            data.add(new Chart_Data(p));
            
        }
            

        // int count = 0;
        // for (HashMap<String,Double> hashMap : data) {

        //     hashMap.put("x", (double) count);
        //     count++;
            
        // }

        for (Chart_Data chart_Data : data) {
            System.out.println(chart_Data);
        }
        model.addAttribute("chart_data", data.toArray());
    
        return "index-4";

    }
    /**
     *
     *
     * API
     *
     */
    @GetMapping("/api/plants")
    @ResponseBody
    List<Plant> all_plants() {
        return plantRep.findAll(Plant.class);
    }

    @GetMapping("/plant_db")
    public String all_plants(Model model) {
        model.addAttribute("all_plants", plantRep.findAll(Plant.class));
        return "plant_";
    }

    /**
     * 
     * 
     * Login
     * 
     */
    @GetMapping("/login")
    public String loginForm(Model model){
        model.addAttribute("login", new Login());
        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute Login login) {
        User u=plantRep.findOne(new Query(where("username").is(login.getUsername()).and("password").is(login.getPassword())), User.class);
        if (u==null){
            System.out.println("User not in database");
            return "login";
        }
        this.logged_user = u.getUsername();
        return "redirect:/";
    }

    /**
     * 
     * 
     * Register
     * 
     */
    @GetMapping("/register")
    public String registerForm(Model model){
        model.addAttribute("registo", new Register());
        return "register_v2";
    }

    @PostMapping("/registo")



    /**
     * 
     * API Endpoints
     * 
     */

    // Fetching all the data in the database
    @GetMapping("/api/plants")
    @ResponseBody
        public String registerSubmit(@ModelAttribute Register registo) {
        User u = new User(registo.getUsername(),registo.getPassword(),registo.getEmail());
        plantRep.save(u);
        System.out.println(u.getUsername());
        System.out.println("User inserido");
        return "User Registado";
    }

    @GetMapping("/users")
    @ResponseBody
    List<User> all() {
        return plantRep.findAll(User.class);
    }

    // TODO: Fetch all users
    // TODO: Fetch every plant
    // TODO: Fetch all the entries (for collected data)

}


