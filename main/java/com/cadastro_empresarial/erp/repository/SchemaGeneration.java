package com.cadastro_empresarial.erp.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.cadastro_empresarial.erp.model.Empresa;

public class SchemaGeneration {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mypersistenceunit");
		
		EntityManager em = emf.createEntityManager();
		
		List<Empresa> empresas = em.createQuery("from Empresa", Empresa.class).getResultList();
		
		System.out.println(empresas);
		
		em.close();
		emf.close();
		
	}

}
