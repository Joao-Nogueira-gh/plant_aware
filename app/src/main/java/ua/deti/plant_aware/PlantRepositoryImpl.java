package ua.deti.plant_aware;

import java.util.HashMap;

import org.springframework.data.mongodb.MongoDbFactory;

import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.data.mongodb.core.query.Query;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import ua.deti.plant_aware.model.*;
import ua.deti.plant_aware.repository.*;
import ua.deti.plant_aware.util.*;



public class PlantRepositoryImpl extends MongoTemplate implements PlantRepository {

    public PlantRepositoryImpl(MongoDbFactory mongoDbFactory) {
		super(mongoDbFactory);
		
    }

    public void handle(String message) {
        
        // Converting message from JSON to a HashMap
		HashMap<String, Object> hm = new HashMap<>(Utility.jsonToMap(message));
		String type = (String) hm.get("type");
		String timestamp = (String) hm.get("timestamp");
		Listener listener = new Listener();
		long id;
		String owner;
		User user;
		// Plant plant;

		switch (type) {
			case "PLANT_UPDATE":
		
				// Fetch the owner name
				owner = (String) hm.get("owner");
				id = ((Integer) hm.get("plant_id")).longValue();

				// Fetch owner's plants
				user = this.findOne(new Query(where("username").is(owner)), User.class);

				// Iterate over the user's plants, get the one with the right ID, update it, rewrite it
				for (Plant p : user.getPlants()) {
					if(p.getId() == id){
						p.addTemp(timestamp, (double) hm.get("temp"));
						p.addSoil(timestamp, (double) hm.get("soil"));
						p.addWind(timestamp, (double) hm.get("wind"));
						
						listener.process(p, (double) hm.get("temp"), (double) hm.get("soil"), (double) hm.get("wind"));
						
						// Get warnings and add them to the database, associated with the user
						for (String s : listener.getWarnings()) {
							user.addWarning(s);
							continue;
						}

						break;
					}
				}

				// Updating the db data -> SORTED INSERT ? ?
				this.remove(new Query(where("username").is(owner)), User.class);
				System.out.println(this.insert(user));
				break;
				

			case "USER_REG":
				// Insert user in database with empty arrays
				this.dropCollection("main");
				User u = new User((String) hm.get("username"),(String) hm.get("password"),(String) hm.get("email"));

				System.out.println(this.insert(u));
				break;

			case "ADD_PLANT":
				// Add a new plant to a user
				// Fetch the owner name
				owner = (String) hm.get("owner");

				// Fetch owner's plants
				user = this.findOne(new Query(where("username").is(owner)), User.class);
				user.addPlant(new Plant(
											(String) hm.get("name"),
											(long) 1,
											(double) hm.get("ideal_temp"),
											(double) hm.get("ideal_soil"),
											(double) hm.get("ideal_wind")											
										)
							);

				this.remove(new Query(where("username").is(owner)), User.class);
				this.insert(user);
		
			default:
				break;
		}

		

    }

    

}
