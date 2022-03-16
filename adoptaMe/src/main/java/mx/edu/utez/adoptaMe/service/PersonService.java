package mx.edu.utez.adoptaMe.service;

import java.util.List;

import mx.edu.utez.adoptaMe.entity.Person;

public interface PersonService {
    
    List<Person> listAll();

    Person save();

    Person edit();

    Person update();

    void delete();

}
