package com.openwebinars.spring.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.openwebinars.spring.entidades.Usuario;
import com.openwebinars.spring.servicios.UsuarioServicio;

@Controller
public class RegistroControlador {

    @Autowired
    private UsuarioServicio bdUsuario;

    @GetMapping("/registro")
    public String registro(Model model, @RequestParam(value = "error", required = false) String error) {

        model.addAttribute("usuarioNuevo", new Usuario());

        if (error != null) {
            model.addAttribute("error", "Confirma la contraseña correctamente.");
        } else {
            model.addAttribute("error", null);
        }

        return "public/registro";
    }

    @PostMapping("/registro/submit")
    public String nuevoUsuarioSubmit(@ModelAttribute("usuarioNuevo") Usuario u) {

        bdUsuario.add(u);

        return "redirect:/login";
    }
}
