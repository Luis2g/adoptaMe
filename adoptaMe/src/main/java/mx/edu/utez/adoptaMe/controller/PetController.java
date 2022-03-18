package mx.edu.utez.adoptaMe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import mx.edu.utez.adoptaMe.entity.Pet;
import mx.edu.utez.adoptaMe.service.ColorServiceImpl;
import mx.edu.utez.adoptaMe.service.PersonalityServiceImpl;
import mx.edu.utez.adoptaMe.service.PetServiceImpl;

@Controller
public class PetController {

    @Autowired
    private ColorServiceImpl colorServiceImpl;

    @Autowired
    private PersonalityServiceImpl personalityServiceImpl;

    @Autowired
    private PetServiceImpl petServiceImpl;

    @GetMapping("/modals")
    public String modals(){
        return "inicioModals";
    }

    @GetMapping("/createPet")
    public String createPet(Model model, Pet pet){
        model.addAttribute("colorsList", colorServiceImpl.listAll());
        model.addAttribute("personalitiesList", personalityServiceImpl.listAll());
        return "createPet";
    }

    @PostMapping("/savePet")
    public String savePet(Model model,Pet pet){
        System.out.println(pet);
        boolean saved = petServiceImpl.save(pet);
        if(!saved){
            return "createPet";
        }
        return "petsList";
    }

    

}
