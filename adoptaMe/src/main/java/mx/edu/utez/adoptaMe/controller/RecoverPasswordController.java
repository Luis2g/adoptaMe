package mx.edu.utez.adoptaMe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mx.edu.utez.adoptaMe.entity.User;
import mx.edu.utez.adoptaMe.service.EmailServiceImpl;
import mx.edu.utez.adoptaMe.service.UserServiceImpl;

@Controller
public class RecoverPasswordController {

	@Autowired
	EmailServiceImpl emailServiceImpl;

	@Autowired
	private UserServiceImpl userServiceImpl;

	@Autowired
	private PasswordEncoder passwordEncoder;

	String NUMEROS = "0123456789";
	String MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";

	public String generarContrasena(int length) {
		return contrasenaAleatoria(NUMEROS + MAYUSCULAS + MINUSCULAS, length);
	}

	public String contrasenaAleatoria(String key, int length) {
		String contrasena = "";
		for (int i = 0; i < length; i++) {
			contrasena += (key.charAt((int) (Math.random() * key.length())));
		}
		return contrasena;
	}

	@GetMapping("/restablecerContrasena")
	public String recuperarContrasena() {
		return "restablecerContrasena";
	}

	@PostMapping("/reset/password/email")
	public String enviarContrasenaEmail(@RequestParam("email") String email, RedirectAttributes redirectAttributes) {

		email = email.replaceAll("[\\s]", "");
		User user = userServiceImpl.findByEmail(email);
		if (user.getUsername()!=null){
			String nuevaContrasena = generarContrasena(12);
			String contrasenaEncriptada = passwordEncoder.encode(nuevaContrasena);
			boolean respuestaCambio = userServiceImpl.changePassword(contrasenaEncriptada, user.getEmail());
			String nombreUsuario = user.getPerson().getName().concat(" ").concat(user.getPerson().getSurname());
			String htmlContent = plantillaRecuperacionContrasena(nombreUsuario, user.getEmail(), nuevaContrasena);
			
			boolean respuestaEmail = emailServiceImpl.sendEmail(user.getEmail(), "Cambio de contraseña", htmlContent);
			if (respuestaCambio && respuestaEmail) {
				redirectAttributes.addFlashAttribute("msg_success",
						"Correo de recuperación de contraseña enviado, por favor revisa tu bandeja de correo.");
				return "redirect:/login";
			} else {
				redirectAttributes.addFlashAttribute("msg_error", "Ocurrió un error, por favor intenta de nuevo.");
				return "redirect:/reset/password";
			}
		}else{
			redirectAttributes.addFlashAttribute("msg_error", "Ocurrió un error, por favor intenta de nuevo.");
				return "redirect:/reset/password";
		}
		
	}

	public String plantillaRecuperacionContrasena(String nombreUsuario, String email, String contrasena) {
		StringBuilder contenidoCorreo = new StringBuilder();
		contenidoCorreo.append("<!doctype html>");
		contenidoCorreo.append("<html lang=\"es\"");
		contenidoCorreo.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
		contenidoCorreo.append("<meta charset=\"UTF-8\">");
		contenidoCorreo.append("<head>");
		contenidoCorreo.append("<style>");
		contenidoCorreo.append(".h1, .h2, .h3 { font-family: Arial, Helvetica, sans-serif; }");
		contenidoCorreo.append("</style>");
		contenidoCorreo.append("</head>");
		contenidoCorreo.append("<body>");
		contenidoCorreo.append("<center><h1 style=\"color: #398AB9\">Cambio de contraseña</h1></center>");
		contenidoCorreo.append("<center><h2 style=\"color: #006778\">Estimad@ ").append(nombreUsuario)
				.append("</h2></center>");
		contenidoCorreo.append(
				"<center><h2 style=\"color: #333\">Hemos recibido una solicitud de cambio de contraseña</h2></center>");
		contenidoCorreo.append("<center><h2 style=\"color: #0093AB\">Nuevos datos de acceso a tu cuenta</h2></center>");
		contenidoCorreo.append("<center><h2 style=\"color: #00AFC1\">Correo electrónico: ").append(email)
				.append("</h2></center>");
		contenidoCorreo.append("<center><h2 style=\"color: #00AFC1\">Contraseña: ").append(contrasena)
				.append("</h2></center>");
		contenidoCorreo.append(
				"<br><center><h3 style=\"color: #333\">Por favor ingresa a tu cuenta con los datos proporcionados en este correo.<br>Recuerda cambiar la contraseña por una de tu preferencia.</h3></center>");
		contenidoCorreo.append("</body>");
		contenidoCorreo.append("</html>");
		return contenidoCorreo.toString();
	}

}
