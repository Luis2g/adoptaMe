package mx.edu.utez.adoptaMe.service;

import java.util.List;

import mx.edu.utez.adoptaMe.entity.Permit;

public interface PermitService {
    
    List<Permit> listAll();

    Permit save();

    Permit edit();

    Permit update();

    void delete();

}
