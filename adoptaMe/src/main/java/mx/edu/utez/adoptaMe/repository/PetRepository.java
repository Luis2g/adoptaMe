package mx.edu.utez.adoptaMe.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import mx.edu.utez.adoptaMe.entity.Pet;
import mx.edu.utez.adoptaMe.entity.User;

public interface PetRepository extends JpaRepository<Pet, Long>{
    
	List<Pet> findByStatus(String status);
	List<Pet> findTop8ByStatusOrderByIdDesc(String status);
	
	@Modifying
	@Transactional
    @Query(value="update pets set status= :statusIn where id = :idIn", nativeQuery=true)
    void updateStatus(String statusIn, long idIn);

	@Modifying
	@Transactional
	@Query(value="SELECT * FROM pets P JOIN requests R ON p.id = R.pet_id WHERE P.user_id = :username AND is_accepted = 'pending'", nativeQuery=true)
	List<Pet> getRequestedPetsForVolunteer(String username);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE pets SET status = 'adopted' WHERE id = :petId", nativeQuery=true)
	void changePetStatusToAdopted(long petId);
	
	@Modifying
	@Transactional
	@Query(value="SELECT * FROM pets P JOIN requests R ON P.id = R.pet_id WHERE R.user_id = :username", nativeQuery=true)
	List<Pet> getAdopterRequests(String username);

	@Modifying
	@Transactional
	@Query(value="SELECT * FROM pets WHERE status = 'adopted' AND user_id = :username", nativeQuery=true)
	List<Pet> getAdoptedPets(String username);
	
	List<Pet> findByType(String type);
	
	List<Pet> findByTypeAndStatus(String type, String status);
	
	List<Pet> findByUserAndStatus(User username, String status);
	
	
}
