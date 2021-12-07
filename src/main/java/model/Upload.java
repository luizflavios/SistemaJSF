package model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Upload implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id; 
	
	@Column(columnDefinition = "text") /* Gravar arquivos no banco de dados em base64*/
	private String fotoInconBase64; 
	
	@Lob /* Gravar arquivos no banco de dados*/
	@Basic(fetch = FetchType.LAZY)
	private byte[] fotoCompletaBase64;
	
	private String extensaoFoto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFotoInconBase64() {
		return fotoInconBase64;
	}

	public void setFotoInconBase64(String fotoInconBase64) {
		this.fotoInconBase64 = fotoInconBase64;
	}

	public byte[] getFotoCompletaBase64() {
		return fotoCompletaBase64;
	}

	public void setFotoCompletaBase64(byte[] fotoCompletaBase64) {
		this.fotoCompletaBase64 = fotoCompletaBase64;
	}

	public String getExtensaoFoto() {
		return extensaoFoto;
	}

	public void setExtensaoFoto(String extensaoFoto) {
		this.extensaoFoto = extensaoFoto;
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
		Upload other = (Upload) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	

}
