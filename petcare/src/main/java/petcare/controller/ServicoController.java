
package petcare.controller;

import petcare.entity.Servico;
import petcare.repository.ServicoRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/servicos")
public class ServicoController {

    private final ServicoRepository servicoRepository;

    public ServicoController(ServicoRepository servicoRepository) {
        this.servicoRepository = servicoRepository;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("servicos", servicoRepository.findAll());
        return "servicos/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("servico", new Servico());
        return "servicos/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Servico servico) {
        servicoRepository.save(servico);
        return "redirect:/servicos";
    }

    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        servicoRepository.deleteById(id);
        return "redirect:/servicos";
    }
}
