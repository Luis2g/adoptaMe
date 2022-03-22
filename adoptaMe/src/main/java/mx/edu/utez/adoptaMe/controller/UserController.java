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
import mx.edu.utez.adoptaMe.entity.UserHasRole;
import mx.edu.utez.adoptaMe.entity.Role;
import mx.edu.utez.adoptaMe.helpers.Encrypt;
import mx.edu.utez.adoptaMe.helpers.Session;
import mx.edu.utez.adoptaMe.service.UserServiceImpl;
import mx.edu.utez.adoptaMe.service.UserHasRoleServiceImpl;

@Controller 
@RequestMapping("/usuarios")
public class UserController {

	User user;
	
	@Autowired
	private UserServiceImpl userServiceImpl;

	@Autowired
	private UserHasRoleServiceImpl userHasRoleServiceImpl;
	
	@GetMapping("/registro")
	public String register(User user) {
		return "registro";
	}
	@GetMapping("/perfil2")
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
	
	@GetMapping( value = {"/perfil", "/perfil/{option}"})
	public String profile(@PathVariable(required = false) String option, Model model) {
		
		String highlighted;
		
		if(option != null) {
			highlighted = option;
			model.addAttribute("highlighted", highlighted);
		}
				
		user =  Session.getSession();
    	model.addAttribute("user", user);
		return "accountOptions";
	}

	@PostMapping("/iniciarSesion")
	public String login(@RequestParam("username") String username, 
		@RequestParam("password") String password,
		RedirectAttributes redirectAttributes){

		password = new Encrypt().encrypt(password);

	    
		User user = userServiceImpl.login(username, password);
		System.out.println(user);
		
		Role role = new Role();
		role.setId(user.getUserHasRoles().get(0).getRole().getId());
		
		System.out.println(role);
		
		if(user != null && user.getUserId() != 2){
			
			Session.setSession(user);
			
			redirectAttributes.addFlashAttribute("msg_success", "¡Bienvenido!");
			return "redirect:/";
		}
		redirectAttributes.addFlashAttribute("msg_error", "¡Usuario y/o contraseña incorrecta!");
		return "redirect:/usuarios/acceso";
	}

	@PostMapping("/guardar")
	public String save(@Valid @ModelAttribute("user") User user, BindingResult result, 
						Model model, RedirectAttributes redirectAttributes, 
						@RequestParam(name = "rol", required = false) String rol,
						@RequestParam(name = "confirmation", required = true) String confirmation){

		System.out.println("This is the confirmation: " + confirmation);

		if(result.hasErrors()){
			
			
			for(ObjectError error: result.getAllErrors()){
				System.out.println("Error" + error.getDefaultMessage());
			}
			return "registro";
		}
		if(!user.getPassword().equals(confirmation)){
			redirectAttributes.addFlashAttribute("msg_error", "¡Las contraseñas no coinciden, por favor verifique!");
			return "registro";
		}
		//Encrypting the password
		user.setPassword(new Encrypt().encrypt(user.getPassword()));
		//Transforming int to long type
		long role = Integer.parseInt(rol);
		//Creating the necessary objects for UserHasRole
		User saved = userServiceImpl.save(user);
		Role roleObj = new Role(role);
		//Initializing the userHasRole
		UserHasRole userHasRole = new UserHasRole(saved, roleObj);
		//Creating the userHasRole so the user has a role
		userHasRoleServiceImpl.save(userHasRole);


		if(saved != null){
			redirectAttributes.addFlashAttribute("msg_success", "¡Se ha realizado el registro correctamente, por favor inicie sesión!");
			return "redirect:/usuarios/acceso";
		}
		redirectAttributes.addFlashAttribute("msg_error", "¡Ha ocurrido un error en el registro!");
		return "registro";

	}
}
