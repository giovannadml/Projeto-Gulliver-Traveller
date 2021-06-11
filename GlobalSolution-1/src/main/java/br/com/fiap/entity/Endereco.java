package br.com.fiap.entity;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class Endereco {
	
	@Id @Column(name="cd_endereco")
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int codigo;
	
	@Column(name="ds_cep", nullable=false, length=9)
	private String cep;
	
	//Relacionamento one-to-one com hotel - lado não-dono
	@OneToOne(mappedBy = "endereco", cascade = CascadeType.PERSIST)
	private Hotel hotel;
	
	@Column(name="nm_rua", nullable=false, length=50)
	private String nome;
	
	@Column(name="nr_endereco", nullable=false, length=10)
	private String numeroEndereco;
	
	@Column(name="nm_bairro", nullable=false, length=50)
	private String bairro;
	
	@Column(name="nm_cidade", nullable=false, length=50)
	private String cidade;
	
	@Column(name="sg_estado", nullable=false, length=2)
	private String estado;
	
	@Column(name="ds_latitude", nullable=false)
	private Float latitude;
	
	@Column(name="ds_longitude", nullable=false)
	private Float longitude;
	
	
	public Endereco () {}

	public Endereco(String cep, String nome, String numeroEndereco, String bairro, String cidade, String estado,
			Float latitude, Float longitude) {
		super();
		this.cep = cep;
		this.nome = nome;
		this.numeroEndereco = numeroEndereco;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.latitude = latitude;
		this.longitude = longitude;
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


	public String getCep() {
		return cep;
	}


	public void setCep(String cep) {
		this.cep = cep;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getNumeroEndereco() {
		return numeroEndereco;
	}


	public void setNumeroEndereco(String numeroEndereco) {
		this.numeroEndereco = numeroEndereco;
	}


	public String getBairro() {
		return bairro;
	}


	public void setBairro(String bairro) {
		this.bairro = bairro;
	}


	public String getCidade() {
		return cidade;
	}


	public void setCidade(String cidade) {
		this.cidade = cidade;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public Float getLatitude() {
		return latitude;
	}


	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}


	public Float getLongitude() {
		return longitude;
	}


	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}



}
