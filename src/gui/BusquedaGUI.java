package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.Vector;

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
	private DefaultComboBoxModel<String> modeloEC = new DefaultComboBoxModel<String>();
	private JButton btnSalvar;
	private DefaultComboBoxModel<String> modeloCity = new DefaultComboBoxModel<String>();
	private DefaultComboBoxModel<String> modeloBanos = new DefaultComboBoxModel<String>();
	private DefaultComboBoxModel<String> modeloDorm = new DefaultComboBoxModel<String>();
	private DefaultComboBoxModel<String> modeloCocin = new DefaultComboBoxModel<String>();
	private DefaultComboBoxModel<String> modeloSala = new DefaultComboBoxModel<String>();
	private DefaultComboBoxModel<String> modeloParkin = new DefaultComboBoxModel<String>();
	private JComboBox<String> comboBanos;
	private JComboBox<String> comboCocinas;
	private JComboBox<String> comboHabita;
	private JComboBox<String> comboEstar;
	private JComboBox<String> comboPark;
	private JComboBox<String> comboCity;
	
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
		
		comboBanos = new JComboBox<String>();
		comboBanos.setBounds(456, 176, 128, 27);
		comboBanos.setModel(modeloBanos);
		add(comboBanos);
		
		comboCocinas = new JComboBox<String>();
		comboCocinas.setBounds(456, 217, 128, 27);
		comboCocinas.setModel(modeloCocin);
		add(comboCocinas);
		
		comboHabita = new JComboBox<String>();
		comboHabita.setBounds(456, 255, 128, 27);
		comboHabita.setModel(modeloDorm);
		add(comboHabita);
		
		comboEstar = new JComboBox<String>();
		comboEstar.setBounds(456, 294, 128, 27);
		comboEstar.setModel(modeloSala);
		add(comboEstar);
		
		comboPark = new JComboBox<String>();
		comboPark.setBounds(456, 331, 128, 27);
		comboPark.setModel(modeloParkin);
		add(comboPark);
		
		
		comboCity = new JComboBox<String>();
		comboCity.setBounds(456, 131, 128, 27);
		comboCity.setModel(modeloCity);
		add(comboCity);
		
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
		ApplicationFacadeInterface facade = Start.getBusinessLogic();
		try {
			Vector<RuralHouse> vectorCasas = facade.getAllRuralHouses();
			for(int i=0; i<vectorCasas.size();i++){
				if(!estaCity(vectorCasas.get(i).getCity())){
					modeloCity.addElement(vectorCasas.get(i).getCity());
				}
				if(!estaCocina(Integer.toString(vectorCasas.get(i).getKitchen()))){
					modeloCocin.addElement(Integer.toString(vectorCasas.get(i).getKitchen()));
				}
				if(!estaBanos(Integer.toString(vectorCasas.get(i).getBaths()))){
					modeloBanos.addElement(Integer.toString(vectorCasas.get(i).getBaths()));
				}
				if(!estaSala(Integer.toString(vectorCasas.get(i).getLiving()))){
					modeloSala.addElement(Integer.toString(vectorCasas.get(i).getLiving()));
				}
				if(!estaDorm(Integer.toString(vectorCasas.get(i).getRooms()))){
					modeloDorm.addElement(Integer.toString(vectorCasas.get(i).getRooms()));
				}
				if(!estaPark(Integer.toString(vectorCasas.get(i).getPark()))){
					modeloParkin.addElement(Integer.toString(vectorCasas.get(i).getPark()));
				}
			}
			comboBanos.setSelectedIndex(-1);
			comboCity.setSelectedIndex(-1);
			comboCocinas.setSelectedIndex(-1);
			comboEstar.setSelectedIndex(-1);
			comboHabita.setSelectedIndex(-1);
			comboPark.setSelectedIndex(-1);
			btnSalvar.setEnabled(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public DefaultComboBoxModel<String> getModeloEC() {
		return modeloEC;
	}

	public void setModeloEC(DefaultComboBoxModel<String> modeloEC) {
		this.modeloEC = modeloEC;
	}
	
	public boolean estaCity(String s){
		for (int j = 0; j<modeloCity.getSize();j++){
			System.out.println(j+modeloCity.getElementAt(j)+" "+s);
			if(modeloCity.getElementAt(j).compareTo(s)==0){
				return true;
			}
		}
		return false;		
	}
	
	public boolean estaBanos(String s){
		for (int j = 0; j<modeloBanos.getSize();j++){
			if(modeloBanos.getElementAt(j).compareTo(s)==0){
				return true;
			}
		}
		return false;		
	}
	
	public boolean estaCocina(String s){
		for (int j = 0; j<modeloCocin.getSize();j++){
			if(modeloCocin.getElementAt(j).compareTo(s)==0){
				return true;
			}
		}
		return false;		
	}
	
	public boolean estaSala(String s){
		for (int j = 0; j<modeloSala.getSize();j++){
			if(modeloSala.getElementAt(j).compareTo(s)==0){
				return true;
			}
		}
		return false;		
	}
	
	public boolean estaDorm(String s){
		for (int j = 0; j<modeloDorm.getSize();j++){
			if(modeloDorm.getElementAt(j).compareTo(s)==0){
				return true;
			}
		}
		return false;		
	}
	
	public boolean estaPark(String s){
		for (int j = 0; j<modeloParkin.getSize();j++){
			if(modeloParkin.getElementAt(j).compareTo(s)==0){
				return true;
			}
		}
		return false;		
	}
	
}
