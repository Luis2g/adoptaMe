package mx.edu.utez.adoptaMe.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import mx.edu.utez.adoptaMe.entity.Pet;
import mx.edu.utez.adoptaMe.entity.User;
import mx.edu.utez.adoptaMe.repository.PetRepository;

@Service
public class PetServiceImpl implements PetService {

    @Autowired
    private PetRepository petRepository;
    
    @Override
    public List<Pet> findByType(String type){
    	return petRepository.findByType(type);
    }

    @Override
    public List<Pet> listAll() {
        return petRepository.findAll();
    }

    @Override 
    public List<Pet> listSortedPets(){
        return petRepository.findTop8ByStatusOrderByIdDesc("accepted");
    }

    @Override
    public boolean save(Pet pet) {
        try{
            petRepository.save(pet);
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Pet showPetRequest(Long id) {
        Optional<Pet> optional = petRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }
    
    @Override
    public Pet editPet(Long id) {
        Optional<Pet> optional = petRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    @Override
    public Pet update() {
        return null;
    }

    @Override
    public boolean delete() {
        return false;
    }
    
    @Override
    public List<Pet> findByStatus(String status){
    	return petRepository.findByStatus(status);
    }
    
    @Override
    public void updateStatus(String status, long id) {
    	petRepository.updateStatus(status, id);
    }
    
    @Override
    public List<Pet> findByTypeAndStatus (String type, String status){
    	return petRepository.findByTypeAndStatus(type, status);
    }
    
    @Override
    public List<Pet> getRequestedPetsForVolunteer(String username){
    	return petRepository.getRequestedPetsForVolunteer(username);
    }
    
    @Override
    public List<Pet> findByUserAndStatus(User user, String status){
    	return petRepository.findByUserAndStatus(user, status);
    }
    
    @Override
    public void changePetStatusToAdopted(long petId) {
    	petRepository.changePetStatusToAdopted(petId);
    }

    @Override
    public List<Pet> getAdopterRequests(String username) {
    	return petRepository.getAdopterRequests(username);
    }

    @Override
    public List<Pet> getAdoptedPets(String username) {
    	return petRepository.getAdoptedPets(username);
    }

    public Pet savePet(Pet pet, String username) {
        return petRepository.savePet(pet.getAge(), pet.getDescription(),pet.getName(), pet.getImage(), pet.getSex(), pet.getSize(), pet.getStatus(), pet.getType(), pet.getColor().getId(), pet.getPersonality().getId(), pet.getUser().getUsername(), username, pet.getUnitAge()).get();       
    }

    @Override
    public Pet modifyPet(Pet pet, String username) {
        return petRepository.modifyPet(pet.getId(),pet.getAge(), pet.getDescription(),pet.getName(),pet.getImage(), pet.getSex(), pet.getSize(), pet.getStatus(), pet.getType(), pet.getColor().getId(), pet.getPersonality().getId(), pet.getUser().getUsername(), username, pet.getUnitAge()).get();
    }

    @Override
    public List<Pet> scopePet(String text) {
        return petRepository.scopePet(text);
    }
}
