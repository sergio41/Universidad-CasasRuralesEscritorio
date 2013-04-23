package gui;

import java.awt.EventQueue;
import java.rmi.Naming;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import businessLogic.ApplicationFacadeInterface;
import businessLogic.FacadeImplementation;
import configuration.Config;
import java.awt.Color;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;

public class Start extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JPanel contentPane;
	public static boolean isLocal=true;
	public static ApplicationFacadeInterface facadeInterface;
	public static JPanel panelArriba;
	public static JPanel panelPrincipal;
	
	private static Vector<String> vectorTwitter;
	private static int intTwitter;
	private static JTextPane textTwitter;
	
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
					panelPrincipal = new PantallaPrincipalGUI();
					modificarPanelAbajo(panelPrincipal);
					panelArriba = new LoginGUI();
					modificarPanelArriba(panelArriba);
					controlTiempo();
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
		setTitle("Casas Rurales Villatripas de Arriba");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Start.class.getResource("/imagenes/logo100x100.png")));
		setResizable(false);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GREEN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblTitulo = new JLabel("Villatripas de Arriba");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setForeground(new Color(102, 204, 51));
		lblTitulo.setFont(new Font("Viner Hand ITC", Font.BOLD, 32));
		lblTitulo.setBounds(112, 0, 443, 50);
		contentPane.add(lblTitulo);
		
		JLabel lblTwitter = new JLabel("");
		lblTwitter.setBounds(124, 50, 50, 50);
		lblTwitter.setIcon(new ImageIcon(getClass().getResource("/imagenes/twitter50x50.png")));
		contentPane.add(lblTwitter);
		
		textTwitter = new JTextPane();
		textTwitter.setForeground(new Color(0, 255, 255));
		textTwitter.setFont(new Font("Viner Hand ITC", Font.BOLD, 20));
		textTwitter.setText("Error al procesar twitter");
		textTwitter.setOpaque(false);
		textTwitter.setEditable(false);
		textTwitter.setBounds(175, 39, 443, 61);
		
		contentPane.add(textTwitter);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				JPanel panel = new PantallaPrincipalGUI();
				modificarPanelAbajo(panel);
			}
		});
		lblNewLabel_2.setBounds(567, 0, 50, 50);
		lblNewLabel_2.setIcon(new ImageIcon(getClass().getResource("/imagenes/home50x50.png")));
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(15, 0, 100, 100);
		lblNewLabel_1.setIcon(new ImageIcon(getClass().getResource("/imagenes/logo100x100.png")));
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 618, 100);
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/imagenes/fondoArriba.jpg")));
		contentPane.add(lblNewLabel);
	}

	public static void modificarPanelAbajo(JPanel panel){
		panelPrincipal.setVisible(false);
		contentPane.remove(panelPrincipal);
		panel.setBounds(0, 100, 1018, 465);
		panelPrincipal = panel;
		contentPane.add(panelPrincipal);
		panelPrincipal.setVisible(true);
	}
	
	public static void modificarPanelArriba(JPanel panel){
		panelArriba.setVisible(false);
		contentPane.remove(panelArriba);
		panel.setBounds(618, 0, 400, 100);
		panelArriba = panel;
		contentPane.add(panelArriba);
		panelArriba.setVisible(true);
	}
	
	public static void controlTiempo(){
		TimerTask timerTask = new TimerTask() { 
			public void run() {
				try {
					intTwitter++;
					if (vectorTwitter == null || intTwitter == 10 || vectorTwitter.size()<intTwitter){
						vectorTwitter = facadeInterface.Ultimos10Tweets();
						intTwitter =0;
					}
					textTwitter.setText(vectorTwitter.get(intTwitter));
				} catch (Exception e) {
					textTwitter.setText("Error al procesar twitter");
				}
			} 
		}; 
		Timer timer = new Timer(); 
		timer.scheduleAtFixedRate(timerTask, 0, 15000);
	}
}
