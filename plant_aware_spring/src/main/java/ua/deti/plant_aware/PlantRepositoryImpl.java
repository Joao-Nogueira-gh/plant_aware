package ua.deti.plant_aware;

import java.util.HashMap;

import org.springframework.data.mongodb.MongoDbFactory;

import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.data.mongodb.core.query.Query;

import ua.deti.plant_aware.model.Plant;
import ua.deti.plant_aware.repository.*;
import ua.deti.plant_aware.util.*;

import static org.springframework.data.mongodb.core.query.Criteria.where;


public class PlantRepositoryImpl extends MongoTemplate implements PlantRepository {

    public PlantRepositoryImpl(MongoDbFactory mongoDbFactory) {
        super(mongoDbFactory);
    }

    @Override
    public void handle(String message) {
        
        // Converting message from JSON to a HashMap
		HashMap<String, Object> hm = new HashMap<>(Utility.jsonToMap(message));
		System.out.println(hm);
		String timestamp = (String) hm.get("timestamp");

        // Constructing the object that will be saved
		Plant plant = new Plant("Tulipa");
        plant.addTemp(timestamp, (double) hm.get("temp"));
        plant.addSoil(timestamp, (double) hm.get("soil"));
        plant.addWind(timestamp, (int) hm.get("wind"));
        plant.setId(((Integer) hm.get("plant_id")).longValue());


        // Deciding if this a CREATE or UPDATE
        if(!this.exists(new Query(where("id").is(plant.getId())), "main"))
        {
            this.dropCollection("main");
            System.out.println("Saving " + this.insert(plant));
        }
        else
        {
            System.out.println("ALREADY EXISTS");
        }

    }

    

}
