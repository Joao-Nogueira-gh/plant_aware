package ua.deti.plant_aware.model;

import lombok.Getter;

import java.util.HashMap;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document(collection = "main")
public class Plant {

    private long id;
    private double ideal_temp;
    private double ideal_soil; //humidade da terra
    private double ideal_wind;

    private HashMap<String, Double> temp;
    private HashMap<String, Double> soil; //humidade da terra
    private HashMap<String, Double> wind; //m/s

    private double happiness;
    
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

    public Plant(String name, long id, double ideal_temp, double ideal_soil, double ideal_wind){
        this.name = name;
        this.id = id;
        this.ideal_temp = ideal_temp;
        this.ideal_soil = ideal_soil;
        this.ideal_wind = ideal_wind;
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

    public HashMap<String, Double> getWind() {
        return wind;
    }
    public void setWind(HashMap<String, Double> wind) {
        this.wind=wind;
    }

    public double getIdealSoil()
    {
        return this.ideal_soil;
    }

    public double getIdealTemp()
    {
        return this.ideal_temp;
    }

    public double getIdealWind()
    {
        return this.ideal_wind;
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

    public void addWind(String timestamp, double wind)
    {
        this.wind.put(timestamp, wind);
    }


    public double getAvgTemp()
    {
        double sum = 0;
        for (double d : this.temp.values()) {

            sum += d;
            
        }

        return sum/this.temp.size();
    }

    public double getAvgSoil()
    {
        double sum = 0;
        for (double d : this.soil.values()) {

            sum += d;
            
        }

        return sum/this.soil.size();
    }

    public double getAvgWind()
    {
        double sum = 0;
        for (double d : this.wind.values()) {

            sum += d;
            
        }

        return sum/this.wind.size();
    }


    public int getTempPercent()
    {
        double avg = this.getAvgTemp();
        double desvio = Math.abs(this.ideal_temp - avg);

        double div = (this.ideal_temp - desvio) / this.ideal_temp;

        return (int) (Math.floor(div * 100));
    }

    public int getSoilPercent()
    {
        double avg = this.getAvgSoil();
        double desvio = Math.abs(this.ideal_soil - avg);

        double div = (this.ideal_soil - desvio) / this.ideal_soil;

        return (int) (Math.floor(div * 100));
    }

    public int getWindPercent()
    {
        double avg = this.getAvgWind();
        double desvio = Math.abs(this.ideal_wind - avg);

        double div = (this.ideal_wind - desvio) / this.ideal_wind;

        return (int) (Math.floor(div * 100));
    }


    public int getHappiness()
    {
        return (int) Math.floor((this.getSoilPercent() + this.getWindPercent() + this.getTempPercent()) / 3);
    }
 
}