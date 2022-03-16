package mx.edu.utez.adoptaMe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.edu.utez.adoptaMe.entity.Personality;

public interface PersonalityRepository extends JpaRepository<Personality, Long>{
    
}
