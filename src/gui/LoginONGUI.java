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

public class LoginONGUI extends JPanel {
	private static JButton btnLogout;
	/**
	 * Create the panel.
	 */
	public LoginONGUI() {
		setBackground(new Color(0, 255, 0));
		setLayout(null);
	
		btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnLogout.setBounds(120, 30, 97, 25);
		add(btnLogout);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 400, 100);
		add(lblNewLabel);

		lblNewLabel.setBounds(0, 0, 400, 100);
		//lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/imagenes/loginfondo.jpg")));
		
	}
}
