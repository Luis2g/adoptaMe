package mx.edu.utez.adoptaMe.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import mx.edu.utez.adoptaMe.entity.User;
import mx.edu.utez.adoptaMe.service.UserServiceImpl;

@Controller 
@RequestMapping(("/usuario"))
public class UserController {

	@Autowired
	private UserServiceImpl userServiceImpl;

	@GetMapping("/registro")
	public String register(User user) {
		return "registro";
	}
	@GetMapping("/perfil")
	public String profile(User user) {
		return "perfilUsuario";
	}
	@GetMapping("/acceso")
	public String login(User user) {
		return "login";
	}
	@GetMapping("/restablecerContrasena")
	public String resetPassword(User user) {
		return "restablecerContrasena";
	}

	@PostMapping("/guardar")
	public String save(User user, Model model){

		System.out.println(user);

		User saved = userServiceImpl.save(user);
		if(saved != null){
			return "login";
		}
		return "registro";

	}
}
