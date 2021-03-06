package converter;

import java.io.Serializable;

import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import model.Estado;


@FacesConverter(forClass = Estado.class, value = "estadoConverter")
public class EstadoConverter implements Converter, Serializable{

	private static final long serialVersionUID = 1L;
	
	
	/*Retorna o obj inteiro*/
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String codigoEstado) {
		
		EntityManager manager = CDI.current().select(EntityManager.class).get();
		Estado estados = (Estado) manager.find(Estado.class, Long.parseLong(codigoEstado));
		
		return estados;
	}

	/*Retorna apenas String*/
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object estado) {
		
		if (estado == null ) {
			return null; 
		}
		
		if (estado instanceof Estado) {
			return ((Estado) estado).getId().toString();
		} else {
			return estado.toString();
		}
	}
	
}