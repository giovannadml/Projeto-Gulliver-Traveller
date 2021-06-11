package br.com.fiap.dao.impl;

import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;

import br.com.fiap.dao.GenericDAO;
import br.com.fiap.exception.CommitException;
import br.com.fiap.exception.EntityNotFoundException;

public class GenericDAOImpl<E, K> implements GenericDAO<E, K> {

	protected EntityManager em;
	
	private Class<E> classTeste;
	
	@SuppressWarnings("unchecked")
	public GenericDAOImpl(EntityManager em) {
		this.em = em;
		this.classTeste = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public void transaction() {
		em.getTransaction().begin();
	}
	
	@Override
	public void create(E entidade) {
		em.persist(entidade);		
	}

	@Override
	public E findById(K id) throws EntityNotFoundException {
		
		E entidade = em.find(classTeste, id);
		if(entidade == null) {
			throw new EntityNotFoundException();
		}
		return entidade;
	}

	@Override
	public void update(E entidade) {
		em.merge(entidade);
		em.flush();
		
	}

	@Override
	public void delete(K id) throws EntityNotFoundException {
		E entidade = findById(id);
		em.remove(entidade);
		
	}

	@Override
	public void commit() throws CommitException {
		try {
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
			throw new CommitException();
		}
		
	}

	
}