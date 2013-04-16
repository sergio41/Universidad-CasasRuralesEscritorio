package gui;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import businessLogic.ApplicationFacadeInterface;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginGUI extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textEmail;
	private JPasswordField passPass;
	private static JButton btnEntrar;
	private static JButton btnSignUp;
	/**
	 * Create the panel.
	 */
	public LoginGUI() {
		setBackground(new Color(0, 255, 0));
		setLayout(null);
	
		btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String email = textEmail.getText();
				@SuppressWarnings("deprecation")
				String pass = passPass.getText();
				ApplicationFacadeInterface facade = Start.getBusinessLogic();
				try {
					if (facade.hacerLogin(email, pass)) {
						javax.swing.JOptionPane.showMessageDialog(null, "Ok...", "Bien....", javax.swing.JOptionPane.NO_OPTION);
						JPanel temp = new LoginONGUI();
						Start.modificarPanelArriba(temp);
					} else javax.swing.JOptionPane.showMessageDialog(null, "No, no, no, no...", "Mal....", javax.swing.JOptionPane.ERROR_MESSAGE);
				} catch (Exception e) {
					javax.swing.JOptionPane.showMessageDialog(null, e.toString(), "Mal....", javax.swing.JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnEntrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnSignUp.setText("hola"); 
			}
		});
		btnEntrar.setBounds(275, 47, 97, 25);
		add(btnEntrar);
		
		btnSignUp = new JButton("Sign UP!");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JPanel temp = new UserRegisterGUI();
				Start.modificarPanelAbajo(temp);
			}
		});
		btnSignUp.setBounds(275, 12, 97, 25);
		add(btnSignUp);
		
		textEmail = new JTextField();
		textEmail.setBounds(115, 13, 116, 22);
		add(textEmail);
		textEmail.setColumns(10);
		
		passPass = new JPasswordField();
		passPass.setBounds(115, 48, 116, 22);
		add(passPass);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 400, 100);
		add(lblNewLabel);

		lblNewLabel.setBounds(0, 0, 400, 100);
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/imagenes/loginfondo.jpg")));
		lblNewLabel.setVisible(true);
		
	}
}
