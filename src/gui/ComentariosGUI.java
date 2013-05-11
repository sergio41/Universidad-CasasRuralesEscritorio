package gui;
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
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;

import businessLogic.ApplicationFacadeInterface;

public class ComentariosGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultComboBoxModel<String> modeloEC = new DefaultComboBoxModel<String>();
	
	private String center = "SanchoElSabio19,Gasteiz";
	private int zoomI = 15;
	private String size = "500x500";
	private String maptype = "roadmap";
	private String sensor = "false";
	private String marker = "icon:http://montesinos.wikispaces.com/file/view/kfm_home(2).png/73196529/43x43/kfm_home(2).png%7csize:mid%7Ccolor:0xFFFF00%7Clabel:C%7C";
	private StarRater starRater;
	private JButton btnNewButton;
	private JTextPane txtpnComentar;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private int num;
	/**
	 * Launch the application.
	 */
	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ComentariosGUI frame = new ComentariosGUI(args[0]);
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
	public ComentariosGUI(String nRH) {
		center="";
		/*Sergio
			center = direccion.replaceAll(" ", "");
		 */
	
	    num = Integer.parseInt(nRH);
		setForeground(Color.YELLOW);
		setTitle("Mapa de la Casa Rural");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ComentariosGUI.class.getResource("/localData/iconMap.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 522, 628);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 206, 209));
		contentPane.setForeground(new Color(0, 206, 209));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		modeloEC.addElement("roadmap");
		modeloEC.addElement("satellite");
		modeloEC.addElement("terrain");
		modeloEC.addElement("hybrid");
		
		starRater = new StarRater(5, 2, 1);
		starRater.addStarListener(new StarRater.StarListener(){
			public void handleSelection(int selection) {
				System.out.println(selection);
			}});
		starRater.setBounds(247, 200, 80, 16);
	    starRater.setEnabled(false);
	    contentPane.add(starRater);
		
		txtpnComentar = new JTextPane();
		txtpnComentar.setForeground(new Color(50, 205, 50));
		txtpnComentar.setOpaque(false);
		//txtpnComentar.setForeground(new Color(255, 0, 0));
		//txtpnComentar.setBackground(new Color(0, 206, 209));
		txtpnComentar.setFont(new Font("Viner Hand ITC", Font.BOLD, 20));
		txtpnComentar.setText("Comentarios:");
		txtpnComentar.setBounds(187, 0, 140, 31);
		contentPane.add(txtpnComentar);
		
		btnNewButton = new JButton("Enviar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ApplicationFacadeInterface facade = Start.getBusinessLogic();
				try {
					facade.anadirCalificacionACasaRural(num, txtpnComentar.getText(), starRater.getSelection());
					dispose();
				} catch (Exception e1) {
					javax.swing.JOptionPane.showMessageDialog(null,e1.getMessage(), "Mal....",javax.swing.JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(356, 200, 140, 31);
		contentPane.add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(10, 69, 486, 112);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JTextPane txtpnOtrosHanComentado = new JTextPane();
		txtpnOtrosHanComentado.setText("Otros han comentado:");
		txtpnOtrosHanComentado.setForeground(Color.RED);
		txtpnOtrosHanComentado.setOpaque(false);
		txtpnOtrosHanComentado.setFont(new Font("Viner Hand ITC", Font.BOLD, 20));
		//txtpnOtrosHanComentado.setBackground(new Color(0, 206, 209));
		//txtpnOtrosHanComentado.setBounds(10, 291, 273, 31);
		contentPane.add(txtpnOtrosHanComentado);
		
		textField_1 = new JTextField();
		textField_1.setBounds(10, 330, 486, 112);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(10, 453, 486, 112);
		contentPane.add(textField_2);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 527, 600);
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/localData/verfotos.jpg")));
		contentPane.add(lblNewLabel);
	}
}