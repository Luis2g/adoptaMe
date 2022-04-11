package mx.edu.utez.adoptaMe.service;

import java.util.List;

import mx.edu.utez.adoptaMe.entity.Access;

public interface AccessService {

	void registerAccess(String username, String roles);
	
	List<Access> findAll();
	
}
