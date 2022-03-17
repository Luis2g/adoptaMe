package mx.edu.utez.adoptaMe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import mx.edu.utez.adoptaMe.entity.User;

@Controller 
@RequestMapping(("/usuario"))
public class UserController {

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
}
