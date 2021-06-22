package br.ufscar.dc.dsw.dao;

import org.springframework.data.repository.CrudRepository;
import java.util.List;
import br.ufscar.dc.dsw.domain.Inscricao;
import br.ufscar.dc.dsw.domain.Profissional;

@SuppressWarnings("unchecked")
public interface InscricaoDAO extends CrudRepository<Inscricao, Long>{
	Inscricao findById(long id);
	List<Inscricao> findAll();
	Inscricao save(Inscricao inscricao);
	void deleteById(Long id);
	List<Inscricao> findByProfissional(Profissional p);
}