package gui;


import javax.swing.ImageIcon;
import javax.swing.JPanel;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.filechooser.FileNameExtensionFilter;

import domain.RuralHouse;
import businessLogic.ApplicationFacadeInterface;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Vector;


public class GestionCasaRuralGUI extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textCity;
	private JTextField textBath;
	private JTextField textKitchen;
	private JTextField textRooms;
	private JTextField textLiving;
	private JTextField textPark;
	private JComboBox<String> comBoxCasas;
	private DefaultComboBoxModel<String> modeloEC = new DefaultComboBoxModel<String>();
	private JButton btnSalvar;
	private JButton btnEliminar;
	private JTextPane textPaneDescription;
	private JLabel image1label;
	private JLabel labelflechaDer;
	private JLabel labelflechaIzq;
	private JButton btnEliminarimg;
	private JComboBox comboBox;
	private JButton btnanadirImg;
	private Vector<Image> images;
	
	/**
	 * Create the panel.
	 */
	public GestionCasaRuralGUI(){
		setLayout(null);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comBoxCasas.getSelectedIndex() > 0) {
					ApplicationFacadeInterface facade=Start.getBusinessLogic();
					try {
						facade.eliminarCasaRural(Integer.parseInt(comBoxCasas.getSelectedItem().toString()));						
						JPanel panel = new PantallaPrincipalGUI();
						Start.modificarPanelAbajo(panel);
						javax.swing.JOptionPane.showMessageDialog(null,"Se ha eliminado la casa Rural", "Bien....",javax.swing.JOptionPane.INFORMATION_MESSAGE);	
					}catch (Exception e) {
						javax.swing.JOptionPane.showMessageDialog(null,"Error al eliminar: " + e.getMessage(), "No....",javax.swing.JOptionPane.INFORMATION_MESSAGE);
					} 
					
				}
			}
		});
		btnEliminar.setEnabled(false);
		btnEliminar.setBounds(437, 25, 134, 33);
		add(btnEliminar);
		
		JLabel label_5 = new JLabel("N\u00BA Habitaciones*:");
		label_5.setHorizontalAlignment(SwingConstants.RIGHT);
		label_5.setFont(new Font("Dialog", Font.PLAIN, 21));
		label_5.setBounds(337, 211, 153, 34);
		add(label_5);
		
		JLabel label_6 = new JLabel("N\u00BA Salas de estar*:");
		label_6.setHorizontalAlignment(SwingConstants.RIGHT);
		label_6.setFont(new Font("Dialog", Font.PLAIN, 21));
		label_6.setBounds(307, 256, 183, 34);
		add(label_6);
		
		JLabel label_7 = new JLabel("N\u00BA Aparcamientos*:");
		label_7.setHorizontalAlignment(SwingConstants.RIGHT);
		label_7.setFont(new Font("Dialog", Font.PLAIN, 21));
		label_7.setBounds(312, 312, 178, 33);
		add(label_7);
		
		
		textPaneDescription = new JTextPane();
		textPaneDescription.setText("");
		textPaneDescription.setEnabled(false);
		textPaneDescription.setEditable(false);
		textPaneDescription.setBounds(201, 83, 370, 105);
		add(textPaneDescription);
		
		textCity = new JTextField();
		textCity.setText("");
		textCity.setEnabled(false);
		textCity.setColumns(10);
		textCity.setBounds(201, 213, 128, 33);
		add(textCity);
		
		textBath = new JTextField();
		textBath.setText("");
		textBath.setEnabled(false);
		textBath.setColumns(10);
		textBath.setBounds(201, 256, 69, 34);
		add(textBath);
		
		textKitchen = new JTextField();
		textKitchen.setText("");
		textKitchen.setEnabled(false);
		textKitchen.setColumns(10);
		textKitchen.setBounds(201, 312, 69, 33);
		add(textKitchen);
		
		JLabel label = new JLabel("N\u00BA Cocinas*:");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setFont(new Font("Dialog", Font.PLAIN, 21));
		label.setBounds(54, 311, 124, 34);
		add(label);
		
		JLabel label_1 = new JLabel("N\u00BA Ba\u00F1os*:");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("Dialog", Font.PLAIN, 21));
		label_1.setBounds(82, 256, 96, 34);
		add(label_1);
		
		JLabel label_2 = new JLabel("Ciudad*:");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("Dialog", Font.PLAIN, 21));
		label_2.setBounds(54, 211, 124, 34);
		add(label_2);
		
		JLabel label_3 = new JLabel("Descripci\u00F3n:");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setFont(new Font("Dialog", Font.PLAIN, 21));
		label_3.setBounds(54, 83, 124, 34);
		add(label_3);
		
		textRooms = new JTextField();
		textRooms.setText("");
		textRooms.setEnabled(false);
		textRooms.setColumns(10);
		textRooms.setBounds(502, 211, 69, 34);
		add(textRooms);
		
		textLiving = new JTextField();
		textLiving.setText("");
		textLiving.setEnabled(false);
		textLiving.setColumns(10);
		textLiving.setBounds(502, 256, 69, 34);
		add(textLiving);
		
		textPark = new JTextField();
		textPark.setText("");
		textPark.setEnabled(false);
		textPark.setColumns(10);
		textPark.setBounds(502, 312, 69, 33);
		add(textPark);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String description = textPaneDescription.getText();
					String city = textCity.getText();
					int nRooms = Integer.parseInt(textRooms.getText());
					int nKitchen = Integer.parseInt(textKitchen.getText());
					int nBaths = Integer.parseInt(textBath.getText());
					int nLiving = Integer.parseInt(textLiving.getText());
					int nPark = Integer.parseInt(textPark.getText());
					
					ApplicationFacadeInterface facade=Start.getBusinessLogic();
					if (comBoxCasas.getSelectedIndex() == 0){
						facade.anadirRuralHouse(description, city, nRooms, nKitchen, nBaths, nLiving, nPark, images);
					} else if (comBoxCasas.getSelectedIndex() > 0) {
						facade.modificarRuralHouse((Integer.parseInt((String) comBoxCasas.getSelectedItem())), description, city, nRooms, nKitchen, nBaths, nLiving, nPark);
					}
					JPanel panel = new PantallaPrincipalGUI();
					Start.modificarPanelAbajo(panel);
				} catch (Exception e) {
					javax.swing.JOptionPane.showMessageDialog(null,e.toString(), "Mal....",javax.swing.JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnSalvar.setForeground(Color.BLUE);
		btnSalvar.setFont(new Font("Dialog", Font.PLAIN, 21));
		btnSalvar.setEnabled(false);
		btnSalvar.setBounds(863, 385, 124, 45);
		add(btnSalvar);
		
		JLabel label_4 = new JLabel("Los campos marcados con * son obligatorios");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setForeground(Color.RED);
		label_4.setFont(new Font("Dialog", Font.PLAIN, 18));
		label_4.setBounds(473, 390, 366, 34);
		add(label_4);
		
		comBoxCasas = new JComboBox<String>();
		comBoxCasas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ApplicationFacadeInterface facade = Start.getBusinessLogic();
				if (comBoxCasas.getSelectedIndex() != -1){
					enaDis(true);
					btnEliminar.setEnabled(false);
					if (comBoxCasas.getSelectedIndex() != 0){
						RuralHouse rh;
						java.util.Iterator<RuralHouse> i;
						try {
							i = facade.getOwner().getRuralHouses().iterator();
							while (i.hasNext()){
								rh = i.next();
								if (rh.getHouseNumber() == Integer.parseInt((String) comBoxCasas.getSelectedItem())){
									btnEliminar.setEnabled(true);
									textPaneDescription.setText(rh.getDescription());
									textCity.setText(rh.getCity());
									textRooms.setText(Integer.toString(rh.getRooms()));
									textKitchen.setText(Integer.toString(rh.getKitchen()));
									textBath.setText(Integer.toString(rh.getBaths()));
									textLiving.setText(Integer.toString(rh.getLiving()));
									textPark.setText(Integer.toString(rh.getPark()));
									break;
								}
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		});
		comBoxCasas.setSelectedIndex(-1);
		comBoxCasas.setModel(modeloEC);
		comBoxCasas.setBounds(31, 25, 377, 33);
		add(comBoxCasas);

		image1label = new JLabel("");
		image1label.setBounds(674, 95, 250, 250);
		ImageIcon imagen = new ImageIcon("C:\\Users\\Public\\Pictures\\avatar-22648.jpg");
        Image aux = imagen.getImage();
        Image aux1= aux.getScaledInstance(image1label.getHeight(), image1label.getWidth(), java.awt.Image.SCALE_SMOOTH);
        image1label.setIcon(new ImageIcon(aux1));
		add(image1label);
		
		btnanadirImg = new JButton("A\u00F1adir");
		btnanadirImg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				buscarImagen();
				
		        
			}
		});
		btnanadirImg.setBounds(674, 59, 76, 23);
		add(btnanadirImg);
		
		comboBox = new JComboBox();
		comboBox.setBounds(761, 59, 77, 23);
		add(comboBox);
		
		btnEliminarimg = new JButton("Eliminar");
		btnEliminarimg.setBounds(848, 59, 76, 23);
		add(btnEliminarimg);
		
		labelflechaIzq = new JLabel("");
		labelflechaIzq.setBounds(606, 196, 50, 50);
		labelflechaIzq.setIcon(new ImageIcon(getClass().getResource("/imagenes/flecha.png")));
		add(labelflechaIzq);
		
		labelflechaDer = new JLabel("");
		labelflechaDer.setBounds(941, 196, 50, 50);
		labelflechaDer.setIcon(new ImageIcon(getClass().getResource("/imagenes/flecha.png")));
		add(labelflechaDer);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/imagenes/fondoAbajo.jpg")));
		lblNewLabel.setBounds(0, 0, 1018, 465);
		add(lblNewLabel);
		
		inicializarCampos();
	}

	private void enaDis(boolean b){
		btnSalvar.setEnabled(b);
		btnEliminar.setEnabled(b);
		textBath.setEnabled(b);
		textCity.setEnabled(b);
		textKitchen.setEnabled(b);
		textLiving.setEnabled(b);
		textPaneDescription.setEnabled(b);
		textPaneDescription.setEditable(b);
		textPark.setEnabled(b);
		textRooms.setEnabled(b);
		comboBox.setEnabled(b);
		btnanadirImg.setEnabled(b);
		btnEliminarimg.setEnabled(b);
		textPaneDescription.setText("");
		textCity.setText("");
		textRooms.setText("");
		textKitchen.setText("");
		textBath.setText("");
		textLiving.setText("");
		textPark.setText("");
	}

	private void inicializarCampos() {
		modeloEC.addElement("Nueva Casa Rural");
		ApplicationFacadeInterface facade = Start.getBusinessLogic();
		java.util.Iterator<RuralHouse> i;
		try {
			i = facade.getOwner().getRuralHouses().iterator();
			while (i.hasNext()){
				modeloEC.addElement(Integer.toString(i.next().getHouseNumber()));
			}
			enaDis(false);
			comBoxCasas.setSelectedIndex(-1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public DefaultComboBoxModel<String> getModeloEC() {
		return modeloEC;
	}

	public void setModeloEC(DefaultComboBoxModel<String> modeloEC) {
		this.modeloEC = modeloEC;
	}
	
	public void buscarImagen(){
		
		
		JFileChooser fc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Imágenes", "bmp", "gif", "jpg", "png");
		fc.setFileFilter(filter);
		//fc.setCurrentDirectory();

        int respuesta = fc.showOpenDialog(this);

        if (respuesta == JFileChooser.APPROVE_OPTION)
        {
            File archivoElegido = fc.getSelectedFile();
            ImageIcon imagen = new ImageIcon(archivoElegido.getPath());
            Image aux = imagen.getImage();
            Image aux1= aux.getScaledInstance(image1label.getHeight(), image1label.getWidth(), java.awt.Image.SCALE_SMOOTH);
            images.add(aux);
            
            image1label.setIcon(new ImageIcon(aux1));
        }
	}
}	
