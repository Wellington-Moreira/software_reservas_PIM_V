package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static final EntityManagerFactory emf = 
			Persistence.createEntityManagerFactory("sistema_reservas");
	
	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
}
