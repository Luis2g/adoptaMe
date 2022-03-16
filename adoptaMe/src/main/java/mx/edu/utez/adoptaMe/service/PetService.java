package mx.edu.utez.adoptaMe.service;

import java.util.List;

import mx.edu.utez.adoptaMe.entity.Pet;

public interface PetService {
    
    List<Pet> listAll();

    Pet save();

    Pet edit();

    Pet update();

    void delete();

}
