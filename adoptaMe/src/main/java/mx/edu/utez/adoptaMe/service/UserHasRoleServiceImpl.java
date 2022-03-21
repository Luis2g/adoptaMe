package mx.edu.utez.adoptaMe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.utez.adoptaMe.entity.Role;
import mx.edu.utez.adoptaMe.entity.User;
import mx.edu.utez.adoptaMe.entity.UserHasRole;
import mx.edu.utez.adoptaMe.repository.UserHasRoleRepository;

@Service
public class UserHasRoleServiceImpl implements UserHasRoleService {

    @Autowired
    private UserHasRoleRepository userHasRoleRepository;

    @Override
    public List<UserHasRole> listAll() {
        return null;
    }

    @Override
    public UserHasRole edit() {
        return null;
    }

    @Override
    public UserHasRole update() {
        return null;
    }

    @Override
    public void delete() {
        
    }

    @Override
    public UserHasRole save(UserHasRole userHasRole) {
        return userHasRoleRepository.save(userHasRole);
    }

}
