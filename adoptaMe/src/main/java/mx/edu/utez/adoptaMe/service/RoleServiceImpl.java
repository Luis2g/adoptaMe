package mx.edu.utez.adoptaMe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.utez.adoptaMe.entity.Role;
import mx.edu.utez.adoptaMe.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
    @Override
    public List<Role> listAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role save() {
        return null;
    }
    
    @Override
    public Role edit() {
        return null;
    }

    @Override
    public Role update() {
        return null;
    }
    
    @Override
    public void delete() {
        
    }

}
