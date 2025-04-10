package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Sala;
import util.JPAUtil;

public class SalaDAO {

	
	//Insere um novo registro no banco de dados
	public void salvar (Sala sala) {
		EntityManager em = JPAUtil.getEntityManager();
		
		try {
			em.getTransaction().begin();
			
			//Verifica se já existe uma sala com o mesmo nome
			TypedQuery<Sala> query = em
					.createQuery("SELECT s FROM Sala s WHERE s.nome = :nome", Sala.class);
			query.setParameter("nome", sala.getNome());
			List<Sala> salasExistentes = query.getResultList();
			
			if (salasExistentes.isEmpty()) {
				em.persist(sala);//Salva se não existir
				System.out.println("Sala '" + sala.getNome() + " 'inserida com sucesso.");
			} else {
				System.out.println("A sala '" + sala.getNome() + "' já está cadastrada.");
			}
			
			em.getTransaction().commit();
			
		} finally {
			em.close();
		}	
	}
	
	//Buscar uma sala por ID
	public Sala buscarPorId(int id) {
		EntityManager em = JPAUtil.getEntityManager();
		
		try {
			return em.find(Sala.class, id);
		} finally {
			em.close();
		}		
	}
	
	//Atualiza os dados de uma sala no bd, caso não exista cria uma nova
	public void atualizar(Sala sala) {
		EntityManager em = JPAUtil.getEntityManager();
		
		try {
			em.getTransaction().begin();
			em.merge(sala);
			em.getTransaction().commit();
		} finally {
			em.close();
		}		
	}
	
	//Remove um registro do bd
	public void remover(int id) {
		
	EntityManager em = JPAUtil.getEntityManager();
		
		try {
			Sala sala = em.find(Sala.class, id);
			
			//Valida se existe sala com o id
			if (sala != null) {
				em.getTransaction().begin();
				em.remove(sala);
				em.getTransaction().commit();
			}
		} finally {
			em.close();
		}	
	}
	
	//Lista todos os registros de uma entidade
	public List<Sala> listarTodos(){
		EntityManager em = JPAUtil.getEntityManager();
		
		try {
			String jpql = "SELECT s FROM Sala s";
			TypedQuery<Sala> query = em.createQuery(jpql, Sala.class);
			return query.getResultList();
			
		} finally {
			em.close();
		}		
	}	
	
}
