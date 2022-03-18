package mx.edu.utez.adoptaMe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import mx.edu.utez.adoptaMe.service.ColorServiceImpl;

@Controller
public class MainController {

    @Autowired
    private ColorServiceImpl colorServiceImpl;
    
    @GetMapping("/")
    public String index(){
        return "landingPage";
    }

    @GetMapping("/error401")
    public String error(){
        return "/errorPages/401";
    }

    @GetMapping("/perros")
    public String dogs(Model model){
        model.addAttribute("colorsList", colorServiceImpl.listAll());
        return "petsList";
    }

    @GetMapping("/noticias")
    public String news(){
        return "news";
    }

}
