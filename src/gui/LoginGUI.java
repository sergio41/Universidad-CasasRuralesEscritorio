package gui;

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

import businessLogic.Login;

import dataAccess.DB4oManager;
import domain.UserAplication;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginGUI extends JFrame {

	private JPanel contentPane;
	private JPasswordField passPass;
	private JTextField textEmail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
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
	public LoginGUI() {
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
					if (Login.hacerLogin(email, pass)) {
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
	}

}
