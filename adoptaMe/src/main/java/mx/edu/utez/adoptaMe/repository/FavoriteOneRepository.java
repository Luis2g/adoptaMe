package mx.edu.utez.adoptaMe.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import mx.edu.utez.adoptaMe.entity.FavoriteOne;

public interface FavoriteOneRepository extends JpaRepository<FavoriteOne, Long>{
 
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM favorite_ones WHERE pet_id = :petId AND user_id = :username", nativeQuery=true)
	void removeFavoriteOne(long petId, String username);
	
}