package mx.edu.utez.adoptaMe.service;

import java.util.List;

import mx.edu.utez.adoptaMe.entity.Donation;

public interface DonationService {
    
    List<Donation> listAll();

    Donation save();

    Donation edit();

    Donation update();

    void delete();

}
