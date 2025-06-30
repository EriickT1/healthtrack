package com.healthtrack.controller;

import com.healthtrack.model.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UsuarioController {

    private Usuario usuario = new Usuario("Erick", 70.0);

    @GetMapping("/")
    public String mostrarFormulario(Model model) {
        model.addAttribute("usuario", usuario);
        return "index";
    }

    @PostMapping("/actualizar")
    public String actualizarPeso(@RequestParam double peso, Model model) {
        usuario.actualizarPeso(peso);
        model.addAttribute("usuario", usuario);
        return "index";
    }
}