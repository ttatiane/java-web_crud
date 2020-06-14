package com.cadastro_empresarial.erp.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.cadastro_empresarial.erp.model.RamoAtividade;

/**
 *	Implementa query via Criteria 
 *
 */
public class RamoAtividades implements Serializable {

	private static final long serialVersionUID = 1L;

	private EntityManager manager;	// Interface que interage com contexto de persistência.
	
	public RamoAtividades() {
	}

	public RamoAtividades(EntityManager manager) {
		this.manager = manager;
	}
	
	public List<RamoAtividade> pesquisar(String descricao) {
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();	//Criteria facilita encontrar erro de ortografia em tempo de compilação
		
		CriteriaQuery<RamoAtividade> criteriaQuery = criteriaBuilder.createQuery(RamoAtividade.class);
		Root<RamoAtividade> root = criteriaQuery.from(RamoAtividade.class);
		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.like(root.get("descricao"), descricao + "%"));
		
		TypedQuery<RamoAtividade> query = manager.createQuery(criteriaQuery);
		
		return query.getResultList();
	}
	
}
