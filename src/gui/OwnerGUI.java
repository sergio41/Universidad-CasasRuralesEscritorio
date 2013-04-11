package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.JButton;

import businessLogic.ApplicationFacadeInterface;

import domain.Owner;
import domain.RuralHouse;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Vector;

public class OwnerGUI extends JFrame {

	
	private JPanel contentPane;
	private JTextField textCuentaBancaria;
	private JTextField textProfesion;
	private JTextField textIdioma1;
	private JTextField textIdioma2;
	private JTextField textIdioma3;
	private JTextField textIdioma4;
	private JLabel lblMoneda;
	private JLabel label;
	private static JButton buttonGuardar;
	private static JComboBox comboMoneda = new JComboBox();
	private static JRadioButton rdbtnProfesional = new JRadioButton("Profesional");
	private static JRadioButton rdbtnParticular = new JRadioButton("Particular");
	private DefaultComboBoxModel<String> modeloMon = new DefaultComboBoxModel<String>();

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OwnerGUI frame = new OwnerGUI();
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
	public OwnerGUI() {
		setTitle("Perfil Propietario");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 658, 396);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(178,238,238));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCuentaBancaria = new JLabel("Cuenta bancar\u00EDa:*");
		lblCuentaBancaria.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCuentaBancaria.setFont(new Font("Tekton Pro", Font.PLAIN, 21));
		lblCuentaBancaria.setBounds(32, 30, 161, 34);
		contentPane.add(lblCuentaBancaria);
		
		textCuentaBancaria = new JTextField();
		textCuentaBancaria.setColumns(10);
		textCuentaBancaria.setBounds(248, 30, 366, 34);
		contentPane.add(textCuentaBancaria);
		
		JLabel lblUstedEs = new JLabel("Usted es:*");
		lblUstedEs.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUstedEs.setFont(new Font("Tekton Pro", Font.PLAIN, 21));
		lblUstedEs.setBounds(32, 77, 161, 34);
		contentPane.add(lblUstedEs);
		
		rdbtnParticular.setBackground(new Color(178,238,238));
		rdbtnParticular.setBounds(248, 77, 127, 34);
		contentPane.add(rdbtnParticular);
		
		
		rdbtnProfesional.setBackground(new Color(178,238,238));
		rdbtnProfesional.setBounds(453, 77, 127, 34);
		contentPane.add(rdbtnProfesional);
		
		textProfesion = new JTextField();
		textProfesion.setColumns(10);
		textProfesion.setBounds(248, 214, 366, 34);
		contentPane.add(textProfesion);
		
		JLabel lblProfesion = new JLabel("Profesi\u00F3n:*");
		lblProfesion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblProfesion.setFont(new Font("Tekton Pro", Font.PLAIN, 21));
		lblProfesion.setBounds(32, 214, 161, 34);
		contentPane.add(lblProfesion);
		
		JLabel lblIdiomasHablados = new JLabel("Idiomas hablados:*");
		lblIdiomasHablados.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIdiomasHablados.setFont(new Font("Tekton Pro", Font.PLAIN, 21));
		lblIdiomasHablados.setBounds(32, 124, 161, 34);
		contentPane.add(lblIdiomasHablados);
		
		textIdioma1 = new JTextField();
		textIdioma1.setColumns(10);
		textIdioma1.setBounds(248, 124, 161, 34);
		contentPane.add(textIdioma1);
		
		textIdioma2 = new JTextField();
		textIdioma2.setColumns(10);
		textIdioma2.setBounds(453, 124, 161, 34);
		contentPane.add(textIdioma2);
		
		textIdioma3 = new JTextField();
		textIdioma3.setColumns(10);
		textIdioma3.setBounds(248, 167, 161, 34);
		contentPane.add(textIdioma3);
		
		textIdioma4 = new JTextField();
		textIdioma4.setColumns(10);
		textIdioma4.setBounds(453, 167, 161, 34);
		contentPane.add(textIdioma4);
		
		lblMoneda = new JLabel("Moneda:*");
		lblMoneda.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMoneda.setFont(new Font("Tekton Pro", Font.PLAIN, 21));
		lblMoneda.setBounds(32, 261, 161, 34);
		contentPane.add(lblMoneda);
		
		
		comboMoneda.setBounds(248, 261, 109, 34);
		comboMoneda.setModel(modeloMon);
		modeloMon.addElement("");
		modeloMon.addElement("�");
		modeloMon.addElement("$");
		contentPane.add(comboMoneda);
		
		label = new JLabel("Los campos marcados con * son obligatorios");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setForeground(Color.RED);
		label.setFont(new Font("Tekton Pro", Font.PLAIN, 18));
		label.setBounds(126, 302, 366, 34);
		contentPane.add(label);
		contentPane.add(getSaveButton());

		inicializarCampos();
		
	}
	private JButton getSaveButton() {
		buttonGuardar=new JButton("Registrar");
		buttonGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ApplicationFacadeInterface facade = StartWindow.getBusinessLogic();
				String cuenta= "";
				String tipo = "";
				String profes= "";
				String mon="";
				Vector<String> idiom = new Vector<String>();
				
				cuenta= textCuentaBancaria.getText();
				if(rdbtnParticular.isSelected()) tipo= "Particular";				
				else if(rdbtnProfesional.isSelected()) tipo= "Profesional";	
				idiom.add(textIdioma1.getText());
				idiom.add(textIdioma2.getText());
				idiom.add(textIdioma3.getText());
				idiom.add(textIdioma4.getText());
				profes = textProfesion.getText();
				mon= (String) comboMoneda.getSelectedItem();
				//Owner own = new Owner(cuenta, tipo, idiom, profes, mon);
				
				try {
					/*if(facade.getOwner()!=null){
						java.util.Iterator<RuralHouse> i =  facade.getOwner().getRuralHouses().iterator();
							while (i.hasNext()){
								own.addRuralHouse(i.next());
							}
					}*/
					facade.modificarOwner(cuenta, tipo, idiom, profes, mon);
					//Login.setPropietario(own);
					StartWindow.actualizarLogin();
					setVisible(false);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		buttonGuardar.setForeground(Color.BLUE);
		buttonGuardar.setFont(new Font("Tekton Pro", Font.PLAIN, 21));
		buttonGuardar.setBounds(504, 291, 124, 45);
		return buttonGuardar;
	}

	@SuppressWarnings("deprecation")
	private void inicializarCampos(){
		ApplicationFacadeInterface facade = StartWindow.getBusinessLogic();
		Owner ow;
		try {
			ow = facade.getOwner();
			if (ow!=null){
				textCuentaBancaria.enable(false);
				textCuentaBancaria.setText(ow.getBankAccount());
				if(ow.getTipo()=="Particular"){
					rdbtnProfesional.setSelected(false);
					rdbtnParticular.setSelected(true);
				}else if(ow.getTipo()=="Profesional"){
					rdbtnProfesional.setSelected(true);
					rdbtnParticular.setSelected(false);
				}
				textIdioma1.setText(ow.getIdiomas().elementAt(0));
				textIdioma2.setText(ow.getIdiomas().elementAt(1));
				textIdioma3.setText(ow.getIdiomas().elementAt(2));
				textIdioma4.setText(ow.getIdiomas().elementAt(3));
				textProfesion.setText(ow.getProfesion());
				comboMoneda.setSelectedItem(ow.getMoneda());
				buttonGuardar.setText("Guardar");
			} else {
				textCuentaBancaria.enable(true);
				textCuentaBancaria.setText("");
				rdbtnProfesional.setSelected(false);
				rdbtnParticular.setSelected(false);
				textIdioma1.setText("");
				textIdioma2.setText("");
				textIdioma3.setText("");
				textIdioma4.setText("");
				textProfesion.setText("");
				comboMoneda.setSelectedIndex(0);
				buttonGuardar.setText("Registrar");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

