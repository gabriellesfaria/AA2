  
package br.ufscar.dc.dsw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.dao.InscricaoDAO;
import br.ufscar.dc.dsw.domain.Inscricao;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.service.spec.IInscricaoService;

@Service
@Transactional(readOnly = false)
public class InscricaoService implements IInscricaoService {

	@Autowired
	InscricaoDAO dao;
	
	public void salvar(Inscricao inscricao) {
		dao.save(inscricao);
	}

	public void excluir(Long id) {
		dao.deleteById(id);
	}

	@Transactional(readOnly = true)
	public Inscricao buscarPorId(Long id) {
		return dao.findById(id.longValue());
	}

	@Transactional(readOnly = true)
	public List<Inscricao> buscarTodas() {
		return dao.findAll();
	}
	
	@Transactional(readOnly = true)
	public List<Inscricao> buscarPorProfissional(Profissional p) {
		return dao.findByProfissional(p);
	}
}