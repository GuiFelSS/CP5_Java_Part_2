package br.com.fiap.cp5_Brinquedos_esportivos.controller;

import br.com.fiap.cp5_Brinquedos_esportivos.model.Usuario;
import br.com.fiap.cp5_Brinquedos_esportivos.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    // Injeção via construtor
    public AuthController(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registrar")
    public String registrarForm() {
        return "registrar";
    }

    @PostMapping("/registrar")
    public String registrar(@ModelAttribute Usuario usuario) {
        usuario.setRole("USER");
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuarioRepository.save(usuario);
        return "redirect:/login?registro=sucesso";
    }
}