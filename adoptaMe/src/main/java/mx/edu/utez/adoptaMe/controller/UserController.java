package mx.edu.utez.adoptaMe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import mx.edu.utez.adoptaMe.entity.User;

@Controller 
@RequestMapping(("/usuario"))
public class UserController {

	@GetMapping("/Registro")
	public String register(User user) {
		return "registro";
	}
}
