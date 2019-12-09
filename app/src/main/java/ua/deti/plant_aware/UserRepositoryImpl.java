package ua.deti.plant_aware;


import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import ua.deti.plant_aware.model.User;
import ua.deti.plant_aware.repository.*;
import ua.deti.plant_aware.util.*;

public class UserRepositoryImpl extends MongoTemplate implements UserRepository {

    public UserRepositoryImpl(MongoDbFactory mongoDbFactory) {
		super(mongoDbFactory);
		
    }
    
}