package mx.edu.utez.adoptaMe.util;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class ImagenUtileria {

    public static String guardarImagen(MultipartFile multipartFile, String ruta, String nombre){

		try {

			String rutaArchivo = ruta + "/" + nombre;

			System.err.println(rutaArchivo);

			File imagen = new File(rutaArchivo);

			multipartFile.transferTo(imagen);

			return nombre;

		} catch (IOException e) {

			System.err.println(e.getMessage());

			return "null";

		}
    }
    
}
