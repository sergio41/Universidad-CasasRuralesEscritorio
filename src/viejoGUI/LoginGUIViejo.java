package viejoGUI;


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import businessLogic.ApplicationFacadeInterface;

import dataAccess.DB4oManager;
import domain.UserAplication;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginGUIViejo extends JFrame {

	private JPanel contentPane;
	private JPasswordField passPass;
	private JTextField textEmail;

	ApplicationFacadeInterface facade = StartWindow.getBusinessLogic();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUIViejo frame = new LoginGUIViejo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	/**
	 * Create the frame.
	 */
	public LoginGUIViejo() {
		setTitle("Login");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 392, 265);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(178,238,238));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton buttonLogin = new JButton("LOGIN");
		buttonLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String email = textEmail.getText();
				String pass = passPass.getText();
				try {
					if (facade.hacerLogin(email, pass)) {
						javax.swing.JOptionPane.showMessageDialog(null, "Ok...", "Bien....", javax.swing.JOptionPane.NO_OPTION);
						StartWindow.actualizarLogin();
						setVisible(false);
					} else javax.swing.JOptionPane.showMessageDialog(null, "No, no, no, no...", "Mal....", javax.swing.JOptionPane.ERROR_MESSAGE);
				} catch (Exception e) {
					javax.swing.JOptionPane.showMessageDialog(null, e.toString(), "Mal....", javax.swing.JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		buttonLogin.setForeground(Color.BLUE);
		buttonLogin.setFont(new Font("Tekton Pro", Font.PLAIN, 21));
		buttonLogin.setBounds(238, 160, 124, 45);
		contentPane.add(buttonLogin);
		
		passPass = new JPasswordField();
		passPass.setBounds(148, 114, 192, 34);
		contentPane.add(passPass);
		
		textEmail = new JTextField();
		textEmail.setColumns(10);
		textEmail.setBounds(148, 66, 192, 34);
		contentPane.add(textEmail);
		
		JLabel label = new JLabel("User/Email:");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setFont(new Font("Tekton Pro", Font.PLAIN, 21));
		label.setBounds(12, 66, 124, 34);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("Pass:");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("Tekton Pro", Font.PLAIN, 21));
		label_1.setBounds(12, 114, 124, 34);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("Introduzca usuario y contrase\u00F1a");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("Tekton Pro", Font.PLAIN, 21));
		label_2.setBounds(12, 13, 362, 40);
		contentPane.add(label_2);
		
		JButton btnRecuperar = new JButton("Recuperar");
		btnRecuperar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ApplicationFacadeInterface facade = StartWindow.getBusinessLogic();
				try {
					facade.recuperarContrasena(textEmail.getText());
					javax.swing.JOptionPane.showMessageDialog(null, "Se te ha enviado un email con la contraseña", "Bien.", javax.swing.JOptionPane.NO_OPTION);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					javax.swing.JOptionPane.showMessageDialog(null, e.toString(), "Mal....", javax.swing.JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnRecuperar.setForeground(Color.BLUE);
		btnRecuperar.setFont(new Font("Dialog", Font.PLAIN, 21));
		btnRecuperar.setBounds(22, 160, 124, 45);
		contentPane.add(btnRecuperar);
	}
}
