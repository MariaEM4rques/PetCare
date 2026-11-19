package petcare.controller;

import petcare.entity.Veterinario;
import petcare.repository.VeterinarioRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/veterinarios")
public class VeterinarioController {

    private final VeterinarioRepository veterinarioRepository;

    public VeterinarioController(VeterinarioRepository veterinarioRepository){
        this.veterinarioRepository = veterinarioRepository;
    }


    //Listar veterinarios
    @GetMapping
    public String listarVeterinarios(Model model) {
        model.addAttribute("veterinarios", veterinarioRepository.findAll());

        return "veterinarios/lista";    
    }

    //Adicionar novo veterinario
    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("veterinario", new Veterinario());

        return "veterinarios/formulario";
    }

    //Salvar veterinario
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Veterinario veterinario) {
        veterinarioRepository.save(veterinario);

        return "redirect:/veterinarios";
    }

    //Excluir veterinario
    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        veterinarioRepository.deleteById(id);
        return "redirect:/veterinarios";
    }

}
    