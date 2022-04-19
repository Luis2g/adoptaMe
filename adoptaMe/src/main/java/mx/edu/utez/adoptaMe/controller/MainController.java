package mx.edu.utez.adoptaMe.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mx.edu.utez.adoptaMe.entity.FavoriteOne;
import mx.edu.utez.adoptaMe.entity.Pet;
import mx.edu.utez.adoptaMe.entity.Request;
import mx.edu.utez.adoptaMe.entity.RequestedPet;
import mx.edu.utez.adoptaMe.entity.User;
import mx.edu.utez.adoptaMe.service.ColorServiceImpl;
import mx.edu.utez.adoptaMe.service.PetServiceImpl;
import mx.edu.utez.adoptaMe.service.PostServiceImpl;
import mx.edu.utez.adoptaMe.service.RequestServiceImpl;
import mx.edu.utez.adoptaMe.service.UserServiceImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@Controller
public class MainController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private PostServiceImpl postServiceImpl;

    @Autowired
    private PetServiceImpl petServiceImpl;

    // Remember to move this to the petController, it's just here to not create
    // github problems
    
    
    @GetMapping("/misSolicitudes")
    @Secured("ROLE_ADOPTER")
    public String myRequests(Authentication authentication, HttpSession session, Model model) {
    	
    	String username = authentication.getName();
        User user = userServiceImpl.findByUsername(username);
        session.setAttribute("user", user);
        
        List<Pet> adopterRequestedPets = petServiceImpl.getAdopterRequests(username);
        
        for(Pet pet: adopterRequestedPets) {
        	for(Request request: pet.getRequests()) {
        		if(request.getUser().getUsername().equals(username)) {

        			System.out.println("This is the request " + request);
        			
        			if(request.getIsAccepted().equals("accepted")) {
        				pet.setStatus("adoptedForYou");
        			}else if(request.getIsAccepted().equals("rejected")) {
            			pet.setStatus("rejectedForYou");
            		}else {
            			pet.setStatus("pending");
            		}
        		}
        	}
        }
        
        model.addAttribute("adopterRequestedPets", adopterRequestedPets);
    	
    	return "/functions/adopter/requestedPets";
    }

    @GetMapping("/misPublicaciones")
    @Secured("ROLE_VOLUNTEER")
    public String postedPets(Authentication authentication, HttpSession session, Model model) {

        String username = authentication.getName();
        User user = userServiceImpl.findByUsername(username);
        session.setAttribute("user", user);

        List<Pet> pets = petServiceImpl.getRequestedPetsForVolunteer(username);
        List<Pet> filteredPets = pets.stream().distinct().collect(Collectors.toList());
        List<RequestedPet> requestedPets = new ArrayList<>();
        List<Pet> pendingPets = petServiceImpl.findByUserAndStatus(user, "pending");
        List<Pet> rejectedPets = petServiceImpl.findByUserAndStatus(user, "rejected");
        List<Pet> acceptedPets = petServiceImpl.findByUserAndStatus(user, "accepted");
        List<Pet> adoptedPets = petServiceImpl.findByUserAndStatus(user, "adopted");
        
        

        for (Pet pet : filteredPets) {

            List<User> usersRequesting = new ArrayList<>();
            for (Request request : pet.getRequests()) {
                User userRequesting = new User();
                userRequesting = userServiceImpl.findByUsername(request.getUser().getUsername());
                usersRequesting.add(userRequesting);
            }

            RequestedPet requestedPet = new RequestedPet(pet, usersRequesting);
            requestedPets.add(requestedPet);
        }

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        for (RequestedPet requestedPet : requestedPets) {
            try {
                String json = ow.writeValueAsString(requestedPet);
                requestedPet.setJsonForFront(json);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        
        List<RequestedPet> adoptedPetsWithOwner = new ArrayList<>();
        for(Pet pet: adoptedPets) {
        	for(Request request: pet.getRequests()) {
        		if(request.getIsAccepted().equals("accepted")) {
        			RequestedPet adoptedPet = new RequestedPet(pet, request.getUser());
        			adoptedPetsWithOwner.add(adoptedPet);
        		}
        	}
        }
        
        model.addAttribute("adoptedPets", adoptedPetsWithOwner);
        model.addAttribute("pendingPets", pendingPets);
        model.addAttribute("rejectedPets", rejectedPets);
        model.addAttribute("acceptedPets", acceptedPets);
        model.addAttribute("requestedPets", requestedPets);
        return "/functions/volunteer/postedPets";
    }

    @GetMapping("/solicitudesParaPublicar")
    @Secured("ROLE_ADMIN")
    public String petPostRequest(Authentication authentication, HttpSession session, Model model) {

        model.addAttribute("petPostRequests", petServiceImpl.findByStatus("pending"));

        if (session.getAttribute("user") == null) {
            User user = userServiceImpl.findByUsername(authentication.getName());
            user.setPassword(null);
            session.setAttribute("user", user);
        }

        return "/functions/admin/petPostRequest";
    }

    @PostMapping("/acceptPet")
    @Secured("ROLE_ADMIN")
    public String acceptPet(@RequestParam(name = "accepted", required = true) boolean accepted,
            @RequestParam(name = "id", required = true) long id,
            RedirectAttributes redirectAttributes, Authentication authentication) {

                Pet petEdit=petServiceImpl.editPet(id);
                petEdit.setStatus(accepted ? "accepted" : "rejected");
                petServiceImpl.modifyPet(petEdit, authentication.getName());        

        redirectAttributes.addFlashAttribute("msg_success",
                accepted ? "La mascota se ha publicado" : "Se ha rechazado la mascota");

        return "redirect:/solicitudesParaPublicar";
    }

    //

    @GetMapping("/inicio")
    public String index(Authentication authentication, HttpSession session, Model model) {
    	
        List<Pet> sortedPets = petServiceImpl.listSortedPets();
        List<Pet> petList = new ArrayList<>();
        List<Pet> secondPetList = new ArrayList<>();
    	
    	User user = new User();
       
    	
    	
    	
    	


        for(int i=0; i<sortedPets.size(); i++){
            System.out.print(sortedPets.get(i).getName());
        }

        if (sortedPets.size() > 3) {
            for (int i = 0; i < 4; i++) {
                if (sortedPets.get(i).getStatus().equals("accepted")) {
                    petList.add(sortedPets.get(i));
                }
            }
            model.addAttribute("petList", petList);
        } else {
            model.addAttribute("petList", sortedPets);
        }

        if (sortedPets.size() > 7) {
            for (int i = 4; i < 8; i++) {
                if (sortedPets.get(i).getStatus().equals("accepted")) {
                    secondPetList.add(sortedPets.get(i));
                }
            }
        } else if (sortedPets.size() > 4) {
            for (int i = 4; i < sortedPets.size(); i++) {
                if (sortedPets.get(i).getStatus().equals("accepted")) {
                    secondPetList.add(sortedPets.get(i));
                }
            }
        }
        
        
        
        if(authentication != null) {
    		String username = authentication.getName();
    		model.addAttribute("usernameFromModel", username);
    		user = userServiceImpl.findByUsername(username);
    		session.setAttribute("user", user);
    		
    		for(Pet petVar: sortedPets) {
        		for(Request request: petVar.getRequests()) {
        			if(request.getUser().getUsername().equals(username)) {
        				petVar.setStatus("requestedByYou");        				
        			}
        		}
        	}
    		
	     	for(Pet thisPet: sortedPets ) {
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
    		for(Pet thisPet: sortedPets ) {
    			thisPet.setType(thisPet.getFavoriteOnes().size() + "");
	     	}
    	}
        
        
        

        model.addAttribute("postList", postServiceImpl.findByIsMain());
        model.addAttribute("secondPetList", secondPetList);
        return "/landingPage";
    }

    @GetMapping("/")
    public String mostrarIndex(Authentication authentication, HttpSession session) {

        if (authentication != null) {
            String username = authentication.getName();
            User user = userServiceImpl.findByUsername(username);
            session.setAttribute("user", user);
        }
        return "redirect:/inicio";
    }

    // @GetMapping("/logout")
    // public String logout() {
    // System.out.println("Entra al metodo de cerrar la sesion");
    // return "redirect:/inicio";
    // }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, RedirectAttributes redirectAttributes) {

        System.out.println("Entra al metodo de cerrar sesion");

        try {
            SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
            logoutHandler.logout(request, null, null);
            redirectAttributes.addFlashAttribute("msg_success", "¡Sesión cerrada! Hasta luego");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg_error",
                    "Ocurrió un error al cerrar la sesión, intenta de nuevo.");
        }
        return "redirect:/inicio";
    }

    @GetMapping("/error400")
    public String error400() {
        return "/errorPages/400";
    }

    @GetMapping("/error401")
    public String error401() {
        return "/errorPages/401";
    }

    @GetMapping("/error403")
    public String error403() {
        return "/errorPages/403";
    }

    @GetMapping("/error404")
    public String error404() {
        return "/errorPages/404";
    }

    @GetMapping("/error500")
    public String error500() {
        return "/errorPages/500";
    }
}
