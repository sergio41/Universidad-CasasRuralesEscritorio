package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import businessLogic.Login;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegisterGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textPais;
	private JTextField textTelefono;
	private JTextField textApellido;
	private JTextField textNombre;
	private JTextField textEdad;
	private JPasswordField passPass;
	private JTextField textEmail;
	private JComboBox<String> comboEC;
	private DefaultComboBoxModel<String> modeloEC = new DefaultComboBoxModel<String>();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI frame = new RegisterGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public RegisterGUI() {
		setTitle("Nuevo Usuario");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 555, 391);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBackground(new Color(178,238,238));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton buttonRegister = new JButton("Register");
		buttonRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String email = textEmail.getText();
				@SuppressWarnings("deprecation")
				String pass = passPass.getText();
				String edad = textEdad.getText();
				String estadoCivil = (String) comboEC.getSelectedItem();
				String nombre = textNombre.getText();
				String apellidos = textApellido.getText();
				String telefono = textTelefono.getText();
				String pais = textPais.getText();
				try {
					Login.nuevoUsuario(email, pass, estadoCivil, nombre, apellidos, telefono, pais, edad);
					javax.swing.JOptionPane.showMessageDialog(null, "Nuevo usuario registrado correctamente.\nLogueado.", "Bien....", javax.swing.JOptionPane.NO_OPTION);
					StartWindow.actualizarLogin();
					setVisible(false);
				} catch (Exception e) {
					javax.swing.JOptionPane.showMessageDialog(null, e.toString(), "Mal....", javax.swing.JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		buttonRegister.setForeground(Color.BLUE);
		buttonRegister.setFont(new Font("Tekton Pro", Font.PLAIN, 21));
		buttonRegister.setBounds(390, 285, 124, 45);
		contentPane.add(buttonRegister);
		
		textPais = new JTextField();
		textPais.setColumns(10);
		textPais.setBounds(358, 229, 156, 34);
		contentPane.add(textPais);
		
		JLabel lblPais = new JLabel("Pais*");
		lblPais.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPais.setFont(new Font("Tekton Pro", Font.PLAIN, 21));
		lblPais.setBounds(293, 229, 53, 34);
		contentPane.add(lblPais);
		
		textTelefono = new JTextField();
		textTelefono.setColumns(10);
		textTelefono.setBounds(148, 229, 133, 34);
		contentPane.add(textTelefono);
		
		JLabel label_1 = new JLabel("Telefono");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("Tekton Pro", Font.PLAIN, 21));
		label_1.setBounds(12, 229, 124, 34);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("Apellidos");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("Tekton Pro", Font.PLAIN, 21));
		label_2.setBounds(12, 175, 124, 34);
		contentPane.add(label_2);
		
		textApellido = new JTextField();
		textApellido.setColumns(10);
		textApellido.setBounds(148, 175, 366, 34);
		contentPane.add(textApellido);
		
		JLabel lblEstadoCivil = new JLabel("Estado Civil*");
		lblEstadoCivil.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEstadoCivil.setFont(new Font("Tekton Pro", Font.PLAIN, 21));
		lblEstadoCivil.setBounds(12, 121, 124, 34);
		contentPane.add(lblEstadoCivil);
		
		comboEC = new JComboBox<String>();
		comboEC.setBounds(148, 121, 75, 34);
		comboEC.setModel(modeloEC);
		modeloEC.addElement("");
		modeloEC.addElement("Sr.");
		modeloEC.addElement("Sra.");
		modeloEC.addElement("Srta.");
		contentPane.add(comboEC);
		
		JLabel lblNombre = new JLabel("Nombre*");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre.setFont(new Font("Tekton Pro", Font.PLAIN, 21));
		lblNombre.setBounds(235, 121, 81, 34);
		contentPane.add(lblNombre);
		
		textNombre = new JTextField();
		textNombre.setColumns(10);
		textNombre.setBounds(322, 121, 192, 34);
		contentPane.add(textNombre);
		
		textEdad = new JTextField();
		textEdad.setColumns(10);
		textEdad.setBounds(433, 67, 81, 34);
		contentPane.add(textEdad);
		
		JLabel label_5 = new JLabel("Edad");
		label_5.setHorizontalAlignment(SwingConstants.RIGHT);
		label_5.setFont(new Font("Tekton Pro", Font.PLAIN, 21));
		label_5.setBounds(352, 67, 53, 34);
		contentPane.add(label_5);
		
		passPass = new JPasswordField();
		passPass.setBounds(148, 67, 192, 34);
		contentPane.add(passPass);
		
		JLabel lblPass = new JLabel("Pass*:");
		lblPass.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPass.setFont(new Font("Tekton Pro", Font.PLAIN, 21));
		lblPass.setBounds(12, 67, 124, 34);
		contentPane.add(lblPass);
		
		JLabel lblEmailuser = new JLabel("Email (user)*:");
		lblEmailuser.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmailuser.setFont(new Font("Tekton Pro", Font.PLAIN, 21));
		lblEmailuser.setBounds(12, 13, 124, 34);
		contentPane.add(lblEmailuser);
		
		textEmail = new JTextField();
		textEmail.setColumns(10);
		textEmail.setBounds(148, 13, 366, 34);
		contentPane.add(textEmail);
		
		JLabel lblLosCamposMarcados = new JLabel("Los campos marcados con * son obligatorios");
		lblLosCamposMarcados.setForeground(new Color(255, 0, 0));
		lblLosCamposMarcados.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLosCamposMarcados.setFont(new Font("Tekton Pro", Font.PLAIN, 18));
		lblLosCamposMarcados.setBounds(12, 290, 366, 34);
		contentPane.add(lblLosCamposMarcados);	
		
		inicializarCampos();
	}
	
	@SuppressWarnings("deprecation")
	private void inicializarCampos(){
		if (Login.estadoLogin()){
			textEmail.enable(false);
		} else {
			textEmail.enable(true);
			textEmail.setText("");
			passPass.setText("");
			textEdad.setText("");
			textNombre.setText("");
			textApellido.setText("");
			textTelefono.setText("");
			textPais.setText("");
			comboEC.setSelectedIndex(0);
		}
	}
}
