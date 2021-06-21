package br.ufscar.dc.dsw.dao;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import br.ufscar.dc.dsw.domain.Empresa;

@SuppressWarnings("unchecked")
public interface EmpresaDAO extends CrudRepository<Empresa, Long>{
	Empresa findById(long id);
	List<Empresa> findAll();
	Empresa save(Empresa empresa);
	void deleteById(Long id);
    @Query("SELECT e FROM Empresa e WHERE e.email = :email")
    public Empresa getEmpresabyEmail(@Param("email") String email);
}
