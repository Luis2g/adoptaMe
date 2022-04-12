package mx.edu.utez.adoptaMe.service;

import java.util.List;

import mx.edu.utez.adoptaMe.entity.Request;


public interface RequestService {
    
    List<Request> listAll();

    Request save(Request request);

    Request edit(long id);

    Request update();

    void delete();

}
