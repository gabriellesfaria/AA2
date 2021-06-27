package br.ufscar.dc.dsw.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufscar.dc.dsw.domain.Vaga;
import br.ufscar.dc.dsw.service.spec.IVagaService;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private IVagaService vagaService;
	
	@GetMapping("")
	public String listarVagas(ModelMap model, Principal principal) {
		List<Vaga> todasVagas = vagaService.buscarTodas();
		List<Vaga> vagasAbertas = new ArrayList<Vaga>();
		
		for(Vaga v: todasVagas) {
			if(v.getStatus().contains("ABERTO")) {
				vagasAbertas.add(v);
			}
		}
		System.out.println(vagasAbertas);
		model.addAttribute("vagas", vagasAbertas);
		
		
		return "login";
	}
}