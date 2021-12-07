package model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Pessoa implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id; 
	
	private String usuario; 
	
	private String senha;
	
	private String nomeCompleto; 
	
	private String email; 
	
	private String sexo; 
	
	private String cep; 
	
	private String numeroResidencial;
	
	private String logradouro;
	
	private String bairro;
	
	private String localidade;
	
	private String uf;
	
	private String perfil; 

	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	private Date dtaCadastro; 

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getNumeroResidencial() {
		return numeroResidencial;
	}

	public void setNumeroResidencial(String numeroResidencial) {
		this.numeroResidencial = numeroResidencial;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String cidade) {
		this.localidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}
	
	public Date getDtaCadastro() {
		return dtaCadastro;
	}
	
	public void setDtaCadastro(Date dtaCadastro) {
		this.dtaCadastro = dtaCadastro;
	}

	public String getPerfil() {
		return perfil;
	}
	
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		return Objects.equals(id, other.id);
	} 
	
	
	
	

}
