package ua.deti.plant_aware;

import java.util.HashMap;

import org.springframework.data.mongodb.MongoDbFactory;

import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Update.update;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import ua.deti.plant_aware.model.Plant;
import ua.deti.plant_aware.repository.*;
import ua.deti.plant_aware.util.*;

import static org.springframework.data.mongodb.core.query.Criteria.where;


public class PlantRepositoryImpl extends MongoTemplate implements PlantRepository {

    public PlantRepositoryImpl(MongoDbFactory mongoDbFactory) {
		super(mongoDbFactory);
		
    }

    public void handle(String message) {
        
        // Converting message from JSON to a HashMap
		HashMap<String, Object> hm = new HashMap<>(Utility.jsonToMap(message));

		String type = (String) hm.get("type");

		switch (type) {
			case "PLANT_UPDATE":
				String timestamp = (String) hm.get("timestamp");
				long id = ((Integer) hm.get("plant_id")).longValue();
				Plant plant;
		
				
				// Deciding if this a CREATE or UPDATE
				if( this.findOne(new Query(where("id").is(id)), Plant.class) == null)
				{
					// Constructing the object that will be saved
					plant = new Plant("Tulipa"); // Hardcoded "Tulipa", will later be sent by Sensor
					plant.addTemp(timestamp, (double) hm.get("temp"));
					plant.addSoil(timestamp, (double) hm.get("soil"));
					plant.addWind(timestamp, (int) hm.get("wind"));
					plant.setId(((Integer) hm.get("plant_id")).longValue());
					System.out.println("Adding " + this.insert(plant) + " to database");
				}
				else
				{
		
					plant = this.findOne(new Query(where("id").is(id)), Plant.class);
		
					plant.addTemp(timestamp, (double) hm.get("temp"));
					plant.addSoil(timestamp, (double) hm.get("soil"));
					plant.addWind(timestamp, (int) hm.get("wind"));
		
					// TODO: Switch this to an update
					// this.updateFirst(new Query(where("id").is(id)), new Update().push("key", value), Plant.class);
					this.remove(new Query(where("id").is(id)), Plant.class);
					this.insert(plant);
				
				}
				break;

			case "REGISTER_USER":
				break;
		
			default:
				break;
		}

		

    }

    

}
