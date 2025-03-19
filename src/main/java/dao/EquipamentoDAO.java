package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Equipamento;

public class EquipamentoDAO {

	private static EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("sistema_reservas");
	
	private static EntityManager em = emf.createEntityManager();
	
	//Insere um novo registro no banco de dados
	public void salvar (Equipamento equipamento) {
		em.getTransaction().begin();
		em.persist(equipamento);
		em.getTransaction().commit();
	}
	
	//Buscar um equipamento por ID
	public Equipamento buscarPorId(int id) {
		return em.find(Equipamento.class, id);
	}
	
	//Atualiza os dados de um equipamento no bd, caso não exista cria uma nova
	public void atualizar(Equipamento equipamento) {
		em.getTransaction().begin();
		em.merge(equipamento);
		em.getTransaction().commit();
	}
	
	//Remove um registro do bd
	public void remover(int id) {
		Equipamento equipamento = em.find(Equipamento.class, id);
		
		//Valida se existe equipamento com o id
		if (equipamento != null) {
			em.getTransaction().begin();
			em.remove(equipamento);
			em.getTransaction().commit();
		}
	}
	
	//Lista todos os registros de uma entidade
	public List<Equipamento> listarTodos(){
		String jpql = "SELECT u FROM Equipamento u";
		TypedQuery<Equipamento> query = em.createQuery(jpql, Equipamento.class);
		return query.getResultList();
	}
	
	 // Fechar o EntityManager
    public void fechar() {
        em.close();
        emf.close();
    }
}
