package ua.deti.plant_aware.repository;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends MongoOperations{
}