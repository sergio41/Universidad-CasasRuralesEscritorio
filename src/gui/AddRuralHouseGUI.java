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

public class AddRuralHouseGUI extends JFrame {

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
					AddRuralHouseGUI frame = new AddRuralHouseGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AddRuralHouseGUI() {
		setTitle("Añadir Casa Rural");
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 555, 391);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBackground(new Color(178, 238, 238));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		buttonAddRuralHouse = new JButton("A\u00F1adir");
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
		
		buttonAddRuralHouse.setForeground(Color.BLUE);
		buttonAddRuralHouse.setFont(new Font("Tekton Pro", Font.PLAIN, 21));
		buttonAddRuralHouse.setBounds(390, 285, 124, 45);
		contentPane.add(buttonAddRuralHouse);

		JLabel lblPark = new JLabel("N\u00BA Aparcamientos*:");
		lblPark.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPark.setFont(new Font("Tekton Pro", Font.PLAIN, 21));
		lblPark.setBounds(257, 230, 178, 33);
		contentPane.add(lblPark);

		JLabel lblLiving = new JLabel("N\u00BA Salas de estar*:");
		lblLiving.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLiving.setFont(new Font("Tekton Pro", Font.PLAIN, 21));
		lblLiving.setBounds(252, 174, 183, 34);
		contentPane.add(lblLiving);

		JLabel lblEstadoCivil = new JLabel("N\u00BA Cocinas*:");
		lblEstadoCivil.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEstadoCivil.setFont(new Font("Tekton Pro", Font.PLAIN, 21));
		lblEstadoCivil.setBounds(10, 229, 124, 34);
		contentPane.add(lblEstadoCivil);

		JLabel lblBaths = new JLabel("N\u00BA Ba\u00F1os*:");
		lblBaths.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBaths.setFont(new Font("Tekton Pro", Font.PLAIN, 21));
		lblBaths.setBounds(38, 174, 96, 34);
		contentPane.add(lblBaths);

		JLabel lblRooms = new JLabel("N\u00BA Habitaciones*:");
		lblRooms.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRooms.setFont(new Font("Tekton Pro", Font.PLAIN, 21));
		lblRooms.setBounds(282, 129, 153, 34);
		contentPane.add(lblRooms);

		JLabel lblCity = new JLabel("Ciudad*:");
		lblCity.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCity.setFont(new Font("Tekton Pro", Font.PLAIN, 21));
		lblCity.setBounds(10, 129, 124, 34);
		contentPane.add(lblCity);

		JLabel lblDescription = new JLabel("Descripci\u00F3n:");
		lblDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescription.setFont(new Font("Tekton Pro", Font.PLAIN, 21));
		lblDescription.setBounds(12, 13, 124, 34);
		contentPane.add(lblDescription);

		JLabel lblLosCamposMarcados = new JLabel(
				"Los campos marcados con * son obligatorios");
		lblLosCamposMarcados.setForeground(new Color(255, 0, 0));
		lblLosCamposMarcados.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLosCamposMarcados.setFont(new Font("Tekton Pro", Font.PLAIN, 18));
		lblLosCamposMarcados.setBounds(12, 290, 366, 34);
		contentPane.add(lblLosCamposMarcados);

		textPaneDescription = new JTextPane();
		textPaneDescription.setBounds(144, 13, 370, 105);
		contentPane.add(textPaneDescription);

		textCity = new JTextField();
		textCity.setBounds(144, 131, 128, 33);
		contentPane.add(textCity);
		textCity.setColumns(10);

		textBath = new JTextField();
		textBath.setBounds(144, 174, 69, 34);
		contentPane.add(textBath);
		textBath.setColumns(10);

		textKitchen = new JTextField();
		textKitchen.setBounds(144, 230, 69, 33);
		contentPane.add(textKitchen);
		textKitchen.setColumns(10);

		textRooms = new JTextField();
		textRooms.setBounds(445, 129, 69, 34);
		contentPane.add(textRooms);
		textRooms.setColumns(10);

		textLiving = new JTextField();
		textLiving.setBounds(445, 174, 69, 34);
		contentPane.add(textLiving);
		textLiving.setColumns(10);

		textPark = new JTextField();
		textPark.setBounds(445, 230, 69, 33);
		contentPane.add(textPark);
		textPark.setColumns(10);

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
