package util;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import model.TipoUsuario;
import model.Usuario;

public class DatabaseSeeder {
	
	public static void criarAdminPadrao() {
		EntityManager em = JPAUtil.getEntityManager();
		
		try {
			TypedQuery<Usuario> query = em.createQuery(
					"SELECT u FROM Usuario u WHERE u.login = :login", Usuario.class);
			query.setParameter("login", "admin");
			query.getSingleResult();
			System.out.println("Usuário admin já existe.");
		} catch (NoResultException e) {
			System.out.println("Criando usuário admin padrão.");
			em.getTransaction().begin();
			
			Usuario admin = new Usuario();
			admin.setNome("Administrador");
			admin.setLogin("admin");
			admin.setSenha("admin123");
			admin.setTipo(TipoUsuario.ADMIN);
			
			em.persist(admin);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

}
