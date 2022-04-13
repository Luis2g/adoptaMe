package mx.edu.utez.adoptaMe.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.utez.adoptaMe.entity.Request;
import mx.edu.utez.adoptaMe.repository.RequestRepository;

@Service
public class RequestServiceImpl implements RequestService{
	
	@Autowired
	private RequestRepository requestRepository;
    
    private List<Request> list = null;

    @Override
    public List<Request> listAll(){
        return requestRepository.findAll();
    }

    @Override
    public Request save(Request request) {
        return requestRepository.save(request);
    }

    @Override
    public Request edit(long id) {
        return requestRepository.getById(id);
    }

    @Override
    public Request update() {
        return null;
    }

    @Override
    public void delete() {
        
    }
    
    @Override
    public void removeRequest(long id, String username) {
    	requestRepository.deleteRequest(id, username);
    }
    
    @Override
    public List<Request> getUserVolunteerRequests(String username){
    	return requestRepository.getUserVolunteerRequests(username);
    }

}
