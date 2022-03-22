package mx.edu.utez.adoptaMe.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mx.edu.utez.adoptaMe.entity.Post;
import mx.edu.utez.adoptaMe.entity.User;
import mx.edu.utez.adoptaMe.helpers.Session;
import mx.edu.utez.adoptaMe.service.PostServiceImpl;

@Controller
public class PostController {
    
    @Autowired
    private PostServiceImpl postServiceImpl;
    
    private User user;

    @GetMapping("/modals")
    public String modals(Post post){

        return "inicioModals";
    }

    @PostMapping("/savePost")
    public String savePet(@Valid @ModelAttribute("post") Post post, BindingResult result, Model model, RedirectAttributes redirectAttributes){    
        User user = new User();             
        user.setUserId(Long.valueOf(1));
        post.setUser(user);    

        if(result.hasErrors()){
            for(ObjectError error: result.getAllErrors()){
				System.out.println("Error" + error.getDefaultMessage());
			}
            return "Error";
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
    public String news(Model model) {
    	user =  Session.getSession();
    	model.addAttribute("user", user);
        return "news";
    }

}
