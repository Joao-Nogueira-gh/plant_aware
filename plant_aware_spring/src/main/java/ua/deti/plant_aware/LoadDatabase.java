package ua.deti.plant_aware;

import ua.deti.plant_aware.repository.UserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ua.deti.plant_aware.model.*;

@Configuration
class LoadDatabase {

  @Bean
  CommandLineRunner initDatabase(UserRepository userRep) {
    return args -> {
      System.out.print("Preloading " + userRep.save(new User("Bilbo Baggins", "password1")));
      System.out.print("Preloading " + userRep.save(new User("Frodo Baggins", "password2")));
      System.out.print("Preloading users in local database");
    };
  }
}