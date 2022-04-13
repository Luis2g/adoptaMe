package mx.edu.utez.adoptaMe.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.hibernate.exception.SQLGrammarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.utez.adoptaMe.entity.User;
import mx.edu.utez.adoptaMe.repository.UserRepository;

@Service
@Transactional
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
    public User update() {
        return null;
    }

    @Override
    public void delete() {

    }

    @Override
    public User findByUsername(String email) {
        return userRepository.findByUsername(email);
    }

    // @Transactional
    // @Override
    // public void changePassword(String password, String username) {
    // userRepository.changePassword(password, username);
    // }

    @Transactional
    @Override
    public User login(String username, String password) {
        Optional<User> found = null;
        try {
            found = userRepository.login(username, password);
            // if the next code line is left right below will override the value in the
            // database
            // found.setPassword("password");
        } catch (SQLGrammarException ex) {
            return null;
        }
        return found.get();
    }

    @Override
    public boolean changePassword(String password, String email) {
        try {
            userRepository.updatePassword(password, email);
            return true;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            exception.printStackTrace();
            return false;
        }
    }

}
