package br.ufscar.dc.dsw.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.security.Principal;


import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.core.annotation.AuthenticationPrincipal;


import br.ufscar.dc.dsw.domain.Inscricao;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Vaga;
import br.ufscar.dc.dsw.service.spec.IInscricaoService;
import br.ufscar.dc.dsw.service.spec.IProfissionalService;
import br.ufscar.dc.dsw.service.spec.IVagaService;

@Controller
@RequestMapping("/profissionais")
public class ProfissionalController {
	
	@Autowired
	private IInscricaoService inscricaoService;
	
	@Autowired
	private IProfissionalService profissionalService;
	
	@Autowired
	private IVagaService vagaService;
		
	
	@GetMapping("/cadastrarInscricao/{id}")
	public String cadastrar( @PathVariable("id") long id, Inscricao inscricao, Principal principal) {		
		return "profissional/cadastro";
	}
	
	@GetMapping("/listarInscricoes")
	public String listar(ModelMap model, Principal principal) {
		
		Profissional profissional = profissionalService.buscarPorEmail(principal.getName());
						
		model.addAttribute("inscricoes", inscricaoService.buscarPorProfissional(profissional));
				
		model.addAttribute(profissional);
		
		return "profissional/lista";
	}
	
	@GetMapping("/listarVagas")
	public String listarVagas(ModelMap model, Principal principal) {
		
		Profissional profissional = profissionalService.buscarPorEmail(principal.getName());
	
		model.addAttribute("vagas", vagaService.buscarTodas());
		
		model.addAttribute(profissional);
		return "profissional/listaVagas";
	}
	
	@PostMapping("/salvarInscricao/{id}")
	public String salvar(Principal principal, @PathVariable("id") Long id, @Valid Inscricao inscricao, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			System.out.println("result em salvar: " + result);
			return "profissional/cadastro";
		}
		Vaga vaga = vagaService.buscarPorId(id);
		Profissional profissional = profissionalService.buscarPorEmail(principal.getName());
		String data = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
		inscricao.setProfissional(profissional);
		inscricao.setVaga(vaga);
		inscricao.setStatus("ABERTO"); 
		inscricao.setData(data);
		inscricaoService.salvar(inscricao);
		attr.addFlashAttribute("sucess", "Inscricao inserida com sucesso.");
		return "redirect:/profissionais/listarInscricoes";
	}
	
	@GetMapping("/editarInscricao/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("inscricao", inscricaoService.buscarPorId(id));
		return "profissional/cadastro";
	}
	
	@PostMapping("/editarInscricao")
	public String editar(@Valid Inscricao inscricao, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			System.out.println("result em editar: " + result);
			return "profissional/cadastro";
		}

		inscricaoService.salvar(inscricao);
		attr.addFlashAttribute("sucess", "Profissional editado com sucesso.");
		return "redirect:/profissionais/listarInscricoes";
	}
	

	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model, Principal principal) {

		inscricaoService.excluir(id);
		model.addAttribute("sucess", "Inscrição excluída com sucesso.");
	
		return listar(model, principal);
	}
	
	@ModelAttribute("vagas")
	public List<Vaga> listaVagas() {
		return vagaService.buscarTodas();
	}
}