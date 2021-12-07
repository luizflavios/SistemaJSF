package dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.Upload;
import repository.IDaoUpload;

@Named
@SuppressWarnings("unchecked")
public class DaoUpload implements IDaoUpload, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager; 
	
	@Override
	public List<Upload> consultarTodos() {
		
		List<Upload> retorno = null;
		EntityTransaction transaction = manager.getTransaction(); 
		transaction.begin();
		
		retorno = manager.createQuery("From Upload ORDER BY id").getResultList();
		
		transaction.commit();
		return retorno;
	}

}
