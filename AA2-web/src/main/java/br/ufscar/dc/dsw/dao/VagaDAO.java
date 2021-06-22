package br.ufscar.dc.dsw.dao;

import org.springframework.data.repository.CrudRepository;
import java.util.List;
import br.ufscar.dc.dsw.domain.Vaga;
import br.ufscar.dc.dsw.domain.Empresa;

@SuppressWarnings("unchecked")
public interface VagaDAO extends CrudRepository<Vaga, Long>{
	Vaga findById(long id);
	List<Vaga> findByEmpresa(Empresa empresa);
	List<Vaga> findAll();
	Vaga save(Vaga vaga);
	void deleteById(Long id);
}