package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JComboBox;

import businessLogic.ApplicationFacadeInterface;
import businessLogic.Login;

import com.toedter.calendar.JCalendar;

import domain.RuralHouse;

import java.awt.Rectangle;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Date;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CrearOfertaGUI extends JFrame {

	private JPanel contentPane;
	private JCalendar firstdaycalendar = new JCalendar();
	private JCalendar lastdaycalendar = new JCalendar();
	private Calendar calendar1 = null;
	private Calendar calendar2 = null;
	private JTextField firstdayTextField = new JTextField();
	private JTextField lastdayTextField = new JTextField();
	private JComboBox comBoxCasas;
	private DefaultComboBoxModel<String> modeloEC = new DefaultComboBoxModel<String>();
	
	
	  // Code for JCalendar
	  private JCalendar jCalendar1 = new JCalendar();
	  private JCalendar jCalendar2 = new JCalendar();
	  private Calendar calendarInicio = null;
	  private Calendar calendarFin = null;
	  private JButton jButton2 = new JButton();
	  private JLabel jLabel5 = new JLabel();
	  private JTextField jTextField3;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrearOfertaGUI frame = new CrearOfertaGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CrearOfertaGUI() {
		setResizable(false);
		setTitle("Crear ofertas");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 579, 476);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBackground(new Color(178, 238, 238));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel ruralhouselbl = new JLabel("Casa rural: ");
		ruralhouselbl.setBounds(23, 31, 102, 22);
		ruralhouselbl.setForeground(Color.BLUE);
		ruralhouselbl.setFont(new Font("Tekton Pro", Font.PLAIN, 21));
		contentPane.add(ruralhouselbl);
		
		comBoxCasas = new JComboBox();
		comBoxCasas.setBounds(125, 32, 149, 23);
		comBoxCasas.setModel(modeloEC);
		contentPane.add(comBoxCasas);
		
		
		firstdayTextField.setBounds(125, 99, 149, 28);
		contentPane.add(firstdayTextField);
		firstdayTextField.setColumns(10);
		
		firstdaycalendar.setBounds(284, 76, 268, 138);
		firstdaycalendar.getDayChooser().setBorder(new LineBorder(new Color(0, 0, 0), 0));
		firstdaycalendar.getDayChooser().setBackground(Color.CYAN);
		firstdaycalendar.setBackground(new Color(178, 238, 238));
		contentPane.add(firstdaycalendar);
		
		// Codigo para el FirstDayCalendar
		firstdaycalendar.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent propertychangeevent) {
				if (propertychangeevent.getPropertyName().equals("locale")) {
					firstdaycalendar.setLocale((Locale) propertychangeevent
							.getNewValue());
					DateFormat dateformat = DateFormat.getDateInstance(1,
							firstdaycalendar.getLocale());
					firstdayTextField.setText(dateformat.format(calendar1.getTime()));
				} else if (propertychangeevent.getPropertyName().equals(
						"calendar")) {
					calendar1 = (Calendar) propertychangeevent.getNewValue();
					DateFormat dateformat1 = DateFormat.getDateInstance(1,
							firstdaycalendar.getLocale());
					firstdayTextField.setText(dateformat1.format(calendar1
							.getTime()));
					firstdaycalendar.setCalendar(calendar1);
				}
			}
		});
		
		lastdayTextField.setBounds(125, 253, 149, 28);
		contentPane.add(lastdayTextField);
		lastdayTextField.setColumns(10);
		
		lastdaycalendar.getDayChooser().setBorder(new LineBorder(new Color(0, 0, 0), 0));
		lastdaycalendar.getDayChooser().setBackground(Color.CYAN);
		lastdaycalendar.setBackground(new Color(178, 238, 238));
		lastdaycalendar.setBounds(284, 225, 268, 138);
		contentPane.add(lastdaycalendar);
		
		// Codigo para el LastDayCalendar
				lastdaycalendar.addPropertyChangeListener(new PropertyChangeListener() {
					public void propertyChange(PropertyChangeEvent propertychangeevent) {
						if (propertychangeevent.getPropertyName().equals("locale")) {
							lastdaycalendar.setLocale((Locale) propertychangeevent
									.getNewValue());
							DateFormat dateformat = DateFormat.getDateInstance(1,
									lastdaycalendar.getLocale());
							lastdayTextField.setText(dateformat.format(calendar2.getTime()));
						} else if (propertychangeevent.getPropertyName().equals(
								"calendar")) {
							calendar2 = (Calendar) propertychangeevent.getNewValue();
							DateFormat dateformat1 = DateFormat.getDateInstance(1,
									lastdaycalendar.getLocale());
							lastdayTextField.setText(dateformat1.format(calendar2
									.getTime()));
							lastdaycalendar.setCalendar(calendar2);
						}
					}
				});
		JButton OfferButton = new JButton("Salvar");
		OfferButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  	RuralHouse ruralHouse=((RuralHouse)comBoxCasas.getSelectedItem());
				  	Date firstDay=new Date(jCalendar1.getCalendar().getTime().getTime());
				    //Remove the hour:minute:second:ms from the date 
				  	firstDay=Date.valueOf(firstDay.toString());
				  	Date lastDay=new Date(jCalendar2.getCalendar().getTime().getTime());
				    //Remove the hour:minute:second:ms from the date 
				  	lastDay=Date.valueOf(lastDay.toString());
				  	//It could be to trigger an exception if the introduced string is not a number
				  	float price= Float.parseFloat(jTextField3.getText());
				  	try {
				  	    //Obtain the business logic from a StartWindow class (local or remote)
						ApplicationFacadeInterface facade=StartWindow.getBusinessLogic();
						
				  		facade.createOffer(ruralHouse, firstDay, lastDay, price); 
				  		  		
						setVisible(false);
					} catch (Exception e1) {

						e1.printStackTrace();
					}
			}
		});
		OfferButton.setEnabled(false);
		OfferButton.setFont(new Font("Tekton Pro", Font.PLAIN, 21));
		OfferButton.setForeground(Color.BLUE);
		OfferButton.setBounds(403, 391, 149, 46);
		contentPane.add(OfferButton);
		
		JLabel firstdaylbl = new JLabel("Primer d\u00EDa:");
		firstdaylbl.setFont(new Font("Tekton Pro", Font.PLAIN, 21));
		firstdaylbl.setForeground(Color.BLUE);
		firstdaylbl.setBounds(23, 101, 110, 22);
		contentPane.add(firstdaylbl);
		
		JLabel lastdaylbl = new JLabel("\u00DAltimo d\u00EDa:");
		lastdaylbl.setFont(new Font("Tekton Pro", Font.PLAIN, 21));
		lastdaylbl.setForeground(Color.BLUE);
		lastdaylbl.setBounds(23, 255, 110, 22);
		contentPane.add(lastdaylbl);
		
		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setForeground(Color.BLUE);
		lblPrecio.setFont(new Font("Dialog", Font.PLAIN, 21));
		lblPrecio.setBounds(23, 371, 110, 22);
		contentPane.add(lblPrecio);
		
		jTextField3 = new JTextField();
		jTextField3.setColumns(10);
		jTextField3.setBounds(125, 365, 149, 28);
		contentPane.add(jTextField3);
		
		inicializarCampos();
	}
	
	private void inicializarCampos() {
		java.util.Iterator<RuralHouse> i =  Login.getPropietario().getRuralHouses().iterator();
		while (i.hasNext()){
			modeloEC.addElement(Integer.toString(i.next().getHouseNumber()));
		}
		//enaDis(false);
		comBoxCasas.setSelectedIndex(-1);
	}
}
