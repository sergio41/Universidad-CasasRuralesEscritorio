package gui;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.util.Date;
import javax.swing.JButton;

import businessLogic.ApplicationFacadeInterface;

import com.jgoodies.looks.plastic.Plastic3DLookAndFeel;
import com.toedter.calendar.JDateChooser;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import com.jgoodies.forms.factories.DefaultComponentFactory;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;



public class PantallaPrincipalGUI extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JDateChooser dateDesde;
	private static JDateChooser dateHasta;
	private static JTextField textCiudad;
	private static JButton btnGestionCasasRurales;
	private static JButton btnGestionOfertas;
	private static JButton btnGestionFechas;
	private static JLabel lblMapa;
	
	/**
	 * Create the panel.
	 */
	public PantallaPrincipalGUI(){
		setLayout(null);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(20, 179, 223, 28);
		add(btnBuscar);
		
		JButton btnBusquedaAvanzada = new JButton("Busqueda avanzada");
		btnBusquedaAvanzada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JPanel panel = new BusquedaGUI();
				Start.modificarPanelAbajo(panel);
			}
		});
		btnBusquedaAvanzada.setBounds(20, 223, 223, 28);
		add(btnBusquedaAvanzada);
		
		textCiudad = new JTextField();
		textCiudad.setColumns(10);
		textCiudad.setBounds(105, 58, 137, 28);
		add(textCiudad);
		
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
		lblCiudad.setBounds(20, 58, 73, 28);
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
		btnGestionCasasRurales.setBounds(20, 427, 223, 28);
		add(btnGestionCasasRurales);
		
		btnGestionFechas = new JButton("Gestionar Disponibilidad");
		btnGestionFechas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JPanel temp = new AnadirFechaGUI();
				Start.modificarPanelAbajo(temp);
			}
		});
		btnGestionFechas.setBounds(20, 327, 223, 28);
		add(btnGestionFechas);
		
		btnGestionOfertas = new JButton("Gestionar Ofertas");
		btnGestionOfertas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JPanel temp = new CreateOfferGUI();
				Start.modificarPanelAbajo(temp);
			}
		});
		btnGestionOfertas.setBounds(20, 377, 223, 28);
		add(btnGestionOfertas);
		
		lblMapa = new JLabel("mapa");
		lblMapa.setBounds(376, 98, 319, 278);
		add(lblMapa);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 1018, 465);
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/imagenes/fondoAbajo.jpg")));
		add(lblNewLabel);
		
		
		inicializarCampos();
	}
	
	public void inicializarCampos(){
		ApplicationFacadeInterface i = Start.getBusinessLogic();
		boolean login = false;
		try {
			if (i.estadoLogin())  
				if(i.getOwner()!=null)
					login = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		btnGestionCasasRurales.setVisible(login);
		btnGestionOfertas.setVisible(login);
		btnGestionFechas.setVisible(login);
		mapa();
	}
	
	public void mapa(){
		try{
		  //BufferedImage img = ImageIO.read(new URL("http://maps.googleapis.com/maps/api/staticmap?center=Brooklyn+Bridge,New+York,NY&zoom=13&size=600x300&maptype=roadmap&markers=color:blue%7Clabel:S%7C40.702147,-74.015794&markers=color:green%7Clabel:G%7C40.711614,-74.012318&markers=color:red%7Ccolor:red%7Clabel:C%7C40.718217,-73.998284&sensor=false"));
		 // lblMapa.setIcon(new ImageIcon(img));
				 //File outputfile = new File("map.png");
				   // ImageIO.write(img, "png", outputfile);
				   // System.out.println("Saved!");
				    } catch (Exception ex) {
				         System.out.println("Error!" + ex);
				    }
		
	
	}

}

