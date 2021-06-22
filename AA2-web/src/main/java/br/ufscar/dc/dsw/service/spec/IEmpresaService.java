package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Profissional;

public interface IEmpresaService {
	
	Empresa buscarPorId(Long id);
	
	List<Empresa> buscarTodos();
	
	void salvar(Empresa empresa);
	
	void excluir(Long id);
		
	Empresa buscarPorEmail(String email);

}
