package mx.edu.utez.adoptaMe.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
    
    @GetMapping("/inicio")
    public String index(Model model) {
        model.addAttribute("postList", postServiceImpl.listAll());
        return "/landingPage";
    }
    
    @GetMapping("/logout")
    public String logout() {
    	System.out.println("Entra al metodo de cerrar la sesion");
    	return "redirect:/inicio";
    }
    
    @GetMapping("/")
	public String mostrarIndex(Authentication authentication, HttpSession session) {
    	
    	if(authentication != null) {
    		String username = authentication.getName();
    		System.out.println("Username: " + username);
    		for(GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
    			System.out.println("Role: " + grantedAuthority.getAuthority());
    		}
    		User user = userServiceImpl.findByUsername(username);
    		//Add data user session
    		System.out.println("Nombre: " + user.getPerson().getName());
    		
    		session.setAttribute("user", user);
    		
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

    @GetMapping("/mascotas")
    public String dogs(Model model) {
    	user =  Session.getSession();
    	model.addAttribute("user", user);
        model.addAttribute("colorsList", colorServiceImpl.listAll());
        return "petsList";
    }    

}
