package mx.edu.utez.adoptaMe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(("/donativo"))

public class DonationController {
	
	
	@GetMapping("/historialAdoptador")
	public String donationAdopter() {
		return "adopterDonation";
	}
	
	@GetMapping("/historialGeneral")
	public String donationGeneral() {
		return "generalDonation";
	}
	
	
}
