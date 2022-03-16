package mx.edu.utez.adoptaMe.service;

import java.util.List;

import mx.edu.utez.adoptaMe.entity.Personality;

public interface PersonalityService {
    
    List<Personality> listAll();

    Personality save();

    Personality edit();

    Personality update();

    void delete();

}
