package br.com.fiap.dao.impl;

import java.util.List;

import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.dao.AvaliacaoDAO;
import br.com.fiap.entity.Avaliacao;

public class AvaliacaoDAOImpl extends GenericDAOImpl<Avaliacao, Integer> implements AvaliacaoDAO {

	public AvaliacaoDAOImpl(EntityManager em) {
		super(em);
	}

	@Override
	public void transaction() {
		em.getTransaction().begin();
	}
	
	@Override
	public void create(Avaliacao avaliacao) {
		em.persist(avaliacao);
	}
	
	@Override
	public List<Avaliacao> listar(int codigo) {
		
		TypedQuery<Avaliacao> query = em.createQuery("from Avaliacao c where c.hotel.codigo = :c order "
				+ "by c.numeroAvaliacao desc", Avaliacao.class)
				.setParameter("c", codigo);
		String pagina = FacesContext.getCurrentInstance().getViewRoot().getViewId();
		if(pagina.equals("/hotel.xhtml")) {
			query.setMaxResults(6);
		}
		return query.getResultList();
	}

	@Override
	public double mediaAvaliacoes(int codigo) {
		
		TypedQuery<Double> query = em.createQuery("select floor(avg(a.numeroAvaliacao)) from Avaliacao a where a.hotel.codigo = :c", Double.class);
		query.setParameter("c", codigo);
		return query.getSingleResult();
	
		
	}	

}


