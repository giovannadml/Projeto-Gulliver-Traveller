package br.com.fiap.util;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.com.fiap.entity.Usuario;

public class AuthorizationListener implements PhaseListener {

	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent event) {
		FacesContext context = FacesContext.getCurrentInstance();
		
		String pagina = context.getViewRoot().getViewId();
		if(pagina.equals("/login.xhtml") || pagina.equals("/cadastro.xhtml")) return;		
		
		Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
//		Se o usuário não estiver nulo ele é autorizado para acessar o link desejado
		if (usuario != null) return;
		
//		Caso contrário, a pessoa é direcionada novamente para a tela de login
		NavigationHandler navigationHandler = context.getApplication().getNavigationHandler();
		navigationHandler.handleNavigation(context, "", "login?faces-redirect=true");
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
