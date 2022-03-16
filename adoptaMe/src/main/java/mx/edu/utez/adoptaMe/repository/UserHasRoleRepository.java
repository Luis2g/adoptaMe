package mx.edu.utez.adoptaMe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.edu.utez.adoptaMe.entity.UserHasRole;

public interface UserHasRoleRepository extends JpaRepository<UserHasRole, Long> {
    
}
