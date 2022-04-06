package mx.edu.utez.adoptaMe.controller;


import javax.validation.Valid;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.core.Authentication;

import mx.edu.utez.adoptaMe.entity.Post;
import mx.edu.utez.adoptaMe.entity.User;
import mx.edu.utez.adoptaMe.service.PostServiceImpl;

import mx.edu.utez.adoptaMe.service.UserServiceImpl;

@Controller
public class PostController {
    
    @Autowired
    private PostServiceImpl postServiceImpl;

    
    @Autowired
    private UserServiceImpl userServiceImpl;
    

    @GetMapping("/modals")
    public String modals(Post post){

        return "inicioModals";
    }

    @PostMapping("/savePost")
    public String savePet(@Valid @ModelAttribute("post") Post post, BindingResult result, Model model, RedirectAttributes redirectAttributes,Authentication authentication, HttpSession session){            
        String username = authentication.getName();

        User user = userServiceImpl.findByUsername(username);

        session.setAttribute("user", user);

        post.setUser(user);

        if(result.hasErrors()){
            for(ObjectError error: result.getAllErrors()){
				System.out.println("Error" + error.getDefaultMessage());
			}
            redirectAttributes.addFlashAttribute("msg_error", "¡Ha ocurrido un error en el registro!");
            return "redirect:/modals";
        }else{
            boolean response = postServiceImpl.save(post); 
            if(response){
                redirectAttributes.addFlashAttribute("msg_success", "¡Se ha realizado el registro correctamente!");
                return "redirect:/noticias";
            }else{
                redirectAttributes.addFlashAttribute("msg_error", "¡Ha ocurrido un error en el registro!");
                return "redirect:/modals";
            }
        }        
    }
    

    @GetMapping("/noticias")
    public String news(Model model,Post post) {

        model.addAttribute("postList", postServiceImpl.listAll());    	

        return "news";
    }


    

}
