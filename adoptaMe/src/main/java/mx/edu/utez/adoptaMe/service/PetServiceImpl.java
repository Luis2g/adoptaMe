package mx.edu.utez.adoptaMe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.utez.adoptaMe.entity.Pet;
import mx.edu.utez.adoptaMe.repository.PetRepository;

@Service
public class PetServiceImpl implements PetService {

    @Autowired
    private PetRepository petRepository;

    @Override
    public List<Pet> listAll() {
        return petRepository.findAll();
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
    public Pet edit() {
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

}
