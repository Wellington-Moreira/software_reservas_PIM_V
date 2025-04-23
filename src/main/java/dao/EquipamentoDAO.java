package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Equipamento;
import util.JPAUtil;

public class EquipamentoDAO {

		
	//Insere um novo registro no banco de dados
	public void salvar (Equipamento equipamento) {
		EntityManager em = JPAUtil.getEntityManager();
		
		try {
			em.getTransaction().begin();
			em.persist(equipamento);
			em.getTransaction().commit();
		} finally {
			em.close();
		}		
	}
	
	//Buscar um equipamento por ID
	public Equipamento buscarPorId(int id) {		
		EntityManager em = JPAUtil.getEntityManager();
		
		try {
			Equipamento equipamento = em.find(Equipamento.class, id);
			equipamento.getReservas().size(); // Força inicialização da lista
			return equipamento;
		} finally {
			em.close();
		}				
	}
	
	//Atualiza os dados de um equipamento no bd, caso não exista cria uma nova
	public void atualizar(Equipamento equipamento) {
		EntityManager em = JPAUtil.getEntityManager();
		
		try {
			em.getTransaction().begin();
			em.merge(equipamento);
			em.getTransaction().commit();
		} finally {
			em.close();
		}				
	}
	
	//Remove um registro do bd
	public void remover(int id) {
	EntityManager em = JPAUtil.getEntityManager();
		
		try {
			Equipamento equipamento = em.find(Equipamento.class, id);
			
			//Valida se existe equipamento com o id
			if (equipamento != null) {
				em.getTransaction().begin();
				em.remove(equipamento);
				em.getTransaction().commit();
			}
		} finally {
			em.close();
		}					
	}
	
	//Lista todos os registros de uma entidade
	public List<Equipamento> listarTodos(){
		EntityManager em = JPAUtil.getEntityManager();
		
		try {
			String jpql = "SELECT e FROM Equipamento e";
			TypedQuery<Equipamento> query = em.createQuery(jpql, Equipamento.class);
			return query.getResultList();
			
		} finally {
			em.close();
		}			
	}
	
	 
}
