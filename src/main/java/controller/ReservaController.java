package controller;

import java.util.ArrayList;
import java.util.List;

import model.Equipamento;
import model.Reserva;
import model.Sala;
import model.Usuario;

public class ReservaController {
	
	//Armazenamento das reservas no sistema
	private List<Reserva> reservas = new ArrayList<>();
	
	public Reserva criarReserva (int id, Usuario usuario, Sala sala, List<Equipamento> equipamentos ) {
		Reserva reserva = new Reserva(usuario, sala);
		usuario.adicionarReserva(reserva);//add um usuario à reserva
		sala.adicionarReserva(reserva);//add uma sala à reserva
		for(Equipamento e: equipamentos) {
			reserva.adicionarEquipamento(e);
		}		
		reservas.add(reserva);//add a reserva à lista geral de reservas
		return reserva;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}
}
