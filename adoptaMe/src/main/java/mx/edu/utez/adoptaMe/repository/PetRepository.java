package mx.edu.utez.adoptaMe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.edu.utez.adoptaMe.entity.Pet;

public interface PetRepository extends JpaRepository<Pet, Long>{
    
}
