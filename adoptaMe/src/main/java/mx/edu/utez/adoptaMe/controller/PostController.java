package mx.edu.utez.adoptaMe.controller;

import javax.validation.Valid;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.core.Authentication;

import mx.edu.utez.adoptaMe.entity.Post;
import mx.edu.utez.adoptaMe.entity.User;
import mx.edu.utez.adoptaMe.helpers.Session;
import mx.edu.utez.adoptaMe.service.PostServiceImpl;

import mx.edu.utez.adoptaMe.service.UserServiceImpl;
import mx.edu.utez.adoptaMe.util.ImagenUtileria;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.security.access.annotation.Secured;

@Controller
public class PostController {

    @Autowired
    private PostServiceImpl postServiceImpl;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("/modals")
    public String modals(Post post) {

        return "inicioModals";
    }

    public String save() {
        String token = "";

        for (int i = 0; i < 16; i++) {
            double numero = Math.random() * 10;
            int parcear = (int) numero;
            token += parcear;
        }

        return token;
    }

    @PostMapping("/savePost")
    @Secured("ROLE_ADMIN")
    public String savePost(@Valid @ModelAttribute("post") Post post, BindingResult result, Model model,
            RedirectAttributes redirectAttributes, Authentication authentication, HttpSession session,
            @RequestParam(name = "imagenPost", required = false) MultipartFile multipartFile) {

        if (post.getId() == null) {
            String username = authentication.getName();
            User user = userServiceImpl.findByUsername(username);
            session.setAttribute("user", user);
            post.setUser(user);

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDateTime now = LocalDateTime.now();
            post.setPostDate(dtf.format(now));

            if (result.hasErrors()) {
                for (ObjectError error : result.getAllErrors()) {
                    System.out.println("Error" + error.getDefaultMessage());
                }
                redirectAttributes.addFlashAttribute("msg_error", "¡Ha ocurrido un error en el registro!");
                return "redirect:/noticias/editar/" + post.getId();
            } else {

                boolean response = false;
                String generatedToken = save();
                try {                
                    post.setImage(generatedToken);
                    post.setStatus("enabled");
                    response = postServiceImpl.save(post);
                } catch (Exception e) {
                    return "redirect:/savePost";
                }
    
                String ruta = "C:/mascotas/img-post";
                ImagenUtileria.guardarImagen(multipartFile, ruta, generatedToken);
                
    
                if (response) {
                    redirectAttributes.addFlashAttribute("msg_success", "¡Se ha realizado el registro correctamente!");
                    return "redirect:/noticias";
    
                } else {
                    redirectAttributes.addFlashAttribute("msg_error", "¡Ha ocurrido un error en el registro!");
                    return "redirect:/noticias";
                }
            }

        }else{
            Post postFromDB = postServiceImpl.edit(post.getId());

            postFromDB.setTitle(post.getTitle());
            postFromDB.setContent(post.getContent());
            postFromDB.setIsMain(post.getIsMain());
            System.out.println(postFromDB.getImage());

            if (result.hasErrors()) {
                for (ObjectError error : result.getAllErrors()) {
                    System.out.println("Error" + error.getDefaultMessage());
                }
                redirectAttributes.addFlashAttribute("msg_error", "¡Ha ocurrido un error en el registro!");
                return "redirect:/noticias/editar/" + post.getId();
            } else {

                boolean response = false;

                if(multipartFile != null && !multipartFile.isEmpty()){                    
                    String generatedToken = save();
                    try {                
                        postFromDB.setImage(generatedToken);
                        response = postServiceImpl.save(postFromDB);
                    } catch (Exception e) {
                        return "redirect:/savePost";
                    }
        
                    String ruta = "C:/mascotas/img-post";
                    ImagenUtileria.guardarImagen(multipartFile, ruta, generatedToken);
                }else{

                    response = postServiceImpl.save(postFromDB);
                }
                
                if (response) {
                    redirectAttributes.addFlashAttribute("msg_success", "¡Se ha realizado la modificación correctamente!");
                    return "redirect:/noticias";
    
                } else {
                    redirectAttributes.addFlashAttribute("msg_error", "¡Ha ocurrido un error al modificar!");
                    return "redirect:/noticias";
                }
            }
        }
        
    }

    @GetMapping("/noticias")
    public String news(Model model, Post post, Authentication authentication, HttpSession session) {

        if (authentication != null) {
            String username = authentication.getName();
            User user = userServiceImpl.findByUsername(username);
            session.setAttribute("user", user);
            model.addAttribute("postList", postServiceImpl.listAll());
        }else{
            model.addAttribute("postList", postServiceImpl.findByStatus());
        }

        
        Session.setUrl("/noticias");
        return "news";
    }

    @GetMapping("/noticias/deshabilitar/{id}")
    @Secured("ROLE_ADMIN")
    public String disableNew(@PathVariable long id, RedirectAttributes redirectAttributes){
        Post post = postServiceImpl.edit(id);
        post.setStatus("disabled");
        boolean response = postServiceImpl.save(post);
        if(response){
            redirectAttributes.addFlashAttribute("msg_success", "¡Se deshabilitó correctamente la noticia!");
        }else{
            redirectAttributes.addFlashAttribute("msg_error", "¡Ha ocurrido un error al deshabilitar la noticia!");
        }
        return "redirect:/noticias";
    }

    @GetMapping("/noticias/habilitar/{id}")
    @Secured("ROLE_ADMIN")
    public String enableNew(@PathVariable long id, RedirectAttributes redirectAttributes){
        Post post = postServiceImpl.edit(id);
        post.setStatus("enabled");
        boolean response = postServiceImpl.save(post);
        if(response){
            redirectAttributes.addFlashAttribute("msg_success", "¡Se habilitó correctamente la noticia!");
        }else{
            redirectAttributes.addFlashAttribute("msg_error", "¡Ha ocurrido un error al habilitar la noticia!");
        }
        return "redirect:/noticias";
    }

    @GetMapping("/noticias/editar/{id}")
    @Secured("ROLE_ADMIN")
    public String editNews(@PathVariable long id, Model model, RedirectAttributes redirectAttributes,
            HttpSession session) {
        Post post = postServiceImpl.edit(id);
        if (post != null) {
            model.addAttribute("post", post);
            Session.setUrl("/noticias/editar/{i}");
            return "editNews";
        } else {
            redirectAttributes.addFlashAttribute("msg_error", "Registro no encontrado");
            return "redirect:/noticias";
        }

    }

}
