package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Reserva;
import util.JPAUtil;

public class ReservaDAO {


	
	
	//Insere um novo registro no banco de dados
	public void salvar (Reserva reserva) {
		EntityManager em = JPAUtil.getEntityManager();
		
		try {
			em.getTransaction().begin();
			em.persist(reserva);
			em.getTransaction().commit();
		} finally {
			em.close();
		}				
	}
	
	//Buscar uma reserva por ID
	public Reserva buscarPorId(int id) {
	EntityManager em = JPAUtil.getEntityManager();
		
		try {
			return em.find(Reserva.class, id);
		} finally {
			em.close();
		}		
	}
	
	//Atualiza os dados de uma reserva no bd, caso não exista cria uma nova
	public void atualizar(Reserva reserva) {
		EntityManager em = JPAUtil.getEntityManager();
		
		try {
			em.getTransaction().begin();
			em.merge(reserva);
			em.getTransaction().commit();
		} finally {
			em.close();
		}		
	}
	
	//Remove um registro do bd
	public void remover(int id) {
		EntityManager em = JPAUtil.getEntityManager();
		
		try {
			Reserva reserva = em.find(Reserva.class, id);
			
			//Valida se existe reserva com o id
			if (reserva != null) {
				em.getTransaction().begin();
				em.remove(reserva);
				em.getTransaction().commit();
			}
		} finally {
			em.close();
		}		
	}
	
	//Lista todos os registros de uma entidade
	public List<Reserva> listarTodos(){
	EntityManager em = JPAUtil.getEntityManager();
		
		try {
			String jpql = "SELECT r FROM Reserva r";
			TypedQuery<Reserva> query = em.createQuery(jpql, Reserva.class);
			return query.getResultList();
			
		} finally {
			em.close();
		}		
	}	
	
}
