package ua.deti.plant_aware.model;

import java.util.ArrayList;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;

@Getter
@Document(collection = "main")
public class User {

    private String username;
    private String password;
    private String email;
    private ArrayList<Plant> plants;
    private ArrayList<Warning> warnings;

    public User() {
  
    }
 
    public User(String username, String password, String email) {
         this.username=username;
         this.password=password;
         this.email=email;
         this.plants = new ArrayList<Plant>();
         this.warnings = new ArrayList<Warning>();
    }

    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username=username;
    }
 
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password=password;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email=email;
    }

    public ArrayList<Plant> getPlants() {
        return this.plants;
    }
    public void setPlants(ArrayList<Plant> plants) {
        this.plants=plants;
    }
    public void addPlant(Plant p){
        this.plants.add(p);
    }

    public void addWarning(Warning w) {
        this.warnings.add(w);
    }
    public ArrayList<Warning> getWarnings() {
        return this.warnings;
    }

    public int averageHappiness()
    {
        double sum = 0;
        for (Plant plant : this.plants) {
            sum += plant.getHappiness();
        }

        return (int) Math.floor(sum/this.plants.size());
    }

    @Override
    public String toString() {
        return "User [username=" + username + "] " + this.plants;
    }
 
}