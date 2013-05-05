package gui;
import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.Toolkit;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;

public class Mapas extends JFrame {
	private static final long serialVersionUID = 1L;
	private static JPanel contentPane;
	private static DefaultComboBoxModel<String> modeloEC = new DefaultComboBoxModel<String>();
	private static JComboBox<String> comboBox;
	private static JSlider slider;
	
	private static String center = "SanchoElSabio19,Gasteiz";
	private static int zoomI = 15;
	private static String size = "500x500";
	private static String maptype = "roadmap";
	private static String sensor = "false";
	private static String marker = "icon:http://montesinos.wikispaces.com/file/view/kfm_home(2).png/73196529/43x43/kfm_home(2).png%7csize:mid%7Ccolor:0xFFFF00%7Clabel:C%7C";
	private static JLabel lblMapa;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mapas frame = new Mapas();
					frame.setVisible(true);
					crearMapa();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Mapas() {
		setForeground(Color.YELLOW);
		setTitle("Mapa de la Casa Rural");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Mapas.class.getResource("/imagenes/iconMap.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 522, 628);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 206, 209));
		contentPane.setForeground(new Color(0, 206, 209));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblMapa = new JLabel("New label");
		lblMapa.setBounds(0, 81, 500, 500);
		contentPane.add(lblMapa);
		
		comboBox = new JComboBox<String>();
		modeloEC.addElement("roadmap");
		modeloEC.addElement("satellite");
		modeloEC.addElement("terrain");
		modeloEC.addElement("hybrid");
		comboBox.setModel(modeloEC);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comboBox.getSelectedItem().toString().compareTo(maptype) != 0) {
					maptype = comboBox.getSelectedItem().toString();
					crearMapa();
				}
			}
		});
		comboBox.setBounds(12, 37, 174, 31);
		contentPane.add(comboBox);
		
		slider = new JSlider();
		slider.setBackground(new Color(0, 206, 209));
		slider.setForeground(new Color(0, 0, 0));
		slider.setPaintLabels(true);
		slider.setSnapToTicks(true);
		slider.setPaintTicks(true);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				zoomI = slider.getValue();
				crearMapa();
			}
		});
		slider.setValue(15);
		slider.setMaximum(21);
		slider.setBounds(293, 37, 174, 31);
		contentPane.add(slider);
		
		JTextPane txtpnEligeTipoDe = new JTextPane();
		txtpnEligeTipoDe.setForeground(new Color(255, 0, 0));
		txtpnEligeTipoDe.setBackground(new Color(0, 206, 209));
		txtpnEligeTipoDe.setFont(new Font("Viner Hand ITC", Font.BOLD, 20));
		txtpnEligeTipoDe.setText("Elige tipo de mapa y el zoom:");
		txtpnEligeTipoDe.setBounds(89, 0, 344, 31);
		contentPane.add(txtpnEligeTipoDe);
	}
	
	public static void crearMapa(){
		String aux = "http://maps.googleapis.com/maps/api/staticmap?";
		aux = aux.concat("center=" + center);
		aux = aux.concat("&zoom=" + Integer.toString(zoomI));
		aux = aux.concat("&size=" + size);
		aux = aux.concat("&maptype=" + maptype);
		aux = aux.concat("&sensor=" + sensor);
		aux = aux.concat("&markers=" + marker + center);
		System.out.println("hola ." + aux);
		ponerMapa(aux);
	}
	
	private static void ponerMapa(String sURL){
		try {
			BufferedImage imgBufferedImage = ImageIO.read(new URL(sURL));
			lblMapa.setIcon(new ImageIcon(imgBufferedImage));
		} catch (Exception ex) {
			System.out.println("Error!" + ex);
		}
	}
}