package gui;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import businessLogic.ApplicationFacadeInterface;
import com.jgoodies.looks.plastic.Plastic3DLookAndFeel;
import com.toedter.calendar.JDateChooser;
import javax.swing.SwingConstants;
import com.jgoodies.forms.factories.DefaultComponentFactory;

import domain.RuralHouse;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;



public class PantallaPrincipalGUI extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JDateChooser dateDesde;
	private static JDateChooser dateHasta;
	private static JButton btnGestionCasasRurales;
	private static JButton btnGestionOfertas;
	private static JButton btnGestionFechas;
	private static JComboBox<String> comboCity ;
	private DefaultComboBoxModel<String> modeloCity = new DefaultComboBoxModel<String>();
	private JButton btnGestReserv;
	
	/**
	 * Create the panel.
	 */
	public PantallaPrincipalGUI(){
		setLayout(null);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboCity.getSelectedIndex() != -1){
					JPanel panel = new BusquedaConFechasGUI(dateDesde.getDate(), dateHasta.getDate(), (String)comboCity.getSelectedItem());
					Start.modificarPanelAbajo(panel);
				}else{
					
				}
			}
		});
		btnBuscar.setBounds(20, 179, 223, 28);
		add(btnBuscar);
		
		JButton btnBusquedaAvanzada = new JButton("Busqueda de chollos");
		btnBusquedaAvanzada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JPanel panel = new BusquedaGUI();
				Start.modificarPanelAbajo(panel);
			}
		});
		btnBusquedaAvanzada.setBounds(20, 223, 223, 28);
		add(btnBusquedaAvanzada);
		
		Date fechaHoy = new Date();
		long time = fechaHoy.getTime() + 1*(3600*24*1000);
		Date fechaManana = new Date();
		fechaManana.setTime(time);
		
		dateHasta = new JDateChooser();
		dateHasta.setMinSelectableDate(fechaManana);
		dateHasta.setDate(fechaManana);
		dateHasta.setDateFormatString("yyyy-MM-dd");
		dateHasta.setBounds(105, 138, 137, 28);
		add(dateHasta);
	
		dateDesde = new JDateChooser();
		dateDesde.setBounds(105, 97, 137, 28);
		dateDesde.setDate(fechaHoy);
		dateDesde.setDateFormatString("yyyy-MM-dd");
		dateDesde.setMinSelectableDate(fechaHoy);
		try {
			UIManager.setLookAndFeel(new Plastic3DLookAndFeel());
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		dateDesde.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
	        @Override
	        public void propertyChange(PropertyChangeEvent e) {
	            if ("date".equals(e.getPropertyName())) {
	                System.out.println(e.getPropertyName()+ ": " + (Date) e.getNewValue());
					long time = dateDesde.getDate().getTime() + 1*(3600*24*1000);
					Date fechaSiguiente = new Date();
					fechaSiguiente.setTime(time);
					dateHasta.setMinSelectableDate(fechaSiguiente);
					dateHasta.setDate(fechaSiguiente);
	            }
	        }
	    });
		add(dateDesde);

		
		JLabel lblHasta = new JLabel("Hasta:");
		lblHasta.setBounds(20, 138, 73, 28);
		lblHasta.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHasta.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		add(lblHasta);
		
		JLabel lblDesde = new JLabel("Desde:");
		lblDesde.setBounds(20, 97, 73, 28);
		lblDesde.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDesde.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		add(lblDesde);
		
		JLabel lblCiudad = new JLabel("Ciudad:");
		lblCiudad.setBounds(20, 57, 73, 28);
		lblCiudad.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCiudad.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		add(lblCiudad);
		
		JLabel lblSeleccioneLosDatos = DefaultComponentFactory.getInstance().createTitle("Seleccione los datos:");
		lblSeleccioneLosDatos.setForeground(new Color(255, 0, 0));
		lblSeleccioneLosDatos.setFont(new Font("Viner Hand ITC", Font.BOLD, 20));
		lblSeleccioneLosDatos.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeleccioneLosDatos.setBounds(20, 23, 223, 22);
		add(lblSeleccioneLosDatos);
		
		btnGestionCasasRurales = new JButton("Gestionar Casas Rurales");
		btnGestionCasasRurales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JPanel temp = new GestionCasaRuralGUI();
				Start.modificarPanelAbajo(temp);
			}
		});
		btnGestionCasasRurales.setBounds(666, 98, 223, 28);
		add(btnGestionCasasRurales);
		
		btnGestionFechas = new JButton("Gestionar Disponibilidad");
		btnGestionFechas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JPanel temp = new AnadirFechaGUI();
				Start.modificarPanelAbajo(temp);
			}
		});
		btnGestionFechas.setBounds(666, 35, 223, 28);
		add(btnGestionFechas);
		
		btnGestionOfertas = new JButton("Gestionar Ofertas");
		btnGestionOfertas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JPanel temp = new CreateOfferGUI();
				Start.modificarPanelAbajo(temp);
			}
		});
		btnGestionOfertas.setBounds(666, 161, 223, 28);
		add(btnGestionOfertas);
		
		btnGestReserv = new JButton("Gestionar Reservas Realizadas");
		btnGestReserv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JPanel temp = new GestionarReservasGUI();
				Start.modificarPanelAbajo(temp);
			}
		});
		btnGestReserv.setBounds(666, 224, 223, 28);
		add(btnGestReserv);
		
		JButton button = new JButton("Busqueda avanzada");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel panel = new BusquedaConFechasGUI(null, null, null);
				Start.modificarPanelAbajo(panel);
			}
		});
		button.setBounds(20, 264, 223, 28);
		add(button);
		
		comboCity = new JComboBox<String>();
		comboCity.setSelectedIndex(-1);
		comboCity.setFont(new Font("Dialog", Font.BOLD, 11));
		comboCity.setBounds(105, 57, 137, 27);
		comboCity.setModel(modeloCity);
		add(comboCity);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 1018, 465);
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/localData/fondoAbajo.jpg")));
		add(lblNewLabel);
		
		
		inicializarCampos();
	}
	
	public void inicializarCampos(){
		ApplicationFacadeInterface facade = Start.getBusinessLogic();
		boolean login = false;
		boolean reserv = false;
		try {
			Vector<RuralHouse> vectorCasas = facade.getAllRuralHouses();
			for(int i=0; i<vectorCasas.size();i++){
				if(!estaCity(vectorCasas.get(i).getCity())){
					modeloCity.addElement(vectorCasas.get(i).getCity());
				}
				
			}
			comboCity.setSelectedIndex(-1);
			if (Start.estadoLogin()){  
				if(facade.getOwner(Start.getUsuario())!=null)
					login = true;
				if(facade.getUsuario(Start.getUsuario()).getReservas().size()>0) reserv=true;
				else reserv=false;
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		btnGestionCasasRurales.setVisible(login);
		btnGestionOfertas.setVisible(login);
		btnGestionFechas.setVisible(login);
		btnGestReserv.setVisible(reserv);
	}
	
	public boolean estaCity(String s){
		for (int j = 0; j<modeloCity.getSize();j++){
			if(modeloCity.getElementAt(j).compareTo(s)==0){
				return true;
			}
		}
		return false;		
	}
}

