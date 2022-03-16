package mx.edu.utez.adoptaMe.service;

import java.util.List;

import mx.edu.utez.adoptaMe.entity.FavoriteOne;

public interface FavoriteOneService {
    
    List<FavoriteOne> listAll();

    FavoriteOne save();

    FavoriteOne edit();

    FavoriteOne update();

    void delete();

}
