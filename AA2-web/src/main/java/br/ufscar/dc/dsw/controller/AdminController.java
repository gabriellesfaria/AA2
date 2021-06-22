package br.ufscar.dc.dsw.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;
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

import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Inscricao;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Vaga;
import br.ufscar.dc.dsw.domain.Usuario;

import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.service.spec.IVagaService;
import br.ufscar.dc.dsw.service.spec.IEmpresaService;
import br.ufscar.dc.dsw.service.spec.IUsuarioService;
import br.ufscar.dc.dsw.service.spec.IProfissionalService;
import br.ufscar.dc.dsw.service.spec.IInscricaoService;
@Controller
@RequestMapping("/admins")
public class AdminController {
	
	@Autowired
	private IVagaService vagaService;
	
	@Autowired
	private IInscricaoService inscricaoService;

	@Autowired
	private IProfissionalService profissionalService;
	
	@Autowired
	private IEmpresaService empresaService;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@GetMapping("/listarEmpresas")
	public String listarEmpresas(ModelMap model) {
			
		model.addAttribute("empresas", empresaService.buscarTodos());
		return "admin/listaEmpresa";
	}

	@GetMapping("/excluirEmpresa/{id}")
	public String excluirEmpresa(@PathVariable("id") Long id, ModelMap model) {

		empresaService.excluir(id);
		model.addAttribute("sucess", "Empresa excluída com sucesso.");
		return listarEmpresas(model);
	}


	@GetMapping("/cadastrarEmpresa")
	public String cadastrarEmpresa(Empresa empresa) {
		return "admin/cadastroEmpresa";
	}
	
	@PostMapping("/salvarEmpresa")
	public String salvarEmpresa(@Valid Empresa empresa, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			return "admin/cadastroEmpresa";
		}
		
		empresaService.salvar(empresa);
		attr.addFlashAttribute("sucess", "Empresa inserida com sucesso.");
		return "redirect:/admin/listarEmpresas";
	}

	@GetMapping("/editarEmpresa/{id}")
	public String preEditarEmpresa(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("profissional", profissionalService.buscarPorId(id));
		return "admin/cadastroProfissional";
	}
	
	
	@PostMapping("/editarEmpresa")
	public String editarEmpresa(@Valid Empresa empresa, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			return "admin/cadastroEmpresa";
		}

		empresaService.salvar(empresa);
		attr.addFlashAttribute("sucess", "Empresa editada com sucesso.");
		return "redirect:/admin/listarEmpresas";
	}

	@GetMapping("/listarProfissionais")
	public String listarProfissionais(ModelMap model) {
	
		model.addAttribute("profissionais", profissionalService.buscarTodos());
		return "admin/listaProfissional";
	}
	
	@GetMapping("/excluirProfissional/{id}")
	public String excluirProfissional(@PathVariable("id") Long id, ModelMap model) {

		profissionalService.excluir(id);
		model.addAttribute("sucess", "Profissional excluído com sucesso.");
		return listarProfissionais(model);
	}
	
	@GetMapping("/cadastrarProfissional")
	public String cadastrarProfissional(Profissional profissional) {
		return "admin/cadastroProfissional";
	}
	
	@PostMapping("/salvarProfissional")
	public String salvarProfissional(@Valid Profissional profissional, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			return "admin/cadastroProfissional";
		}
		
		profissionalService.salvar(profissional);
		attr.addFlashAttribute("sucess", "Profissional inserido com sucesso.");
		return "redirect:/admin/listarProfissionais";
	}
	
	@GetMapping("/editarProfissional/{id}")
	public String preEditarProfissional(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("profissional", profissionalService.buscarPorId(id));
		return "admin/cadastroProfissional";
	}
	
	@PostMapping("/editarProfissional")
	public String editarProfissional(@Valid Profissional profissional, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			return "admin/cadastroProfissional";
		}

		profissionalService.salvar(profissional);
		attr.addFlashAttribute("sucess", "Profissional editado com sucesso.");
		return "redirect:/admin/listarProfissionais";
	}
	
}
