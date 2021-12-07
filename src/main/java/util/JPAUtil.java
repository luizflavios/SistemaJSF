package util;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class JPAUtil {
	
	private static EntityManagerFactory managerFactory; 
	
	public JPAUtil() {
		if(managerFactory == null) {
			managerFactory = Persistence.createEntityManagerFactory("sistemabaseadmjsf"); 
		}	
	}
	
	@Produces
	@RequestScoped
	public EntityManager getEntityManager() {		
		return managerFactory.createEntityManager();
	}
	
	public Object getPrimaryKey(Object entity) {
		return managerFactory.getPersistenceUnitUtil().getIdentifier(entity); 
	}

}
