package petcare.controller;

import petcare.entity.Tutor;
import petcare.repository.TutorRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping ("/tutores")
public class TutorController {
    private final TutorRepository tutorRepository;

    public TutorController(TutorRepository tutorRepository) {
        this.tutorRepository = tutorRepository;
    }

    //Exibir a lista de tutores 
    @GetMapping
    public String listarTutores(Model model) {
        model.addAttribute("tutores", tutorRepository.findAll());
        return "tutores/lista";
    }

    // Abre o formulário de cadastro
    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("tutor", new Tutor());
        return "tutores/formulario";
    }

    //Salva um novo tutor no banco de dados
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Tutor tutor) {
        tutorRepository.save(tutor);
        return "redirect:/tutores";
    }

    // Exclui um tutor
    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        tutorRepository.deleteById(id);
        return "redirect:/tutores";
    }
    
}
