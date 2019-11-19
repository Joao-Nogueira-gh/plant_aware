package ua.deti.plant_aware.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Plants")

public class Plant {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Double ideal_temp;
    private Double ideal_soil; //humidade da terra
    private Double temp;
    private Double soil; //humidade da terra
    private int wind; //km/s
    private String name;

    public Plant() {
  
    }
 
    public Plant(String name) {
         this.name=name;
    }
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
        public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
 
    @Column(name = "Plantname", nullable = false)
    public String getPlantname() {
        return name;
    }
    public void setPlantname(String Plantname) {
        this.name=Plantname;
    }
 
    @Column(name = "temp", nullable = false)
    public Double getTemp() {
        return temp;
    }
    public void setTemp(Double temp) {
        this.temp=temp;
    }
    @Column(name = "soil", nullable = false)
    public Double getSoil() {
        return soil;
    }
    public void setSoil(Double soil) {
        this.soil=soil;
    }
    @Column(name = "wind", nullable = false)
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