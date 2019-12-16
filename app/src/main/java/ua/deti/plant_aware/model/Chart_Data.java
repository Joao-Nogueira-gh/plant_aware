package ua.deti.plant_aware.model;

import lombok.Getter;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
public class Chart_Data {

    @Autowired
    private String plant_name;
    private ArrayList<HashMap<String, Double>> soil;
    private ArrayList<HashMap<String, Double>> temp;
    private ArrayList<HashMap<String, Double>> wind;
    private Plant planta;


    public Chart_Data(Plant p)
    {    
        this.plant_name = p.getPlantname();

        soil = new ArrayList<>();
        temp = new ArrayList<>();
        wind = new ArrayList<>();

        HashMap<String, Double> hm;

        for (String key : p.getSoil().keySet() ){
            hm = new HashMap<>();
            hm.put("x", Double.parseDouble(key)*1000);
            hm.put("y", p.getSoil().get(key));
            soil.add(hm);
        }

        soil.sort(new MapComparator("x"));


        for (String key : p.getTemp().keySet() ){
            hm = new HashMap<>();
            hm.put("x", Double.parseDouble(key)*1000);
            hm.put("y", p.getTemp().get(key));
            temp.add(hm);
        }

        temp.sort(new MapComparator("x"));


        for (String key : p.getWind().keySet() ){
            hm = new HashMap<>();
            hm.put("x", Double.parseDouble(key)*1000);
            hm.put("y", p.getWind().get(key));
            wind.add(hm);
        }

        wind.sort(new MapComparator("x"));

    }

    public String toString()
    {
        System.out.println(this.soil);
        System.out.println(this.temp);
        System.out.println(this.wind);

        return "Chart data for " + this.plant_name;
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
}