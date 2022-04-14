package mx.edu.utez.adoptaMe.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mx.edu.utez.adoptaMe.entity.FavoriteOne;
import mx.edu.utez.adoptaMe.entity.Pet;
import mx.edu.utez.adoptaMe.entity.Request;
import mx.edu.utez.adoptaMe.entity.User;
import mx.edu.utez.adoptaMe.helpers.Session;
import mx.edu.utez.adoptaMe.service.ColorServiceImpl;
import mx.edu.utez.adoptaMe.service.FavoriteOneServiceImpl;
import mx.edu.utez.adoptaMe.service.PersonalityServiceImpl;
import mx.edu.utez.adoptaMe.service.PetServiceImpl;
import mx.edu.utez.adoptaMe.service.RequestServiceImpl;
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
    
    @Autowired
    private RequestServiceImpl requestServiceImpl;
    
    @Autowired
    private FavoriteOneServiceImpl favoriteOneServiceImpl;

    @GetMapping("/{type}")
	public String pets(@ModelAttribute("pet") Pet pet, Model model, RedirectAttributes redirectAttributes, Authentication authentication, HttpSession session,
			@PathVariable(required = true) String type) {
    	
    	model.addAttribute("location", type);
    	type = type.equals("perros") ? "perro" : "gato";
    	
    	List<Pet> filteredPets = petServiceImpl.findByTypeAndStatus(type, "accepted");
    	
    	User user = new User();
        if(authentication != null) {
    		String username = authentication.getName();
    		model.addAttribute("usernameFromModel", username);
    		user = userServiceImpl.findByUsername(username);
    		session.setAttribute("user", user);
    		
    		for(Pet petVar: filteredPets) {
        		for(Request request: petVar.getRequests()) {
        			if(request.getUser().getUsername().equals(username)) {
        				petVar.setStatus("requestedByYou");        				
        			}
        		}
        	}
    		
			System.err.println("This is before the favoriteOne impresion");
	     	for(Pet thisPet: filteredPets ) {
	     		System.err.println("It gets to the pets");
	        	for(FavoriteOne favoriteOne: thisPet.getFavoriteOnes()) {
	        		if(thisPet.getStatus().equals("requestedByYou") && favoriteOne.getUser().getUsername().equals(username)) {
	        			thisPet.setStatus("lovedIt");
	        		}else if (favoriteOne.getUser().getUsername().equals(username)){
	        			thisPet.setStatus("heart");
	        		}
//	        		System.err.println("it gets to its favorites");
//	        		System.err.println(favoriteOne);
	        	}  	
		    }
    	}
        
       
       
        
        pet.setUser(user);
    	model.addAttribute("user", user);
        model.addAttribute("colorsList", colorServiceImpl.listAll());
        model.addAttribute("personalityList", personalityServiceImpl.listAll());
        model.addAttribute("listPets",  filteredPets);
		return "petsList";
	}
    

    @GetMapping("/registro")
    public String createPet(Model model, Pet pet, @RequestParam("location") String location){
    	model.addAttribute("location", location);
        model.addAttribute("colorsList", colorServiceImpl.listAll());
        model.addAttribute("personalitiesList", personalityServiceImpl.listAll());
        return "/createPet";
    }

    // Method to show specific pet to adopter, this method includes to create request
    @GetMapping("/adoptar/{id}")
    public String showPetRequest(@PathVariable long id, Model model, RedirectAttributes redirectAttributes){
        Pet pet = petServiceImpl.showPetRequest(id);
        if(pet != null){
            model.addAttribute("pet", pet);
            System.out.println("Mascota: "+pet);
            return "fragments/requestPetModal";
        }
        return "redirect:/Mascota/Mascotas";
    }
    
    // Method to show specific pet to administrator, this method include update the pet
    @GetMapping("/mascota/{id}")
    public String showPetAdministrator(@PathVariable long id, Model model, RedirectAttributes redirectAttributes) {
    	Pet pet = petServiceImpl.editPet(id);
    	if(pet != null) {
    		model.addAttribute("pet", pet);
    		model.addAttribute("colorsList", colorServiceImpl.listAll());
    		model.addAttribute("personalitiesList", personalityServiceImpl.listAll());
            model.addAttribute("listPets", petServiceImpl.listAll());
    		return "/createPet";
    	}else {
        	redirectAttributes.addFlashAttribute("msg_error", "¡Mascota no encontrada!");
    		return "redirect:/mascotas";
    	}
    }

    @PostMapping("/save")
    public String savePet(Model model, @Valid @ModelAttribute("pet") Pet pet,
    		@RequestParam("location") String location,
    		BindingResult bindingResult, 
    		RedirectAttributes redirectAttributes, 
    		Authentication authentication, HttpSession session){
        // All pet when is created, the available always is true
    	if(pet.getId() == null) {
    		System.out.println("Entro a registrar");
         // Configuration for user before controller session is created
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
                redirectAttributes.addFlashAttribute("msg_success", "¡Se ha realizado el registro correctamente!, espere a que el admistrador acepte la publicacion de la mascota");
    			return "redirect:/misPublicaciones";
            }else{
                redirectAttributes.addFlashAttribute("msg_error", "¡Ha ocurrido un error en el registro!");
                model.addAttribute("colorsList", colorServiceImpl.listAll());
                model.addAttribute("personalitiesList", personalityServiceImpl.listAll());
                return "createPet";
            }
    	}else {
    		System.out.println("Entra en la actualizacion");
    		Pet petExist = petServiceImpl.editPet(pet.getId());
    		pet.setName(petExist.getName());
    		pet.setDescription(petExist.getDescription());
    		pet.setSex(petExist.getSex());
    		pet.setAge(petExist.getAge());
    		pet.setSize(petExist.getSize());
    		pet.setType(petExist.getType());
    		pet.setPersonality(petExist.getPersonality());
    		pet.setColor(petExist.getColor());
    		User user = new User();
    		if(authentication != null) {
        		String username = authentication.getName();
        		user = userServiceImpl.findByUsername(username);
        		session.setAttribute("user", user);
        	}
            pet.setUser(user);
            pet.setStatus("pending");
    	}
    	if(bindingResult.hasErrors()){
            for(ObjectError error: bindingResult.getAllErrors()){
				System.out.println("Error: " + error.getDefaultMessage());
			}
            redirectAttributes.addFlashAttribute("msg_error", "¡Ha ocurrido un error en la actualización!");
            model.addAttribute("colorsList", colorServiceImpl.listAll());
            model.addAttribute("personalitiesList", personalityServiceImpl.listAll());
			return "createPet";
        }
    	Pet petEdited = petServiceImpl.editPet(pet.getId());
        if(petEdited != null){
            redirectAttributes.addFlashAttribute("msg_success", "¡Se ha realizado la actualización correctamente!");
			return "redirect:/misPublicaciones";
        }else{
            redirectAttributes.addFlashAttribute("msg_error", "¡Ha ocurrido un error en la actualizacion!");
            model.addAttribute("colorsList", colorServiceImpl.listAll());
            model.addAttribute("personalitiesList", personalityServiceImpl.listAll());
            return "createPet";
        }

    }
    
    @PostMapping("/removeRequest")
    @Secured("ROLE_ADOPTER")
    public String removeRequest(@RequestParam("petId") long petId,
    		@RequestParam(name = "location", required = true) String location,
    		Authentication authentication, RedirectAttributes redirectAttributes) {
    	
    	
    	System.err.println("This is the id for removal " + petId);
    	
    	
    	requestServiceImpl.removeRequest(petId, authentication.getName());
    	redirectAttributes.addFlashAttribute("msg_success", "Se ha removido la solicitud con exito");
    	
    	return "redirect:/mascotas/" + location;
    }

    @PostMapping("/requestAdoption")
    @Secured("ROLE_ADOPTER")
    public String adopt(@RequestParam(name = "petId", required = true) long id,
    		@RequestParam(name = "location", required = true) String location,
    		Authentication authentication,
    		RedirectAttributes redirectAttributes) {
    	
    	User user = userServiceImpl.findByUsername(authentication.getName());
    	Pet pet = petServiceImpl.editPet(id);
    	
    	Request request = new Request(user, pet);
    	Request response = requestServiceImpl.save(request);
    	
    	if(response.getId() > 0) {
    		redirectAttributes.addFlashAttribute("msg_success", "Solicitud realizada con éxito");    		
    		return "redirect:/mascotas/" + location;
    	}
    	redirectAttributes.addFlashAttribute("msg_error", "Ha ocurrido un error");
    	return "redirect:/mascotas/" + location;
    }
    
    @PostMapping("/endAdoption")
    @Secured("ROLE_VOLUNTEER")
    public String endAdoption(@RequestParam(name = "petId", required = true)long petId,
    		@RequestParam(name = "adopterName", required = true) String adopterName,
    		RedirectAttributes redirectAttributes) {    	
    	
    	requestServiceImpl.endAdoption(petId, adopterName);
    	requestServiceImpl.cancelRequestsOfOtherUsers(petId);
    	petServiceImpl.changePetStatusToAdopted(petId);
    	redirectAttributes.addFlashAttribute("msg_success", "Se ha confirmado la adopcion para el usuario " + adopterName);
    	
    	return "redirect:/misPublicaciones";
    }
    
    @PostMapping("/heartOne")
    @Secured("ROLE_ADOPTER")
    public String heartOne(@RequestParam("petId") long petId, Authentication authentication,
    		@RequestParam("location") String location) {
    	
    	
    	System.err.println("Entra a heartOne");
    	
    	User user = userServiceImpl.findByUsername(authentication.getName());
    	Pet pet = petServiceImpl.editPet(petId);
    	
    	FavoriteOne favoriteOne = new FavoriteOne(user, pet);
    	
    	favoriteOneServiceImpl.save(favoriteOne);
    	
    	return "redirect:/mascotas/" + location;
    }
    
    @PostMapping("/removeHeart")
    @Secured("ROLE_ADOPTER")
    public String removeHeart(@RequestParam("petId") long petId, Authentication authentication,
    		@RequestParam("location") String location) {  	
    	
    	
    	favoriteOneServiceImpl.removeFavoriteOne(petId, authentication.getName());
    	
    	return "redirect:/mascotas/" + location;
    }
    

}
