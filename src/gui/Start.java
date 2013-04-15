package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.rmi.Naming;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;

import businessLogic.ApplicationFacadeInterface;
import businessLogic.FacadeImplementation;
import configuration.Config;

public class Start extends JFrame {
	private static JPanel contentPane;
	public static boolean isLocal=true;
	public static ApplicationFacadeInterface facadeInterface;
	
	public static ApplicationFacadeInterface getBusinessLogic(){
		return facadeInterface;
	}
	
	/**
	 * Launch the application.
	 */
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
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Start frame = new Start();
					frame.setVisible(true);
					JPanel panel = new PantallaPrincipalGUI();
					modificarPanelAbajo(panel);
					JPanel panel1 = new LoginGUI();
					modificarPanelArriba(panel1);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Start() {
		setResizable(false);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 618, 100);
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/imagenes/fondoArriba.jpg")));
		contentPane.add(lblNewLabel);
	}

	public static void modificarPanelAbajo(JPanel panel){
		panel.setBounds(0, 100, 1018, 465);
		contentPane.add(panel);
	}
	
	public static void modificarPanelArriba(JPanel panel){
		panel.setBounds(618, 0, 400, 100);
		contentPane.add(panel);
	}
}
