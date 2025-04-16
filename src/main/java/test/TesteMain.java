package test;

import model.Equipamento;
import model.Reserva;
import model.Sala;
import model.TipoUsuario;
import model.Usuario;
import util.DatabaseSeeder;
import dao.EquipamentoDAO;
import dao.ReservaDAO;
import dao.SalaDAO;
import dao.UsuarioDAO;

public class TesteMain {
	
public static void main(String[] args) {
	
	DatabaseSeeder.criarAdminPadrao();//Sempre cria admin padrão
	
	UsuarioDAO usuarioDAO = new UsuarioDAO();
	SalaDAO salaDAO = new SalaDAO();
	EquipamentoDAO equipamentoDAO = new EquipamentoDAO();
	ReservaDAO reservaDAO = new ReservaDAO();
	
	//Criando os usuarios
	Usuario u1 = new Usuario("Bruna", "misuriChan", "123123123", TipoUsuario.COMUM);	usuarioDAO.salvar(u1);
	Usuario u2 = new Usuario("Wellington", "shimulh", "123123123", TipoUsuario.COMUM);	usuarioDAO.salvar(u2);
	Usuario u3 = new Usuario("Liliane", "liliChan", "123123123", TipoUsuario.COMUM);	usuarioDAO.salvar(u3);
	Usuario u4 = new Usuario("Lilian", "abbaChan", "123123123", TipoUsuario.COMUM);	usuarioDAO.salvar(u4);
	Usuario u5 = new Usuario("Astolfo", "astolfoChan", "123123123", TipoUsuario.COMUM);	usuarioDAO.salvar(u5);
	Usuario u6 = new Usuario("Pikachu", "pikaChan", "123123123", TipoUsuario.COMUM);	usuarioDAO.salvar(u6);
	Usuario u7 = new Usuario("Bart Simpsom", "bartChan", "123123123", TipoUsuario.COMUM);	usuarioDAO.salvar(u7);
	Usuario u8 = new Usuario("Cubo Mágico", "cuboChan", "123123123", TipoUsuario.COMUM);	usuarioDAO.salvar(u8);
	
	//Criando as salas
	Sala s1 = new Sala("1-01"); salaDAO.salvar(s1);
	Sala s2 = new Sala("1-05"); salaDAO.salvar(s2);
	Sala s3 = new Sala("2-03"); salaDAO.salvar(s3);
	Sala s4 = new Sala("Auditorio 2"); salaDAO.salvar(s4);
	Sala s5 = new Sala("3-04"); salaDAO.salvar(s5);
	
	//Criando os equipamentos
	Equipamento e1 = new Equipamento("Notebook"); equipamentoDAO.salvar(e1);
	Equipamento e2 = new Equipamento("Projetor"); equipamentoDAO.salvar(e2);
	Equipamento e3 = new Equipamento("Multimídea"); equipamentoDAO.salvar(e3);
	Equipamento e4 = new Equipamento("Notebook"); equipamentoDAO.salvar(e4);
	Equipamento e5 = new Equipamento("DVD"); equipamentoDAO.salvar(e5);
	Equipamento e6 = new Equipamento("Notebook"); equipamentoDAO.salvar(e6);
	
	
	//Criando as reservas
	
	//Buscar no banco pois o objeto já está persistido no banco de dados
	Equipamento ebusca6 = equipamentoDAO.buscarPorId(6);
	Equipamento ebusca3 = equipamentoDAO.buscarPorId(3);
	Sala sbusca3 = salaDAO.buscarPorId(3);
	Reserva r1 = new Reserva(u1, sbusca3);
	r1.adicionarEquipamento(ebusca6);
	r1.adicionarEquipamento(ebusca3);
	reservaDAO.salvar(r1);
	
	Equipamento ebusca5 = equipamentoDAO.buscarPorId(5);
	Sala sbusca1 = salaDAO.buscarPorId(1);
	Reserva r2 = new Reserva(u2, sbusca1);
	r2.adicionarEquipamento(ebusca5);
	r2.adicionarEquipamento(ebusca6);
	reservaDAO.salvar(r2);
	
//	System.out.println(u1.getId());
	
}

}
