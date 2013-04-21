package gui;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;

import com.toedter.calendar.JDateChooser;




import businessLogic.ApplicationFacadeInterface;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class PantallaPrincipalGUI extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JButton modPerfil;
	private static JButton modOwner;
	ApplicationFacadeInterface facade = Start.getBusinessLogic();
	private JTextField textCiudad;

	/**
	 * Create the panel.
	 */
	public PantallaPrincipalGUI(){
		setLayout(null);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(146, 179, 97, 25);
		add(btnBuscar);
		
		textCiudad = new JTextField();
		textCiudad.setColumns(10);
		textCiudad.setBounds(105, 58, 137, 28);
		add(textCiudad);
		
		JDateChooser dateHasta = new JDateChooser();
		dateHasta.setDateFormatString("yyyy-MM-dd");
		dateHasta.setBounds(105, 138, 137, 28);
		add(dateHasta);
		
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
		JLabel lblInicio = new JLabel("INICIO");
		lblInicio.setBounds(473, 221, 379, 207);
		lblInicio.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 98));
		add(lblInicio);
		
		modPerfil = new JButton("Editar Perfil");
		modPerfil.setBounds(20, 339, 116, 23);
		modPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					JPanel temp1= new UserRegisterGUI();
					Start.modificarPanelAbajo(temp1);
				} catch (Exception e) {
					e.getMessage();
				}
				
			}
		});
		add(modPerfil);
		//JCalendar a;
		//JDateChooser calendario = new JDateChooser(new Date(), "dd/MM/yyyy"); //Aqui ele seta a data de hoje no formato dd/mm/aaaa 

		try {
			JDateChooser dateDesde = new JDateChooser();
			dateDesde.setBounds(105, 97, 137, 28);
			dateDesde.setDateFormatString("yyyy-MM-dd");
			JLabel dateLabel = new JLabel("Date");
			dateLabel.setBounds(0, 0, 0, 0);
			add(dateLabel);//1
			add(dateDesde); 
		} catch (Exception e) {
		    e.printStackTrace();
		}
		  
		//calendario.setBounds(350,50,145,20); // x y ancho alto
	     //   add(calendario);

		
		
		
		
		modOwner = new JButton("Ser Propietario");
		modOwner.setBounds(20, 373, 116, 23);
		modOwner.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					JPanel temp1= new OwnerRegisterGUI();
					Start.modificarPanelAbajo(temp1);
				} catch (Exception e) {
					e.getMessage();
				}
				
			}
		});
		add(modOwner);
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 1018, 465);
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/imagenes/fondoAbajo.jpg")));
		add(lblNewLabel);
		inicializarCampos();

	}
	
	public void inicializarCampos(){
		try {
			if (facade.estadoLogin()){
				modPerfil.setVisible(true);
				modOwner.setVisible(true);
				if(facade.getOwner() != null){
					modOwner.setText("Editar Propietario");
				}else{
					modOwner.setText("Ser Propietario");
				}
			} else {
				modPerfil.setVisible(false);
				modOwner.setVisible(false);
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}
}

