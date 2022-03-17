package mx.edu.utez.adoptaMe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import mx.edu.utez.adoptaMe.service.ColorServiceImpl;
import mx.edu.utez.adoptaMe.service.PersonalityServiceImpl;

@Controller
public class PetController {

    @Autowired
    private ColorServiceImpl colorServiceImpl;

    @Autowired
    private PersonalityServiceImpl personalityServiceImpl;

    @GetMapping("/modals")
    public String modals(){
        return "inicioModals";
    }

    @GetMapping("/createPet")
    public String createPet(Model model){
        model.addAttribute("colorsList", colorServiceImpl.listAll());
        model.addAttribute("personalitiesList", personalityServiceImpl.listAll());
        return "createPet";
    }
}
