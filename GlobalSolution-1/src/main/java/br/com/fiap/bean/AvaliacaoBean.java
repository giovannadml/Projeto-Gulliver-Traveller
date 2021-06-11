package br.com.fiap.bean;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;

import br.com.fiap.dao.AvaliacaoDAO;
import br.com.fiap.dao.impl.AvaliacaoDAOImpl;
import br.com.fiap.singleton.EntityManagerFactorySingleton;

@Named
@RequestScoped
public class AvaliacaoBean {
	
	EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
	FacesContext context = FacesContext.getCurrentInstance();
	
	private AvaliacaoDAO avalDAO = new AvaliacaoDAOImpl(em);

	public int MediaRating(int cdHotel) {
		try{
			int resultado = (int) avalDAO.mediaAvaliacoes(cdHotel);
			return resultado;
		} catch (Exception e) {
			return 0;
		}

	}

	public AvaliacaoDAO getAvalDAO() {
		return avalDAO;
	}

	public void setAvalDAO(AvaliacaoDAO avalDAO) {
		this.avalDAO = avalDAO;
	}
	
}
