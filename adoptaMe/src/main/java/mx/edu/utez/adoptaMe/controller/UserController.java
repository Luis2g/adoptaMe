package mx.edu.utez.adoptaMe.controller;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mx.edu.utez.adoptaMe.entity.Role;
import mx.edu.utez.adoptaMe.entity.User;
import mx.edu.utez.adoptaMe.helpers.Session;
import mx.edu.utez.adoptaMe.service.UserServiceImpl;
import mx.edu.utez.adoptaMe.service.AccessServiceImpl;
import mx.edu.utez.adoptaMe.service.RoleServiceImpl;

@Controller 
@RequestMapping("/usuarios")
public class UserController {
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Autowired
	private RoleServiceImpl roleServiceImpl;
	
	@Autowired
	private AccessServiceImpl accessServiceImpl;
	
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
	public String profile(@PathVariable(required = false) String option, Model model, Authentication authentication, HttpSession session) {
		
		if(authentication != null) {
    		String username = authentication.getName();
    		User user = userServiceImpl.findByUsername(username);
    		session.setAttribute("user", user);
    	}
		
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
	
//	@PostMapping("/actualizar")
//	public String update(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {
//		user.setPassword(passwordEncoder.encode(user.getPassword()));
//		if(userServiceImpl.save(user).getUsername() != null) {
//			redirectAttributes.addFlashAttribute("msg_success", "¡Se han guardado correctamente los datos!");
//		}else {
//			redirectAttributes.addFlashAttribute("msg_error", "¡Ha ocurrido un error por favor verifique los datos proporcionados!");
//		}
//		return "redirect:/usuarios/perfil/miCuenta";
//	}
	
	@PostMapping("/updateUserData")
	public String changeUserData (@ModelAttribute("user") User user, 
			@RequestParam(name = "password", required = true) String passwordIn,
			RedirectAttributes redirectAttributes,
			Authentication authentication) {
		
		System.out.println("This is the password from the front " + passwordIn);		
		User userFromDB = userServiceImpl.findByUsername(authentication.getName());
		
		if(passwordEncoder.matches(passwordIn, userFromDB.getPassword())) {
			userFromDB.getPerson().setName(user.getPerson().getName());
			userFromDB.getPerson().setSurname(user.getPerson().getSurname());
			userFromDB.getPerson().setSecondSurname(user.getPerson().getSecondSurname());
			userFromDB.getPerson().setPhoneNumber(user.getPerson().getPhoneNumber());
			userFromDB.setEmail(user.getEmail());
			
			userServiceImpl.save(userFromDB);
			
			redirectAttributes.addFlashAttribute("msg_success", "¡Los datos han sido guardados exitosamente!");
			return "redirect:/usuarios/perfil/miCuenta";
		}
		redirectAttributes.addFlashAttribute("msg_error", "¡La contraseña proporcionada no es valida!");		
		return "redirect:/usuarios/perfil/miCuenta";
		
	}
	
	@PostMapping("/changePassword")
	public String changePassword(@RequestParam(name = "oldPassword", required = true) String oldPassword, 
			@RequestParam(name = "newPassword", required = true) String newPassword,
			@RequestParam(name = "confirmationPassword", required = true) String confirmationPassword,
			Authentication authentication,
			RedirectAttributes redirectAttributes) {
				
		User userFromDB = userServiceImpl.findByUsername(authentication.getName());
		
		if(passwordEncoder.matches(oldPassword, userFromDB.getPassword())) {
			userFromDB.setPassword(passwordEncoder.encode(newPassword));
			userServiceImpl.save(userFromDB);
			
			redirectAttributes.addFlashAttribute("msg_success", "¡Se ha cambiado la contraseña!");
			
			return "redirect:/logout";
		}
		
		redirectAttributes.addFlashAttribute("msg_error", "¡La contraseña actual no coincide!, por favor verifique");
		return "redirect:/usuarios/perfil/miCuenta";		
	}
	
	@GetMapping("/accesos")
	public String accesses(Authentication authentication, HttpSession session, Model model) {
		
		if(authentication != null) {
    		String username = authentication.getName();
    		User user = userServiceImpl.findByUsername(username);
    		session.setAttribute("user", user);
    	}
		
		
		
		model.addAttribute("accesses", accessServiceImpl.findAll());
		
		
		return "/accesses";
	}
    
}
