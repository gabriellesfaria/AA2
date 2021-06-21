package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.Vaga;

public interface IVagaService {

	Vaga buscarPorId(Long id);
	
	List<Vaga> buscarTodas();
	
	void salvar(Vaga livro);
	
	void excluir(Long id);
	
}