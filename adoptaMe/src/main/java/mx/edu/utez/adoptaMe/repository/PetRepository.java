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
	
}
