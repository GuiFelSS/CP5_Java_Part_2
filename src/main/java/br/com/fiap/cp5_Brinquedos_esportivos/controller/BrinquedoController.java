package br.com.fiap.cp5_Brinquedos_esportivos.controller;

import br.com.fiap.cp5_Brinquedos_esportivos.model.Brinquedo;
import br.com.fiap.cp5_Brinquedos_esportivos.repository.BrinquedoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/brinquedos")
public class BrinquedoController {

    private final BrinquedoRepository brinquedoRepository;

    // Injeção via construtor
    public BrinquedoController(BrinquedoRepository brinquedoRepository) {
        this.brinquedoRepository = brinquedoRepository;
    }

    @GetMapping
    public String listarBrinquedos(Model model) {
        model.addAttribute("brinquedos", brinquedoRepository.findAll());
        return "brinquedos";
    }

    @GetMapping("/novo")
    public String mostrarFormularioNovo(Model model) {
        model.addAttribute("brinquedo", new Brinquedo());
        return "form-brinquedo";
    }

    @PostMapping("/salvar")
    public String salvarBrinquedo(@ModelAttribute Brinquedo brinquedo) {
        brinquedoRepository.save(brinquedo);
        return "redirect:/brinquedos";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicao(@PathVariable Long id, Model model) {
        Brinquedo brinquedo = brinquedoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID do Brinquedo inválido:" + id));
        model.addAttribute("brinquedo", brinquedo);
        return "form-brinquedo";
    }

    @GetMapping("/deletar/{id}")
    public String deletarBrinquedo(@PathVariable Long id) {
        brinquedoRepository.deleteById(id);
        return "redirect:/brinquedos";
    }
}