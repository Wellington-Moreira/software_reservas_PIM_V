package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "salas")
public class Sala {
	// Atributos de classe
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private String nome;

	// Atributos de associação
	 @OneToMany(mappedBy = "sala", cascade = CascadeType.ALL)
	private List<Reserva> reservas;
	 
	 public Sala () {
		 
	 }

	// Construtor
	public Sala(String nome) {
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


	@Override
	public String toString() {
		return this.nome;
	}
}
