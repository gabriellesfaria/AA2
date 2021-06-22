package br.ufscar.dc.dsw.dao;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Profissional;

@SuppressWarnings("unchecked")
public interface EmpresaDAO extends CrudRepository<Empresa, Long>{
	
	Empresa findById(long id);
	
	List<Empresa> findAll();
	
	Empresa save(Empresa empresa);
	
	void deleteById(Long id);
	
	Empresa findByEmail(String email);

}
