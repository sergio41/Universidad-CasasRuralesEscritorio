package gui;


import javax.swing.ImageIcon;
import javax.swing.JPanel;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.border.LineBorder;

import viejoGUI.StartWindow;

import com.jgoodies.looks.plastic.Plastic3DLookAndFeel;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import domain.RuralHouse;
import businessLogic.ApplicationFacadeInterface;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
//import java.sql.Date;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Date;




public class CreateOfferGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private DefaultComboBoxModel<String> modeloEC = new DefaultComboBoxModel<String>();
	private JComboBox comBoxCasas;
	//private JCalendar calendarFirstday = new JCalendar();
	private JDateChooser calendarFirstday;
	private Calendar calendar1 = null;
	private JCalendar jCalendar1 = new JCalendar();
	//private JCalendar calendarLastday = new JCalendar();
	private JDateChooser calendarLastday;

	private Calendar calendar2 = null;
	private JButton bttnSalvar;
	private JCalendar jCalendar2 = new JCalendar();
	private JTextField textPrecio;

	public CreateOfferGUI(){
		setLayout(null);
		JLabel ruralhouselbl = new JLabel("Casa rural: ");
		ruralhouselbl.setBounds(366, 35, 102, 22);
		ruralhouselbl.setForeground(Color.BLUE);
		ruralhouselbl.setFont(new Font("Tekton Pro", Font.PLAIN, 21));
		add(ruralhouselbl);
		
		comBoxCasas = new JComboBox();
		comBoxCasas.setBounds(478, 35, 149, 22);
		comBoxCasas.setModel(modeloEC);
		comBoxCasas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comBoxCasas.getSelectedIndex() != -1){
					enaDis(true);
				}
			}
		});
		add(comBoxCasas);
		
		/*calendarLastday.getDayChooser().setBorder(new LineBorder(new Color(0, 0, 0), 0));
		calendarLastday.getDayChooser().setBackground(Color.CYAN);
		calendarLastday.setBackground(new Color(178, 238, 238));
		calendarLastday.setBounds(359, 283, 268, 138);
		add(calendarLastday);

		calendarLastday.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent propertychangeevent) {
				if (propertychangeevent.getPropertyName().equals("locale")) {
					calendarLastday.setLocale((Locale) propertychangeevent
							.getNewValue());
					DateFormat dateformat = DateFormat.getDateInstance(1,
							calendarLastday.getLocale());
					textLastday.setText(dateformat.format(calendar2.getTime()));
				} else if (propertychangeevent.getPropertyName().equals(
						"calendar")) {
					calendar2 = (Calendar) propertychangeevent.getNewValue();
					DateFormat dateformat1 = DateFormat.getDateInstance(1,
							calendarLastday.getLocale());
					textLastday.setText(dateformat1.format(calendar2
							.getTime()));
					calendarLastday.setCalendar(calendar2);
				}
			}
		});*/
		
		Date fechaHoy = new Date();
		long time = fechaHoy.getTime() + 1*(3600*24*1000);
		Date fechaManana = new Date();
		fechaManana.setTime(time);
		
		calendarLastday = new JDateChooser();
		calendarLastday.setMinSelectableDate(fechaManana);
		calendarLastday.setDate(fechaManana);
		calendarLastday.setDateFormatString("yyyy-MM-dd");
		calendarLastday.setBounds(200, 105, 268, 20);
		add(calendarLastday);
	
		calendarFirstday = new JDateChooser();
		calendarFirstday.setBounds(200, 287, 268, 22);
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
					calendarFirstday.setMinSelectableDate(fechaSiguiente);
					calendarFirstday.setDate(fechaSiguiente);
	            }
	        }
	    });
		add(calendarFirstday);	
				
		bttnSalvar = new JButton("Salvar");
		bttnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ApplicationFacadeInterface facade = StartWindow.getBusinessLogic();
				java.util.Iterator<RuralHouse> i;
				try {
					i = facade.getOwner().getRuralHouses().iterator();
					RuralHouse ruralHouse = null;
					while (i.hasNext()){
						ruralHouse = i.next();
						if (ruralHouse.getHouseNumber() == Integer.parseInt((String) comBoxCasas.getSelectedItem())){
							break;
						}
					}
				  	/*//RuralHouse ruralHouse=((RuralHouse)comBoxCasas.getSelectedItem());
				  	Date firstDay=new Date(jCalendar1.getCalendar().getTime().getTime());
				    //Remove the hour:minute:second:ms from the date 
				  	firstDay=Date.valueOf(firstDay.toString());
				  	Date lastDay=new Date(jCalendar2.getCalendar().getTime().getTime());
				    //Remove the hour:minute:second:ms from the date 
				  	lastDay=Date.valueOf(lastDay.toString());
				  	//It could be to trigger an exception if the introduced string is not a number
				  	float price= Float.parseFloat(textPrecio.getText());

			  	    //Obtain the business logic from a StartWindow class (local or remote)					
			  		facade.createOffer(ruralHouse, firstDay, lastDay, price); 	  		
					setVisible(false);*/
					JPanel panel = new PantallaPrincipalGUI();
					Start.modificarPanelAbajo(panel);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
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
		lblPrecio.setBounds(671, 109, 110, 22);
		add(lblPrecio);
		
		textPrecio = new JTextField();
		textPrecio.setColumns(10);
		textPrecio.setBounds(773, 109, 149, 22);
		add(textPrecio);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/imagenes/fondoAbajo.jpg")));
		lblNewLabel.setBounds(0, 0, 1018, 465);
		add(lblNewLabel);
		
		inicializarCampos();
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
}