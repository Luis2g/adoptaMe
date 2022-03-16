package mx.edu.utez.adoptaMe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.edu.utez.adoptaMe.entity.Donation;

public interface DonationRepository extends JpaRepository<Donation, Long>{
    
}
