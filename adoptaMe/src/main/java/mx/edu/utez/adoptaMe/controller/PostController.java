package mx.edu.utez.adoptaMe.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mx.edu.utez.adoptaMe.entity.Post;
import mx.edu.utez.adoptaMe.service.PostServiceImpl;

@Controller
public class PostController {
    
    @Autowired
    private PostServiceImpl postServiceImpl;

    @GetMapping("/modals")
    public String modals(Post post){

        return "inicioModals";
    }

    @PostMapping("/savePost")
    public String savePet(@ModelAttribute("post") Post post, Model model, RedirectAttributes redirectAttributes){
        boolean response = postServiceImpl.save(post);        
        if(response){
            redirectAttributes.addFlashAttribute("msg_success", "Registro exitoso");
            return "redirect:/noticias";
        }else{
            return "redirect:/inicioModals";
        }
    }

}
