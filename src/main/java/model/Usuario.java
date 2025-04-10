package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {
	//Atributos de classe
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false) // Atributo obrigatório
	private String nome;
	
	@Column(nullable = false, unique = true)
	private String login;
	
	@Column(nullable = false)
	private String senha;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Enum<TipoUsuario> tipo;
	
	//Atributos de associação
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	private List<Reserva> reservas;
	
	
	//Construtor padrão
	public Usuario () {
		
	}
	
	//Construtor
	public Usuario (String nome, String login, String senha, TipoUsuario tipo) {
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.tipo = tipo;
		// Inicializa a lista de reservas
		this.reservas = new ArrayList<>();
	}
	
	public void adicionarReserva (Reserva reserva) {
		reservas.add(reserva);
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}	

	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Enum<TipoUsuario> getTipo() {
		return tipo;
	}
	public void setTipo(Enum<TipoUsuario> tipo) {
		this.tipo = tipo;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}


	
	
}
