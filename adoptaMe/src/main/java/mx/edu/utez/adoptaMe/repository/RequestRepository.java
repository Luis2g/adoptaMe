package mx.edu.utez.adoptaMe.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import mx.edu.utez.adoptaMe.entity.Request;

public interface RequestRepository extends JpaRepository<Request, Long> {
 
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM requests WHERE pet_id = :petId AND user_id = :username", nativeQuery=true)
	void deleteRequest(long petId, String username);
	
	
	
}
