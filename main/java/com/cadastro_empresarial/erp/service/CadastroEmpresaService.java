package com.cadastro_empresarial.erp.service;

import java.io.Serializable;

import javax.inject.Inject;
import javax.transaction.Transactional;

import com.cadastro_empresarial.erp.model.Empresa;
import com.cadastro_empresarial.erp.repository.Empresas;

public class CadastroEmpresaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Empresas empresas;
	
	@Transactional
	public void salvar(Empresa empresa) {
		empresas.guardar(empresa);
	}
	
	@Transactional
	public void excluir(Empresa empresa) {
		empresas.remove(empresa);
	}
	
}
