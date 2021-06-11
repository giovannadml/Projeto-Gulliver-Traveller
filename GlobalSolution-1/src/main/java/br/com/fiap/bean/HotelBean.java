package br.com.fiap.bean;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;

import br.com.fiap.dao.AvaliacaoDAO;
import br.com.fiap.dao.HotelDAO;
import br.com.fiap.dao.impl.AvaliacaoDAOImpl;
import br.com.fiap.dao.impl.HotelDAOImpl;
import br.com.fiap.entity.Avaliacao;
import br.com.fiap.entity.Hotel;
import br.com.fiap.entity.Usuario;
import br.com.fiap.exception.CommitException;
import br.com.fiap.singleton.EntityManagerFactorySingleton;
import br.com.fiap.util.DataUtil;

@Named
@RequestScoped
public class HotelBean {

	EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
	FacesContext context = FacesContext.getCurrentInstance();
	
	private Hotel hotel = new Hotel();
	private HotelDAO hotelDAO = new HotelDAOImpl(em);

	private Avaliacao avaliacao = new Avaliacao();
	private AvaliacaoDAO avalDAO = new AvaliacaoDAOImpl(em);
	
	String vessel = "";
	List<Hotel> listaGlobe = null;
	
	public List<Hotel> getHoteis(){
		if (vessel.equals("")) {
			return hotelDAO.getAll();
		} else {
			return listaGlobe;
		}
	}
	
	public String carregarHotel(Hotel hotel) {
		
		String pagina = context.getViewRoot().getViewId();
		
		if(context.getExternalContext().getSessionMap().get("hotel") != null && pagina.equals("/hotel.xhtml")) {
			context.getExternalContext().getSessionMap().remove("hotel");
		}
		context.getExternalContext().getSessionMap().put("hotel", hotel);
		
		System.out.println("codigo: " + hotel.getCodigo());
		
		return "hotel?faces-redirect=true";
		
		
	}
	
	
	public List<Hotel> buscarCategoria(String categoria) {

		vessel = categoria;
		listaGlobe = hotelDAO.buscarPorCategoria(categoria);
//		listaGlobe = new ArrayList<Hotel>(listaHotel);
		
		listaGlobe.forEach(h -> System.out.println("código: " + h.getCodigo()));

		return getHoteis();

	}
	
	
	public void criarAvaliacao(Hotel hotel) {
		Usuario user = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
				
		avaliacao.setUsuario(user);
		avaliacao.setHotel(hotel);
		avaliacao.setDataRegistro(DataUtil.dataAtual());
		
		avalDAO.transaction();
		avalDAO.create(avaliacao);
		try {
			avalDAO.commit();
			context.addMessage(null, new FacesMessage("Avaliação cadastrada com sucesso!"));
		} catch (CommitException e) {
			System.out.println("Falha ao salvar avaliação.");
		}
	}
	
	public List<Avaliacao> getAvaliacoes() {
		Hotel hotel = (Hotel) context.getExternalContext().getSessionMap().get("hotel");
		return avalDAO.listar(hotel.getCodigo());
	}
	
	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

}
