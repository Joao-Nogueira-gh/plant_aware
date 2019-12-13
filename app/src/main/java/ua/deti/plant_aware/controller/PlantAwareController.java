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
    private final PlantRepository plantRep;

    PlantAwareController(PlantRepository plantRep) {
        this.plantRep = plantRep;
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

        if(u.getUsername() != null)
        {
            System.out.println(u);
            System.out.println(u.getPlants());
            model.addAttribute("welcome_str", "Welcome, Plant_Lover99!");
            model.addAttribute("avg_happ", u.averageHappiness());
            model.addAttribute("all_plants", u.getPlants());


            ArrayList<HashMap<String, Double>> data = new ArrayList<>();
            HashMap<String, Double> hm;
            for (String key : u.getPlants().get(0).getSoil().keySet() ){
                hm = new HashMap<>();
                hm.put("x", Double.parseDouble(key));
                hm.put("y", u.getPlants().get(0).getSoil().get(key));
                data.add(hm);
            }

            data.sort(new MapComparator("x"));
            int count = 0;
            for (HashMap<String,Double> hashMap : data) {

                hashMap.put("x", (double) count);
                count++;
                
            }

            System.out.println(data);
            model.addAttribute("chart_data", data.toArray());
        }

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

class MapComparator implements Comparator<Map<String, Double>>
{
    private final String key;

    public MapComparator(String key)
    {
        this.key = "x";
    }

    @Override
    public int compare(Map<String, Double> first,
                       Map<String, Double> second)
    {
        // TODO: Null checking, both for maps and values
        Double firstValue = first.get(key);
        Double secondValue = second.get(key);
        return firstValue.compareTo(secondValue);
    }
}
