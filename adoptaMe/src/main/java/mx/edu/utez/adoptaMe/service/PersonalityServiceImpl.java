package mx.edu.utez.adoptaMe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.utez.adoptaMe.entity.Personality;
import mx.edu.utez.adoptaMe.repository.PersonalityRepository;

@Service
public class PersonalityServiceImpl implements PersonalityService{

    @Autowired
    private PersonalityRepository personalityRepository;

    @Override
    public List<Personality> listAll() {
        return personalityRepository.findAll();
    }

    @Override
    public Personality save() {
        return null;
    }

    @Override
    public Personality edit() {
        return null;
    }

    @Override
    public Personality update() {
        return null;
    }
    
    @Override
    public void delete() {
        
    }

}
