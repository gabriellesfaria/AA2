package br.ufscar.dc.dsw.dao;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import br.ufscar.dc.dsw.domain.Profissional;


@SuppressWarnings("unchecked")
public interface ProfissionalDAO extends CrudRepository<Profissional, Long>{
	Profissional findById(long id);
	List<Profissional> findAll();
	Profissional save(Profissional profissional);
	void deleteById(Long id);
    @Query("SELECT p FROM Profissional p WHERE p.email = :email")
    public Profissional getProfissionalByEmail(@Param("email") String email);
}
