package ua.deti.plant_aware.controller;

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

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.query.Criteria;
import static org.springframework.data.mongodb.core.query.Criteria.where;


import ua.deti.plant_aware.repository.*;
import ua.deti.plant_aware.model.*;

@Controller
@RequestMapping("/")
public class PlantAwareController {

    @Autowired
    private final PlantRepository plantRep;

    PlantAwareController(PlantRepository plantRep) {
        this.plantRep = plantRep;
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

        User u = plantRep.findOne(new Query(where("username").is("Plant_Lover99")), User.class);
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
     * Login GET
     * Passes along error messages if needed
     * 
     */
    @GetMapping("/login")
    String login() {
        return "login-register";
    }



    /**
     * 
     * Register POST
     * 
     */
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
     * API Endpoints
     * 
     */

    // Fetching all the data in the database
    @GetMapping("/api/plants")
    @ResponseBody
    List<User> all_plants() {
        return plantRep.findAll(User.class);
    }


    // TODO: Fetch all users
    // TODO: Fetch every plant
    // TODO: Fetch all the entries (for collected data)

}


