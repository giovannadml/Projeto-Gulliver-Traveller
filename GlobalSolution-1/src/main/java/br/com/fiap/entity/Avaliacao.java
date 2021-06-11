package br.com.fiap.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Avaliacao {
	
	@Id @Column(name="cd_avaliacao")
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int codigo;
		
	//Mapeamento do relacionamento muitos-para-um com usuário - lado dono
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name="cd_usuario", nullable = false)
	private Usuario usuario;
	
	//Mapeamento do relacionamento muitos-para-um com hotel - lado dono
	@ManyToOne
	@JoinColumn(name="cd_hotel", nullable = false)
	private Hotel hotel;

	@Column(name="nr_avaliacao", nullable=false)
	private int numeroAvaliacao;
	
	@Column(name="ds_avaliacao", nullable=false, length=300)
	private String descricao;
	
	@Column(name="dt_registro", nullable=false)
	private String dataRegistro;
	
	
	public Avaliacao () {
		
	}

	public Avaliacao(int numeroAvaliacao, String descricao, String dataRegistro) {
		super();
		this.numeroAvaliacao = numeroAvaliacao;
		this.descricao = descricao;
		this.dataRegistro = dataRegistro;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public int getNumeroAvaliacao() {
		return numeroAvaliacao;
	}
	

	public void setNumeroAvaliacao(int numeroAvaliacao) {
		this.numeroAvaliacao = numeroAvaliacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(String dataRegistro) {
		this.dataRegistro = dataRegistro;
	}



}
