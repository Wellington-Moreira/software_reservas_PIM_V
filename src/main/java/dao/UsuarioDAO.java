package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import model.Usuario;
import util.JPAUtil;

public class UsuarioDAO {
	
	//Insere um novo registro no banco de dados
	public void salvar (Usuario usuario) {
		EntityManager em = JPAUtil.getEntityManager();
		
		try {
			em.getTransaction().begin();
			em.persist(usuario);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
		
	}
	
	//Buscar um usuário por ID
	public Usuario buscarPorId(int id) {
		EntityManager em = JPAUtil.getEntityManager();
		
		try {
			return em.find(Usuario.class, id);
		} finally {
			em.close();
		}		
	}
	
	//Buscar um usuário por Login
	public Usuario buscarPorLogin (String login) {
		EntityManager em = JPAUtil.getEntityManager();
		Usuario usuario = null;
		
		try {
			usuario = em.createQuery("SELECT u FROM Usuario u WHERE u.login = :login", Usuario.class)
					.setParameter("login", login)
					.getSingleResult();
			
		} catch (NoResultException e) {
			// Nenhum usuário com esse login
		} finally {
			em.close();
		}
		return usuario;		
	}
	
	//Atualiza os dados de um usuario no bd, caso não exista cria uma nova
	public void atualizar(Usuario usuario) {
		EntityManager em = JPAUtil.getEntityManager();
		
		try {
			em.getTransaction().begin();
			em.merge(usuario);
			em.getTransaction().commit();
			
		} finally {
			em.close();
		}		
	}
	
	//Remove um registro do bd
	public void remover(int id) {
		EntityManager em = JPAUtil.getEntityManager();
		
		try {
			Usuario usuario = em.find(Usuario.class, id);
			//Valida se existe usuario com o id
			if (usuario != null) {
				em.getTransaction().begin();
				em.remove(usuario);
				em.getTransaction().commit();
			}
		} finally {
			em.close();
		}		
		
	}
	
	//Lista todos os registros de uma entidade
	public List<Usuario> listarTodos(){
		EntityManager em = JPAUtil.getEntityManager();
		try {
			String jpql = "SELECT u FROM Usuario u";
			TypedQuery<Usuario> query = em.createQuery(jpql, Usuario.class);
			return query.getResultList();
		} finally {
			em.close();
		}
		
	}
	
}
