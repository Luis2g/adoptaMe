package mx.edu.utez.adoptaMe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.edu.utez.adoptaMe.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{
    
}
