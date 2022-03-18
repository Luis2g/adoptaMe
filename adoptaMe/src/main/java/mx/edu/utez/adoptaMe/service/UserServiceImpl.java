package mx.edu.utez.adoptaMe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.utez.adoptaMe.entity.User;
import mx.edu.utez.adoptaMe.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    
    @Override
    public List<User> listAll() {
        return null;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User edit() {
        return null;
    }

    @Override
    public User update() {
        return null;
    }
    
    @Override
    public void delete() {
        
    }

}
