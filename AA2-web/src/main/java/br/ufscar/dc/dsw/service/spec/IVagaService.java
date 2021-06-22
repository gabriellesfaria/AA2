package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.Vaga;
import br.ufscar.dc.dsw.domain.Empresa;

public interface IVagaService {

	Vaga buscarPorId(Long id);
	
	List<Vaga> buscarTodas();
	
	List<Vaga> buscarPorEmpresa(Empresa empresa);
	
	void salvar(Vaga livro);
	
	void excluir(Long id);
	
	
	
}