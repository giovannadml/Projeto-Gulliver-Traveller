package br.com.fiap.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Usuario {

	@Id @Column(name="cd_usuario")
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int codigo;
	
	@Column(name="nm_usuario", nullable=false, length=50)
	private String nome;
	
	@Column(name="ds_email", nullable=true, length=50)
	private String email;
	
	@Column(name="ds_senha", nullable=false, length=20)
	private String senha;
	
	@Temporal(TemporalType.DATE)
	@Column(name="dt_nascimento", nullable=false)
	private Date dataNascimento;

	public Usuario() {}

	public Usuario(String nome, String email, String senha, Date dataNascimento) {
		super();
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.dataNascimento = dataNascimento;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}


}
