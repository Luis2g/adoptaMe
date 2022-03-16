package mx.edu.utez.adoptaMe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.edu.utez.adoptaMe.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    
}
