package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
import com.toedter.calendar.JDateChooser;
import javax.swing.JCheckBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JSpinner;

public class BusquedaConFechasGUI extends JPanel {
	private static final long serialVersionUID = 1L;
	private DefaultComboBoxModel<String> modeloEC = new DefaultComboBoxModel<String>();
	private JButton btnSalvar;
	private DefaultComboBoxModel<String> modeloCity = new DefaultComboBoxModel<String>();
	private DefaultComboBoxModel<String> modeloBanos = new DefaultComboBoxModel<String>();
	private DefaultComboBoxModel<String> modeloDorm = new DefaultComboBoxModel<String>();
	private DefaultComboBoxModel<String> modeloCocin = new DefaultComboBoxModel<String>();
	private DefaultComboBoxModel<String> modeloSala = new DefaultComboBoxModel<String>();
	private DefaultComboBoxModel<String> modeloParkin = new DefaultComboBoxModel<String>();
	private JComboBox<String> comboCity;
	private JTable tableCasas;
	private DefaultTableModel modelTb = new DefaultTableModel(
			new Object[][] {
			},
			new Object[] {
				"nº", "Ciudad", "Baños", "Cocinas", "Salas", "Dormitorios", "Parkings"
			}
		);
	private DefaultTableModel modelTbOfertas = new DefaultTableModel(
			new Object[][] {
			},
			new Object[] {
				/*"nº",*/ "FechaInicio", "FechaFin", "Precio"
			}
		);
	private JScrollPane scrollPane;
	private JButton btnNewButton;
	private JTextPane textDescrp;
	private JLabel lblprop;
	private JLabel lblTelef;
	private JDateChooser dateChooserFin;
	private JDateChooser dateChooserIni;
	
	@SuppressWarnings("serial")
	public BusquedaConFechasGUI() {
		setLayout(null);
		
		JLabel lblNMinHabitaciones = new JLabel("N\u00BA min. habitaciones*:");
		lblNMinHabitaciones.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNMinHabitaciones.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNMinHabitaciones.setBounds(23, 138, 156, 27);
		add(lblNMinHabitaciones);
		
		JLabel lblNMinSalas = new JLabel("N\u00BA min. salas de estar*:");
		lblNMinSalas.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNMinSalas.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNMinSalas.setBounds(13, 181, 166, 27);
		add(lblNMinSalas);
		
		JLabel lblNMinaparcamientos = new JLabel("N\u00BA min. aparcamientos*:");
		lblNMinaparcamientos.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNMinaparcamientos.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNMinaparcamientos.setBounds(0, 224, 179, 27);
		add(lblNMinaparcamientos);
		
		JLabel lblNMinCocinas = new JLabel("N\u00BA min. cocinas*:");
		lblNMinCocinas.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNMinCocinas.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNMinCocinas.setBounds(65, 95, 114, 27);
		add(lblNMinCocinas);
		
		JLabel lblNMinBaos = new JLabel("N\u00BA min. ba\u00F1os*:");
		lblNMinBaos.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNMinBaos.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNMinBaos.setBounds(65, 52, 114, 27);
		add(lblNMinBaos);
		
		JLabel label_2 = new JLabel("Ciudad*:");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("Times New Roman", Font.BOLD, 15));
		label_2.setBounds(94, 9, 83, 27);
		add(label_2);
		
