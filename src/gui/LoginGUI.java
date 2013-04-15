package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginGUI extends JPanel {
	private JTextField textField;
	private JPasswordField passwordField;
	private static JButton btnEntrar;
	private static JButton btnSignUp;
	/**
	 * Create the panel.
	 */
	public LoginGUI() {
		setBackground(new Color(0, 255, 0));
		setLayout(null);
		
		btnEntrar = new JButton("Entrar");
		btnEntrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnSignUp.setText("hola"); 
			}
		});
		btnEntrar.setBounds(275, 47, 97, 25);
		add(btnEntrar);
		
		btnSignUp = new JButton("Sign UP!");
		btnSignUp.setBounds(275, 12, 97, 25);
		add(btnSignUp);
		
		textField = new JTextField();
		textField.setBounds(115, 13, 116, 22);
		add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(115, 48, 116, 22);
		add(passwordField);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 400, 100);
		add(lblNewLabel);
		
	}
}
