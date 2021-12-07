package repository;

import java.util.List;

import model.Pessoa;

public interface IDaoPessoa {
	
	Pessoa consultarExistenciaUsuario(String usuario, String senha); 
	
	List<Pessoa> consultaParametrizada(String palavra); 
	
	List<Pessoa> consultaTodos(); 

}
