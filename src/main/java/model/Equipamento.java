package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "equipamentos")
public class Equipamento {
	// Atributos de classe
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private String nome;

	// Atributos de associação
	@ManyToMany(mappedBy = "equipamentos")
	private List<Reserva> reservas;
	
	public Equipamento () {
		
	}

	// Construtor
	public Equipamento(String nome) {
		this.nome = nome;
		// Inicializa a lista de reservas
		this.reservas = new ArrayList<>();
	}

	public void adicionarReserva (Reserva reserva) {
		reservas.add(reserva);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}


	
	
}
