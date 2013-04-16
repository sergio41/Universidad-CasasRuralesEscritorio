package gui;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JFormattedTextField;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;

import businessLogic.ApplicationFacadeInterface;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserRegisterGUI extends JPanel {
	private JTextField textEmail;
	private JPasswordField passPass;
	private JTextField textEdad;
	private JTextField textNombre;
	private JTextField textApellido;
	private JTextField textTelefono;
	private JTextField textPais;
	private JComboBox<String> comboEC;
	private DefaultComboBoxModel<String> modeloEC = new DefaultComboBoxModel<String>();

	/**
	 * Create the panel.
	 */
	public UserRegisterGUI(){
		setLayout(null);
		
		JLabel label = new JLabel("Email (user)*:");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setFont(new Font("Dialog", Font.PLAIN, 21));
		label.setBounds(12, 13, 124, 34);
		add(label);
		
		textEmail = new JTextField();
		textEmail.setColumns(10);
		textEmail.setBounds(148, 13, 366, 34);
		add(textEmail);
		
		JLabel label_1 = new JLabel("Pass*:");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("Dialog", Font.PLAIN, 21));
		label_1.setBounds(12, 67, 124, 34);
		add(label_1);
		
		passPass = new JPasswordField();
		passPass.setBounds(148, 67, 192, 34);
		add(passPass);
		
		JLabel label_2 = new JLabel("Edad");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("Dialog", Font.PLAIN, 21));
		label_2.setBounds(352, 67, 53, 34);
		add(label_2);
		
		textEdad = new JTextField();
		textEdad.setColumns(10);
		textEdad.setBounds(433, 67, 81, 34);
		add(textEdad);
		
		textNombre = new JTextField();
		textNombre.setColumns(10);
		textNombre.setBounds(322, 121, 192, 34);
		add(textNombre);
		
		JLabel label_3 = new JLabel("Nombre*");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setFont(new Font("Dialog", Font.PLAIN, 21));
		label_3.setBounds(235, 121, 81, 34);
		add(label_3);
		
		comboEC = new JComboBox<String>();
		comboEC.setBounds(148, 121, 75, 34);
		comboEC.setModel(modeloEC);
		modeloEC.addElement("");
		modeloEC.addElement("Sr.");
		modeloEC.addElement("Sra.");
		modeloEC.addElement("Srta.");
		add(comboEC);
		
		JLabel label_4 = new JLabel("Estado Civil*");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setFont(new Font("Dialog", Font.PLAIN, 21));
		label_4.setBounds(12, 121, 124, 34);
		add(label_4);
		
		JLabel label_5 = new JLabel("Apellidos");
		label_5.setHorizontalAlignment(SwingConstants.RIGHT);
		label_5.setFont(new Font("Dialog", Font.PLAIN, 21));
		label_5.setBounds(12, 175, 124, 34);
		add(label_5);
		
		textApellido = new JTextField();
		textApellido.setColumns(10);
		textApellido.setBounds(148, 175, 366, 34);
		add(textApellido);
		
		textTelefono = new JTextField();
		textTelefono.setColumns(10);
		textTelefono.setBounds(148, 229, 133, 34);
		add(textTelefono);
		
		JLabel label_6 = new JLabel("Telefono");
		label_6.setHorizontalAlignment(SwingConstants.RIGHT);
		label_6.setFont(new Font("Dialog", Font.PLAIN, 21));
		label_6.setBounds(12, 229, 124, 34);
		add(label_6);
		
		JLabel label_7 = new JLabel("Pais*");
		label_7.setHorizontalAlignment(SwingConstants.RIGHT);
		label_7.setFont(new Font("Dialog", Font.PLAIN, 21));
		label_7.setBounds(293, 229, 53, 34);
		add(label_7);
		
		textPais = new JTextField();
		textPais.setColumns(10);
		textPais.setBounds(358, 229, 156, 34);
		add(textPais);
		
		JButton button = new JButton("Register");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ApplicationFacadeInterface facade = StartWindow.getBusinessLogic();
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
					if(facade.estadoLogin()){
						try {
							facade.modificarPerfil(email, pass, estadoCivil, nombre, apellidos, telefono, pais, edad);
							javax.swing.JOptionPane.showMessageDialog(null, "Perfil modificado correctamente.", "Bien....", javax.swing.JOptionPane.NO_OPTION);
						} catch (Exception e) {
							javax.swing.JOptionPane.showMessageDialog(null, e.toString(), "Mal....", javax.swing.JOptionPane.ERROR_MESSAGE);
						}
					}else{ 
						try {
							facade.nuevoUsuario(email, pass, estadoCivil, nombre, apellidos, telefono, pais, edad);
							javax.swing.JOptionPane.showMessageDialog(null, "Nuevo usuario registrado correctamente.\nLogueado.", "Bien....", javax.swing.JOptionPane.NO_OPTION);
							int x=javax.swing.JOptionPane.showConfirmDialog(null, "�Eres propietario de una casa rural?", "Bien....", javax.swing.JOptionPane.YES_NO_OPTION);
							if (x==0){
								JFrame a = new OwnerGUI();
								a.setVisible(true);
							}
						} catch (Exception e) {
							javax.swing.JOptionPane.showMessageDialog(null, e.toString(), "Mal....", javax.swing.JOptionPane.ERROR_MESSAGE);
						}

					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button.setForeground(Color.BLUE);
		button.setFont(new Font("Dialog", Font.PLAIN, 21));
		button.setBounds(390, 285, 124, 45);
		add(button);
		
		JLabel label_8 = new JLabel("Los campos marcados con * son obligatorios");
		label_8.setHorizontalAlignment(SwingConstants.RIGHT);
		label_8.setForeground(Color.RED);
		label_8.setFont(new Font("Dialog", Font.PLAIN, 18));
		label_8.setBounds(12, 290, 366, 34);
		add(label_8);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/imagenes/fondoAbajo.jpg")));
		lblNewLabel.setBounds(0, 0, 1018, 465);
		add(lblNewLabel);

	}
}
