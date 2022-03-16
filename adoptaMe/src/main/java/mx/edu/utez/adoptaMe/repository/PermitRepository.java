package mx.edu.utez.adoptaMe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.edu.utez.adoptaMe.entity.Permit;

public interface PermitRepository extends JpaRepository<Permit, Long>{
    
}
