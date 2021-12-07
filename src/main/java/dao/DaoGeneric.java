package dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import util.JPAUtil;

@Named
@SuppressWarnings("unchecked")
public class DaoGeneric<E> implements Serializable{
	
	
	
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager; 
	
	@Inject
	private JPAUtil jpaUtil;
	
	public void salvar(E e) {		
		
		EntityTransaction transaction = manager.getTransaction(); 
		transaction.begin();		
		manager.persist(e);
		transaction.commit();		
		
	}
	
	/* Salva e retorna a entidade */
	
	public E salvarMerge(E e) {			
		EntityTransaction transaction = manager.getTransaction(); 
		transaction.begin();		
		E retorno = manager.merge(e);
		transaction.commit();	
		return retorno; 
	}
	
	public void delete(E e) {		
		EntityTransaction transaction = manager.getTransaction(); 
		transaction.begin();
		
		manager.remove(manager.contains(e) ? e : manager.merge(e));
		transaction.commit();	
		
	}

	
	public void deletePorId(E e) {
				
		EntityTransaction transaction = manager.getTransaction(); 
		transaction.begin();		
		Object id = jpaUtil.getPrimaryKey(e);		
		manager.createQuery("delete from " + e.getClass().getSimpleName() + " where id=" + id).executeUpdate(); 
		transaction.commit();		
		
	}
	
	public List<E> getListEntity(E e) {		
		
		EntityTransaction transaction = manager.getTransaction(); 
		transaction.begin();		
		List<E> lista = manager.createQuery("FROM " + e.getClass().getSimpleName() + " ORDER BY id").setMaxResults(5).getResultList();		
		transaction.commit();
		return lista; 
		
	}
	
	public E consultarEntidade(Class<E> entidade, String pk) {		
		
		EntityTransaction transaction = manager.getTransaction(); 
		transaction.begin();		
		E objeto = (E) manager.find(entidade, Long.parseLong(pk)); 		
		transaction.commit();
		return objeto;
		
	}

}
