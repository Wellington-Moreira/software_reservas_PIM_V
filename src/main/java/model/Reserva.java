package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "reservas")
public class Reserva {
	// Atributos da classe
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	// Atributos de associação
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "sala_id", nullable = false)
	private Sala sala;
	
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinTable(
			name = "reserva_equipamento",
			joinColumns = @JoinColumn(name = "reserva_id"),
			inverseJoinColumns = @JoinColumn(name = "equipamento_id")
			)	
	private List<Equipamento> equipamentos;
	
	public Reserva () {
		
	}

	// Construtor
	public Reserva(Usuario usuario, Sala sala) {
		this.usuario = usuario;
		this.sala = sala;
		// Inicializa a lista de equipamentos
		this.equipamentos = new ArrayList<>();
	}
	
	public void adicionarEquipamento (Equipamento equipamento) {
		//Adiciona o equipamento à essa reserva
		equipamentos.add(equipamento);
		//Adiciona esta reserva ao equipamento
		equipamento.adicionarReserva(this);		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public List<Equipamento> getEquipamentos() {
		return equipamentos;
	}

	public void setEquipamentos(List<Equipamento> equipamentos) {
		this.equipamentos = equipamentos;
	}
	
	@Override
	public String toString() {
	    String equipamentosStr = equipamentos.isEmpty() ? "Nenhum" :
	        equipamentos.stream() // Cria o stream a partir da lista
	                    .map(Equipamento::getNome)  // Mapeia os nomes dos equipamentos
	                    .collect(Collectors.joining(", "));  // Junta os nomes com vírgulas

	    return String.format("Reserva de %s na sala %s com equipamentos: %s",
	            usuario.getNome(), sala.getNome(), equipamentosStr);
	}

}
