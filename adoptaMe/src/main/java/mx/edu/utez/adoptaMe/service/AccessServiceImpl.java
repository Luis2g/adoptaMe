package mx.edu.utez.adoptaMe.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import mx.edu.utez.adoptaMe.entity.Access;
import mx.edu.utez.adoptaMe.repository.AccessRepository;

@Service
public class AccessServiceImpl implements AccessService{
	
	@Autowired
	private AccessRepository accessRepository;
	
	@Override
	public void registerAccess(String username, String roles) {
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
	    LocalDateTime now = LocalDateTime.now();  
	    System.out.println(dtf.format(now)); 
		
		Access access = new Access(username, roles, now);
		
		System.out.println("This is the access object " + access);
		accessRepository.save(access);
	}
	
	@Override
	public List<Access> findAll(){
		
		List<Access> accesses = accessRepository.findAll(Sort.by(Sort.Direction.DESC, "date"));

		return accesses;
	}

}
