package controller;

import javax.swing.JOptionPane;

import org.mindrot.jbcrypt.BCrypt;

import dao.UsuarioDAO;
import model.Usuario;

public class LoginController {
	
	public static void validarLogin (String login, String senha) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscarPorLogin(login);
		
		if (usuario == null) {
			JOptionPane.showMessageDialog(null, "Usuário não encontrado");
		} else {
			boolean senhaCorreta = BCrypt.checkpw(senha, usuario.getSenha());
			
			if (senhaCorreta) {
				if (usuario.getTipo().equals("admin")) {
					JOptionPane.showMessageDialog(null, "Bem-vindo, administrador!");
					//TODO chamar tela do sistema
				} else {
					JOptionPane.showMessageDialog(null, "Bem-vindo usuário!");
					//TODO chamar tela do sistema
				}
			} else {
				JOptionPane.showMessageDialog(null, "Senha incorreta!");
			}
		}
	}

}
