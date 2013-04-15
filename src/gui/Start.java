package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Start extends JFrame {
	private static JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Start frame = new Start();
					frame.setVisible(true);
					JPanel panel = new PantallaPrincipalGUI();
					modificarPanelAbajo(panel);
					JPanel panel1 = new LoginGUI();
					modificarPanelArriba(panel1);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Start() {
		setResizable(false);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 618, 100);
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/imagenes/fondoArriba.jpg")));
		contentPane.add(lblNewLabel);
	}

	public static void modificarPanelAbajo(JPanel panel){
		panel.setBounds(0, 100, 1018, 465);
		contentPane.add(panel);
	}
	
	public static void modificarPanelArriba(JPanel panel){
		panel.setBounds(618, 0, 400, 100);
		contentPane.add(panel);
	}
}
