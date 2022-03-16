package mx.edu.utez.adoptaMe.service;

import java.util.List;

import mx.edu.utez.adoptaMe.entity.Image;

public interface ImageService {
    
    List<Image> listAll();

    Image save();

    Image edit();

    Image update();

    void delete();

}
