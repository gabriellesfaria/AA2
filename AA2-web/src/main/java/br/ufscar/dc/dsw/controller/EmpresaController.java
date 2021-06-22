package br.ufscar.dc.dsw.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.InternetAddress;
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

import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Inscricao;
import br.ufscar.dc.dsw.domain.Vaga;
import br.ufscar.dc.dsw.service.EmailService;
import br.ufscar.dc.dsw.service.spec.IEmpresaService;
import br.ufscar.dc.dsw.service.spec.IInscricaoService;
import br.ufscar.dc.dsw.service.spec.IVagaService;

@Controller
@RequestMapping("/empresas")
public class EmpresaController {

	@Autowired
	private IVagaService vagaService;

	@Autowired
	private IInscricaoService inscricaoService;

	@Autowired
	private IEmpresaService empresaService;

	@Autowired
	private EmailService emailService;

	@GetMapping("/listarVagas")
	public String listarVagas(ModelMap model, Principal principal) {

		Empresa empresa = empresaService.buscarPorEmail(principal.getName());

		model.addAttribute("vagas", vagaService.buscarPorEmpresa(empresa));

		model.addAttribute(empresa);
		return "empresa/listaVagas";
	}

	@GetMapping("/cadastrarVaga")
	public String cadastrar(Vaga vaga, Principal principal) {
		Empresa empresa = empresaService.buscarPorEmail(principal.getName());
		vaga.setEmpresa(empresa);
		return "empresa/cadastroVaga";
	}

	@PostMapping("/salvarVagas")
	public String salvar(@Valid Vaga vaga, BindingResult result, RedirectAttributes attr, Principal principal) {

		Empresa empresa = empresaService.buscarPorEmail(principal.getName());
		vaga.setEmpresa(empresa);
		System.out.println(vaga.getDataLimite());

		String[] data = vaga.getDataLimite().split("-");
		String dataOK = data[2] + "/" + data[1] + "/" + data[0];
		vaga.setDataLimite(dataOK);

		if (result.hasErrors()) {
			return "empresa/cadastroVaga";
		}

		vagaService.salvar(vaga);

		attr.addFlashAttribute("sucess", "Vaga inserida com sucesso.");
		return "redirect:/empresas/listarVagas";
	}

	@GetMapping("/listarEntrevistas")
	public String listar(ModelMap model, Principal principal) {

		Empresa empresa = empresaService.buscarPorEmail(principal.getName());

		List<Vaga> vagas = vagaService.buscarPorEmpresa(empresa);

		List<Inscricao> todasInscricoes = inscricaoService.buscarTodas();

		List<Inscricao> inscricoesEntrevista = new ArrayList<Inscricao>();

		for (Vaga v : vagas) {
			for (Inscricao i : todasInscricoes) {
				if (i.getVaga() == v && i.getStatus().equals("ENTREVISTA")) { // inscrito de vaga da empresa
					inscricoesEntrevista.add(i);
				}
			}
		}
		model.addAttribute("inscricoes", inscricoesEntrevista);

		return "empresa/listaInscritos";
	}

	@GetMapping("/listarInscritos/{id}")
	public String listar(@PathVariable("id") Long id, ModelMap model, Principal principal) {

		Empresa empresa = empresaService.buscarPorEmail(principal.getName());

		List<Vaga> vagas = vagaService.buscarPorEmpresa(empresa);

		List<Inscricao> todasInscricoes = inscricaoService.buscarTodas();

		List<Inscricao> inscricoesVaga = new ArrayList<Inscricao>();

		for (Vaga v : vagas) {
			for (Inscricao i : todasInscricoes) {
				if (i.getVaga() == v && v.getId() == id) { // inscrito de vaga da empresa
					inscricoesVaga.add(i);
				}
			}
		}
		model.addAttribute("inscricoes", inscricoesVaga);

		return "empresa/listaInscritos";
	}

	@GetMapping("/editarInscricao/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		String message = "";
		
		model.addAttribute("inscricao", inscricaoService.buscarPorId(id));
		model.addAttribute("message", message);

		return "empresa/cadastroInscricao";
	}

	@PostMapping("/editarInscricao")
	public String editar(@ModelAttribute("inscricao") @Valid Inscricao inscricao, @ModelAttribute("message") String message, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			System.out.println(result.getAllErrors().get(0).toString());
			return "empresa/cadastroInscricao";
		}

		System.out.println(message);

		inscricaoService.salvar(inscricao);

		try {
			if (inscricao.getStatus().equals("ENTREVISTA")) {
				InternetAddress from = new InternetAddress("pedroma@estudante.ufscar.br", "From");
				InternetAddress to = new InternetAddress("pedroadorno99@gmail.com", "To");
				
				String subject1 = inscricao.getStatus() + " para a vaga de " + inscricao.getVaga().getNome();
				String body1 = message;
				
				// Envio sem anexo
				emailService.send(from, to, subject1, body1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		attr.addFlashAttribute("sucess", "Inscricao editada e profissional notificado com sucesso.");
		return "redirect:/empresas/listarInscritos/" + inscricao.getVaga().getId().toString();
	}

}