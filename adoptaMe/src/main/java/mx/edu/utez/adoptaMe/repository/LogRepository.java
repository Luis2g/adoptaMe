package mx.edu.utez.adoptaMe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.edu.utez.adoptaMe.entity.Log;

public interface LogRepository extends JpaRepository<Log, Long> {

}
