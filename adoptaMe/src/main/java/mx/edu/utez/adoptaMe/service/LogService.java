package mx.edu.utez.adoptaMe.service;

import java.util.List;

import mx.edu.utez.adoptaMe.entity.Log;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LogService {

	List<Log> findAll ();

	Page<Log> listarPaginacion(Pageable page);
	
}
