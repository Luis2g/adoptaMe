package mx.edu.utez.adoptaMe.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mx.edu.utez.adoptaMe.entity.Post;
import mx.edu.utez.adoptaMe.service.PostServiceImpl;

@Controller
public class PostController {
    
    @Autowired
    private PostServiceImpl postServiceImpl;

    @PostMapping("/savePost")
    public String savePet(Post post, Model model, RedirectAttributes redirectAttributes){
        boolean response = postServiceImpl.save(post);
        if(response){
			// redirectAttributes.addFlashAttribute("msg_success", "¡Registro exitoso!");
			return "/mascotas/petsList";
		} else {
			// redirectAttributes.addFlashAttribute("msg_error", "¡Registro fallido!");
			return "/mascotas/createPet";
		}
    }

}
