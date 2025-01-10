package com.openwebinars.spring.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.openwebinars.spring.entidades.Usuario;
import com.openwebinars.spring.servicios.UsuarioServicio;

import jakarta.servlet.http.HttpSession;

@Controller
public class PerfilControlador {

    @Autowired
    private UsuarioServicio bdUsuario;

    @Autowired
	private HttpSession sesion;

    @GetMapping("/perfil")
    public String getMethodName(Model model) {
        Usuario u = bdUsuario.findById((Long) sesion.getAttribute("id"));
        model.addAttribute("usuario", u);
        return "public/perfil";
    }

    @PostMapping("/perfil/edit")
    public String postMethodName(@ModelAttribute("nombre") String nombre, @ModelAttribute("apellido") String apellido,
            @ModelAttribute("email") String email, @ModelAttribute("dni") String dni,
            @ModelAttribute("contraseña") String contraseña,
            @ModelAttribute("direccion") String direccion, @ModelAttribute("telefono") String telefono,
            BCryptPasswordEncoder passwordEncoder) {

        Usuario u = bdUsuario.findById((Long) sesion.getAttribute("id"));

        u.setNombre(nombre);
        u.setApellido(apellido);
        u.setEmail(email);
        u.setDni(dni);
        if (contraseña != null) {
            contraseña = passwordEncoder.encode(contraseña);
            u.setContraseña(contraseña);
        }
        u.setDireccion(direccion);
        u.setTelefono(telefono);

        bdUsuario.edit(u);

        return "redirect:/";
    }
}
