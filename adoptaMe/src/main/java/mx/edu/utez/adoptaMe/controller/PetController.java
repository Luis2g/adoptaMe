package mx.edu.utez.adoptaMe.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mx.edu.utez.adoptaMe.entity.Pet;
import mx.edu.utez.adoptaMe.entity.User;
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

    @GetMapping("/listPets")
	public String pets(Model model, RedirectAttributes redirectAttributes) {
        model.addAttribute("listPets", petServiceImpl.listAll());
		return "petsList";
	}

    @GetMapping("/RegistrarMascota")
    public String createPet(Model model, Pet pet){
        model.addAttribute("colorsList", colorServiceImpl.listAll());
        model.addAttribute("personalitiesList", personalityServiceImpl.listAll());
        return "createPet";
    }

    @GetMapping("/AdoptarMascota/{id}")
    public String showPet(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes){
        Pet pet = petServiceImpl.edit(id);
        if(pet != null){
            model.addAttribute("pet", pet);
            System.out.println(pet.getName());
            return "fragments/requestPetModal";
        }
        return "redirect:/listPets";
    }

    @PostMapping("/GuardarMascota")
    public String savePet(Model model, @Valid @ModelAttribute("pet") Pet pet, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        // All pet when is created, the available always is true
        pet.setIsAvailable(true);

        // Configuration for user before controller session is created
        User user = new User();
        int idInt = 1;
        long id = idInt;
        user.setUserId(id);
        pet.setUser(user);
        // End configuration

        if(bindingResult.hasErrors()){
            for(ObjectError error: bindingResult.getAllErrors()){
				System.out.println("Error: " + error.getDefaultMessage());
			}
            redirectAttributes.addFlashAttribute("msg_error", "¡Ha ocurrido un error en el registro!");
            model.addAttribute("colorsList", colorServiceImpl.listAll());
            model.addAttribute("personalitiesList", personalityServiceImpl.listAll());
			return "createPet";
        }

        boolean saved = petServiceImpl.save(pet);
        if(saved){
            redirectAttributes.addFlashAttribute("msg_success", "¡Se ha realizado el registro correctamente!");
			return "redirect:/listPets";
        }else{
            redirectAttributes.addFlashAttribute("msg_error", "¡Ha ocurrido un error en el registro!");
            model.addAttribute("colorsList", colorServiceImpl.listAll());
            model.addAttribute("personalitiesList", personalityServiceImpl.listAll());
            return "createPet";
        }

    }

    

}
