package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Usuario;

public class UsuarioDAO {

	private static EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("sistema_reservas");
	
	private static EntityManager em = emf.createEntityManager();
	
	//Insere um novo registro no banco de dados
	public void salvar (Usuario usuario) {
		em.getTransaction().begin();
		em.persist(usuario);
		em.getTransaction().commit();
	}
	
	//Buscar um usuário por ID
	public Usuario buscarPorId(int id) {
		return em.find(Usuario.class, id);
	}
	
	//Atualiza os dados de um usuario no bd, caso não exista cria uma nova
	public void atualizar(Usuario usuario) {
		em.getTransaction().begin();
		em.merge(usuario);
		em.getTransaction().commit();
	}
	
	//Remove um registro do bd
	public void remover(int id) {
		Usuario usuario = em.find(Usuario.class, id);
		
		//Valida se existe usuario com o id
		if (usuario != null) {
			em.getTransaction().begin();
			em.remove(usuario);
			em.getTransaction().commit();
		}
	}
	
	//Lista todos os registros de uma entidade
	public List<Usuario> listarTodos(){
		String jpql = "SELECT u FROM Usuario u";
		TypedQuery<Usuario> query = em.createQuery(jpql, Usuario.class);
		return query.getResultList();
	}
	
	 // Fechar o EntityManager
    public void fechar() {
        em.close();
        emf.close();
    }
}
