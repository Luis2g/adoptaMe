	package mx.edu.utez.adoptaMe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.edu.utez.adoptaMe.entity.Access;

public interface AccessRepository extends JpaRepository<Access, Long>{
	
}
