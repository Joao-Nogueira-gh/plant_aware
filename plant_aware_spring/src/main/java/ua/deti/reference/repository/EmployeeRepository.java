package ies.mysqlExample.springbootJPA.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ies.mysqlExample.springbootJPA.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
    Employee findByEmailId(String emailId);

}