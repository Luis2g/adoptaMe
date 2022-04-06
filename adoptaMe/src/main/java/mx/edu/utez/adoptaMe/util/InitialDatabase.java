package mx.edu.utez.adoptaMe.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class InitialDatabase implements CommandLineRunner {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String ... args) throws Exception {
        String myPassword = "123456";
        System.out.println( myPassword + "enconde: " + passwordEncoder.encode(myPassword) );
        System.out.println(passwordEncoder.matches("Uno23456", "$2a$10$V7W0FHNE5vfFQuujTXkA9edS3invSxkY/IswkuCd272aDwhmflZkG"));
    }


}
