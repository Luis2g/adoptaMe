package mx.edu.utez.adoptaMe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.edu.utez.adoptaMe.entity.RoleHasPermit;

public interface RoleHasPermitRepository extends JpaRepository<RoleHasPermit, Long>{
    
}
