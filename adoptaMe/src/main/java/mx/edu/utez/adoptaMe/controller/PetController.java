package mx.edu.utez.adoptaMe.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mx.edu.utez.adoptaMe.entity.Pet;
import mx.edu.utez.adoptaMe.entity.User;
import mx.edu.utez.adoptaMe.helpers.Session;
import mx.edu.utez.adoptaMe.service.ColorServiceImpl;
import mx.edu.utez.adoptaMe.service.PersonalityServiceImpl;
import mx.edu.utez.adoptaMe.service.PetServiceImpl;
import mx.edu.utez.adoptaMe.service.UserServiceImpl;

@Controller
@RequestMapping("/mascotas")
public class PetController {

    @Autowired
    private ColorServiceImpl colorServiceImpl;

    @Autowired
    private PersonalityServiceImpl personalityServiceImpl;

    @Autowired
    private PetServiceImpl petServiceImpl;
    
    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("")
	public String pets(@ModelAttribute("pet") Pet pet, Model model, RedirectAttributes redirectAttributes, Authentication authentication, HttpSession session) {
    	User user = new User();
        if(authentication != null) {
    		String username = authentication.getName();
    		user = userServiceImpl.findByUsername(username);
    		session.setAttribute("user", user);
    	}
        pet.setUser(user);
    	model.addAttribute("user", user);
        model.addAttribute("colorsList", colorServiceImpl.listAll());
        model.addAttribute("personalityList", personalityServiceImpl.listAll());
        model.addAttribute("listPets", petServiceImpl.listAll());
		return "petsList";
	}
    

    @GetMapping("/registro")
    public String createPet(Model model, Pet pet){
        model.addAttribute("colorsList", colorServiceImpl.listAll());
        model.addAttribute("personalitiesList", personalityServiceImpl.listAll());
        return "createPet";
    }

    @GetMapping("/adoptar/{id}")
    public String showPet(@PathVariable long id, Model model, RedirectAttributes redirectAttributes){
        Pet pet = petServiceImpl.request(id);
        if(pet != null){
            model.addAttribute("pet", pet);
            System.out.println("Mascota: "+pet);
            return "fragments/requestPetModal";
        }
        return "redirect:/Mascota/Mascotas";
    }

    @PostMapping("/save")
    public String savePet(Model model, @Valid @ModelAttribute("pet") Pet pet, BindingResult bindingResult, RedirectAttributes redirectAttributes, Authentication authentication, HttpSession session){
        // All pet when is created, the available always is true
        pet.setIsAvailable(true);

        // Configuration for user before controller session is created
        int idInt = 1;
        long id = idInt;
        User user = new User();
        if(authentication != null) {
    		String username = authentication.getName();
    		user = userServiceImpl.findByUsername(username);
    		session.setAttribute("user", user);
    	}
        pet.setUser(user);
        pet.setStatus("pending");
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
			return "redirect:/mascotas";
        }else{
            redirectAttributes.addFlashAttribute("msg_error", "¡Ha ocurrido un error en el registro!");
            model.addAttribute("colorsList", colorServiceImpl.listAll());
            model.addAttribute("personalitiesList", personalityServiceImpl.listAll());
            return "createPet";
        }

    }

    

}
