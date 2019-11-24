package ua.deti.plant_aware.repository;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import ua.deti.plant_aware.model.*;

@Repository
public interface PlantRepository extends QueryByExampleExecutor<Plant>, MongoOperations{

	void handle(String message);
}