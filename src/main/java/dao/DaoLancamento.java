package dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.Estado;
import model.Lancamento;
import model.Pessoa;
import repository.IDaoLancamento;

@Named
@SuppressWarnings("unchecked")
public class DaoLancamento implements IDaoLancamento, Serializable {
		
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager; 

	@Override
	public List<Lancamento> consultarTodos() {
		
		List<Lancamento> retorno = null;		
		EntityTransaction transaction = manager.getTransaction(); 
		transaction.begin();			
		retorno = manager.createQuery("FROM Lancamento ORDER BY id").getResultList();		
		transaction.commit();
		return retorno;
	}

	
	@Override
	public List<SelectItem> listaEstados() {
	
		List<SelectItem> selectItens = new ArrayList<SelectItem>(); 
		EntityTransaction transaction = manager.getTransaction(); 
		transaction.begin();
		List<Estado> retorno = manager.createQuery("FROM Estado ORDER BY id").getResultList();
		
		for (Estado estados : retorno) {
			selectItens.add(new SelectItem(estados, estados.getSigla()));
		}
		
		transaction.commit();
		return selectItens;
	}


	@Override
	public List<SelectItem> listaUsuarios() {
		List<SelectItem> selectItens = new ArrayList<SelectItem>(); 
		EntityTransaction transaction = manager.getTransaction(); 
		transaction.begin();
		
		List<Pessoa> retorno = manager.createQuery("FROM Pessoa ORDER BY id").getResultList();
		
		for (Pessoa pessoa : retorno) {
			selectItens.add(new SelectItem(pessoa, pessoa.getNomeCompleto()));
		}
		
		transaction.commit();
		return selectItens;
		
	}


	@Override
	public List<Lancamento> consultarLimite5() {
		List<Lancamento> retorno = null;		
		EntityTransaction transaction = manager.getTransaction(); 
		transaction.begin();			
		retorno = manager.createQuery("FROM Lancamento ORDER BY id ASC").setMaxResults(5).getResultList();		
		transaction.commit();
		return retorno;
	}
	
	

}
