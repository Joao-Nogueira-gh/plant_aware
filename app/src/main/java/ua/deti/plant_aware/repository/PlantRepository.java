package ua.deti.plant_aware.repository;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;


@Repository
public interface PlantRepository extends MongoOperations{
	void handle(String message);
}
