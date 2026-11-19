package petcare.controller;

import petcare.entity.Animal;
import petcare.entity.Tutor;
import petcare.repository.AnimalRepository;
import petcare.repository.TutorRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller 
@RequestMapping ("/animais")
public class AnimalController {

    private final AnimalRepository animalRepository;
    private final TutorRepository tutorRepository;

    public AnimalController(AnimalRepository animalRepository, TutorRepository tutorRepository) {
        this.animalRepository = animalRepository;
        this.tutorRepository = tutorRepository;
    }

    //Exibir a lista de animais
    @GetMapping
    public String listarAnimais(Model model) {
        model.addAttribute("animais", animalRepository.findAll());
        return "animais/lista";
    }

    // Abre o formulário de cadastro
    @GetMapping("/novo")
public String novo(Model model) {

    var listaTutores = tutorRepository.findAll();

    System.out.println("================================");
    System.out.println("TUTORES ENCONTRADOS: " + listaTutores.size());

    listaTutores.forEach(tutor ->
        System.out.println(
            "ID: " + tutor.getId() +
            " | Nome: " + tutor.getNome()
        )
    );

    System.out.println("================================");

    model.addAttribute("animal", new Animal());
    model.addAttribute("tutores", listaTutores);

    return "animais/formulario";
}

    //Salva um novo animal no banco de dados
   @PostMapping("/salvar")
    public String salvar(
            @ModelAttribute Animal animal,
            @RequestParam Long tutorId) {

        Tutor tutor = tutorRepository.findById(tutorId)
                .orElseThrow(() ->
                    new IllegalArgumentException("Tutor não encontrado"));

        animal.setTutor(tutor);

        animalRepository.save(animal);

        return "redirect:/animais";
    }

    // Exclui um animal
    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        animalRepository.deleteById(id);
        return "redirect:/animais";
    }
    
}
