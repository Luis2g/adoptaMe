package mx.edu.utez.adoptaMe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    
    @GetMapping("/")
    public String index(){
        return "landingPage";
    }

    @GetMapping("/error401")
    public String error(){
        return "/errorPages/401";
    }

    @GetMapping("/perros")
    public String dogs(){
        return "petsList";
    }

    @GetMapping("/noticias")
    public String news(){
        return "news";
    }

}
