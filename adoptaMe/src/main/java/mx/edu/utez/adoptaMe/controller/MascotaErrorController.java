package mx.edu.utez.adoptaMe.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MascotaErrorController implements ErrorController{
	
	@RequestMapping("/error")
	public String error(HttpServletRequest request ) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		System.out.println(status);
		if(status != null) {
			Integer statusCode = Integer.valueOf(status.toString());
			if(HttpStatus.BAD_REQUEST.value() == statusCode) {
				return "/errorPages/400";
			}
			if(HttpStatus.SERVICE_UNAVAILABLE.value() == statusCode) {
				return "/errorPages/401";
			}
			if(HttpStatus.FORBIDDEN.value() == statusCode) {
				return "/errorPages/403";
			}
			if(HttpStatus.NOT_FOUND.value() == statusCode) {
				return "/errorPages/404";
			}
			if(HttpStatus.INTERNAL_SERVER_ERROR.value() == statusCode) {
				return "/errorPages/500";
			}
		}
		return "error";
	}
	
	/*public String getErroPath() {
		return null;
	}*/

}
