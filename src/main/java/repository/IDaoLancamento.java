package repository;

import java.util.List;

import javax.faces.model.SelectItem;

import model.Lancamento;

public interface IDaoLancamento {

	List<Lancamento> consultarTodos();
	
	List<Lancamento> consultarLimite5();
	
	List<SelectItem> listaEstados();
	
	List<SelectItem> listaUsuarios();
}
