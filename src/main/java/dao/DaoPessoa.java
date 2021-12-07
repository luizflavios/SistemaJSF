package dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.Pessoa;
import repository.IDaoPessoa;

@Named
public class DaoPessoa implements IDaoPessoa, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Inject
	private EntityManager manager;

	@Override
	public Pessoa consultarExistenciaUsuario(String usuario, String senha) {
		
		Pessoa pessoa = null; 
		EntityTransaction transaction = manager.getTransaction(); 
		transaction.begin();
		
		pessoa = (Pessoa) manager.createQuery("SELECT p FROM Pessoa p WHERE p.usuario= '" +usuario+"' AND p.senha= '"+senha+"'").getSingleResult();
		
		transaction.commit();
		return pessoa;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pessoa> consultaParametrizada(String palavra) {
		List<Pessoa> retorno = null; 
		EntityTransaction transaction = manager.getTransaction(); 
		transaction.begin();
		
		retorno = manager.createQuery("FROM Pessoa p WHERE UPPER (p.nomeCompleto) LIKE UPPER ('%" +palavra+ "%')").getResultList();
		
		transaction.commit();
		return retorno;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pessoa> consultaTodos() {
		List<Pessoa> retorno = null; 
		EntityTransaction transaction = manager.getTransaction(); 
		transaction.begin();
		
		retorno = manager.createQuery("From Pessoa p ORDER BY id").getResultList(); 
		
		transaction.commit();	
		
		return retorno;
	}

}
