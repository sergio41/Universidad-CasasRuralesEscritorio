package gui;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;


import businessLogic.ApplicationFacadeInterface;

public class PantallaPrincipalGUI extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JButton modPerfil;
	private static JButton modOwner;
	ApplicationFacadeInterface facade = Start.getBusinessLogic();

	/**
	 * Create the panel.
	 */
	public PantallaPrincipalGUI(){
		//ApplicationFacadeInterface facade = Start.getBusinessLogic();
		setLayout(null);		
		JLabel lblInicio = new JLabel("INICIO");
		lblInicio.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 98));
		lblInicio.setBounds(233, 155, 379, 207);
		add(lblInicio);
		
		modPerfil = new JButton("Editar Perfil");
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
		modPerfil.setBounds(20, 339, 116, 23);
		add(modPerfil);		
		modOwner = new JButton("Ser Propietario");
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
		modOwner.setBounds(20, 373, 116, 23);
		add(modOwner);
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/imagenes/fondoAbajo.jpg")));
		lblNewLabel.setBounds(0, 0, 1018, 465);
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

