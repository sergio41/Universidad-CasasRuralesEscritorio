		package gui;

		import javax.swing.DefaultComboBoxModel;
		import javax.swing.ImageIcon;
		import javax.swing.JPanel;
		import javax.swing.JTextField;
		import java.awt.Color;
		import javax.swing.JButton;
		import javax.swing.JLabel;
		import java.awt.Font;
		import javax.swing.SwingConstants;
		import javax.swing.JComboBox;

		import businessLogic.ApplicationFacadeInterface;

		import java.awt.event.ActionListener;
		import java.awt.event.ActionEvent;
		import java.io.File;
		import java.text.ParseException;
		import java.util.Vector;

		import javax.swing.JRadioButton;
		import javax.swing.text.MaskFormatter;

		import domain.Owner;
		import javax.swing.JFormattedTextField;
		import javax.swing.JSlider;
		import javax.swing.JToggleButton;
		import javax.swing.JCheckBox;

public class ReservarGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private static JRadioButton rdbtnProfesional = new JRadioButton("Profesional");
	private static JRadioButton rdbtnParticular = new JRadioButton("Particular");
	private static DefaultComboBoxModel<String> modeloMon = new DefaultComboBoxModel<String>();
	
	
	public ReservarGUI() {

			setLayout(null);
			modeloMon.removeAllElements();
			modeloMon.addElement("");
			modeloMon.addElement("€");
			modeloMon.addElement("$");
			
			
			JButton btnNewButton = new JButton("Terminar");
			btnNewButton.setBounds(782, 388, 194, 37);
			add(btnNewButton);
			
			JLabel lblFechaDeIni = new JLabel("Fecha de inicio de reserva:");
			lblFechaDeIni.setBounds(265, 149, 134, 14);
			add(lblFechaDeIni);
			
			JLabel lblFechaDeFin = new JLabel("Fecha de fin de reserva:");
			lblFechaDeFin.setBounds(277, 189, 134, 14);
			add(lblFechaDeFin);
			
			JButton btnNewButton_1 = new JButton("Mandar Factura");
			btnNewButton_1.setBounds(538, 387, 217, 39);
			add(btnNewButton_1);
			
			JLabel lblPrecio = new JLabel("Precio:\r\n");
			lblPrecio.setBounds(362, 229, 33, 14);
			add(lblPrecio);
			
			JLabel labelIni = new JLabel("");
			labelIni.setForeground(new Color(0, 128, 0));
			labelIni.setBounds(409, 149, 97, 14);
			add(labelIni);
			
			JLabel labelFin = new JLabel("");
			labelFin.setForeground(new Color(0, 128, 0));
			labelFin.setBounds(409, 189, 97, 14);
			add(labelFin);
			
			JLabel labelprecio = new JLabel("");
			labelprecio.setForeground(new Color(0, 128, 0));
			labelprecio.setBounds(409, 229, 97, 14);
			add(labelprecio);
			
			JLabel lblEstadoDePago = new JLabel("Estado de pago:");
			lblEstadoDePago.setBounds(317, 269, 84, 14);
			add(lblEstadoDePago);
			
			JLabel labelEstado = new JLabel("");
			labelEstado.setForeground(new Color(0, 128, 0));
			labelEstado.setBounds(409, 269, 97, 14);
			add(labelEstado);
			
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/localData/fondoAbajo.jpg")));
			lblNewLabel.setBounds(0, 0, 1018, 465);
			add(lblNewLabel);
			
			inicializarCampos();

		}
		
		@SuppressWarnings("deprecation")
		private void inicializarCampos(){
			ApplicationFacadeInterface facade = Start.getBusinessLogic();
			
		}
}


