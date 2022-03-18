package mx.edu.utez.adoptaMe.service;

import java.util.List;

import mx.edu.utez.adoptaMe.entity.User;

public interface UserService {
    
    List<User> listAll();

    User save(User user);

    User edit();

    User update();

    void delete();

}
