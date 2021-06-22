package br.ufscar.dc.dsw.service.spec;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Usuario;

public interface IUsuarioService {

	Usuario buscarPorId(Long id);
	
	Usuario buscarPorEmail(String email);
	
	List<Usuario> buscarTodos();
	
	void salvar(Usuario usuario);
	
	void excluir(Long id);

}