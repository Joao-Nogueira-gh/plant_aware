package ua.deti.plant_aware.model;

import lombok.Getter;

import java.util.HashMap;

import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document(collection = "main")
public class Plant {

    private long id;
    private double ideal_temp;
    private double ideal_soil; //humidade da terra
    private int ideal_wind;

    private HashMap<String, Double> temp;
    private HashMap<String, Double> soil; //humidade da terra
    private HashMap<String, Integer> wind; //m/s
    
    private String name;

    public Plant() {
        this.temp = new HashMap<>();
        this.soil = new HashMap<>();
        this.wind = new HashMap<>();
    }
 
    public Plant(String name) {
         this.name=name;
         this.temp = new HashMap<>();
         this.soil = new HashMap<>();
         this.wind = new HashMap<>();
    }
 

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
 
    public String getPlantname() {
        return name;
    }
    public void setPlantname(String Plantname) {
        this.name=Plantname;
    }
 
    public HashMap<String, Double> getTemp() {
        return temp;
    }
    public void setTemp(HashMap<String, Double> temp) {
        this.temp=temp;
    }

    public HashMap<String, Double> getSoil() {
        return soil;
    }
    public void setSoil(HashMap<String, Double> soil) {
        this.soil=soil;
    }

    public HashMap<String, Integer> getWind() {
        return wind;
    }
    public void setWind(HashMap<String, Integer> wind) {
        this.wind=wind;
    }

    @Override
    public String toString() {
        return "Plant [Plantname=" + name + "]";
    }


    public void addTemp(String timestamp, double temp)
    {
        this.temp.put(timestamp, temp);
    }

    public void addSoil(String timestamp, double soil)
    {
        this.soil.put(timestamp, soil);
    }

    public void addWind(String timestamp, int wind)
    {
        this.wind.put(timestamp, wind);
    }
 
}