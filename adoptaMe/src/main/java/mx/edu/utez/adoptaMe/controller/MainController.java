package mx.edu.utez.adoptaMe.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mx.edu.utez.adoptaMe.entity.User;
import mx.edu.utez.adoptaMe.helpers.Session;
import mx.edu.utez.adoptaMe.service.ColorServiceImpl;
import mx.edu.utez.adoptaMe.service.PostServiceImpl;
import mx.edu.utez.adoptaMe.service.UserServiceImpl;

@Controller
public class MainController {

    @Autowired
    private ColorServiceImpl colorServiceImpl;
    
    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private PostServiceImpl postServiceImpl;
    
    private User user;
    
    // Remember to move this to the petController, it's just here to not create github problems 
    
    @GetMapping("/misPublicaciones")
    @Secured("ROLE_VOLUNTEER")
    public String postedPets(Authentication authentication, HttpSession session) {
    	
    	if(authentication != null) {
    		String username = authentication.getName();
    		User user = userServiceImpl.findByUsername(username);
    		session.setAttribute("user", user);
    	}
    	return "/functions/volunteer/postedPets";
    }
    
    @GetMapping("/solicitudesParaPublicar")
    @Secured("ROLE_ADMIN")
    public String petPostRequest(Authentication authentication, HttpSession session) {
    	if (session.getAttribute("user") == null) {
            User user = userServiceImpl.findByUsername(authentication.getName());
            user.setPassword(null);
            session.setAttribute("user", user);
        }
    	return "/functions/admin/petPostRequest";
    }    
    //
    
    @GetMapping("/inicio")
    public String index(Authentication authentication, HttpSession session, Model model) {
    	if(authentication != null) {
    		String username = authentication.getName();
    		User user = userServiceImpl.findByUsername(username);
    		session.setAttribute("user", user);
    	}
        model.addAttribute("postList", postServiceImpl.listAll());
        return "/landingPage";
    }
    
    @GetMapping("/")
	public String mostrarIndex(Authentication authentication, HttpSession session) {
    	
    	if(authentication != null) {
    		String username = authentication.getName();
    		User user = userServiceImpl.findByUsername(username);
    		session.setAttribute("user", user);
    	}
		return "redirect:/inicio";
	}
    
    
//    @GetMapping("/logout")
//    public String logout() {
//    	System.out.println("Entra al metodo de cerrar la sesion");
//    	return "redirect:/inicio";
//    }
    
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, RedirectAttributes redirectAttributes) {
    	
    		System.out.println("Entra al metodo de cerrar sesion");
    	
           try {
               SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
               logoutHandler.logout(request, null, null);
               redirectAttributes.addFlashAttribute("msg_success", "¡Sesión cerrada! Hasta luego");
           } catch (Exception e) {
               redirectAttributes.addFlashAttribute("msg_error", "Ocurrió un error al cerrar la sesión, intenta de nuevo.");
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
    public String error500(){
        return "/errorPages/500";
    }
}
