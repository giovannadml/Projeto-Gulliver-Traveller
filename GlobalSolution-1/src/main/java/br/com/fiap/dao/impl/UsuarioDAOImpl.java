package br.com.fiap.dao.impl;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.entity.Usuario;
import br.com.fiap.exception.EntityNotFoundException;

public class UsuarioDAOImpl extends GenericDAOImpl<Usuario, Integer> implements UsuarioDAO {

	FacesContext context = FacesContext.getCurrentInstance();
	
	public UsuarioDAOImpl(EntityManager em) {
		super(em);
	}


	public Usuario BuscarPorId(int codigo) {
		
		UsuarioDAO userDAO = new UsuarioDAOImpl(em);
		try {
			Usuario usuario = userDAO.findById(codigo);
			return usuario;
		} catch (EntityNotFoundException e) {
			System.out.println("Deu ruim");
			return null;
		}

	}
	
	@Override
	public boolean existe(String email) {
		TypedQuery<Usuario> query = em.createQuery("from Usuario u where u.email = :email", Usuario.class)
				.setParameter("email", email);
		try {
			query.getSingleResult();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public String criar(Usuario usuario) {
		try {
			em.getTransaction().begin();
			em.persist(usuario);
			em.getTransaction().commit();
			context.getExternalContext().getSessionMap().put("usuario", usuario);
			return "index?faces-redirect=true";
		} catch (Exception e) {
			context.getExternalContext().getFlash().setKeepMessages(true);
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha ao cadastrar usuário", "Erro"));
			return "cadastro?faces-redirect=true";
		}
	}
	
//	método para cadastrar um usuário pela api
	public void create(Usuario usuario) {
		em.getTransaction().begin();
		em.persist(usuario);
		em.getTransaction().commit();
	}
	
	@Override
	public void delete(int codigo) {
		em.getTransaction().begin();
		em.createQuery("DELETE from Avaliacao a where a.usuario.codigo = :c").setParameter("c", codigo).executeUpdate();
		em.createQuery("DELETE from Usuario u where u.codigo= :id").setParameter("id", codigo).executeUpdate();
		em.getTransaction().commit();
		System.out.println("Apagou?");

	}
	
	@Override
	public List<Usuario> listarTodos() {
		return em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
	}

	
	@Override
	public void update(Usuario usuario) {
		em.merge(usuario);
		em.flush();
		em.getTransaction().commit();
	}

}
