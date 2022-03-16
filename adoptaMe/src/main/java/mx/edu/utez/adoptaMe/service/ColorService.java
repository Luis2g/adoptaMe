package mx.edu.utez.adoptaMe.service;

import java.util.List;

import mx.edu.utez.adoptaMe.entity.Color;

public interface ColorService {
    
    List<Color> listAll();

    Color save();

    Color edit();

    Color update();

    void delete();

}
