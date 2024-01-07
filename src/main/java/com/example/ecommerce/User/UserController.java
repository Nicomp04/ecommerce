package com.example.ecommerce.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/usuarios")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/registro")
    public ModelAndView mostrarFormularioRegistro() {
        ModelAndView modelAndView = new ModelAndView("registro_usuario");
        modelAndView.addObject("newUser", new User());
        return modelAndView;
    }

    @PostMapping("/registro")
    public String registrarUsuario(@ModelAttribute User newUser) {
        newUser.setRol(Rol.USER);
        userService.registerUser(newUser);
        return "redirect:/usuarios/registro"; // Redirige al formulario de registro después de registrar
    }

    @GetMapping("/login")
    public ModelAndView mostrarLogin() {
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("newUser", new User());
        return modelAndView;
    }
}
