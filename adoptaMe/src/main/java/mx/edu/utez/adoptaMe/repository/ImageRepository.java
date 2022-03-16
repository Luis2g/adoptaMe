package mx.edu.utez.adoptaMe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.edu.utez.adoptaMe.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Long>{
    
}
