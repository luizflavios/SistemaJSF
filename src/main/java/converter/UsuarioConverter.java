package converter;

import java.io.Serializable;

import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import model.Pessoa;


@FacesConverter(forClass = Pessoa.class, value = "usuarioConverter")
public class UsuarioConverter implements Converter, Serializable{

	
	private static final long serialVersionUID = 1L;
	
	

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String codigoPessoa) {
		
		EntityManager manager = CDI.current().select(EntityManager.class).get();
		Pessoa pessoa = (Pessoa) manager.find(Pessoa.class, Long.parseLong(codigoPessoa));		
		
		return pessoa; 
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object pessoa) {
		
		if (pessoa == null ) {
			return null; 
		}
		
		if (pessoa instanceof Pessoa) {
			return ((Pessoa) pessoa).getId().toString();
		} else {
			return pessoa.toString();
		}
		
	}

}
