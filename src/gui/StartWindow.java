package gui;

/**
 * @author Grupo 2
 */
import javax.swing.*;


import businessLogic.FacadeImplementation;
import businessLogic.ApplicationFacadeInterface;
import businessLogic.Login;
import businessLogic.GestionTwitter;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.rmi.*;
import java.util.List;

import configuration.Config;

import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.font.TextLayout;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class StartWindow extends JFrame {
	
	public static boolean isLocal=true;

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	private JButton boton1 = null;
	private JButton boton2 = null;
	private JButton boton3 = null;
	private JButton boton4 = null;
	public static ApplicationFacadeInterface facadeInterface;
	private static JButton buttonLogin;
	private static JButton buttonRegister;
	private static JButton OwnerButton;
	private static JButton AddRuralHouseButton;
	private static JLabel textLogin = new JLabel("No estas logueado");
	private static JTextPane textTwitter;
	private JLabel lblNewLabel_1;
	

	/**
	 * This is the default constructor
	 */
	public StartWindow() {
		super();
		setResizable(false);
		PantallaCargandoMain a = new PantallaCargandoMain();
		initialize();

		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static ApplicationFacadeInterface getBusinessLogic(){
		return facadeInterface;
	}
	

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		// this.setSize(271, 295);
		this.setSize(647, 606);
		this.setContentPane(getJContentPane());
		this.setTitle("Rural Houses");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getBoton4(), null);
			jContentPane.add(getBoton3());
			jContentPane.add(getBoton2());
			jContentPane.add(getBoton1());
			jContentPane.setBackground(new Color(178, 238, 238));
			jContentPane.add(boton4, null);
			textLogin.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
			textLogin.setForeground(Color.RED);
			textLogin.setHorizontalAlignment(SwingConstants.RIGHT);
			
			//JLabel textLogin = new JLabel("No estas logueado");
			textLogin.setBounds(417, 0, 229, 29);
			jContentPane.add(textLogin);
			jContentPane.add(getButtonLogin());
			jContentPane.add(getButtonRegister());
			jContentPane.add(getOwnerButton());
			jContentPane.add(getAddRuralHouseButton());
			
			textTwitter = new JTextPane();
			textTwitter.setText("Twitter");
			textTwitter.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 14));
			textTwitter.setEditable(false);
			textTwitter.setBounds(96, 524, 511, 54);
			jContentPane.add(textTwitter);
			
			JLabel lblNewLabel = new JLabel("New label");
			lblNewLabel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					try {
						Desktop.getDesktop().browse(new URI("www.twitter.com/CasasVillaArrib"));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (URISyntaxException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			ImageIcon myImage = new ImageIcon("imagen/twitter.png");
			lblNewLabel.setIcon(myImage);
			lblNewLabel.setBounds(23, 524, 55, 54);
			jContentPane.add(lblNewLabel);
			
			jContentPane.add(getLblNewLabel_1());
			
			JTextPane txtpnHazClickEn = new JTextPane();
			txtpnHazClickEn.setForeground(new Color(0, 128, 0));
			txtpnHazClickEn.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
			txtpnHazClickEn.setText("Haz click en el pajaro para acceder a twitter");
			txtpnHazClickEn.setBackground(new Color(178, 238, 238));
			txtpnHazClickEn.setEditable(false);
			txtpnHazClickEn.setBounds(23, 489, 397, 34);
			jContentPane.add(txtpnHazClickEn);
		}
		return jContentPane;
	}


	/**
	 * This method initializes boton1
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBoton1() {
		if (boton1 == null) {
			boton1 = new JButton();
			boton1.setBounds(308, 424, 190, 29);
			boton1.setText("Book rural house");
			boton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					// C?digo cedido por la univerdad
					JFrame a = new BookRuralHouseGUI();
					a.setVisible(true);
				}
			});
		}
		return boton1;
	}

	/**
	 * This method initializes boton2
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBoton2() {
		if (boton2 == null) {
			boton2 = new JButton();
			boton2.setBounds(171, 358, 166, 29);
			boton2.setText("Set availability");
			boton2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					// C?digo cedido por la universidad
					JFrame a = new SetAvailabilityGUI();
					a.setVisible(true);
				}
			});
		}
		return boton2;
	}
	
	/**
	 * This method initializes boton3
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBoton3() {
		if (boton3 == null) {
			boton3 = new JButton();
			boton3.setBounds(42, 297, 173, 29);
			boton3.setText("Query availability");
			boton3.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					// C?digo cedido por la universidad
					//JFrame a = new QueryAvailabilityWindow();
					JFrame a = new QueryAvailabilityGUI();

					a.setVisible(true);
				}
			});
		}
		return boton3;
	}

	/**
	 * This method initializes boton3
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBoton4() {
		if (boton4 == null) {
			boton4 = new JButton();
			boton4.setBounds(434, 483, 173, 29);
			boton4.setText("Close");
			boton4.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					// C?digo cedido por la universidad
					//JFrame a = new QueryAvailabilityWindow();
					ApplicationFacadeInterface facade=StartWindow.facadeInterface;
					try {
						facade.close();
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			});
		}
		return boton4;
	}
	public static void main(String[] args) {
		
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

			Config c=Config.getInstance();
			if (isLocal)
				facadeInterface=new FacadeImplementation();
			else {
				
				final String serverRMI = c.getServerRMI();
				// Remote service name
				String serviceName = "/"+c.getServiceRMI();
				//System.setSecurityManager(new RMISecurityManager());
				// RMI server port number
				int portNumber = Integer.parseInt(c.getPortRMI());
				// RMI server host IP IP 
				facadeInterface = (ApplicationFacadeInterface) Naming.lookup("rmi://"
					+ serverRMI + ":" + portNumber + serviceName);
			} 

		} catch (Exception e) {
		//System.out.println(e.toString());
			e.printStackTrace();
		}

		JFrame a = new StartWindow();
		a.setVisible(true);
		textTwitter.setText(GestionTwitter.getUltimoTweet());
	}
	private JButton getButtonLogin() {
		if (buttonLogin == null) {
			buttonLogin = new JButton("Login");
			buttonLogin.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (Login.estadoLogin()) {
						try {
							Login.logout();
							javax.swing.JOptionPane.showMessageDialog(null, "Estas deslogueado", "Logout", javax.swing.JOptionPane.NO_OPTION);
							actualizarLogin();
						} catch (Exception e1) {
							javax.swing.JOptionPane.showMessageDialog(null, e.toString(), "Mal....", javax.swing.JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JFrame a = new LoginGUI();
						a.setVisible(true);
					}
				}
			});
			buttonLogin.setForeground(Color.BLUE);
			buttonLogin.setFont(new Font("Tekton Pro", Font.PLAIN, 21));
			buttonLogin.setBounds(12, 239, 92, 29);
		}
		return buttonLogin;
	}
	private JButton getButtonRegister() {
		if (buttonRegister == null) {
			buttonRegister = new JButton("Nuevo User");
			buttonRegister.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new RegisterGUI();
					a.setVisible(true);
				}
			});
			buttonRegister.setForeground(Color.BLUE);
			buttonRegister.setFont(new Font("Tekton Pro", Font.PLAIN, 21));
			buttonRegister.setBounds(116, 239, 140, 29);
		}
		return buttonRegister;
	}
	
	private Component getOwnerButton() {
		if (OwnerButton == null) {
			OwnerButton = new JButton("Ser Propietario");
			OwnerButton.setVisible(false);
			OwnerButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new OwnerGUI();
					a.setVisible(true);
				}
			});
			OwnerButton.setForeground(Color.BLUE);
			OwnerButton.setFont(new Font("Tekton Pro", Font.PLAIN, 21));
			OwnerButton.setBounds(268, 239, 166, 29);
		}
		return OwnerButton;
	}
	
	private Component getAddRuralHouseButton(){
		if (AddRuralHouseButton == null){
			AddRuralHouseButton = new JButton("Gestionar Casa Rural");
			AddRuralHouseButton.setVisible(false);
			AddRuralHouseButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new GestionRuralHouseGUI();
					a.setVisible(true);
				}
			});
			AddRuralHouseButton.setForeground(Color.BLUE);
			AddRuralHouseButton.setFont(new Font("Tekton Pro", Font.PLAIN, 21));
			AddRuralHouseButton.setBounds(442, 239, 193, 29);
		}
		return AddRuralHouseButton;
	}
	
	public static void actualizarLogin(){
		textTwitter.setText(GestionTwitter.getUltimoTweet());
		if (Login.estadoLogin()){
			buttonLogin.setText("Logout");
			buttonRegister.setText("Perfil");
			OwnerButton.setVisible(true);
			if(Login.getPropietario() != null){
				textLogin.setText("Est�s logueado como propietario");
				OwnerButton.setText("Editar Propietario");
				AddRuralHouseButton.setVisible(true);
			}else{
				textLogin.setText("Est�s logueado");
				OwnerButton.setText("Ser Propietario");
			}
		} else {
			buttonLogin.setText("Login");
			buttonRegister.setText("Nuevo user");
			buttonRegister.setVisible(true);
			textLogin.setText("No est�s logueado");
			OwnerButton.setVisible(false);
			AddRuralHouseButton.setVisible(false);
		}
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1.setForeground(SystemColor.controlText);
			lblNewLabel_1.setBackground(SystemColor.inactiveCaptionBorder);
			ImageIcon myImage1 = new ImageIcon("imagen/Logo.jpg");
			lblNewLabel_1.setIcon(myImage1);
			lblNewLabel_1.setBounds(80, 12, 487, 218);
		}
		return lblNewLabel_1;
	}
} // @jve:decl-index=0:visual-constraint="0,0"
