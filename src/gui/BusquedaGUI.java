package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import businessLogic.ApplicationFacadeInterface;
import domain.Offer;
import domain.RuralHouse;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.ScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
	private JTable tableCasas;
	private DefaultTableModel modelTb = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"n�", "Ciudad", "Ba�os", "Cocinas", "Salas", "Dormitorios", "Parkings"
			}
		);
	private JTable tableOfertas;
	private DefaultTableModel modelTbOfertas = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				/*"n�",*/ "FechaInicio", "FechaFin", "Precio"
			}
		);
	private JScrollPane scrollPane;
	private JButton btnNewButton;
	private JTextPane textDescrp;
	private JLabel lblprop;
	private JLabel lblTelef;
	
	@SuppressWarnings("serial")
	public BusquedaGUI() {
		setLayout(null);
		
		JLabel label_5 = new JLabel("N\u00BA Habitaciones*:");
		label_5.setHorizontalAlignment(SwingConstants.RIGHT);
		label_5.setFont(new Font("Dialog", Font.BOLD, 11));
		label_5.setBounds(108, 112, 114, 34);
		add(label_5);
		
		JLabel label_6 = new JLabel("N\u00BA Salas de estar*:");
		label_6.setHorizontalAlignment(SwingConstants.RIGHT);
		label_6.setFont(new Font("Dialog", Font.BOLD, 11));
		label_6.setBounds(98, 142, 124, 34);
		add(label_6);
		
		JLabel label_7 = new JLabel("N\u00BA Aparcamientos*:");
		label_7.setHorizontalAlignment(SwingConstants.RIGHT);
		label_7.setFont(new Font("Dialog", Font.BOLD, 11));
		label_7.setBounds(98, 172, 124, 34);
		add(label_7);
		
		JLabel label = new JLabel("N\u00BA Cocinas*:");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setFont(new Font("Dialog", Font.BOLD, 11));
		label.setBounds(128, 82, 94, 34);
		add(label);
		
		JLabel label_1 = new JLabel("N\u00BA Ba\u00F1os*:");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("Dialog", Font.BOLD, 11));
		label_1.setBounds(139, 53, 83, 34);
		add(label_1);
		
		JLabel label_2 = new JLabel("Ciudad*:");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("Dialog", Font.BOLD, 11));
		label_2.setBounds(137, 23, 83, 34);
		add(label_2);
		
		btnSalvar = new JButton("Buscar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String ciudad =(String) comboCity.getSelectedItem();
					int Banos;
					int Habita;
					int Cocina;
					int Estar;
					int Park;
					if(comboBanos.getSelectedIndex()==-1){Banos=0;
					}else{Banos = Integer.parseInt((String) comboBanos.getSelectedItem());}
					
					if(comboHabita.getSelectedIndex()==-1){Habita=0;
					}else{Habita = Integer.parseInt((String) comboHabita.getSelectedItem());}
					
					if(comboCocinas.getSelectedIndex()==-1){Cocina=0;
					}else{Cocina = Integer.parseInt((String) comboCocinas.getSelectedItem());}
					
					if(comboEstar.getSelectedIndex()==-1){Estar=0;
					}else{Estar = Integer.parseInt((String) comboEstar.getSelectedItem());}
					
					if(comboPark.getSelectedIndex()==-1){Park=0;
					}else{Park = Integer.parseInt((String) comboPark.getSelectedItem());}
					
					actualizarTabla(ciudad,Banos,Habita,Cocina,Estar,Park);
				} catch (Exception e) {
					javax.swing.JOptionPane.showMessageDialog(null,e.toString(), "Mal....",javax.swing.JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnSalvar.setForeground(Color.BLUE);
		btnSalvar.setFont(new Font("Dialog", Font.PLAIN, 21));
		btnSalvar.setEnabled(false);
		btnSalvar.setBounds(108, 225, 259, 27);
		add(btnSalvar);
		
		comboBanos = new JComboBox<String>();
		comboBanos.setFont(new Font("Dialog", Font.BOLD, 11));
		comboBanos.setBounds(239, 57, 128, 27);
		comboBanos.setModel(modeloBanos);
		add(comboBanos);
		
		comboCocinas = new JComboBox<String>();
		comboCocinas.setFont(new Font("Dialog", Font.BOLD, 11));
		comboCocinas.setBounds(239, 87, 128, 27);
		comboCocinas.setModel(modeloCocin);
		add(comboCocinas);
		
		comboHabita = new JComboBox<String>();
		comboHabita.setFont(new Font("Dialog", Font.BOLD, 11));
		comboHabita.setBounds(239, 117, 128, 27);
		comboHabita.setModel(modeloDorm);
		add(comboHabita);
		
		comboEstar = new JComboBox<String>();
		comboEstar.setFont(new Font("Dialog", Font.BOLD, 11));
		comboEstar.setBounds(239, 147, 128, 27);
		comboEstar.setModel(modeloSala);
		add(comboEstar);
		
		comboPark = new JComboBox<String>();
		comboPark.setFont(new Font("Dialog", Font.BOLD, 11));
		comboPark.setBounds(239, 177, 128, 27);
		comboPark.setModel(modeloParkin);
		add(comboPark);
		
		
		comboCity = new JComboBox<String>();
		comboCity.setFont(new Font("Dialog", Font.BOLD, 11));
		comboCity.setBounds(239, 27, 128, 27);
		comboCity.setModel(modeloCity);
		add(comboCity);
		
		tableCasas = new JTable(){
	        public boolean isCellEditable(int rowIndex, int vColIndex) {
	            return false;
	        }};
		tableCasas.setModel(modelTb);
		tableCasas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableCasas.setBounds(434, 38, 551, 229);
		ListSelectionModel cellSelectionModel = tableCasas.getSelectionModel();
	    cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	    cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
	      public void valueChanged(ListSelectionEvent e) {
	        try {
		  		ApplicationFacadeInterface facade = Start.getBusinessLogic();
		  		int selectedRow2 = tableCasas.getSelectedRow();
				RuralHouse casita = facade.getCasas((int) tableCasas.getValueAt(selectedRow2, 0));
				lblprop.setText(casita.getUserAplication().getEmail());
				lblTelef.setText(casita.getUserAplication().getTelefono());
				textDescrp.setText(casita.getDescription());
				actualizarTablaOferta((int) tableCasas.getValueAt(selectedRow2, 0));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.getMessage();
			}
	      }

	    });
		add(tableCasas);
		
		scrollPane = new JScrollPane(tableCasas, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(377, 146, 571, 106);
		add(scrollPane);
		
		tableOfertas = new JTable();
		tableOfertas.setModel(modelTbOfertas);
		tableOfertas.setBounds(626, 282, 318, 66);
		add(tableOfertas);
		
		ScrollPane scrollPane_1 = new ScrollPane();
		scrollPane_1.setBounds(626, 281, 318, 67);
		add(scrollPane_1);
		
		JLabel label_3 = new JLabel("Descripci\u00F3n:");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_3.setBounds(56, 258, 114, 34);
		add(label_3);
		
		btnNewButton = new JButton("Reservar\r\n");
		btnNewButton.setBounds(781, 397, 163, 34);
		add(btnNewButton);
		
		textDescrp = new JTextPane();
		textDescrp.setBounds(66, 286, 301, 62);
		textDescrp.setEditable(false);
		add(textDescrp);
		
		JLabel lblNewLabel_1 = new JLabel("Propietario:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(56, 359, 74, 21);
		add(lblNewLabel_1);
		
		lblprop = new JLabel("");
		lblprop.setForeground(new Color(0, 128, 0));
		lblprop.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblprop.setBounds(133, 359, 234, 21);
		add(lblprop);
		
		JLabel lblTelefono = new JLabel("Tel\u00E9fono:");
		lblTelefono.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTelefono.setBounds(56, 404, 74, 14);
		add(lblTelefono);
		
		lblTelef = new JLabel("");
		lblTelef.setForeground(new Color(0, 128, 0));
		lblTelef.setBounds(128, 397, 239, 21);
		add(lblTelef);
		
		JButton btnImg = new JButton("Ver imagenes");
		btnImg.setBounds(373, 397, 249, 23);
		add(btnImg);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/imagenes/fondoAbajo.jpg")));
		lblNewLabel.setBounds(0, 0, 1018, 465);
		add(lblNewLabel);
		
		inicializarCampos();
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
	
	private void actualizarTabla(String city, int banos, int habita, int cocina, int estar, int park ){
		
		ApplicationFacadeInterface facade = Start.getBusinessLogic();
		try {
			borrarTabla();
			Vector<RuralHouse> aux =  facade.getCasas(city,banos,habita,cocina,estar,park );
			Iterator<RuralHouse> i = aux.iterator();
			while (i.hasNext()){
				Vector<Object>  vector = new Vector<Object>();
				RuralHouse auxi = i.next();
				vector.add(auxi.getHouseNumber());
				vector.add(auxi.getCity());
				vector.add(auxi.getBaths());
				vector.add(auxi.getKitchen());
				vector.add(auxi.getLiving());
				vector.add(auxi.getRooms());
				vector.add(auxi.getPark());
				modelTb.addRow(vector);
			}
			ajustarColumnas();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
	}
	
	private void actualizarTablaOferta(int num){
		
		ApplicationFacadeInterface facade = Start.getBusinessLogic();
		try {
			borrarTablaOferta();
			RuralHouse aux =  facade.getCasas(num);
			Vector<Offer> aux2= aux.getOfertas();
			Iterator<Offer> i = aux2.iterator();
			while (i.hasNext()){
				Vector<Object>  vector = new Vector<Object>();
				Offer auxi = i.next();
				//vector.add(auxi.getHouseNumber());
				vector.add(auxi.getPrimerDia());
				vector.add(auxi.getUltimoDia());
				vector.add(auxi.getPrice());
				modelTbOfertas.addRow(vector);
			}
			ajustarColumnas();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
	}
	
	private void borrarTabla(){ while (modelTb.getRowCount() > 0) modelTb.removeRow(modelTb.getRowCount()-1); }

	private void borrarTablaOferta(){ while (modelTbOfertas.getRowCount() > 0) modelTbOfertas.removeRow(modelTbOfertas.getRowCount()-1); }

	
	private void ajustarColumnas(){
		int anchoTabla = 551;
		int anchoColumna = 0, anchoColumnaMin = 0, anchoColumnaMax = 0;
		TableColumn columnaTabla = null;
		for(int i=0; i<tableCasas.getColumnCount(); i++) {
			columnaTabla = tableCasas.getColumnModel().getColumn(i);
			switch(i) {
				case 0: anchoColumna = (5*anchoTabla)/100;
				anchoColumnaMin = (5*anchoTabla)/100;
				anchoColumnaMax = (5*anchoTabla)/100;
				break;
				case 1: anchoColumna = (20*anchoTabla)/100;
				anchoColumnaMin = (20*anchoTabla)/100;
				anchoColumnaMax = (20*anchoTabla)/100;
				break;
				case 2: anchoColumna = (15*anchoTabla)/100;
				anchoColumnaMin = (15*anchoTabla)/100;
				anchoColumnaMax = (15*anchoTabla)/100;
				break;
				case 3: anchoColumna = (15*anchoTabla)/100;
				anchoColumnaMin = (15*anchoTabla)/100;
				anchoColumnaMax = (15*anchoTabla)/100;
				break;
				case 4: anchoColumna = (15*anchoTabla)/100;
				anchoColumnaMin = (15*anchoTabla)/100;
				anchoColumnaMax = (15*anchoTabla)/100;
				break;
				case 5: anchoColumna = (16*anchoTabla)/100;
				anchoColumnaMin = (16*anchoTabla)/100;
				anchoColumnaMax = (16*anchoTabla)/100;
				break;
				case 6: anchoColumna = (15*anchoTabla)/100;
				anchoColumnaMin = (15*anchoTabla)/100;
				anchoColumnaMax = (15*anchoTabla)/100;
				break;
			}
			columnaTabla.setPreferredWidth(anchoColumna);
			columnaTabla.setMinWidth(anchoColumnaMin);
			columnaTabla.setMaxWidth(anchoColumnaMax);
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
