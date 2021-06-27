package br.ufscar.dc.dsw.controller;

<<<<<<< HEAD
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufscar.dc.dsw.domain.Vaga;
=======
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
>>>>>>> 4b5191eb41e7e474a00eba13e1bce087ae498538
import br.ufscar.dc.dsw.service.spec.IVagaService;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
<<<<<<< HEAD
=======
	private IInscricaoService inscricaoService;
	
	@Autowired
	private IProfissionalService profissionalService;
	
	@Autowired
>>>>>>> 4b5191eb41e7e474a00eba13e1bce087ae498538
	private IVagaService vagaService;
	
	@GetMapping("")
	public String listarVagas(ModelMap model, Principal principal) {
<<<<<<< HEAD
		List<Vaga> todasVagas = vagaService.buscarTodas();
		List<Vaga> vagasAbertas = new ArrayList<Vaga>();
		
		for(Vaga v: todasVagas) {
			if(v.getStatus().contains("ABERTO")) {
				vagasAbertas.add(v);
			}
		}
		System.out.println(vagasAbertas);
		model.addAttribute("vagas", vagasAbertas);
		
=======
		model.addAttribute("vagas", vagaService.buscarTodas());
>>>>>>> 4b5191eb41e7e474a00eba13e1bce087ae498538
		
		return "login";
	}
}