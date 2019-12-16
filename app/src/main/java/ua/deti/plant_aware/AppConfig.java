package ua.deti.plant_aware;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ua.deti.plant_aware.repository.*;
import org.springframework.data.mongodb.MongoDbFactory;


@Configuration
public class AppConfig {
    @Bean
    public PlantRepository plantRepository(MongoDbFactory m){
        return new PlantRepositoryImpl(m);
    }
}