package gui;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import businessLogic.ApplicationFacadeInterface;
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
		//setBackground(new Color(0, 255, 0));
		setLayout(null);
		setOpaque(false);
		btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String email = textEmail.getText();
				@SuppressWarnings("deprecation")
				String pass = passPass.getText();
				ApplicationFacadeInterface facade = Start.getBusinessLogic();
				try {
					facade.hacerLogin(email, pass);
					JPanel temp = new LoginONGUI();
					Start.modificarPanelArriba(temp);
					JPanel temp1 = new PantallaPrincipalGUI();
					Start.modificarPanelAbajo(temp1);
				} catch (Exception e) {
					javax.swing.JOptionPane.showMessageDialog(null, e.getMessage(), "Mal....", javax.swing.JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		textEmail = new JTextField();
		textEmail.setBounds(28, 16, 219, 25);
		add(textEmail);
		textEmail.setColumns(10);
		
		passPass = new JPasswordField();
		passPass.setBounds(28, 57, 219, 25);
		add(passPass);
		btnEntrar.setBounds(275, 57, 97, 25);
		add(btnEntrar);
		
		btnSignUp = new JButton("Sign UP!");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JPanel temp = new UserRegisterGUI();
				Start.modificarPanelAbajo(temp);
			}
		});
		btnSignUp.setBounds(275, 16, 97, 25);
		add(btnSignUp);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 400, 100);
		add(lblNewLabel);

		lblNewLabel.setBounds(0, 0, 400, 100);
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/imagenes/loginfondo.jpg")));
		lblNewLabel.setVisible(true);
		
	}
}
