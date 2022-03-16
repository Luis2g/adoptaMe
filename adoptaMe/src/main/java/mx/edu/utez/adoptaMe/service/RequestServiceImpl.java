package mx.edu.utez.adoptaMe.service;

import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import org.springframework.stereotype.Service;

@Service
public class RequestServiceImpl implements RequestService{
    
    private List<Request> list = null;

    @Override
    public List<Request> listAll(){
        return list;
    }

    @Override
    public Request save() {
        return null;
    }

    @Override
    public Request edit() {
        return null;
    }

    @Override
    public Request update() {
        return null;
    }

    @Override
    public void delete() {
        
    }

}
