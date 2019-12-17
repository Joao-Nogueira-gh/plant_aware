package ua.deti.plant_aware.model;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;

public class PlantPost {

    @Autowired
    private String plant_name;
    private double ideal_temp, ideal_soil, ideal_wind;
    private BigInteger id;

    public PlantPost(){

    }

    public String getPlant_name(){
        return this.plant_name;
    }

    public void setPlant_name(String plant_name){
        this.plant_name = plant_name;
    }


    public double getIdeal_temp(){
        return this.ideal_temp;
    }

    public void setIdeal_temp(double ideal_temp){
        this.ideal_temp = ideal_temp;
    }


    public double getIdeal_soil(){
        return this.ideal_soil;
    }

    public void setIdeal_soil(double ideal_soil){
        this.ideal_soil = ideal_soil;
    }


    public double getIdeal_wind(){
        return this.ideal_wind;
    }

    public void setIdeal_wind(double ideal_wind){
        this.ideal_wind = ideal_wind;
    }


    public BigInteger getId(){
        return this.id;
    }

    public void setId(BigInteger id){
        this.id = id;
    }

    public String toString()
    {
        return this.plant_name + "post";
    }

}