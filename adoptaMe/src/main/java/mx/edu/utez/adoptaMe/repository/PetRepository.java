package mx.edu.utez.adoptaMe.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mx.edu.utez.adoptaMe.entity.Pet;

public interface PetRepository extends JpaRepository<Pet, Long>{
    
	List<Pet> findByStatus(String status);
	
	@Modifying
	@Transactional
    @Query(value="update pets set status= :statusIn where id = :idIn", nativeQuery=true)
    void updateStatus(String statusIn, long idIn);
	
	List<Pet> findByType(String type);
	
	List<Pet> findByTypeAndStatus(String type, String status);
	
	
	@Modifying
	@Transactional
    @Query(value="SELECT * FROM pets P JOIN requests R ON p.id = R.pet_id WHERE P.user_id = :username", nativeQuery=true)
    List<Pet> getRequestedPetsForVolunteer(String username);
	
}
