package mx.edu.utez.adoptaMe.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import mx.edu.utez.adoptaMe.entity.Pet;
import mx.edu.utez.adoptaMe.entity.User;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

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
	
	@Query(value="call insertPet(:age , :description, :name,:image,:sex,:size,:status,:type,:color_id,:personality_id,:user_id,:username,:unit_age)", nativeQuery=true)
	Optional<Pet> savePet(
		@Param("age")int age , 
		@Param ("description") String description, 
		@Param ("name") String name,
		@Param ("image") String image,
		@Param("sex") String sex,
		@Param ("size") String size,
		@Param ("status")String status,
		@Param ("type") String type,
		@Param ("color_id") Long color_id,
		@Param ("personality_id") Long personality_id,
		@Param ("user_id") String user_id,
		@Param ("username") String username,
		@Param("unit_age") String unit_age
	);

	@Query(value="call modifyPet(:id,:age,:description,:name,:image,:sex,:size,:status,:type,:color_id,:personality_id,:user_id, :username,:unit_age)", nativeQuery=true)
	Optional<Pet> modifyPet(
		@Param("id") Long id,
		@Param("age")int age , 
		@Param ("description") String description, 
		@Param ("name") String name,
		@Param ("image") String image,
		@Param("sex") String sex,
		@Param ("size") String size,
		@Param ("status")String status,
		@Param ("type") String type,
		@Param ("color_id") Long color_id,
		@Param ("personality_id") Long personality_id,
		@Param ("user_id") String user_id,
		@Param ("username") String username,
		@Param("unit_age") String unit_age
	);


	@Modifying
	@Transactional
	@Query(value="Select * from pets where id in(SELECT pets.id from pets INNER JOIN  colors ON pets.color_id = colors.id INNER JOIN  personalities ON pets.personality_id = personalities.id where CONCAT(description,pets.name,size, colors.name, personalities.name) LIKE (%:text%)) ;", nativeQuery=true)
	List<Pet> scopePet(String text);
}
