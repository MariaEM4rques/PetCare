
package petcare.controller;

import petcare.repository.TutorRepository;
import petcare.repository.AnimalRepository;
import petcare.repository.VeterinarioRepository;
import petcare.repository.ServicoRepository;
import petcare.repository.ConsultaRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final TutorRepository tutorRepository;
    private final AnimalRepository animalRepository;
    private final VeterinarioRepository veterinarioRepository;
    private final ServicoRepository servicoRepository;
    private final ConsultaRepository consultaRepository;

    public HomeController(
            TutorRepository tutorRepository,
            AnimalRepository animalRepository,
            VeterinarioRepository veterinarioRepository,
            ServicoRepository servicoRepository,
            ConsultaRepository consultaRepository) {

        this.tutorRepository = tutorRepository;
        this.animalRepository = animalRepository;
        this.veterinarioRepository = veterinarioRepository;
        this.servicoRepository = servicoRepository;
        this.consultaRepository = consultaRepository;
    }

    @GetMapping("/")
    public String inicio(Model model) {

        model.addAttribute("totalTutores", tutorRepository.count());
        model.addAttribute("totalAnimais", animalRepository.count());
        model.addAttribute("totalVeterinarios", veterinarioRepository.count());
        model.addAttribute("totalServicos", servicoRepository.count());
        model.addAttribute("totalConsultas", consultaRepository.count());

        return "index";
    }
}
