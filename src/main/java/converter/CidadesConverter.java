package converter;

import java.io.Serializable;

import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import model.Cidade;


@FacesConverter(forClass = Cidade.class, value = "cidadesConverter")
public class CidadesConverter implements Converter, Serializable{
	
	private static final long serialVersionUID = -2679166109630976699L;
	
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String codigoCidade) {
		
		EntityManager manager = CDI.current().select(EntityManager.class).get();
				
		Cidade cidades = (Cidade) manager.find(Cidade.class, Long.parseLong(codigoCidade));
		
		return cidades;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object cidade) {
		
		if (cidade == null) {
			return null; 
		}
		
		if (cidade instanceof Cidade) {
			return ((Cidade) cidade).getId().toString();
		} else {
			return cidade.toString();
		}
		
		
	}

}
