package com.cadastro_empresarial.erp.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.cadastro_empresarial.erp.model.Empresa;

/**
 * 	Implementa query via JPQL
 *	
 */

public class Empresas implements Serializable {

	private static final long serialVersionUID = 1L;

	private EntityManager manager;	// Interface que interage com contexto de persistência.
	
	public Empresas() {
	}

	public Empresas(EntityManager manager) {
		this.manager = manager;
	}
	
	public Empresa porId(Long id) {
		return manager.find(Empresa.class, id);
	}
	
	public List<Empresa> pesquisar(String nome) {
		String jpql = "from Empresa where nomeFantasia like :nomeFantasia";	// JPQL é um código mais limpo
		
		TypedQuery<Empresa> query = manager.createQuery(jpql, Empresa.class);
		query.setParameter("nomeFantasia", nome + "%");
		
		return query.getResultList();
	}
	
	public Empresa guardar(Empresa empresa) {
		return manager.merge(empresa);
	}
	
	public void remove(Empresa empresa) {
		empresa = porId(empresa.getId());
		manager.remove(empresa);
	}
	
}
