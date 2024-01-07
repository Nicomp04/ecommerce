package com.example.ecommerce.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuarios")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/registro")
    public ResponseEntity<String> registrarUsuario(@RequestBody User user) {
        // logic para validar y registrar un nuevo usuario
        userService.registerUser(user);
        return new ResponseEntity<>("Usuario registrado exitosamente", HttpStatus.CREATED);
    }

    // Otros endpoints para gestionar usuarios
}
