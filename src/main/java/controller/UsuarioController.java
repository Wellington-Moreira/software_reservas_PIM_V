package controller;

import org.mindrot.jbcrypt.BCrypt;

import dao.UsuarioDAO;
import model.TipoUsuario;
import model.Usuario;

public class UsuarioController {
	
	private UsuarioDAO usuarioDAO;
	
	public UsuarioController() {
		this.usuarioDAO = new UsuarioDAO();
	}
	
	public boolean cadastrarUsuario (String nome, String login, String senha, TipoUsuario tipo) {
		//Verificar se já existe login igual
		Usuario existente = usuarioDAO.buscarPorLogin(login);
		if (existente != null) {
			return false; //Já existe login igual
		}
		
		//Criptografa a senha
		String senhaHash = BCrypt.hashpw(senha, BCrypt.gensalt());
		
		//Cria o objeto Usuário
		Usuario usuario = new Usuario(nome, login, senhaHash, tipo);
		
		//Salva no banco de dados
		usuarioDAO.salvar(usuario);
		
		return true;
	}

}
