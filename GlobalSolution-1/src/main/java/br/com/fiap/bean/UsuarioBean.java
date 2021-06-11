package br.com.fiap.bean;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.dao.impl.UsuarioDAOImpl;
import br.com.fiap.entity.Usuario;
import br.com.fiap.singleton.EntityManagerFactorySingleton;

@Named
@RequestScoped
public class UsuarioBean {

	EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
	FacesContext context = FacesContext.getCurrentInstance();

	private Usuario usuario = new Usuario();
	private UsuarioDAO userDAO = new UsuarioDAOImpl(em);
	private Usuario usuarioSenha = new Usuario();
	private Usuario usuarioNovaSenha = new Usuario();

	public String criar() {

		if(userDAO.existe(usuario.getEmail())) {
			context.getExternalContext().getFlash().setKeepMessages(true);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "E-mail já cadastrado!", "Erro"));
			return "cadastro?faces-redirect=true";
		};
		
		return userDAO.criar(this.usuario);
		
	}
	
	public String validaSenha(Usuario usuario) {
		context.getExternalContext().getFlash().setKeepMessages(true);
		try {
			this.usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
			if (this.usuario.getSenha().equals(usuarioSenha.getSenha())) {
				this.usuario.setSenha(usuarioNovaSenha.getSenha());
				em.getTransaction().begin();
				userDAO.update(this.usuario);
				System.out.println("Senha Alterada");
				context.addMessage(null, new FacesMessage("Senha de Usuário alterada com sucesso"));
				return "MinhaConta?faces-redirect=true";
			} else {
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Senha atual incorreta, tente novamente", "Erro"));
				System.out.println("Senha errada");
			}
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Não foi possível alterar a senha, tente novamente", "Erro"));
			System.out.println("Erro no banco, revisar método validarSenha: " + e);
		}
		return null;
	}

	public String editar(Usuario usuario) {
		context.getExternalContext().getFlash().setKeepMessages(true);
		
		try {
			em.getTransaction().begin();
			userDAO.update(usuario);
			context.addMessage(null, new FacesMessage("Usuário editado com sucesso"));
			return "MinhaConta?faces-redirect=true";
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Não foi possível editar as informações, tente novamente mais tarde", "Erro"));
			System.out.println("Erro: " + e);
		}
		return null;
	}

	public String login() {
		TypedQuery<Usuario> query = em
				.createQuery("SELECT u FROM Usuario u WHERE " + "email = :email AND senha = :senha", Usuario.class)
				.setParameter("email", usuario.getEmail()).setParameter("senha", usuario.getSenha());

		try {
			Usuario usuario2 = query.getSingleResult();
			context.getExternalContext().getSessionMap().put("usuario", usuario2);
		} catch (Exception e) {
			context.getExternalContext().getFlash().setKeepMessages(true);
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "E-mail ou senha inválidos", "Erro"));
			return "login?faces-redirect=true";
		}
		return "index?faces-redirect=true";

	}
	
	public String logout() {
		context.getExternalContext().getSessionMap().remove("usuario");
		return "login?faces-redirect=true";
	}

	public String removerConta() {
		this.usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
		System.out.println("Atualizou: " + usuario.getCodigo());
		try {
			userDAO.delete(usuario.getCodigo());
		} catch (Exception e) {
			context.getExternalContext().getFlash().setKeepMessages(true);
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Algo deu errado, tente novamente mais tarde!", "Erro"));
			System.out.println(e.getMessage());
			return "MinhaConta?faces-redirect=true";
		}
		return logout();
	}
	
	
	
	public Usuario getUsuarioSenha() {
		return usuarioSenha;
	}

	public void setUsuarioSenha(Usuario usuarioSenha) {
		this.usuarioSenha = usuarioSenha;
	}

	public Usuario getUsuarioNovaSenha() {
		return usuarioNovaSenha;
	}

	public void setUsuarioNovaSenha(Usuario usuarioNovaSenha) {
		this.usuarioNovaSenha = usuarioNovaSenha;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
