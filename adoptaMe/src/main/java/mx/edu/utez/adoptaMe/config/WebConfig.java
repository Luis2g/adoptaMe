package mx.edu.utez.adoptaMe.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{

    

    public void addResourceHandlers(ResourceHandlerRegistry handlerRegistry) {

        handlerRegistry.addResourceHandler("/imagenes/**").addResourceLocations("file:C:/mascotas/img-post/");
        handlerRegistry.addResourceHandler("/imagenesPet/**").addResourceLocations("file:C:/mascotas/img-pet/");

    }

    

}