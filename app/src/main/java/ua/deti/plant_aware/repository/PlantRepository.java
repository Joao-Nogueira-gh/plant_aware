package ua.deti.plant_aware.repository;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import ua.deti.plant_aware.model.Plant;



@Repository
public interface PlantRepository extends MongoOperations{
	void handle(String message);
	void add_new_plant(String username, Plant p);
}
