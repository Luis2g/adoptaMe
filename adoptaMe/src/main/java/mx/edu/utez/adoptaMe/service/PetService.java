package mx.edu.utez.adoptaMe.service;

import java.util.List;

import mx.edu.utez.adoptaMe.entity.Pet;
import mx.edu.utez.adoptaMe.entity.User;

public interface PetService {
	
	List<Pet> findByStatus(String status);

	void updateStatus(String status, long id);
	
	List<Pet> findByType(String type);
    
    List<Pet> listAll();

    List<Pet> listSortedPets();

    boolean save(Pet pet);

    Pet showPetRequest(Long id);
    
    Pet editPet(Long id);

    Pet update();

    boolean delete();
    
    List<Pet> findByTypeAndStatus(String type, String status);
    
    List<Pet> getRequestedPetsForVolunteer(String username);
        
    List<Pet> findByUserAndStatus(User user, String status);
    
    void changePetStatusToAdopted(long petId);
    
    List<Pet> getAdopterRequests(String username);
    
    List<Pet> getAdoptedPets(String username);
    
}
