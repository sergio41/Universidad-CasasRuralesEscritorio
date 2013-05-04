package gui;

import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.looks.plastic.Plastic3DLookAndFeel;
import com.toedter.calendar.JDateChooser;

import domain.*;
import businessLogic.*;
import java.awt.event.*;
import java.beans.*;
import java.util.*;

public class CreateOfferGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private DefaultComboBoxModel<Integer> modeloEC = new DefaultComboBoxModel<Integer>();
	private JComboBox<Integer> comBoxCasas;
	private JDateChooser calendarFirstday;
	private JDateChooser calendarLastday;
	private JButton bttnSalvar;
	private JTextField textPrecio;
	private JTable table;
	private DefaultTableModel modelTb = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Numero", "Inicio", "Fin", "Precio", "Obligatorio"
			}
		);

	public CreateOfferGUI(){
		setLayout(null);
		JLabel ruralhouselbl = new JLabel("Casa rural: ");
		ruralhouselbl.setBounds(155, 35, 102, 22);
		ruralhouselbl.setForeground(Color.BLUE);
		ruralhouselbl.setFont(new Font("Tekton Pro", Font.PLAIN, 21));
		add(ruralhouselbl);
		
		comBoxCasas = new JComboBox<Integer>();
		comBoxCasas.setBounds(267, 35, 149, 22);
		comBoxCasas.setModel(modeloEC);
		comBoxCasas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comBoxCasas.getSelectedIndex() != -1){
					enaDis(true);
					actualizarTabla((int)comBoxCasas.getSelectedItem());
				}
			}
		});
		add(comBoxCasas);
		
		
		Date fechaHoy = new Date();
		long time = fechaHoy.getTime() + 1*(3600*24*1000);
		Date fechaManana = new Date();
		fechaManana.setTime(time);
		
		calendarLastday = new JDateChooser();
		calendarLastday.setMinSelectableDate(fechaManana);
		calendarLastday.setDate(fechaManana);
		calendarLastday.setDateFormatString("yyyy-MM-dd");
		calendarLastday.setBounds(200, 287, 268, 20);
		calendarLastday.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
		    @Override
	        public void propertyChange(PropertyChangeEvent e) {
	            if ("date".equals(e.getPropertyName())) {
	                System.out.println(e.getPropertyName()+ ": " + (Date) e.getNewValue());
	            }
	        }
	    });
		add(calendarLastday);
	
		calendarFirstday = new JDateChooser();
		calendarFirstday.setBounds(200, 105, 268, 22);
		calendarFirstday.setDate(fechaHoy);
		calendarFirstday.setDateFormatString("yyyy-MM-dd");
		calendarFirstday.setMinSelectableDate(fechaHoy);
		try {
			UIManager.setLookAndFeel(new Plastic3DLookAndFeel());
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		calendarFirstday.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
	        @Override
	        public void propertyChange(PropertyChangeEvent e) {
	            if ("date".equals(e.getPropertyName())) {
	                System.out.println(e.getPropertyName()+ ": " + (Date) e.getNewValue());
					long time = calendarFirstday.getDate().getTime() + 1*(3600*24*1000);
					Date fechaSiguiente = new Date();
					fechaSiguiente.setTime(time);
					System.out.println(fechaSiguiente.getDate());
					calendarLastday.setMinSelectableDate(fechaSiguiente);
					calendarLastday.setDate(fechaSiguiente);
	            }
	        }
	    });
		add(calendarFirstday);	
				
		bttnSalvar = new JButton("Salvar");
		bttnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ApplicationFacadeInterface facade = Start.getBusinessLogic();
				try {
					System.out.println(calendarFirstday.getDate());
					System.out.println(calendarLastday.getDate());
					facade.anadirOferta(Integer.parseInt(comBoxCasas.getSelectedItem().toString()), calendarFirstday.getDate(), calendarLastday.getDate(), Float.parseFloat(textPrecio.getText()), false);
					JPanel panel = new PantallaPrincipalGUI();
					Start.modificarPanelAbajo(panel);
				} catch (Exception e1) {
					javax.swing.JOptionPane.showMessageDialog(null,"Error al crear oferta. " + e1.getMessage(), "No....",javax.swing.JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});
		bttnSalvar.setFont(new Font("Tekton Pro", Font.PLAIN, 21));
		bttnSalvar.setForeground(Color.BLUE);
		bttnSalvar.setBounds(804, 375, 149, 46);
		add(bttnSalvar);
		
		JLabel firstdaylbl = new JLabel("Primer d\u00EDa:");
		firstdaylbl.setFont(new Font("Tekton Pro", Font.PLAIN, 21));
		firstdaylbl.setForeground(Color.BLUE);
		firstdaylbl.setBounds(98, 105, 110, 22);
		add(firstdaylbl);
		
		JLabel lastdaylbl = new JLabel("\u00DAltimo d\u00EDa:");
		lastdaylbl.setFont(new Font("Tekton Pro", Font.PLAIN, 21));
		lastdaylbl.setForeground(Color.BLUE);
		lastdaylbl.setBounds(98, 287, 110, 22);
		add(lastdaylbl);
		
		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setForeground(Color.BLUE);
		lblPrecio.setFont(new Font("Dialog", Font.PLAIN, 21));
		lblPrecio.setBounds(117, 391, 110, 22);
		add(lblPrecio);
		
		textPrecio = new JTextField();
		textPrecio.setColumns(10);
		textPrecio.setBounds(219, 391, 149, 22);
		add(textPrecio);
		
		table = new JTable();
		table.setModel(modelTb);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBounds(582, 35, 371, 301);
		add(table);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/imagenes/fondoAbajo.jpg")));
		lblNewLabel.setBounds(0, 0, 1018, 465);
		add(lblNewLabel);
		
		inicializarCampos();
	}

	private void inicializarCampos() {
		ApplicationFacadeInterface facade = Start.getBusinessLogic();
		java.util.Iterator<RuralHouse> i;
		try {
			i = facade.getOwner().getRuralHouses().iterator();
			while (i.hasNext())	modeloEC.addElement(i.next().getHouseNumber());
			enaDis(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void enaDis(boolean b){
		textPrecio.setEnabled(b);
		calendarFirstday.setEnabled(b);
		calendarLastday.setEnabled(b);
		bttnSalvar.setEnabled(b);
	}
	
	private void actualizarTabla(int numeroRH){
		ApplicationFacadeInterface facade = Start.getBusinessLogic();
		try {
			int a = modelTb.getRowCount()-1;
			for(int i=0; i<a;i++) modelTb.removeRow(i);
			Vector<Offer> aux =  facade.getOfertas(numeroRH);
			Iterator<Offer> i = aux.iterator();
			while (i.hasNext()){
				Vector<Object> vector = new Vector<Object>();
				Offer auxi = i.next();
				vector.add(1);
				vector.add(auxi.getPrimerDia());
				vector.add(auxi.getUltimoDia());
				vector.add(auxi.getPrice());
				vector.add(auxi.isUnidoAFechas());
				modelTb.addRow(vector);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}