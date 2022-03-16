package mx.edu.utez.adoptaMe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.edu.utez.adoptaMe.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
