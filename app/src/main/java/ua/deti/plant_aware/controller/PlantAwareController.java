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
import static org.springframework.data.mongodb.core.query.Criteria.where;

import org.springframework.data.mongodb.core.query.Query;

@Controller
@RequestMapping("/")
public class PlantAwareController {

    @Autowired
    private final UserRepository userRep;
    @Autowired
    private final PlantRepository plantRep;
    private String logged_user;

    PlantAwareController(UserRepository userRep, PlantRepository plantRep) {
        this.userRep = userRep;
        this.plantRep = plantRep;
        this.logged_user = "";
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
        User u=userRep.findOne(new Query(where("username").is(this.logged_user)), User.class);

        System.out.println(u);
        System.out.println(u.getPlants());
        model.addAttribute("avg_happ", u.averageHappiness());
        model.addAttribute("all_plants", u.getPlants());


        if(u.getPlants().size() > 0){
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
        User u=userRep.findOne(new Query(where("username").is(login.getUsername()).and("password").is(login.getPassword())), User.class);
        if (u==null){
            System.out.println("User not in database");
            return "login";
        }
        this.logged_user = u.getUsername();
        return "index-4";
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
