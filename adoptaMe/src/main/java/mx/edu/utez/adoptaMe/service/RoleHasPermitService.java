package mx.edu.utez.adoptaMe.service;

import java.util.List;

import mx.edu.utez.adoptaMe.entity.RoleHasPermit;

public interface RoleHasPermitService {
    
    List<RoleHasPermit> listAll();

    RoleHasPermit save();

    RoleHasPermit edit();

    RoleHasPermit update();

    void delete();

}
