package mx.edu.utez.adoptaMe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.edu.utez.adoptaMe.entity.FavoriteOne;

public interface FavoriteOneRepository extends JpaRepository<FavoriteOne, Long>{
    
}