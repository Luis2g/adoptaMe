package mx.edu.utez.adoptaMe.service;

import java.util.List;

import mx.edu.utez.adoptaMe.entity.UserHasRole;

public interface UserHasRoleService {
    
    List<UserHasRole> listAll();

    UserHasRole save(UserHasRole userHasRole);

    UserHasRole edit();

    UserHasRole update();

    void delete();

}
