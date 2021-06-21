package br.ufscar.dc.dsw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.dao.EmpresaDAO;
import br.ufscar.dc.dsw.domain.Empresa;

public class EmpresaService {
	@Autowired
	EmpresaDAO dao;
	
	public void salvar(Empresa empresa) {
		dao.save(empresa);
	}

	public void excluir(Long id) {
		dao.deleteById(id);
	}

	@Transactional(readOnly = true)
	public Empresa buscarPorId(Long id) {
		return dao.findById(id.longValue());
	}

	@Transactional(readOnly = true)
	public List<Empresa> buscarTodos() {
		return dao.findAll();
	}
	
	@Transactional(readOnly = true)
	public boolean empresaTemVaga(Long id) {
		return !dao.findById(id.longValue()).getVagas().isEmpty(); 
	}
}
