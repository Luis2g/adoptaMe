package mx.edu.utez.adoptaMe.service;

import java.util.List;

import mx.edu.utez.adoptaMe.entity.User;

public interface UserService {
    
    List<User> listAll();

    User save(User user);
    
    User findByUsername(String username);

    User findByEmail(String email);

    User update();

    User login(String username, String password);

    void delete();

    boolean changePassword(String password, String email);
    
//    void changePassword(String password, String username);

}
