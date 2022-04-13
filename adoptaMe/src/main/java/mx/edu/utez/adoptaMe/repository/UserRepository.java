package mx.edu.utez.adoptaMe.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.ExceptionHandler;

import mx.edu.utez.adoptaMe.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

    @ExceptionHandler(org.hibernate.exception.SQLGrammarException.class)
    @Procedure(name = "login")
    Optional<User> login(@Param("usernameIn") String usernameIn, @Param("passwordIn") String passwordIn);

    User findByUsername(String username);

    @Modifying
    @Query(value = "update users u set u.password = :password where u.email = :email", nativeQuery = true)
    void updatePassword(@Param("password") String password, @Param("email") String email);

    // @Modifying
    // @Query("update users set password = :password where username = :username")
    // void changePassword(@Param("password") String password, @Param("username")
    // String username );

}
