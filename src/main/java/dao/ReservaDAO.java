package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Reserva;

public class ReservaDAO {


	private static EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("sistema_reservas");
	
	private static EntityManager em = emf.createEntityManager();
	
	//Insere um novo registro no banco de dados
	public void salvar (Reserva reserva) {
		em.getTransaction().begin();
		em.merge(reserva);
		em.getTransaction().commit();
	}
	
	//Buscar uma reserva por ID
	public Reserva buscarPorId(int id) {
		return em.find(Reserva.class, id);
	}
	
	//Atualiza os dados de uma reserva no bd, caso não exista cria uma nova
	public void atualizar(Reserva reserva) {
		em.getTransaction().begin();
		em.merge(reserva);
		em.getTransaction().commit();
	}
	
	//Remove um registro do bd
	public void remover(int id) {
		Reserva reserva = em.find(Reserva.class, id);
		
		//Valida se existe reserva com o id
		if (reserva != null) {
			em.getTransaction().begin();
			em.remove(reserva);
			em.getTransaction().commit();
		}
	}
	
	//Lista todos os registros de uma entidade
	public List<Reserva> listarTodos(){
		String jpql = "SELECT u FROM Reserva u";
		TypedQuery<Reserva> query = em.createQuery(jpql, Reserva.class);
		return query.getResultList();
	}
	
	 // Fechar o EntityManager
    public void fechar() {
        em.close();
        emf.close();
    }
}
