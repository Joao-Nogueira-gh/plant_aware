package ua.deti.plant_aware.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.deti.plant_aware.model.*;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
}