package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import model.Pessoa;
import repository.IDaoPessoa;

@ViewScoped
@Named(value = "beanRelatorios")
public class BeanRelatorios implements Serializable {


	private static final long serialVersionUID = 1L;

	private List<Pessoa> usuarios = new ArrayList<Pessoa>();
	
	@Inject
	private IDaoPessoa daoPessoa; 
	
	
	public void buscar() {
		usuarios = daoPessoa.consultaTodos();
	}
	
	
	public List<Pessoa> getUsuarios() {
		return usuarios;
	}
	
	public void setUsuarios(List<Pessoa> usuarios) {
		this.usuarios = usuarios;
	}
	
}
