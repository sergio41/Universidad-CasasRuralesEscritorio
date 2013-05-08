package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import businessLogic.ApplicationFacadeInterface;

import com.toedter.calendar.JDateChooser;

import domain.RuralHouse;
import javax.swing.JTextField;
import javax.swing.JSpinner;

public class anadirFecha extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTable tableCasas;
	private DefaultTableModel modelTb = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"nº", "Ciudad", "Baños", "Cocinas", "Salas", "Dormitorios", "Parkings"
			}
		);
	private JScrollPane scrollPane;
	private JDateChooser dateChooserFin;
	private JDateChooser dateChooserIni;
	private JCheckBox chckbxfechaFinalizacin;
	private JTextField textField;
	
	@SuppressWarnings("serial")
	public anadirFecha() {
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
	        try {
		  		ApplicationFacadeInterface facade = Start.getBusinessLogic();
		  		int selectedRow2 = tableCasas.getSelectedRow();
				RuralHouse casita = facade.getCasas((int) tableCasas.getValueAt(selectedRow2, 0));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.getMessage();
			}
	      }

	    });
		add(tableCasas);
		
		scrollPane = new JScrollPane(tableCasas, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(377, 68, 571, 184);
		add(scrollPane);
		
		dateChooserIni = new JDateChooser();
		dateChooserIni.setEnabled(true);
		dateChooserIni.setDateFormatString("yyyy-MM-dd");
		dateChooserIni.setBounds(165, 68, 149, 21);
		add(dateChooserIni);
		
		dateChooserFin = new JDateChooser();
		dateChooserFin.setEnabled(true);
		dateChooserFin.setDateFormatString("yyyy-MM-dd");
		dateChooserFin.setBounds(165, 175, 149, 22);
		add(dateChooserFin);
		
		JLabel lblFechaInicio = new JLabel("Fecha Inicio:");
		lblFechaInicio.setFont(new Font("Dialog", Font.BOLD, 11));
		lblFechaInicio.setBounds(94, 68, 74, 21);
		add(lblFechaInicio);
		
		JLabel lblFechaFin = new JLabel("Fecha Fin:");
		lblFechaFin.setFont(new Font("Dialog", Font.BOLD, 11));
		lblFechaFin.setBounds(107, 175, 74, 21);
		add(lblFechaFin);
		
		chckbxfechaFinalizacin = new JCheckBox("\u00BFFecha Finalizaci\u00F3n?");
		chckbxfechaFinalizacin.setBounds(165, 119, 149, 23);
		chckbxfechaFinalizacin.setOpaque(false);
		add(chckbxfechaFinalizacin);
		
		JLabel lblPrecioPorDa = new JLabel("Precio por d\u00EDa:");
		lblPrecioPorDa.setFont(new Font("Dialog", Font.BOLD, 11));
		lblPrecioPorDa.setBounds(82, 231, 95, 21);
		add(lblPrecioPorDa);
		
		textField = new JTextField();
		textField.setBounds(165, 232, 149, 20);
		add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Guardar");
		btnNewButton.setBounds(843, 370, 105, 34);
		add(btnNewButton);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(165, 289, 52, 20);
		add(spinner);
		
		JLabel lblMnimoDeDas = new JLabel("M\u00EDnimo de d\u00EDas:");
		lblMnimoDeDas.setFont(new Font("Dialog", Font.BOLD, 11));
		lblMnimoDeDas.setBounds(73, 289, 95, 21);
		add(lblMnimoDeDas);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/imagenes/fondoAbajo.jpg")));
		lblNewLabel.setBounds(0, 0, 1018, 465);
		add(lblNewLabel);
	}
}
