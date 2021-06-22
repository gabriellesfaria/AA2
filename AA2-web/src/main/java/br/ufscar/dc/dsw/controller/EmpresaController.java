package br.ufscar.dc.dsw.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;
import java.security.Principal;

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
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Inscricao;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Vaga;

import br.ufscar.dc.dsw.dao.VagaDAO;
import br.ufscar.dc.dsw.service.spec.IVagaService;
import br.ufscar.dc.dsw.service.EmailService;
import br.ufscar.dc.dsw.service.spec.IEmpresaService;
import br.ufscar.dc.dsw.service.spec.IInscricaoService;

@Controller
@RequestMapping("/empresas")
public class EmpresaController {

	@Autowired
	private IVagaService vagaService;

	@Autowired
	private IInscricaoService inscricaoService;

	@Autowired
	private IEmpresaService empresaService;

	private EmailService emailService = new EmailService();

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

	@GetMapping("/listarInscritos")
	public String listar(ModelMap model, Principal principal) {

		Empresa empresa = empresaService.buscarPorEmail(principal.getName());

		List<Vaga> vagas = vagaService.buscarPorEmpresa(empresa);

		List<Inscricao> todasInscricoes = inscricaoService.buscarTodas();

		List<Inscricao> inscricoesEmpresa = new ArrayList<Inscricao>();

		for (Vaga v : vagas) {
			for (Inscricao i : todasInscricoes) {
				if (i.getVaga() == v) { // inscrito de vaga da empresa
					inscricoesEmpresa.add(i);
					System.out.println(i);
				}
			}
		}
		model.addAttribute("inscricoesEmpresa", inscricoesEmpresa);

		return "empresa/listaInscritos";
	}

	@GetMapping("/editarInscricao/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("inscricao", inscricaoService.buscarPorId(id));
		return "empresa/cadastroInscricao";
	}

	@PostMapping("/editarInscricao")
	public String editar(@Valid Inscricao inscricao, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			System.out.println(result.getAllErrors().get(0).toString());
			return "empresa/cadastroInscricao";
		}

		inscricaoService.salvar(inscricao);

		InternetAddress from = new InternetAddress("pedroma@estudante.ufscar.br", "From");
		InternetAddress to = new InternetAddress("pedroadorno99@gmail.com", "To");

		String subject1 = "Exemplo Subject (Gmail SMTP/Spring)";
		String body1 = "Exemplo mensagem (Gmail SMTP/Spring)";

		// Envio sem anexo
		emailService.send(from, to, subject1, body1);


		attr.addFlashAttribute("sucess", "Inscricao editada e profissional notificado com sucesso.");
		return "redirect:/empresas/listarInscritos";
	}

}
