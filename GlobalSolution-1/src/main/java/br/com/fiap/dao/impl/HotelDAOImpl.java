package br.com.fiap.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.dao.HotelDAO;
import br.com.fiap.entity.Hotel;

public class HotelDAOImpl extends GenericDAOImpl<Hotel, Integer> implements HotelDAO {

	public HotelDAOImpl(EntityManager em) {
		super(em);
	}

	@Override
	public void create(Hotel hotel) {
		em.getTransaction().begin();
		em.persist(hotel);
	}
	
	@Override
	public Hotel buscarPorEndereco(String endereco) {
		
		TypedQuery<Hotel> query = em.createQuery("select c from Hotel c where c.endereco.nome =: c", Hotel.class);
		query.setParameter("c", endereco);
		return query.getSingleResult();
		
	}

	@Override
	public List<Hotel> getAll() {
		TypedQuery<Hotel> query = em.createQuery("select h from Hotel h", Hotel.class);
		return query.getResultList();
		
	}

	@Override
	public List<Hotel> buscarPorCategoria(String categoria) {
	
		TypedQuery<Hotel> query = em.createQuery("from Hotel c where c.categoria = :c", Hotel.class);
		query.setParameter("c", categoria);
		return query.getResultList();
	}



}
