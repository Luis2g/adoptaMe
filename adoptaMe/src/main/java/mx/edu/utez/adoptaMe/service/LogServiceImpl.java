package mx.edu.utez.adoptaMe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mx.edu.utez.adoptaMe.entity.Log;
import mx.edu.utez.adoptaMe.repository.LogRepository;

@Service
public class LogServiceImpl implements LogService {

	@Autowired
	private LogRepository logRepository;
	
	@Override
	public List<Log> findAll(){
		return logRepository.findAll();
	}

	@Override
	public Page<Log> listarPaginacion(Pageable page) {
		return logRepository.findAll(page);
	}
	
}
