package br.com.fiap.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Hotel {
	
	@Id @Column(name="cd_hotel")
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int codigo;
	
	//Relacionamento one-to-one com Endereço - lado dono
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="cd_endereco", nullable = false)
	private Endereco endereco;
	
	//Mapeamento do relacionamento um-para-muitos com avaliacao - lado não dono
	@OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Avaliacao> avaliacoes;
	
	@Column(name="ds_categoria", nullable=false)
	private String categoria;
	
	@Column(name="nm_hotel", nullable=false, length=50)
	private String nome;
	
	@Column(name="ds_hotel", nullable=false, length=2000)
	private String descricao;
	
	@Column(name="vl_estadia", nullable=false)
	private int valorEstadia;
	
	@Column(name="vl_taxa", nullable=false)
	private int valorTaxa;
	
	@Column(name="nr_telefone", nullable=false, length=11)
	private String telefone;
	
	@Column(name="ds_link", nullable=false, length=2000)
	private String link;
	
	@Column(name="ds_distancia_paulista", nullable=false, length=10)
	private String distanciaPaulista;
	
	@Column(name="ds_mapa", nullable=false, length=2000)
	private String mapa;
	
	@Column(name="ds_foto", nullable=false, length=2000)
	private String foto;
	
	public Hotel () {}
	
	


	public Hotel(Endereco endereco, String categoria, String nome, String descricao,
			int valorEstadia, int valorTaxa, String telefone, String link, String distanciaPaulista, String mapa,
			String foto) {
		super();
		this.endereco = endereco;
		this.categoria = categoria;
		this.nome = nome;
		this.descricao = descricao;
		this.valorEstadia = valorEstadia;
		this.valorTaxa = valorTaxa;
		this.telefone = telefone;
		this.link = link;
		this.distanciaPaulista = distanciaPaulista;
		this.mapa = mapa;
		this.foto = foto;
	}




	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}


	public Endereco getEndereco() {
		return endereco;
	}


	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}


	public List<Avaliacao> getAvaliacoes() {
		return avaliacoes;
	}


	public void setAvaliacoes(List<Avaliacao> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}


	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public int getValorEstadia() {
		return valorEstadia;
	}


	public void setValorEstadia(int valorEstadia) {
		this.valorEstadia = valorEstadia;
	}


	public int getValorTaxa() {
		return valorTaxa;
	}


	public void setValorTaxa(int valorTaxa) {
		this.valorTaxa = valorTaxa;
	}


	public String getTelefone() {
		return telefone;
	}


	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}


	public String getLink() {
		return link;
	}


	public void setLink(String link) {
		this.link = link;
	}

	public String getDistanciaPaulista() {
		return distanciaPaulista;
	}

	public void setDistanciaPaulista(String distanciaPaulista) {
		this.distanciaPaulista = distanciaPaulista;
	}

	public String getMapa() {
		return mapa;
	}

	public void setMapa(String mapa) {
		this.mapa = mapa;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	

}
