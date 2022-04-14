package mx.edu.utez.adoptaMe.service;

import java.util.List;

import mx.edu.utez.adoptaMe.entity.FavoriteOne;

public interface FavoriteOneService {
    
    List<FavoriteOne> listAll();

    FavoriteOne save(FavoriteOne favoriteOne);
    
    void removeFavoriteOne(long petId, String username);

}
