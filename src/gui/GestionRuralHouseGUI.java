package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;


import businessLogic.Login;
import businessLogic.AddRuralHouse;

import domain.Owner;
import javax.swing.JComboBox;

public class GestionRuralHouseGUI extends JFrame {

	private JPanel contentPane;
	private JTextPane textPaneDescription;
	private JTextField textCity;
	private JButton buttonAddRuralHouse;
	private JTextField textBath;
	private JTextField textKitchen;
	private JTextField textRooms;
	private JTextField textLiving;
	private JTextField textPark;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionRuralHouseGUI frame = new GestionRuralHouseGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GestionRuralHouseGUI() {
		setTitle("Gesti\u00F3n de las Casas Rurales");
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 579, 476);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBackground(new Color(178, 238, 238));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		buttonAddRuralHouse = new JButton("Salvar");
		buttonAddRuralHouse.setBounds(428, 376, 124, 45);
		buttonAddRuralHouse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (Login.estadoLogin() && Login.getPropietario() != null) {
					try {
						Owner owner = Login.getPropietario();
						String description = textPaneDescription.getText();
						String city = textCity.getText();
						String nRooms = textRooms.getText();
						String nKitchen = textKitchen.getText();
						String nBaths = textBath.getText();
						String nLiving = textLiving.getText();
						String nPark = textPark.getText();

						AddRuralHouse.newRuralHouse(owner,
								description, city, nRooms, nKitchen, nBaths,
								nLiving, nPark);
						
						StartWindow.actualizarLogin();
						setVisible(false);
					} catch (Exception e) {
						javax.swing.JOptionPane.showMessageDialog(null,
								e.toString(), "Mal....",
								javax.swing.JOptionPane.ERROR_MESSAGE);
					}

				} else {
						javax.swing.JOptionPane
								.showMessageDialog(
										null,
										"No eres propietario.\n",
										"Mal....",
										javax.swing.JOptionPane.NO_OPTION);
						StartWindow.actualizarLogin();
						setVisible(false);
				}
			}
		});
		contentPane.setLayout(null);
		
		buttonAddRuralHouse.setForeground(Color.BLUE);
		buttonAddRuralHouse.setFont(new Font("Tekton Pro", Font.PLAIN, 21));
		contentPane.add(buttonAddRuralHouse);

		JLabel lblPark = new JLabel("N\u00BA Aparcamientos*:");
		lblPark.setBounds(294, 300, 178, 33);
		lblPark.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPark.setFont(new Font("Tekton Pro", Font.PLAIN, 21));
		contentPane.add(lblPark);

		JLabel lblLiving = new JLabel("N\u00BA Salas de estar*:");
		lblLiving.setBounds(289, 244, 183, 34);
		lblLiving.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLiving.setFont(new Font("Tekton Pro", Font.PLAIN, 21));
		contentPane.add(lblLiving);

		JLabel lblEstadoCivil = new JLabel("N\u00BA Cocinas*:");
		lblEstadoCivil.setBounds(35, 299, 124, 34);
		lblEstadoCivil.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEstadoCivil.setFont(new Font("Tekton Pro", Font.PLAIN, 21));
		contentPane.add(lblEstadoCivil);

		JLabel lblBaths = new JLabel("N\u00BA Ba\u00F1os*:");
		lblBaths.setBounds(63, 244, 96, 34);
		lblBaths.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBaths.setFont(new Font("Tekton Pro", Font.PLAIN, 21));
		contentPane.add(lblBaths);

		JLabel lblRooms = new JLabel("N\u00BA Habitaciones*:");
		lblRooms.setBounds(319, 199, 153, 34);
		lblRooms.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRooms.setFont(new Font("Tekton Pro", Font.PLAIN, 21));
		contentPane.add(lblRooms);

		JLabel lblCity = new JLabel("Ciudad*:");
		lblCity.setBounds(35, 199, 124, 34);
		lblCity.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCity.setFont(new Font("Tekton Pro", Font.PLAIN, 21));
		contentPane.add(lblCity);

		JLabel lblDescription = new JLabel("Descripci\u00F3n:");
		lblDescription.setBounds(35, 71, 124, 34);
		lblDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescription.setFont(new Font("Tekton Pro", Font.PLAIN, 21));
		contentPane.add(lblDescription);

		JLabel lblLosCamposMarcados = new JLabel(
				"Los campos marcados con * son obligatorios");
		lblLosCamposMarcados.setBounds(38, 381, 366, 34);
		lblLosCamposMarcados.setForeground(new Color(255, 0, 0));
		lblLosCamposMarcados.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLosCamposMarcados.setFont(new Font("Tekton Pro", Font.PLAIN, 18));
		contentPane.add(lblLosCamposMarcados);

		textPaneDescription = new JTextPane();
		textPaneDescription.setBounds(182, 71, 370, 105);
		contentPane.add(textPaneDescription);

		textCity = new JTextField();
		textCity.setBounds(182, 201, 128, 33);
		contentPane.add(textCity);
		textCity.setColumns(10);

		textBath = new JTextField();
		textBath.setBounds(182, 244, 69, 34);
		contentPane.add(textBath);
		textBath.setColumns(10);

		textKitchen = new JTextField();
		textKitchen.setBounds(182, 300, 69, 33);
		contentPane.add(textKitchen);
		textKitchen.setColumns(10);

		textRooms = new JTextField();
		textRooms.setBounds(483, 199, 69, 34);
		contentPane.add(textRooms);
		textRooms.setColumns(10);

		textLiving = new JTextField();
		textLiving.setBounds(483, 244, 69, 34);
		contentPane.add(textLiving);
		textLiving.setColumns(10);

		textPark = new JTextField();
		textPark.setBounds(483, 300, 69, 33);
		contentPane.add(textPark);
		textPark.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(12, 13, 377, 33);
		contentPane.add(comboBox);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(418, 13, 134, 33);
		contentPane.add(btnEliminar);

		inicializarCampos();
	}

	@SuppressWarnings("deprecation")
	private void inicializarCampos() {
		if (Login.estadoLogin() && Login.getPropietario() != null) {
			textPaneDescription.enable(true);
			textPaneDescription.setText("");
			textCity.setText("");
			textRooms.setText("");
			textKitchen.setText("");
			textBath.setText("");
			textLiving.setText("");
			textPark.setText("");
			buttonAddRuralHouse.setText("Añadir");
		}
	}
}
