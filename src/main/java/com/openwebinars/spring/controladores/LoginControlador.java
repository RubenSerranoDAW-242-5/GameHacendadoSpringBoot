package com.openwebinars.spring.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginControlador {

    @GetMapping("/login")
	public String login(Model model, @RequestParam(value = "error", required = false) String error) {

		if (error != null) {
			model.addAttribute("error", "Usuario o contraseña incorrectos. Inténtalo de nuevo.");
		} else {
			model.addAttribute("error", null);
		}
		return "public/login";
	}
    
}
