package br.ufscar.dc.dsw.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.Inscricao;
import br.ufscar.dc.dsw.domain.Vaga;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.security.ProfissionalDetails;
import br.ufscar.dc.dsw.service.spec.IInscricaoService;
import br.ufscar.dc.dsw.service.spec.IVagaService;

@Controller
@RequestMapping("/inscricoes")
public class InscricaoController {
	
	@Autowired
	private IInscricaoService service;
	
	@Autowired
	private IVagaService vagaService;
	
	@GetMapping("/cadastrar")
	public String cadastrar(Inscricao inscricao) {
		String data = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
		inscricao.setProfissional(this.getProfissional());
		inscricao.setStatus("ABERTO"); 
		inscricao.setData(data);
		return "inscricao/cadastro";
	}
	
	private Profissional getProfissional() {
		ProfissionalDetails profissionalDetails = (ProfissionalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return profissionalDetails.getProfissional();
	}
	
	@GetMapping("/listarInscricoes")
	public String listar(ModelMap model) {
					
		model.addAttribute("inscricoes", service.buscarTodasPorProfissional(this.getProfissional()));
		
		return "inscricao/lista";
	}
	
	@PostMapping("/salvar")
	public String salvar(@Valid Inscricao inscricao, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			return "inscricao/cadastro";
		}
		
		service.salvar(inscricao);
		attr.addFlashAttribute("sucess", "Inscricao inserida com sucesso.");
		return "redirect:/inscricoes/listar";
	}
	
	@ModelAttribute("vagas")
	public List<Vaga> listaVagas() {
		return vagaService.buscarTodas();
	}
	
}