package mx.edu.utez.adoptaMe.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.ExceptionHandler;


import mx.edu.utez.adoptaMe.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
    
    @ExceptionHandler(org.hibernate.exception.SQLGrammarException.class)
    @Procedure (name = "login")
	Optional<User> login(@Param("usernameIn") String usernameIn, @Param("passwordIn") String passwordIn);

    User findByUsername(String username);



}
