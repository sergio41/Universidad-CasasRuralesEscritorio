package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.JButton;

public class OwnerGUI extends JFrame {

	
	private JPanel contentPane;
	private JTextField textCuentaBancaria;
	private JTextField textProfesion;
	private JTextField textIdioma1;
	private JTextField textIdioma2;
	private JTextField textIdioma3;
	private JTextField textIdioma4;
	private JLabel lblMoneda;
	private JLabel label;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OwnerGUI frame = new OwnerGUI();
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
	public OwnerGUI() {
		setTitle("Perfil Propietario");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 658, 396);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(178,238,238));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCuentaBancaria = new JLabel("Cuenta bancar\u00EDa:*");
		lblCuentaBancaria.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCuentaBancaria.setFont(new Font("Tekton Pro", Font.PLAIN, 21));
		lblCuentaBancaria.setBounds(32, 30, 161, 34);
		contentPane.add(lblCuentaBancaria);
		
		textCuentaBancaria = new JTextField();
		textCuentaBancaria.setColumns(10);
		textCuentaBancaria.setBounds(248, 30, 366, 34);
		contentPane.add(textCuentaBancaria);
		
		JLabel lblUstedEs = new JLabel("Usted es:*");
		lblUstedEs.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUstedEs.setFont(new Font("Tekton Pro", Font.PLAIN, 21));
		lblUstedEs.setBounds(32, 77, 161, 34);
		contentPane.add(lblUstedEs);
		
		JRadioButton rdbtnParticular = new JRadioButton("Particular");
		rdbtnParticular.setBackground(new Color(178,238,238));
		rdbtnParticular.setBounds(248, 77, 127, 34);
		contentPane.add(rdbtnParticular);
		
		JRadioButton rdbtnProfesional = new JRadioButton("Profesional");
		rdbtnProfesional.setBackground(new Color(178,238,238));
		rdbtnProfesional.setBounds(453, 77, 127, 34);
		contentPane.add(rdbtnProfesional);
		
		textProfesion = new JTextField();
		textProfesion.setColumns(10);
		textProfesion.setBounds(248, 214, 366, 34);
		contentPane.add(textProfesion);
		
		JLabel lblProfesion = new JLabel("Profesi\u00F3n:*");
		lblProfesion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblProfesion.setFont(new Font("Tekton Pro", Font.PLAIN, 21));
		lblProfesion.setBounds(32, 214, 161, 34);
		contentPane.add(lblProfesion);
		
		JLabel lblIdiomasHablados = new JLabel("Idiomas hablados:*");
		lblIdiomasHablados.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIdiomasHablados.setFont(new Font("Tekton Pro", Font.PLAIN, 21));
		lblIdiomasHablados.setBounds(32, 124, 161, 34);
		contentPane.add(lblIdiomasHablados);
		
		textIdioma1 = new JTextField();
		textIdioma1.setColumns(10);
		textIdioma1.setBounds(248, 124, 161, 34);
		contentPane.add(textIdioma1);
		
		textIdioma2 = new JTextField();
		textIdioma2.setColumns(10);
		textIdioma2.setBounds(453, 124, 161, 34);
		contentPane.add(textIdioma2);
		
		textIdioma3 = new JTextField();
		textIdioma3.setColumns(10);
		textIdioma3.setBounds(248, 167, 161, 34);
		contentPane.add(textIdioma3);
		
		textIdioma4 = new JTextField();
		textIdioma4.setColumns(10);
		textIdioma4.setBounds(453, 167, 161, 34);
		contentPane.add(textIdioma4);
		
		lblMoneda = new JLabel("Moneda:*");
		lblMoneda.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMoneda.setFont(new Font("Tekton Pro", Font.PLAIN, 21));
		lblMoneda.setBounds(32, 261, 161, 34);
		contentPane.add(lblMoneda);
		
		JComboBox comboMoneda = new JComboBox();
		comboMoneda.setBounds(248, 261, 109, 34);
		contentPane.add(comboMoneda);
		
		label = new JLabel("Los campos marcados con * son obligatorios");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setForeground(Color.RED);
		label.setFont(new Font("Tekton Pro", Font.PLAIN, 18));
		label.setBounds(126, 302, 366, 34);
		contentPane.add(label);
		
		JButton buttonGuardar = new JButton("Guardar");
		buttonGuardar.setForeground(Color.BLUE);
		buttonGuardar.setFont(new Font("Tekton Pro", Font.PLAIN, 21));
		buttonGuardar.setBounds(504, 291, 124, 45);
		contentPane.add(buttonGuardar);
	}
}

