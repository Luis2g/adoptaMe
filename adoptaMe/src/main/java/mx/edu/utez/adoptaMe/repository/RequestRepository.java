package mx.edu.utez.adoptaMe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.edu.utez.adoptaMe.entity.Request;

public interface RequestRepository extends JpaRepository<Request, Long> {
    
}
