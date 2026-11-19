package petcare.controller;

import petcare.entity.Consulta;
import petcare.entity.Servico;
import petcare.entity.Veterinario;
import petcare.entity.Animal;


import petcare.repository.ServicoRepository;
import petcare.repository.VeterinarioRepository;
import petcare.repository.AnimalRepository;
import petcare.repository.ConsultaRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping("/consultas")
public class ConsultaController {
    private final ConsultaRepository consultaRepository;
    private final ServicoRepository servicoRepository;
    private final VeterinarioRepository veterinarioRepository;
    private final AnimalRepository animalRepository;

    public ConsultaController(ConsultaRepository consultaRepository,
    ServicoRepository servicoRepository, VeterinarioRepository veterinarioRepository,
    AnimalRepository animalRepository) {
        this.consultaRepository = consultaRepository;
        this.servicoRepository = servicoRepository;
        this.veterinarioRepository = veterinarioRepository;
        this.animalRepository = animalRepository;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("consultas", consultaRepository.findAll());
        return "consultas/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("consulta", new Consulta());
        model.addAttribute("servicos", servicoRepository.findAll());
        model.addAttribute("veterinarios", veterinarioRepository.findAll());
        model.addAttribute("animais", animalRepository.findAll());
        return "consultas/formulario";
    }

    
    @PostMapping("/salvar")
    public String salvar(
        @ModelAttribute Consulta consulta,
        @RequestParam("animalId") Long animalId,
        @RequestParam("veterinarioId") Long veterinarioId,
        @RequestParam("servicoId") Long servicoId) {

        Animal animal = animalRepository.findById(animalId)
            .orElseThrow();

        Veterinario veterinario = veterinarioRepository.findById(veterinarioId)
            .orElseThrow();

        Servico servico = servicoRepository.findById(servicoId)
            .orElseThrow();

        consulta.setAnimal(animal);
        consulta.setVeterinario(veterinario);
        consulta.setServico(servico);

        consulta.setValor(servico.getPreco());

        consultaRepository.save(consulta);

        return "redirect:/consultas";
    }


}