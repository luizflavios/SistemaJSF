package model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Lancamento implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id; 
	
	private String empresaOrigem; 
	
	private String empresaDestino; 
	
	private String tipoServico; 
	
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	private Date dataCadastro;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	private Pessoa usuarioLancamento;
	
	@Transient 
	private Estado estados; 
	
	@ManyToOne
	private Cidade cidades;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmpresaOrigem() {
		return empresaOrigem;
	}

	public void setEmpresaOrigem(String empresaOrigem) {
		this.empresaOrigem = empresaOrigem;
	}

	public String getEmpresaDestino() {
		return empresaDestino;
	}

	public void setEmpresaDestino(String empresaDestino) {
		this.empresaDestino = empresaDestino;
	}

	public String getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(String tipoServico) {
		this.tipoServico = tipoServico;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Pessoa getUsuarioLancamento() {
		return usuarioLancamento;
	}

	public void setUsuarioLancamento(Pessoa usuarioLancamento) {
		this.usuarioLancamento = usuarioLancamento;
	}
	
	public Cidade getCidades() {
		return cidades;
	}
	
	public void setCidades(Cidade cidades) {
		this.cidades = cidades;
	}
	
	public Estado getEstados() {
		return estados;
	}
	
	public void setEstados(Estado estados) {
		this.estados = estados;
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
		Lancamento other = (Lancamento) obj;
		return Objects.equals(id, other.id);
	}
	
	
	

}
