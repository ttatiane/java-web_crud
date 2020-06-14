package com.cadastro_empresarial.erp.util;

import java.io.Serializable;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.transaction.Transactional;

/**
 * 
 * CDI (Contexts and Dependency Injection) = Framework para injeção de dependências.
 * 
 * Dependências = nesse contexto é toda instância de uma Classe Java. 
 * 
 * E o CDI instancia as nossas classes de forma automática. E com isso 
 * conseguimos fazer uso do Interceptor.
 * 
 * Utilizaremos o recurso de interceptação para abrir e fechar as transações 
 * com o DB (JPA) de forma transparente.  
 * 
 * Configurado o Interceptador e conjunto com as 3 classes: 
 * EntityManagerProducer, Transacional, TransacionalInterceptor.
 *
 */

@Interceptor
@Transactional
@Priority(Interceptor.Priority.APPLICATION)
public class TransacionalInterceptor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;
	
	@AroundInvoke
	public Object Invoke(InvocationContext context) throws Exception {
		EntityTransaction trx = manager.getTransaction();
		boolean criador = false;
		
		try {
			if (!trx.isActive()) {
				//truque para fazer rollback no que já passou
				//caso contrário um futuro commit confirmaria operações sem transação.
				trx.begin();
				trx.rollback();
				
				//agora, inicia a transação
				trx.begin();
				
				criador = true;
				
			}
			
			return context.proceed();
			
		} catch (Exception e) {
			if (trx != null && criador) {
				trx.rollback();
			}
			
			throw e;
		} finally {
			if (trx != null && trx.isActive() && criador) {
				trx.commit();
			}
		}
	}
	
}
