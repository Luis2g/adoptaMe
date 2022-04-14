package mx.edu.utez.adoptaMe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.utez.adoptaMe.entity.FavoriteOne;
import mx.edu.utez.adoptaMe.repository.FavoriteOneRepository;

@Service
public class FavoriteOneServiceImpl implements FavoriteOneService {

	@Autowired
	private FavoriteOneRepository favoriteOneRepository;

    @Override
    public List<FavoriteOne> listAll() {
        return null;
    }

    @Override
    public FavoriteOne save(FavoriteOne favoriteOne) {
        return favoriteOneRepository.save(favoriteOne);
    }

    @Override
    public void removeFavoriteOne(long petId, String username) {
    	favoriteOneRepository.removeFavoriteOne(petId, username);
    }
    
    
}
