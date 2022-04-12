package mx.edu.utez.adoptaMe.service;

import java.util.List;

import mx.edu.utez.adoptaMe.entity.Access;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccessService {

	void registerAccess(String username, String roles);
	
	List<Access> findAll();

	Page<Access> listarPaginacion(Pageable page);
	
}
