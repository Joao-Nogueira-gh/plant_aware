package ua.deti.plant_aware.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document(collection = "main")
public class Plant {

    private long id;
    private double ideal_temp;
    private double ideal_soil; //humidade da terra
    private int ideal_wind;
    private double temp;
    private double soil; //humidade da terra
    private int wind; //km/s
    private String name;

    public Plant() {
  
    }
 
    public Plant(String name) {
         this.name=name;
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
 
    public double getTemp() {
        return temp;
    }
    public void setTemp(double temp) {
        this.temp=temp;
    }
    public double getSoil() {
        return soil;
    }
    public void setSoil(double soil) {
        this.soil=soil;
    }
    public int getWind() {
        return wind;
    }
    public void setWind(int wind) {
        this.wind=wind;
    }

    @Override
    public String toString() {
        return "Plant [Plantname=" + name + "]";
    }
 
}