		btnSalvar = new JButton("Buscar");
		btnSalvar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnSalvar.setForeground(Color.BLUE);
		btnSalvar.setFont(new Font("Dialog", Font.BOLD, 19));
		btnSalvar.setEnabled(false);
		btnSalvar.setBounds(73, 419, 163, 34);
		add(btnSalvar);
		
		
		comboCity = new JComboBox<String>();
		comboCity.setFont(new Font("Dialog", Font.BOLD, 11));
		comboCity.setBounds(191, 9, 149, 27);
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
	      }

	    });
		add(tableCasas);
		
		scrollPane = new JScrollPane(tableCasas, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(373, 9, 600, 250);
		add(scrollPane);
		
		JLabel label_3 = new JLabel("Descripci\u00F3n:");
		label_3.setFont(new Font("Times New Roman", Font.BOLD, 15));
		label_3.setBounds(373, 272, 114, 27);
		add(label_3);
		
		btnNewButton = new JButton("Reservar\r\n");
		btnNewButton.setForeground(Color.BLUE);
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 19));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
			}
		});	
		btnNewButton.setBounds(781, 419, 163, 34);
		add(btnNewButton);
		
		textDescrp = new JTextPane();
		textDescrp.setBounds(377, 312, 301, 94);
		textDescrp.setEditable(false);
		add(textDescrp);
		
		JLabel lblNewLabel_1 = new JLabel("Propietario:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_1.setBounds(689, 312, 83, 27);
		add(lblNewLabel_1);
		
		lblprop = new JLabel("");
		lblprop.setForeground(new Color(0, 128, 0));
		lblprop.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblprop.setBounds(775, 312, 234, 27);
		add(lblprop);
		
		JLabel lblTelefono = new JLabel("Tel\u00E9fono:");
		lblTelefono.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblTelefono.setBounds(689, 379, 74, 27);
		add(lblTelefono);
		
		lblTelef = new JLabel("");
		lblTelef.setForeground(new Color(0, 128, 0));
		lblTelef.setBounds(775, 379, 239, 27);
		add(lblTelef);
		
		JButton btnImg = new JButton("Ver imagenes");
		btnImg.setForeground(Color.BLUE);
		btnImg.setFont(new Font("Dialog", Font.BOLD, 19));
		btnImg.setBounds(309, 419, 163, 34);
		add(btnImg);
		
		dateChooserIni = new JDateChooser();
		dateChooserIni.setEnabled(true);
		dateChooserIni.setDateFormatString("yyyy-MM-dd");
		dateChooserIni.setBounds(191, 267, 149, 27);
		add(dateChooserIni);
		
		dateChooserFin = new JDateChooser();
		dateChooserFin.setEnabled(true);
		dateChooserFin.setDateFormatString("yyyy-MM-dd");
		dateChooserFin.setBounds(191, 303, 149, 27);
		add(dateChooserFin);
		
		JLabel lblFechaInicio = new JLabel("Fecha Inicio:");
		lblFechaInicio.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblFechaInicio.setBounds(94, 267, 96, 27);
		add(lblFechaInicio);
		
		JLabel lblFechaFin = new JLabel("Fecha Fin:");
		lblFechaFin.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblFechaFin.setBounds(107, 303, 83, 27);
		add(lblFechaFin);
		
		JButton buttonMapa = new JButton("Ver mapa");
		buttonMapa.setForeground(Color.BLUE);
		buttonMapa.setFont(new Font("Dialog", Font.BOLD, 19));
		buttonMapa.setBounds(545, 419, 163, 34);
		add(buttonMapa);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(191, 54, 67, 27);
		add(spinner);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setBounds(191, 95, 67, 27);
		add(spinner_1);
		
		JSpinner spinner_2 = new JSpinner();
		spinner_2.setBounds(191, 138, 67, 27);
		add(spinner_2);
		
		JSpinner spinner_3 = new JSpinner();
		spinner_3.setBounds(191, 181, 67, 27);
		add(spinner_3);
		
		JSpinner spinner_4 = new JSpinner();
		spinner_4.setBounds(191, 224, 67, 27);
		add(spinner_4);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/localData/fondoAbajo.jpg")));
		lblNewLabel.setBounds(0, 0, 1018, 465);
		add(lblNewLabel);
		
		inicializarCampos();
	}

	private void actualizarTabla(String city, int banos, int habita,
			int cocina, int estar, int park, Date ini, Date fin) {
		
		ApplicationFacadeInterface facade = Start.getBusinessLogic();
		try {
			borrarTabla();
			borrarTablaOferta();
			Vector<RuralHouse> aux =  facade.casasRuralesDisponibles(ini, fin, city,banos,habita,cocina,estar,park );
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
			e.getMessage();
		}
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
			if(Start.estadoLogin())
				btnNewButton.setEnabled(true);
			else
				btnNewButton.setEnabled(false);
			comboCity.setSelectedIndex(-1);
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
			borrarTablaOferta();
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
