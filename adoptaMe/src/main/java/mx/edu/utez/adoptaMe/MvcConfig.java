package mx.edu.utez.adoptaMe;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/usuarios/find-all").setViewName("users");
        // registry.addViewController("/admin").setViewName("admin");
        registry.addViewController("/privada").setViewName("privada");
        registry.addViewController("/login").setViewName("login");
//        registry.addViewController("/usuarios/edit").setViewName("edition");
    }
}
