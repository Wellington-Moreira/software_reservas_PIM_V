package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Sala;

public class SalaDAO {

	private static EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("sistema_reservas");
	
	private static EntityManager em = emf.createEntityManager();
	
	//Insere um novo registro no banco de dados
	public void salvar (Sala sala) {
		em.getTransaction().begin();
		
		//Verifica se já existe uma sala com o mesmo nome
		TypedQuery<Sala> query = em
				.createQuery("SELECT s FROM Sala s WHERE s.nome = :nome", Sala.class);
		query.setParameter("nome", sala.getNome());
		List<Sala> salasExistentes = query.getResultList();
		
		if (salasExistentes.isEmpty()) {
			em.merge(sala);//Salva se não existir
			System.out.println("Sala '" + sala.getNome() + " 'inserida com sucesso.");
		} else {
			System.out.println("A sala '" + sala.getNome() + "' já está cadastrada.");
		}
		
		em.getTransaction().commit();
	}
	
	//Buscar uma sala por ID
	public Sala buscarPorId(int id) {
		return em.find(Sala.class, id);
	}
	
	//Atualiza os dados de uma sala no bd, caso não exista cria uma nova
	public void atualizar(Sala sala) {
		em.getTransaction().begin();
		em.merge(sala);
		em.getTransaction().commit();
	}
	
	//Remove um registro do bd
	public void remover(int id) {
		Sala sala = em.find(Sala.class, id);
		
		//Valida se existe sala com o id
		if (sala != null) {
			em.getTransaction().begin();
			em.remove(sala);
			em.getTransaction().commit();
		}
	}
	
	//Lista todos os registros de uma entidade
	public List<Sala> listarTodos(){
		String jpql = "SELECT u FROM Sala u";
		TypedQuery<Sala> query = em.createQuery(jpql, Sala.class);
		return query.getResultList();
	}
	
	 // Fechar o EntityManager
    public void fechar() {
        em.close();
        emf.close();
    }
}
