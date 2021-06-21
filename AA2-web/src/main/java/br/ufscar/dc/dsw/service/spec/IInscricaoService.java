package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.Inscricao;
import br.ufscar.dc.dsw.domain.Profissional;

public interface IInscricaoService {

	Inscricao buscarPorId(Long id);
	
	List<Inscricao> buscarTodas();
	
	List<Inscricao> buscarTodasPorProfissional(Profissional p);
	
	void salvar(Inscricao livro);
	
	void excluir(Long id);
}