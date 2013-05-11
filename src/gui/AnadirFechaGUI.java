package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import businessLogic.ApplicationFacadeInterface;
import com.toedter.calendar.JDateChooser;
import domain.Fechas;
import domain.Offer;
import domain.RuralHouse;
import javax.swing.JTextField;
import javax.swing.JSpinner;

public class AnadirFechaGUI extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTable tableCasas;
	private JScrollPane scrollPane;
	private JDateChooser dateChooserFin;
	private JDateChooser dateChooserIni;
	private JCheckBox chckbxfechaFinalizacin;
	private JTextField textField;
	private JLabel lblFechaFin;
	private JSpinner spinner;
	private JComboBox<String> comBoxCasas;
	private DefaultComboBoxModel<String> modeloEC = new DefaultComboBoxModel<String>();
	private DefaultTableModel modelTb= new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Fecha", "Precio", "MinimoDias", "Oferta", "Obligatoria Oferta" 
			}
		);
	
	@SuppressWarnings("serial")
	public AnadirFechaGUI() {
		setLayout(null);
		
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
		scrollPane.setBounds(377, 68, 571, 184);
		add(scrollPane);
		
		dateChooserIni = new JDateChooser();
		dateChooserIni.setEnabled(true);
		dateChooserIni.setDateFormatString("yyyy-MM-dd");
		dateChooserIni.setBounds(165, 97, 149, 21);
		add(dateChooserIni);
		
		dateChooserFin = new JDateChooser();
		dateChooserFin.setEnabled(false);
		dateChooserFin.setVisible(false);
		dateChooserFin.setDateFormatString("yyyy-MM-dd");
		dateChooserFin.setBounds(165, 204, 149, 22);
		add(dateChooserFin);
		
		JLabel lblFechaInicio = new JLabel("Fecha Inicio:");
		lblFechaInicio.setFont(new Font("Dialog", Font.BOLD, 11));
		lblFechaInicio.setBounds(94, 97, 74, 21);
		add(lblFechaInicio);
		
		lblFechaFin = new JLabel("Fecha Fin:");
		lblFechaFin.setFont(new Font("Dialog", Font.BOLD, 11));
		lblFechaFin.setBounds(107, 204, 74, 21);
		lblFechaFin.setVisible(false);
		add(lblFechaFin);
		
		chckbxfechaFinalizacin = new JCheckBox("\u00BFFecha Finalizaci\u00F3n?");
		chckbxfechaFinalizacin.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(chckbxfechaFinalizacin.isSelected()){
					dateChooserFin.setVisible(true);
					dateChooserFin.setEnabled(true);
					lblFechaFin.setVisible(true);
					spinner.setEnabled(true);
				}else{
					dateChooserFin.setVisible(false);
					dateChooserFin.setEnabled(false);
					lblFechaFin.setVisible(false);
					spinner.setValue(1);
					spinner.setEnabled(false);}
			}
		});
		chckbxfechaFinalizacin.setBounds(165, 148, 149, 23);
		chckbxfechaFinalizacin.setOpaque(false);
		add(chckbxfechaFinalizacin);
		
		JLabel lblPrecioPorDa = new JLabel("Precio por d\u00EDa:");
		lblPrecioPorDa.setFont(new Font("Dialog", Font.BOLD, 11));
		lblPrecioPorDa.setBounds(82, 260, 95, 21);
		add(lblPrecioPorDa);
		
		textField = new JTextField();
		textField.setBounds(165, 261, 149, 20);
		textField.setColumns(10);
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				char car = evt.getKeyChar();
				if (car == '.');
				else if((car<'0' || car>'9')) evt.consume();
			}
		});
		add(textField);

		
		JButton btnNewButton = new JButton("Guardar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ApplicationFacadeInterface facade = Start.getBusinessLogic();
					Date aux = dateChooserIni.getDate();
					Date aux2;
					if(chckbxfechaFinalizacin.isSelected()){
						aux2= dateChooserFin.getDate();
					}else{
						aux2= dateChooserIni.getDate();
					}
					int numdias= (int) spinner.getValue();
					float precio = Float.parseFloat(textField.getText());
					int numero= Integer.parseInt(comBoxCasas.getSelectedItem().toString());
					facade.anadirFechas(Start.getUsuario(), numero, aux, aux2, precio, numdias);
					JPanel temp1= new PantallaPrincipalGUI();
					Start.modificarPanelAbajo(temp1);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(843, 370, 105, 34);
		add(btnNewButton);
		
		spinner = new JSpinner();
		spinner.setBounds(165, 318, 52, 20);
		spinner.setValue(1);
		spinner.setEnabled(false);
		add(spinner);
		
		JLabel lblMnimoDeDas = new JLabel("M\u00EDnimo de d\u00EDas:");
		lblMnimoDeDas.setFont(new Font("Dialog", Font.BOLD, 11));
		lblMnimoDeDas.setBounds(73, 318, 95, 21);
		add(lblMnimoDeDas);
		
		comBoxCasas = new JComboBox<String>();
		comBoxCasas.setSelectedIndex(-1);
		comBoxCasas.setModel(modeloEC);
		comBoxCasas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comBoxCasas.getSelectedIndex() != -1){
					actualizarTabla(Integer.parseInt(comBoxCasas.getSelectedItem().toString()));
				} else borrarTabla();
			}
		});
		comBoxCasas.setBounds(31, 25, 377, 33);
		add(comBoxCasas);
		
		JButton buttonEliminar = new JButton("Eliminar");
		buttonEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ApplicationFacadeInterface facade=Start.getBusinessLogic();
				try {						
					Vector<Fechas> aux1 =  facade.getFechas(Start.getUsuario(), Integer.parseInt(comBoxCasas.getSelectedItem().toString()));
					int x = (int) tableCasas.getValueAt(tableCasas.getSelectedRow(), 0);
					Date ini = aux1.get(x).getFecha();
					facade.eliminarFecha(Start.getUsuario(), Integer.parseInt(comBoxCasas.getSelectedItem().toString()), ini);						
					JPanel panel = new PantallaPrincipalGUI();
					Start.modificarPanelAbajo(panel);
					javax.swing.JOptionPane.showMessageDialog(null,"Se ha eliminado la oferta", "Bien....",javax.swing.JOptionPane.INFORMATION_MESSAGE);	
				}catch (Exception e) {
					javax.swing.JOptionPane.showMessageDialog(null,"Error al eliminar: " + e.getMessage(), "No....",javax.swing.JOptionPane.INFORMATION_MESSAGE);
				} 
			}
		});
		buttonEliminar.setBounds(694, 370, 105, 34);
		add(buttonEliminar);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/localData/fondoAbajo.jpg")));
		lblNewLabel.setBounds(0, 0, 1018, 465);
		add(lblNewLabel);
		
		inicializarCampos();
	}
	
	private void inicializarCampos() {
		ApplicationFacadeInterface facade = Start.getBusinessLogic();
		java.util.Iterator<RuralHouse> i;
		try {
			i = facade.getOwner(Start.getUsuario()).getRuralHouses().iterator();
			while (i.hasNext()){
				modeloEC.addElement(Integer.toString(i.next().getHouseNumber()));
			}
			comBoxCasas.setSelectedIndex(-1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void actualizarTabla(int numeroRH){
		
		ApplicationFacadeInterface facade = Start.getBusinessLogic();
		try {
			borrarTabla();
			Vector<Fechas> aux =  facade.getFechas(Start.getUsuario(), numeroRH);
			Iterator<Fechas> i = aux.iterator();
			while (i.hasNext()){
				Vector<Object> vector = new Vector<Object>();
				Fechas auxi = i.next();
				vector.add(auxi.getFecha());
				vector.add(auxi.getPrecio());
				vector.add(auxi.getMinimoDias());
				if(auxi.getOfer()==null) vector.add(false);
				else vector.add(true);
				vector.add(auxi.isUnidoOferta());
				modelTb.addRow(vector);
			}
			ajustarColumnas();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void ajustarColumnas(){
		int anchoTabla = 551;
		int anchoColumna = 0, anchoColumnaMin = 0, anchoColumnaMax = 0;
		TableColumn columnaTabla = null;
		for(int i=0; i<tableCasas.getColumnCount(); i++) {
			columnaTabla = tableCasas.getColumnModel().getColumn(i);
			switch(i) {
				case 0: anchoColumna = (20*anchoTabla)/100;
				anchoColumnaMin = (20*anchoTabla)/100;
				anchoColumnaMax = (20*anchoTabla)/100;
				break;
				case 1: anchoColumna = (20*anchoTabla)/100;
				anchoColumnaMin = (20*anchoTabla)/100;
				anchoColumnaMax = (20*anchoTabla)/100;
				break;
				case 2: anchoColumna = (10*anchoTabla)/100;
				anchoColumnaMin = (10*anchoTabla)/100;
				anchoColumnaMax = (10*anchoTabla)/100;
				break;
				case 3: anchoColumna = (20*anchoTabla)/100;
				anchoColumnaMin = (20*anchoTabla)/100;
				anchoColumnaMax = (20*anchoTabla)/100;
				break;
				case 4: anchoColumna = (20*anchoTabla)/100;
				anchoColumnaMin = (20*anchoTabla)/100;
				anchoColumnaMax = (20*anchoTabla)/100;
				break;
			}
			columnaTabla.setPreferredWidth(anchoColumna);
			columnaTabla.setMinWidth(anchoColumnaMin);
			columnaTabla.setMaxWidth(anchoColumnaMax);
		}
	}

	private void borrarTabla(){ while (modelTb.getRowCount() > 0) modelTb.removeRow(modelTb.getRowCount()-1); }
}
