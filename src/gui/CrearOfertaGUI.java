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

import viejoGUI.StartWindow;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CrearOfertaGUI extends JFrame {

	private JPanel contentPane;
	private JCalendar calendarFirstday = new JCalendar();
	private JCalendar calendarLastday = new JCalendar();
	private Calendar calendar1 = null;
	private Calendar calendar2 = null;
	private JTextField textFirstday = new JTextField();
	private JTextField textLastday = new JTextField();
	private JComboBox comBoxCasas;
	private DefaultComboBoxModel<String> modeloEC = new DefaultComboBoxModel<String>();
	private JButton bttnSalvar;
	
	
	  // Code for JCalendar
	  private JCalendar jCalendar1 = new JCalendar();
	  private JCalendar jCalendar2 = new JCalendar();
	  private Calendar calendarInicio = null;
	  private Calendar calendarFin = null;
	  private JButton jButton2 = new JButton();
	  private JLabel jLabel5 = new JLabel();
	  private JTextField textPrecio;
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
		comBoxCasas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comBoxCasas.getSelectedIndex() != -1){
					enaDis(true);
				}
			}
		});
		contentPane.add(comBoxCasas);
		
		
		textFirstday.setBounds(125, 99, 149, 28);
		contentPane.add(textFirstday);
		textFirstday.setColumns(10);
		
		calendarFirstday.setBounds(284, 76, 268, 138);
		calendarFirstday.getDayChooser().setBorder(new LineBorder(new Color(0, 0, 0), 0));
		calendarFirstday.getDayChooser().setBackground(Color.CYAN);
		calendarFirstday.setBackground(new Color(178, 238, 238));
		contentPane.add(calendarFirstday);
		
		// Codigo para el FirstDayCalendar
		calendarFirstday.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent propertychangeevent) {
				if (propertychangeevent.getPropertyName().equals("locale")) {
					calendarFirstday.setLocale((Locale) propertychangeevent
							.getNewValue());
					DateFormat dateformat = DateFormat.getDateInstance(1,
							calendarFirstday.getLocale());
					textFirstday.setText(dateformat.format(calendar1.getTime()));
				} else if (propertychangeevent.getPropertyName().equals(
						"calendar")) {
					calendar1 = (Calendar) propertychangeevent.getNewValue();
					DateFormat dateformat1 = DateFormat.getDateInstance(1,
							calendarFirstday.getLocale());
					textFirstday.setText(dateformat1.format(calendar1
							.getTime()));
					calendarFirstday.setCalendar(calendar1);
				}
			}
		});
		
		textLastday.setBounds(125, 253, 149, 28);
		contentPane.add(textLastday);
		textLastday.setColumns(10);
		
		calendarLastday.getDayChooser().setBorder(new LineBorder(new Color(0, 0, 0), 0));
		calendarLastday.getDayChooser().setBackground(Color.CYAN);
		calendarLastday.setBackground(new Color(178, 238, 238));
		calendarLastday.setBounds(284, 225, 268, 138);
		contentPane.add(calendarLastday);
		
		// Codigo para el LastDayCalendar
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
				});
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
				  	//RuralHouse ruralHouse=((RuralHouse)comBoxCasas.getSelectedItem());
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
					setVisible(false);

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		bttnSalvar.setFont(new Font("Tekton Pro", Font.PLAIN, 21));
		bttnSalvar.setForeground(Color.BLUE);
		bttnSalvar.setBounds(403, 391, 149, 46);
		contentPane.add(bttnSalvar);
		
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
		
		textPrecio = new JTextField();
		textPrecio.setColumns(10);
		textPrecio.setBounds(125, 365, 149, 28);
		contentPane.add(textPrecio);
		
		inicializarCampos();
	}
	
	private void inicializarCampos() {
		ApplicationFacadeInterface facade = StartWindow.getBusinessLogic();
		java.util.Iterator<RuralHouse> i;
		try {
			i = facade.getOwner().getRuralHouses().iterator();
			while (i.hasNext()){
				modeloEC.addElement(Integer.toString(i.next().getHouseNumber()));
			}
			enaDis(false);
			comBoxCasas.setSelectedIndex(-1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private void enaDis(boolean b){
		textFirstday.setEnabled(b);
		textLastday.setEnabled(b);
		textPrecio.setEnabled(b);
		calendarFirstday.setEnabled(b);
		calendarLastday.setEnabled(b);
		bttnSalvar.setEnabled(b);
	}
}
