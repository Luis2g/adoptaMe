package mx.edu.utez.adoptaMe.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mx.edu.utez.adoptaMe.entity.User;
import mx.edu.utez.adoptaMe.helpers.Encrypt;
import mx.edu.utez.adoptaMe.service.UserServiceImpl;

@Controller 
@RequestMapping("/usuarios")
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
	public String save(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, RedirectAttributes redirectAttributes, @RequestParam(name = "rol", required=false) String rol){

		System.out.println(rol);

		if(result.hasErrors()){
			for(ObjectError error: result.getAllErrors()){
				System.out.println("Error" + error.getDefaultMessage());
			}
			return "registro";
		}


		user.setPassword(new Encrypt().encrypt(user.getPassword()));

		User saved = userServiceImpl.save(user);
		if(saved != null){
			redirectAttributes.addFlashAttribute("msg_success", "¡Se ha realizado el registro correctamente, por favor inicie sesión!");
			return "redirect:/usuarios/acceso";
		}
		redirectAttributes.addFlashAttribute("msg_error", "¡Ha ocurrido un error en el registro!");
		return "registro";

	}
}
