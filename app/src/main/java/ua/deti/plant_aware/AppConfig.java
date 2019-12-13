package ua.deti.plant_aware;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ua.deti.plant_aware.repository.*;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;


@Configuration
public class AppConfig {
    @Bean
    public PlantRepository plantRepository(MongoDbFactory m){
        return new PlantRepositoryImpl(m);
    }

    @Autowired
    void setMapKeyDotReplacement(MappingMongoConverter mappingMongoConverter) {
        mappingMongoConverter.setMapKeyDotReplacement("_");
    }
}