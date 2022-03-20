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
    public String index() {
        return "landingPage";
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

    @GetMapping("/perros")
    public String dogs(Model model) {
        model.addAttribute("colorsList", colorServiceImpl.listAll());
        return "petsList";
    }    

}
