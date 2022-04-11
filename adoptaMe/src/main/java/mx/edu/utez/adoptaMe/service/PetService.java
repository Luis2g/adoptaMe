package mx.edu.utez.adoptaMe.service;

import java.util.List;

import mx.edu.utez.adoptaMe.entity.Pet;

public interface PetService {
    
    List<Pet> listAll();

    boolean save(Pet pet);

    Pet showPetRequest(Long id);
    
    Pet editPet(Long id);

    Pet update();

    boolean delete();

}
