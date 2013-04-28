package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import businessLogic.ApplicationFacadeInterface;
import domain.RuralHouse;

public class BusquedaGUI extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField textCity;
	private DefaultComboBoxModel<String> modeloEC = new DefaultComboBoxModel<String>();
	private JButton btnSalvar;

	public BusquedaGUI() {
		setLayout(null);
		
		JLabel label_5 = new JLabel("N\u00BA Habitaciones*:");
		label_5.setHorizontalAlignment(SwingConstants.RIGHT);
		label_5.setFont(new Font("Dialog", Font.PLAIN, 21));
		label_5.setBounds(271, 257, 168, 34);
		add(label_5);
		
		JLabel label_6 = new JLabel("N\u00BA Salas de estar*:");
		label_6.setHorizontalAlignment(SwingConstants.RIGHT);
		label_6.setFont(new Font("Dialog", Font.PLAIN, 21));
		label_6.setBounds(256, 294, 183, 34);
		add(label_6);
		
		JLabel label_7 = new JLabel("N\u00BA Aparcamientos*:");
		label_7.setHorizontalAlignment(SwingConstants.RIGHT);
		label_7.setFont(new Font("Dialog", Font.PLAIN, 21));
		label_7.setBounds(256, 331, 183, 34);
		add(label_7);
		
		textCity = new JTextField();
		textCity.setText("");
		textCity.setEnabled(false);
		textCity.setColumns(10);
		textCity.setBounds(456, 127, 128, 33);
		add(textCity);
		
		JLabel label = new JLabel("N\u00BA Cocinas*:");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setFont(new Font("Dialog", Font.PLAIN, 21));
		label.setBounds(315, 217, 124, 34);
		add(label);
		
		JLabel label_1 = new JLabel("N\u00BA Ba\u00F1os*:");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("Dialog", Font.PLAIN, 21));
		label_1.setBounds(335, 172, 104, 34);
		add(label_1);
		
		JLabel label_2 = new JLabel("Ciudad*:");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("Dialog", Font.PLAIN, 21));
		label_2.setBounds(315, 127, 124, 34);
		add(label_2);
		
		btnSalvar = new JButton("Buscar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					/*String description = textPaneDescription.getText();
					String city = textCity.getText();
					int nRooms = Integer.parseInt(textRooms.getText());
					int nKitchen = Integer.parseInt(textKitchen.getText());
					int nBaths = Integer.parseInt(textBath.getText());
					int nLiving = Integer.parseInt(textLiving.getText());
					int nPark = Integer.parseInt(textPark.getText());
					ApplicationFacadeInterface facade=Start.getBusinessLogic();
					if (comBoxCasas.getSelectedIndex() == 0){
						facade.anadirRuralHouse(description, city, nRooms, nKitchen, nBaths, nLiving, nPark);
					} else if (comBoxCasas.getSelectedIndex() > 0) {
						facade.modificarRuralHouse((int) comBoxCasas.getSelectedItem(), description, city, nRooms, nKitchen, nBaths, nLiving, nPark);
					}*/
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
		btnSalvar.setBounds(694, 225, 124, 45);
		add(btnSalvar);
		
		JComboBox comboBanos = new JComboBox();
		comboBanos.setBounds(456, 176, 128, 27);
		add(comboBanos);
		
		JComboBox comboCocinas = new JComboBox();
		comboCocinas.setBounds(456, 217, 128, 27);
		add(comboCocinas);
		
		JComboBox comboHabita = new JComboBox();
		comboHabita.setBounds(456, 255, 128, 27);
		add(comboHabita);
		
		JComboBox comboEstar = new JComboBox();
		comboEstar.setBounds(456, 294, 128, 27);
		add(comboEstar);
		
		JComboBox comboPark = new JComboBox();
		comboPark.setBounds(456, 331, 128, 27);
		add(comboPark);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/imagenes/fondoAbajo.jpg")));
		lblNewLabel.setBounds(0, 0, 1018, 465);
		add(lblNewLabel);
		
		inicializarCampos();
	}
	private void enaDis(boolean b){
		/*btnSalvar.setEnabled(b);
		btnEliminar.setEnabled(b);
		textCity.setEnabled(b);
		textCity.setText("");*/
	}

	private void inicializarCampos() {
		/*modeloEC.addElement("Nueva Casa Rural");
		ApplicationFacadeInterface facade = Start.getBusinessLogic();
		java.util.Iterator<RuralHouse> i;
		try {
			i = facade.getOwner().getRuralHouses().iterator();
			while (i.hasNext()){
				modeloEC.addElement(Integer.toString(i.next().getHouseNumber()));
			}
			enaDis(false);
		} catch (Exception e) {
			e.printStackTrace();
		}*/
	}
	

	public DefaultComboBoxModel<String> getModeloEC() {
		return modeloEC;
	}

	public void setModeloEC(DefaultComboBoxModel<String> modeloEC) {
		this.modeloEC = modeloEC;
	}
}
