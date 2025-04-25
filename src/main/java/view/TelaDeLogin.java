package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import antlr.actions.java.ActionLexer;
import controller.LoginController;

public class TelaDeLogin extends JFrame{
	
	private JTextField campoLogin;
	private JPasswordField campoSenha;
	private JButton botaoEntrar;
	
	public TelaDeLogin() {
		setTitle("Login");
		setSize(300, 150);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new GridLayout(3, 2));
		
		campoLogin = new JTextField();
		campoSenha = new JPasswordField();
		botaoEntrar = new JButton("Entrar");
		
		add(new JLabel("Login: ")); add(campoLogin);
		add(new JLabel("Senha: ")); add(campoSenha);
		add(new JLabel("")); add(botaoEntrar);
		
		botaoEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String login = campoLogin.getText();
				String senha = new String(campoSenha.getPassword());
				
				//Chamar o controller para validar
				LoginController.validarLogin(login, senha);
			}			
		});
		

		
		setVisible(true);

	}

}
