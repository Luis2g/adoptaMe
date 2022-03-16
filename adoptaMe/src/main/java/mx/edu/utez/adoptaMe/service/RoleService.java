package mx.edu.utez.adoptaMe.service;

import java.util.List;

import javax.management.relation.Role;

public interface RoleService {
    
    List<Role> listAll();

    Role save();

    Role edit();

    Role update();

    void delete();

}
