package ua.deti.plant_aware;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import ua.deti.plant_aware.repository.*;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;


@Configuration
public class AppConfig {
    @Primary
    @Bean
    public PlantRepository plantRepository(MongoDbFactory m){
        return new PlantRepositoryImpl(m);
    }

    @Bean
    public UserRepository userRepository(MongoDbFactory m){
        return new UserRepositoryImpl(m);
    }

    @Autowired
    void setMapKeyDotReplacement(MappingMongoConverter mappingMongoConverter) {
        mappingMongoConverter.setMapKeyDotReplacement("_");
    }
}