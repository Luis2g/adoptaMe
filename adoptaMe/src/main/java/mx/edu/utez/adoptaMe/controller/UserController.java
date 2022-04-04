package mx.edu.utez.adoptaMe.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
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

import mx.edu.utez.adoptaMe.entity.Role;
import mx.edu.utez.adoptaMe.entity.User;
import mx.edu.utez.adoptaMe.helpers.Encrypt;
import mx.edu.utez.adoptaMe.helpers.Session;
import mx.edu.utez.adoptaMe.service.UserServiceImpl;
import mx.edu.utez.adoptaMe.service.RoleServiceImpl;

@Controller 
@RequestMapping("/usuarios")
public class UserController {

	User user;
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Autowired
	private RoleServiceImpl roleServiceImpl;
	
	@Autowired
    PasswordEncoder passwordEncoder;

	
	@GetMapping("/registro")
	public String register(User user, Model model) {
		model.addAttribute("rolesList", roleServiceImpl.listAll());
		return "registro";
	}

	@GetMapping("/restablecerContrasena")
	public String resetPassword(User user) {
		return "restablecerContrasena";
	}
	
	@GetMapping( value = {"/perfil", "/perfil/{option}"})
	public String profile(@PathVariable(required = false) String option, Model model, Authentication authentication) {
		
		String highlighted;
		
		if(option != null) {
			highlighted = option;
			model.addAttribute("highlighted", highlighted);
			if(highlighted.equals("miCuenta")) {
				User user = userServiceImpl.findByUsername(authentication.getName());
				user.setPassword(null);
				model.addAttribute("user", user);
	    	}
		}
				
    	
		return "accountOptions";
	}

	@PostMapping("/iniciarSesion")
	public String login(@RequestParam("username") String username, 
		@RequestParam("password") String password,
		RedirectAttributes redirectAttributes){

		password = new Encrypt().encrypt(password);
	    
		User user = userServiceImpl.login(username, password);
		System.out.println(user);
		redirectAttributes.addFlashAttribute("msg_error", "¡Usuario y/o contraseña incorrecta!");
		return "redirect:/usuarios/acceso";
	}

	@PostMapping("/guardar")
	public String save(@Valid @ModelAttribute("user") User user, BindingResult result, 
						Model model, RedirectAttributes redirectAttributes, 
						@RequestParam(name = "confirmation", required = true) String confirmation){
		
		System.out.println("This is the user " + user);

		System.out.println("This is the confirmation: " + confirmation);
		if(!user.getPassword().equals(confirmation)){
			redirectAttributes.addFlashAttribute("msg_error", "¡Las contraseñas no coinciden, por favor verifique!");
			model.addAttribute("rolesList", roleServiceImpl.listAll());
			return "registro";
		}
		user.setEnabled(true);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		System.out.println("This is the user" + user);
		User saved = userServiceImpl.save(user);

		if(saved != null){
			redirectAttributes.addFlashAttribute("msg_success", "¡Se ha realizado el registro correctamente, por favor inicie sesión!");
			return "redirect:/usuarios/acceso";
		}
		redirectAttributes.addFlashAttribute("msg_error", "¡Ha ocurrido un error en el registro!");
		model.addAttribute("rolesList", roleServiceImpl.listAll());
		return "registro";

	}
	
	@PostMapping("/actualizar")
	public String update(@ModelAttribute("user") User user) {
		userServiceImpl.save(user);
		return "redirect:/usuarios/perfil/miCuenta";
	}
}
