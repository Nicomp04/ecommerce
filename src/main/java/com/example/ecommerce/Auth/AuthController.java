package com.example.ecommerce.Auth;

import com.example.ecommerce.Prenda.Prenda;
import com.example.ecommerce.User.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping()
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    @GetMapping(value = "/login")
    public ModelAndView getLoginForm() {
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @PostMapping(value = "/login")
    public String login(@ModelAttribute User user, HttpServletRequest request) {
        System.out.println("usuario: " + user.getUsername() +" y contraseña es:"+ user.getPassword());
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );

            System.out.println("Authentication: " + authentication.toString());

            SecurityContextHolder.getContext().setAuthentication(authentication);
            return "redirect:/prendas";
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return "redirect:/auth/login?error";
        }
    }

    @GetMapping(value = "/register")
    public ModelAndView getRegisterForm() {
        ModelAndView modelAndView = new ModelAndView("registro");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }
    @PostMapping(value = "/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }
}