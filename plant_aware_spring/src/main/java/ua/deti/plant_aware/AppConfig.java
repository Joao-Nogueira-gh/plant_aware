package ua.deti.plant_aware;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ua.deti.plant_aware.repository.PlantRepository;

public class AppConfig {
    public PlantRepository plantRepository() {
        return new PlantRepositoryImpl();
    }
}