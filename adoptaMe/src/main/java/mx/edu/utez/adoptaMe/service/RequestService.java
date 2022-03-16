package mx.edu.utez.adoptaMe.service;

import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

public interface RequestService {
    
    List<Request> listAll();

    Request save();

    Request edit();

    Request update();

    void delete();

}
