package mx.edu.utez.adoptaMe.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
	@Query(value = "UPDATE requests SET is_accepted = 'accepted' WHERE pet_id = :petId AND user_id = :username", nativeQuery=true)
	void endAdoption(long petId, String username);

	@Modifying
	@Transactional
	@Query(value = "UPDATE requests SET is_accepted = 'rejected' WHERE pet_id = :petId AND is_accepted != 'accepted';", nativeQuery=true)
	void cancelRequestsOfOtherUsers(long petId);
	
	@Query(value = "call insertRequest(:is_accepted,:pet_id,:user_id,:username)", nativeQuery=true)
    Optional<Request> saveRequest(
    @Param("is_accepted") String is_accepted,
    @Param("pet_id") Long pet_id,
    @Param("user_id") String user_id,
    @Param("username") String username
    );

    @Query(value = "call modifyRequest(:id, :is_accepted,:pet_id,:user_id,:username)", nativeQuery=true)
    Optional<Request> modifyRequest(
    @Param("id") Long id,
    @Param("is_accepted") String is_accepted,
    @Param("pet_id") Long pet_id,
    @Param("user_id") String user_id,
    @Param("username") String username
    );
}
