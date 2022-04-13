package mx.edu.utez.adoptaMe.repository;

import java.util.List;

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
	
	
	@Modifying
	@Transactional
	@Query(value = "SELECT * FROM requests WHERE user_id = :usernameIn", nativeQuery=true)
	List<Request> getUserVolunteerRequests(String usernameIn);

	
	@Modifying
	@Transactional
	@Query(value = "UPDATE requests SET is_accepted = 1 WHERE pet_id = :petId AND user_id = :username", nativeQuery=true)
	void endAdoption(long petId, String username);
	
	
}
