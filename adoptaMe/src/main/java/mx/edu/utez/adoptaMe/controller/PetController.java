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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mx.edu.utez.adoptaMe.entity.FavoriteOne;
import mx.edu.utez.adoptaMe.entity.Pet;
import mx.edu.utez.adoptaMe.entity.Request;
import mx.edu.utez.adoptaMe.entity.User;
import mx.edu.utez.adoptaMe.service.ColorServiceImpl;
import mx.edu.utez.adoptaMe.service.FavoriteOneServiceImpl;
import mx.edu.utez.adoptaMe.service.PersonalityServiceImpl;
import mx.edu.utez.adoptaMe.service.PetServiceImpl;
import mx.edu.utez.adoptaMe.service.RequestServiceImpl;
import mx.edu.utez.adoptaMe.service.UserServiceImpl;
import mx.edu.utez.adoptaMe.util.ImagenUtileria;

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
	
    
    public String save() {
        String token = "";

        for (int i = 0; i < 16; i++) {
            double numero = Math.random() * 10;
            int parcear = (int) numero;
            token += parcear;
        }

        return token;
    }

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
    		
	     	for(Pet thisPet: filteredPets ) {
	     		int hearts = 0;
	     		thisPet.setType("nada");
	        	for(FavoriteOne favoriteOne: thisPet.getFavoriteOnes()) {
	        		hearts++;
	        		if(thisPet.getStatus().equals("requestedByYou") && favoriteOne.getUser().getUsername().equals(username)) {
	        			thisPet.setStatus("lovedIt");
	        		}else if (favoriteOne.getUser().getUsername().equals(username)){
	        			thisPet.setStatus("heart");
	        		}
	        	}  		        	
	        	thisPet.setType(hearts+"");
	     	}
    	}else {
    		for(Pet thisPet: filteredPets ) {
    			thisPet.setType(thisPet.getFavoriteOnes().size() + "");
	     	}
    	}
        
       
       
        
        pet.setUser(user);
    	model.addAttribute("user", user);
        model.addAttribute("colorsList", colorServiceImpl.listAll());
        model.addAttribute("personalityList", personalityServiceImpl.listAll());
        model.addAttribute("listPets",  filteredPets);
		return "petsList";
	}

    @GetMapping("/filtro")
	public String scopePets(@ModelAttribute("pet") Pet pet, Model model, RedirectAttributes redirectAttributes, Authentication authentication, HttpSession session,
			@RequestParam String text) {
                
    	List<Pet> filteredPets = petServiceImpl.scopePet(text);
    	
    	User user = new User();
        if(authentication != null) {
    		String username = authentication.getName();
    		model.addAttribute("usernameFromModel", username);
    		user = userServiceImpl.findByUsername(username);
    		session.setAttribute("user", user);
    	}
        
        pet.setUser(user);
    	model.addAttribute("user", user);
        model.addAttribute("colorsList", colorServiceImpl.listAll());
        model.addAttribute("personalityList", personalityServiceImpl.listAll());
        model.addAttribute("listPets",  filteredPets);
		return "petsList";
	}
    

    @GetMapping("/registro")
    @Secured("ROLE_VOLUNTEER")
    public String createPet(Model model, Pet pet){
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
    @PostMapping("/editar")
    @Secured("ROLE_VOLUNTEER")
    public String showPetAdministrator(@RequestParam("petId") long id, Model model, RedirectAttributes redirectAttributes) {
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
    @Secured("ROLE_VOLUNTEER")
    public String savePet(Model model, @Valid @ModelAttribute("pet") Pet pet,
	BindingResult bindingResult, RedirectAttributes redirectAttributes, Authentication authentication, HttpSession session,
	@RequestParam(name = "imagenPet", required = false) MultipartFile multipartFile){
        // All pet when is created, the available always is true
    	
    	 String[] blacklist = { ",", ";", "/*", "*/", "@@", "@",
                 "SELECT", "select", "script", "<script", "UPDATE",
                 "update", "DELETE", "delete", "input", "button",
                 "div", "html", "char", "varchar", "nvarchar", "hooks.js",
                 "int", "integer", "String", "sys", "sysobjects",
                 "sysobject", "puto", "puta", "pendejo", "idiota", "estupido",
                 "estúpido", "estupideces", "idioteces", "pendejadas",
                 "encabronarse", "cabron", "cabrón", "chingada", "verga",
                 "pito", "joder", "jodido", "jodete", "imbécil", "imbecil",
                 "culero", "panocha", "fuck", "dick", "asshole", "ass",
                 "bitch", "son of a bitch", "pussy", "nigga", "nigger",
                 "deep throat", "bbc", "cock", "motherfucker", "fucker" };

         String[] blacklist2 = { "@@", "SELECT", "select", "script", "<script", "UPDATE",
                 "update", "DELETE", "delete", "input", "button",
                 "div", "html", "char", "varchar", "nvarchar", "hooks.js",
                 "int", "integer", "String", "sys", "sysobjects",
                 "sysobject", "puto", "puta", "pendejo", "idiota", "estupido",
                 "estúpido", "estupideces", "idioteces", "pendejadas",
                 "encabronarse", "cabron", "cabrón", "chingada", "verga",
                 "pito", "joder", "jodido", "jodete", "imbécil", "imbecil",
                 "culero", "panocha", "fuck", "dick", "asshole", "ass",
                 "bitch", "son of a bitch", "pussy", "nigga", "nigger",
                 "deep throat", "bbc", "cock", "motherfucker", "fucker" };
    	
        User user = null;
        if(pet.getId() == null) {
    		System.out.println("Entro a registrar");
    		
    		for (int i = 0; i < blacklist.length; i++) {
                if (pet.getName().toLowerCase().contains(blacklist[i].toLowerCase())) {
                    redirectAttributes.addFlashAttribute("msg_error", "¡Ha ocurrido un error en el registro!");
                    return "redirect:/misPublicaciones";
                }
            }

            for (int i = 0; i < blacklist2.length; i++) {
                if (pet.getDescription().toLowerCase().contains(blacklist2[i].toLowerCase())) {
                    redirectAttributes.addFlashAttribute("msg_error", "¡Ha ocurrido un error en el registro!");
                    return "redirect:/misPublicaciones";
                }
            }
            
            for (int i = 0; i < blacklist.length; i++) {
                if (String.valueOf(pet.getAge()).toLowerCase().contains(blacklist[i].toLowerCase())) {
                    redirectAttributes.addFlashAttribute("msg_error", "¡Ha ocurrido un error en el registro!");
                    return "redirect:/misPublicaciones";
                }
            }
            
            for (int i = 0; i < blacklist.length; i++) {
                if (pet.getUnitAge().toLowerCase().contains(blacklist[i].toLowerCase())) {
                    redirectAttributes.addFlashAttribute("msg_error", "¡Ha ocurrido un error en el registro!");
                    return "redirect:/misPublicaciones";
                }
            }
            
            for (int i = 0; i < blacklist.length; i++) {
                if (pet.getSex().toLowerCase().contains(blacklist[i].toLowerCase())) {
                    redirectAttributes.addFlashAttribute("msg_error", "¡Ha ocurrido un error en el registro!");
                    return "redirect:/misPublicaciones";
                }
            }
            
            for (int i = 0; i < blacklist.length; i++) {
                if (pet.getSize().toLowerCase().contains(blacklist[i].toLowerCase())) {
                    redirectAttributes.addFlashAttribute("msg_error", "¡Ha ocurrido un error en el registro!");
                    return "redirect:/misPublicaciones";
                }
            }
            
            for (int i = 0; i < blacklist.length; i++) {
                if (pet.getType().toLowerCase().contains(blacklist[i].toLowerCase())) {
                    redirectAttributes.addFlashAttribute("msg_error", "¡Ha ocurrido un error en el registro!");
                    return "redirect:/misPublicaciones";
                }
            }
            
            for (int i = 0; i < blacklist.length; i++) {
                if (pet.getUnitAge().toLowerCase().contains(blacklist[i].toLowerCase())) {
                    redirectAttributes.addFlashAttribute("msg_error", "¡Ha ocurrido un error en el registro!");
                    return "redirect:/misPublicaciones";
                }
            }
            
            for (int i = 0; i < blacklist2.length; i++) {
                if (String.valueOf(pet.getColor().getId()).toLowerCase().contains(blacklist2[i].toLowerCase())) {
                    redirectAttributes.addFlashAttribute("msg_error", "¡Ha ocurrido un error en el registro!");
                    return "redirect:/misPublicaciones";
                }
            }
            
            for (int i = 0; i < blacklist2.length; i++) {
                if (String.valueOf(pet.getPersonality().getId()).toLowerCase().contains(blacklist2[i].toLowerCase())) {
                    redirectAttributes.addFlashAttribute("msg_error", "¡Ha ocurrido un error en el registro!");
                    return "redirect:/misPublicaciones";
                }
            }
    		
    		
         // Configuration for user before controller session is created
            user = new User();
            String username = "";
            if(authentication != null) {
        		username = authentication.getName();
        		user = userServiceImpl.findByUsername(username);
        		session.setAttribute("user", user);
        	}
            pet.setUser(user);
            // End configuration

            if(bindingResult.hasErrors()){
                for(ObjectError error: bindingResult.getAllErrors()){
    				System.out.println("Error: " + error.getDefaultMessage());
    			}
                redirectAttributes.addFlashAttribute("msg_error", "¡Ha ocurrido un error en el registro!");
                model.addAttribute("colorsList", colorServiceImpl.listAll());
                model.addAttribute("personalitiesList", personalityServiceImpl.listAll());
    			return "/createPet";
            }else {
            	boolean response = false;
            	if(multipartFile != null && !multipartFile.isEmpty()) {
            		String generatedToken = save();
                	try {
                		pet.setImage(generatedToken);
                		pet.setStatus("pending");
                		response = petServiceImpl.savePet(pet, user.getUsername())!=null?true:false;
                	}catch(Exception ex) {
                		ex.printStackTrace();
                	}
                	
                	String ruta = "C:/mascotas/img-pet";
                    ImagenUtileria.guardarImagen(multipartFile, ruta, generatedToken);
            	}else {
                	try {
                		pet.setImage("logo.png");
                		pet.setStatus("pending");
                		response = petServiceImpl.savePet(pet, user.getUsername())!=null?true:false;
                	}catch(Exception ex) {
                		ex.printStackTrace();
                	}
            	}
            	


                if(response){
                    redirectAttributes.addFlashAttribute("msg_success", "¡La mascota se ha guardado correctamente y se le presentará a el administrador quien decidira si es apta para ser publicada, mientras tanto se encuentra en estado pendiente!");
        			return "redirect:/misPublicaciones";
                }else{
                    redirectAttributes.addFlashAttribute("msg_error", "¡Ha ocurrido un error en el registro!");
                    model.addAttribute("colorsList", colorServiceImpl.listAll());
                    model.addAttribute("personalitiesList", personalityServiceImpl.listAll());
                    return "/createPet";
                }
            }
    	}else {
    		
    		for (int i = 0; i < blacklist.length; i++) {
                if (pet.getName().toLowerCase().contains(blacklist[i].toLowerCase())) {
                    redirectAttributes.addFlashAttribute("msg_error", "¡Ha ocurrido un error en la actualización!");
                    return "redirect:/misPublicaciones";
                }
            }

            for (int i = 0; i < blacklist2.length; i++) {
                if (pet.getDescription().toLowerCase().contains(blacklist2[i].toLowerCase())) {
                    redirectAttributes.addFlashAttribute("msg_error", "¡Ha ocurrido un error en la actualización!");
                    return "redirect:/misPublicaciones";
                }
            }
            
            for (int i = 0; i < blacklist.length; i++) {
                if (String.valueOf(pet.getAge()).toLowerCase().contains(blacklist[i].toLowerCase())) {
                    redirectAttributes.addFlashAttribute("msg_error", "¡Ha ocurrido un error en la actualización!");
                    return "redirect:/misPublicaciones";
                }
            }
            
            for (int i = 0; i < blacklist.length; i++) {
                if (pet.getUnitAge().toLowerCase().contains(blacklist[i].toLowerCase())) {
                    redirectAttributes.addFlashAttribute("msg_error", "¡Ha ocurrido un error en la actualización!");
                    return "redirect:/misPublicaciones";
                }
            }
            
            for (int i = 0; i < blacklist.length; i++) {
                if (pet.getSex().toLowerCase().contains(blacklist[i].toLowerCase())) {
                    redirectAttributes.addFlashAttribute("msg_error", "¡Ha ocurrido un error en la actualización!");
                    return "redirect:/misPublicaciones";
                }
            }
            
            for (int i = 0; i < blacklist.length; i++) {
                if (pet.getSize().toLowerCase().contains(blacklist[i].toLowerCase())) {
                    redirectAttributes.addFlashAttribute("msg_error", "¡Ha ocurrido un error en la actualización!");
                    return "redirect:/misPublicaciones";
                }
            }
            
            for (int i = 0; i < blacklist.length; i++) {
                if (pet.getType().toLowerCase().contains(blacklist[i].toLowerCase())) {
                    redirectAttributes.addFlashAttribute("msg_error", "¡Ha ocurrido un error en la actualización!");
                    return "redirect:/misPublicaciones";
                }
            }
            
            for (int i = 0; i < blacklist.length; i++) {
                if (pet.getUnitAge().toLowerCase().contains(blacklist[i].toLowerCase())) {
                    redirectAttributes.addFlashAttribute("msg_error", "¡Ha ocurrido un error en la actualización!");
                    return "redirect:/misPublicaciones";
                }
            }
            
            for (int i = 0; i < blacklist2.length; i++) {
                if (String.valueOf(pet.getColor().getId()).toLowerCase().contains(blacklist2[i].toLowerCase())) {
                    redirectAttributes.addFlashAttribute("msg_error", "¡Ha ocurrido un error en la actualización!");
                    return "redirect:/misPublicaciones";
                }
            }
            
            for (int i = 0; i < blacklist2.length; i++) {
                if (String.valueOf(pet.getPersonality().getId()).toLowerCase().contains(blacklist2[i].toLowerCase())) {
                    redirectAttributes.addFlashAttribute("msg_error", "¡Ha ocurrido un error en la actualización!");
                    return "redirect:/misPublicaciones";
                }
            }
    		
    		
    		Pet petExist = petServiceImpl.editPet(pet.getId());
    		petExist.setName(pet.getName());
    		petExist.setDescription(pet.getDescription());
    		petExist.setSex(pet.getSex());
    		petExist.setAge(pet.getAge());
    		petExist.setUnitAge(pet.getUnitAge());
    		petExist.setSize(pet.getSize());
    		petExist.setType(pet.getType());
    		petExist.setPersonality(pet.getPersonality());
    		petExist.setColor(pet.getColor());
    		user = new User();
    		if(authentication != null) {
        		String username = authentication.getName();
        		user = userServiceImpl.findByUsername(username);
        		session.setAttribute("user", user);
        	}
            petExist.setUser(user);
            
            if(bindingResult.hasErrors()){
                for(ObjectError error: bindingResult.getAllErrors()){
    				System.out.println("Error: " + error.getDefaultMessage());
    			}
                redirectAttributes.addFlashAttribute("msg_error", "¡Ha ocurrido un error en la actualización!");
    			return "redirect:/misPublicaciones";
            }else {
            	boolean response = false;
            	if(multipartFile != null && !multipartFile.isEmpty()) {
            		String token = save();
            		try {
            			petExist.setImage(token);
                		//response = petServiceImpl.updatePet(petExist, petExist.getId(), petExist.getUser().getUsername());
            			if(petExist.getStatus().equals("rejected")) {
            				petExist.setStatus("pending");
            			}
                		response = petServiceImpl.modifyPet(petExist, user.getUsername()) != null ? true:false;
            		}catch (Exception e) {
            			e.printStackTrace();
    				}
                    String ruta = "C:/mascotas/img-pet";
                    ImagenUtileria.guardarImagen(multipartFile, ruta, token);
            	}else {
            		if(petExist.getStatus().equals("rejected")) {
        				petExist.setStatus("pending");
        			}
            		//response = petServiceImpl.updatePet(petExist, petExist.getId(), petExist.getUser().getUsername());
            		response = petServiceImpl.modifyPet(petExist, user.getUsername()) != null ? true:false;
            	}
            	System.out.println(response);
                if(response){
                    redirectAttributes.addFlashAttribute("msg_success", "¡Se ha realizado la actualización correctamente!");
        			return "redirect:/misPublicaciones";
                }else{
                    redirectAttributes.addFlashAttribute("msg_error", "¡Ha ocurrido un error en la actualizacion!");
                    model.addAttribute("colorsList", colorServiceImpl.listAll());
                    model.addAttribute("personalitiesList", personalityServiceImpl.listAll());
                    return "redirect:/misPublicaciones";
                }
            }
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
    	
    	if(location.equals("")){
            return "redirect:/misSolicitudes";
        } else {
            return "redirect:/mascotas/" + location;
        }
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
    	Request response = requestServiceImpl.saveRequest(request,authentication.getName());
    	
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
    		RedirectAttributes redirectAttributes, Authentication authentication) {    	
    	
        requestServiceImpl.endAdoption(petId, adopterName);
    	requestServiceImpl.cancelRequestsOfOtherUsers(petId);
    	petServiceImpl.changePetStatusToAdopted(petId);
    	redirectAttributes.addFlashAttribute("msg_success", "Se ha confirmado la adopcion para el usuario " + adopterName);
    	
    	return "redirect:/misPublicaciones";
    }
    
    @PostMapping("/heartOne")
    public String heartOne(@RequestParam("petId") long petId, Authentication authentication,
    		@RequestParam("location") String location) {
    	
    	
    	System.err.println("Entra a heartOne");
    	
    	User user = userServiceImpl.findByUsername(authentication.getName());
    	Pet pet = petServiceImpl.editPet(petId);
    	
    	FavoriteOne favoriteOne = new FavoriteOne(user, pet);
    	
    	favoriteOneServiceImpl.save(favoriteOne);
    	System.err.println(location);
        if(location.equals("")){
            System.err.println("Entra a inicio");
            return "redirect:/inicio";
        } else {
            System.err.println("Entra a location");
            return "redirect:/mascotas/" + location;
        }
    }
    
    @PostMapping("/removeHeart")
    public String removeHeart(@RequestParam("petId") long petId, Authentication authentication,
    		@RequestParam("location") String location) {  	
    	
    	
    	favoriteOneServiceImpl.removeFavoriteOne(petId, authentication.getName());

        if(location.equals("")){
            return "redirect:/inicio";
        } else {
            return "redirect:/mascotas/" + location;
        }
    }
    

}
