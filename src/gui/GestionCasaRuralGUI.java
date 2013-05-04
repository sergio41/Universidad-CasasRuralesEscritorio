package gui;


import javax.swing.ImageIcon;
import javax.swing.JPanel;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.filechooser.FileNameExtensionFilter;

import domain.RuralHouse;
import businessLogic.ApplicationFacadeInterface;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Vector;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JSpinner;


public class GestionCasaRuralGUI extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField textCity;
	private JComboBox<String> comBoxCasas;
	private DefaultComboBoxModel<String> modeloEC = new DefaultComboBoxModel<String>();
	private DefaultComboBoxModel<String> modeloImg = new DefaultComboBoxModel<String>();
	private JButton btnSalvar;
	private JButton btnEliminar;
	private JTextPane textPaneDescription;
	private JLabel image1label;
	private JLabel labelflechaDer;
	private JLabel labelflechaIzq;
	private JButton btnEliminarimg;
	private JComboBox<String> comBoxImg;
	private JButton btnanadirImg;
	private static Vector<File> images = new Vector<File>();
	private JSpinner textLiving;
	private JSpinner textRooms;
	private JSpinner textBath;
	private JSpinner textKitchen;
	private JSpinner textPark;
	private SpinnerNumberModel modeloSpinnerBanos = new SpinnerNumberModel(2, 2, 40, 1);
	private SpinnerNumberModel modeloSpinnerCocinas = new SpinnerNumberModel(1, 1, 40, 1);
	private SpinnerNumberModel modeloSpinnerHabitaciones = new SpinnerNumberModel(3, 3, 40, 1);
	private SpinnerNumberModel modeloSpinnerSala = new SpinnerNumberModel(0, 0, 40, 1);
	private SpinnerNumberModel modeloSpinnerAparcamiento = new SpinnerNumberModel(0, 0, 40, 1);

	
	/**
	 * Create the panel.
	 */
	public GestionCasaRuralGUI(){
		setLayout(null);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comBoxCasas.getSelectedIndex() > 0) {
					ApplicationFacadeInterface facade=Start.getBusinessLogic();
					try {
						facade.eliminarCasaRural(Integer.parseInt(comBoxCasas.getSelectedItem().toString()));						
						JPanel panel = new PantallaPrincipalGUI();
						Start.modificarPanelAbajo(panel);
						javax.swing.JOptionPane.showMessageDialog(null,"Se ha eliminado la casa Rural", "Bien....",javax.swing.JOptionPane.INFORMATION_MESSAGE);	
					}catch (Exception e) {
						javax.swing.JOptionPane.showMessageDialog(null,"Error al eliminar: " + e.getMessage(), "No....",javax.swing.JOptionPane.INFORMATION_MESSAGE);
					} 
					
				}
			}
		});
		btnEliminar.setEnabled(false);
		btnEliminar.setBounds(437, 25, 134, 33);
		add(btnEliminar);
		
		JLabel label_5 = new JLabel("N\u00BA Habitaciones*:");
		label_5.setHorizontalAlignment(SwingConstants.RIGHT);
		label_5.setFont(new Font("Dialog", Font.PLAIN, 21));
		label_5.setBounds(326, 213, 164, 34);
		add(label_5);
		
		JLabel label_6 = new JLabel("N\u00BA Salas de estar*:");
		label_6.setHorizontalAlignment(SwingConstants.RIGHT);
		label_6.setFont(new Font("Dialog", Font.PLAIN, 21));
		label_6.setBounds(282, 258, 208, 34);
		add(label_6);
		
		JLabel label_7 = new JLabel("N\u00BA Aparcamientos*:");
		label_7.setHorizontalAlignment(SwingConstants.RIGHT);
		label_7.setFont(new Font("Dialog", Font.PLAIN, 21));
		label_7.setBounds(282, 314, 208, 33);
		add(label_7);
		
		
		textPaneDescription = new JTextPane();
		textPaneDescription.setText("");
		textPaneDescription.setEnabled(false);
		textPaneDescription.setEditable(false);
		textPaneDescription.setBounds(201, 83, 370, 105);
		add(textPaneDescription);
		
		textCity = new JTextField();
		textCity.setText("");
		textCity.setEnabled(false);
		textCity.setColumns(10);
		textCity.setBounds(201, 213, 116, 33);
		add(textCity);
		
		JLabel label = new JLabel("N\u00BA Cocinas*:");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setFont(new Font("Dialog", Font.PLAIN, 21));
		label.setBounds(54, 311, 124, 34);
		add(label);
		
		JLabel label_1 = new JLabel("N\u00BA Ba\u00F1os*:");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("Dialog", Font.PLAIN, 21));
		label_1.setBounds(54, 256, 124, 34);
		add(label_1);
		
		JLabel label_2 = new JLabel("Ciudad*:");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("Dialog", Font.PLAIN, 21));
		label_2.setBounds(54, 211, 124, 34);
		add(label_2);
		
		JLabel label_3 = new JLabel("Descripci\u00F3n:");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setFont(new Font("Dialog", Font.PLAIN, 21));
		label_3.setBounds(54, 83, 124, 34);
		add(label_3);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String description = textPaneDescription.getText();
					String city = textCity.getText();
					int nRooms = (int) textRooms.getValue();
					int nKitchen = (int) textKitchen.getValue();
					int nBaths = (int) textBath.getValue();
					int nLiving = (int) textLiving.getValue();
					int nPark = (int) textPark.getValue();
							
					ApplicationFacadeInterface facade=Start.getBusinessLogic();
					if (comBoxCasas.getSelectedIndex() == 0){
						File nuevDir = new File("\\imagenes\\"+facade.getUsuario().getEmail()+"\\"+facade.getNumeroCR());
						nuevDir.mkdir();
						Vector<File> vectorImg= rellenarVectorImagenes();
						facade.anadirRuralHouse(description, city, nRooms, nKitchen, nBaths, nLiving, nPark, vectorImg);
					} else if (comBoxCasas.getSelectedIndex() > 0) {
						Vector<File> vectorImg= modificarVectorImagenes();
						facade.modificarRuralHouse((Integer.parseInt((String) comBoxCasas.getSelectedItem())), description, city, nRooms, nKitchen, nBaths, nLiving, nPark, vectorImg);
					}
					JPanel panel = new PantallaPrincipalGUI();
					Start.modificarPanelAbajo(panel);
					new File("/imagenes/"+facade.getUsuario().getEmail()+"/");
				} catch (Exception e) {
					javax.swing.JOptionPane.showMessageDialog(null,e.getMessage(), "Mal....",javax.swing.JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnSalvar.setForeground(Color.BLUE);
		btnSalvar.setFont(new Font("Dialog", Font.PLAIN, 21));
		btnSalvar.setEnabled(false);
		btnSalvar.setBounds(863, 385, 124, 45);
		add(btnSalvar);
		
		JLabel label_4 = new JLabel("Los campos marcados con * son obligatorios");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setForeground(Color.RED);
		label_4.setFont(new Font("Dialog", Font.PLAIN, 18));
		label_4.setBounds(473, 390, 366, 34);
		add(label_4);
		
		comBoxCasas = new JComboBox<String>();
		comBoxCasas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ApplicationFacadeInterface facade = Start.getBusinessLogic();
				if (comBoxCasas.getSelectedIndex() != -1){
					enaDis(true);
					btnEliminar.setEnabled(false);
					if (comBoxCasas.getSelectedIndex() != 0){
						RuralHouse rh;
						java.util.Iterator<RuralHouse> i;
						try {
							i = facade.getOwner().getRuralHouses().iterator();
							while (i.hasNext()){
								rh = i.next();
								if (rh.getHouseNumber() == Integer.parseInt((String) comBoxCasas.getSelectedItem())){
									btnEliminar.setEnabled(true);
									textPaneDescription.setText(rh.getDescription());
									textCity.setText(rh.getCity());
									textRooms.setValue(rh.getRooms());
									textKitchen.setValue(rh.getKitchen());
									textBath.setValue(rh.getBaths());
									textLiving.setValue(rh.getLiving());
									textPark.setValue(rh.getPark());
									images = rh.getImages();
									if (!images.isEmpty()){
									rellenarImg();
									ImageIcon imagen = new ImageIcon(images.elementAt(0).getPath());
						            Image aux = imagen.getImage();
						            Image aux1= aux.getScaledInstance(image1label.getHeight(), image1label.getWidth(), java.awt.Image.SCALE_SMOOTH);
						            image1label.setIcon(new ImageIcon(aux1));}
									break;
								}
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		});
		comBoxCasas.setSelectedIndex(-1);
		comBoxCasas.setModel(modeloEC);
		comBoxCasas.setBounds(31, 25, 377, 33);
		add(comBoxCasas);

		image1label = new JLabel("");
		image1label.setBounds(674, 95, 250, 250);
		ImageIcon imagen = new ImageIcon(getClass().getResource("/imagenes/casaDefault.png"));
        Image aux = imagen.getImage();
        Image aux1= aux.getScaledInstance(image1label.getHeight(), image1label.getWidth(), java.awt.Image.SCALE_SMOOTH);
        image1label.setIcon(new ImageIcon(aux1));
		add(image1label);
		
		btnanadirImg = new JButton("A\u00F1adir");
		btnanadirImg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				buscarImagen();	        
			}
		});
		btnanadirImg.setBounds(674, 59, 76, 23);
		add(btnanadirImg);
		
		comBoxImg = new JComboBox<String>();
		comBoxImg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comBoxCasas.getSelectedIndex() != -1){
					if(images!=null && images.size()>0){
						int x =  comBoxImg.getSelectedIndex();
			            ImageIcon imagen = new ImageIcon(images.get(x).getPath());
			            Image aux = imagen.getImage();
			            Image aux1= aux.getScaledInstance(image1label.getHeight(), image1label.getWidth(), java.awt.Image.SCALE_SMOOTH);
			            image1label.setIcon(new ImageIcon(aux1));
						btnEliminarimg.setEnabled(true);
					}
				}
			}
		});
		comBoxImg.setSelectedIndex(-1);
		comBoxImg.setModel(modeloImg);
		comBoxImg.setBounds(761, 59, 77, 23);
		add(comBoxImg);
		
		btnEliminarimg = new JButton("Eliminar");
		btnEliminarimg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comBoxImg.getItemCount()>0){
				btnEliminarimg.setEnabled(true);
				eliminarImagen();
				}
				else btnEliminarimg.setEnabled(false);
			}
		});
		btnEliminarimg.setBounds(848, 59, 76, 23);
		add(btnEliminarimg);
		
		labelflechaIzq = new JLabel("");
		labelflechaIzq.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(comBoxImg.getSelectedIndex()>0 & images.size()>0){
					comBoxImg.setSelectedIndex(comBoxImg.getSelectedIndex()-1);
		            ImageIcon imagen = new ImageIcon(images.elementAt(comBoxImg.getSelectedIndex()).getPath());
		            Image aux = imagen.getImage();
		            Image aux1= aux.getScaledInstance(image1label.getHeight(), image1label.getWidth(), java.awt.Image.SCALE_SMOOTH);
		            image1label.setIcon(new ImageIcon(aux1));
				}
			}
		});
		labelflechaIzq.setBounds(606, 196, 50, 50);
		labelflechaIzq.setIcon(new ImageIcon(getClass().getResource("/imagenes/flechaIzq.png")));
		add(labelflechaIzq);
		
		labelflechaDer = new JLabel("");
		labelflechaDer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if((comBoxImg.getSelectedIndex()<images.size()-1) & images.size()>0){
					comBoxImg.setSelectedIndex(comBoxImg.getSelectedIndex()+1);
		            ImageIcon imagen = new ImageIcon(images.elementAt(comBoxImg.getSelectedIndex()).getPath());
		            Image aux = imagen.getImage();
		            Image aux1= aux.getScaledInstance(image1label.getHeight(), image1label.getWidth(), java.awt.Image.SCALE_SMOOTH);
		            image1label.setIcon(new ImageIcon(aux1));
				}
			}
		});
		labelflechaDer.setBounds(941, 196, 50, 50);
		labelflechaDer.setIcon(new ImageIcon(getClass().getResource("/imagenes/flechaDer.png")));
		add(labelflechaDer);
		
		textPark = new JSpinner();
		textPark.setModel(modeloSpinnerAparcamiento);
		textPark.setBounds(508, 312, 63, 33);
		add(textPark);
		
		textLiving = new JSpinner();
		textLiving.setModel(modeloSpinnerSala);
		textLiving.setBounds(508, 256, 63, 33);
		add(textLiving);
		
		textRooms = new JSpinner();
		textRooms.setModel(modeloSpinnerHabitaciones);
		textRooms.setBounds(508, 211, 63, 33);
		add(textRooms);
		
		textBath = new JSpinner();
		textBath.setBounds(201, 256, 63, 33);
		textBath.setModel(modeloSpinnerBanos);
		add(textBath);
		
		textKitchen = new JSpinner();
		textKitchen.setModel(modeloSpinnerCocinas);
		textKitchen.setBounds(201, 311, 63, 33);
		add(textKitchen);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/imagenes/fondoAbajo.jpg")));
		lblNewLabel.setBounds(0, 0, 1018, 465);
		add(lblNewLabel);
		
		inicializarCampos();
	}

	private void enaDis(boolean b){
		btnSalvar.setEnabled(b);
		btnEliminar.setEnabled(b);
		textCity.setEnabled(b);
		textPaneDescription.setEnabled(b);
		textPaneDescription.setEditable(b);
		comBoxImg.setEnabled(b);
		btnanadirImg.setEnabled(b);
		btnEliminarimg.setEnabled(b);
		textKitchen.setEnabled(b);
		textBath.setEnabled(b);
		textCity.setEnabled(b);
		textLiving.setEnabled(b);
		textPark.setEnabled(b);
		textRooms.setEnabled(b);
		textPaneDescription.setText("");
		textCity.setText("");
		modeloImg.removeAllElements();
		images.clear();
	}


	private void inicializarCampos() {
		modeloEC.addElement("Nueva Casa Rural");
		ApplicationFacadeInterface facade = Start.getBusinessLogic();
		java.util.Iterator<RuralHouse> i;
		try {
			i = facade.getOwner().getRuralHouses().iterator();
			while (i.hasNext()){
				modeloEC.addElement(Integer.toString(i.next().getHouseNumber()));
			}
			enaDis(false);
			if (comBoxImg.getItemCount()==0){
				btnEliminarimg.setEnabled(false);}
			else btnEliminarimg.setEnabled(true);
			comBoxCasas.setSelectedIndex(-1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public DefaultComboBoxModel<String> getModeloEC() {
		return modeloEC;
	}

	public void setModeloEC(DefaultComboBoxModel<String> modeloEC) {
		this.modeloEC = modeloEC;
	}
	
	public void rellenarImg(){
		if(images!=null){
			int x = images.size();
			for(int i=0; i<x; i++)
				modeloImg.addElement(Integer.toString(i+1));}
		
	}
	
	public Vector<File> rellenarVectorImagenes(){
		Vector<File> auxV= new Vector<File>();
		ApplicationFacadeInterface facade = Start.getBusinessLogic();
		for (int i=0; i<images.size(); i++){
			try {
				File FDestino = new File("\\imagenes\\"+facade.getUsuario().getEmail()+"\\"+facade.getNumeroCR()+"\\"+images.get(i).getName());
				CopiarImagen(images.get(i), FDestino);
				auxV.add(i,FDestino);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		return auxV;
		
	}
	
	public Vector<File> modificarVectorImagenes(){
		Vector<File> auxV= new Vector<File>();
		ApplicationFacadeInterface facade = Start.getBusinessLogic();
		for (int i=0; i<images.size(); i++){
			try {
				File FDestino = new File("\\imagenes\\"+facade.getUsuario().getEmail()+"\\"+comBoxCasas.getSelectedItem().toString()+"\\"+images.get(i).getName());
				CopiarImagen(images.get(i), FDestino);
				auxV.add(i,FDestino);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		return auxV;
		
	}
	
	public void buscarImagen(){
		JFileChooser fc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Imágenes", "bmp", "gif", "jpg", "png");
		fc.setFileFilter(filter);
		//fc.setCurrentDirectory();

        int respuesta = fc.showOpenDialog(this);

        if (respuesta == JFileChooser.APPROVE_OPTION)
        {            		
           		try {
           			File imagenElegida = fc.getSelectedFile();
		            ImageIcon imagen = new ImageIcon(imagenElegida.getPath());
		            Image aux = imagen.getImage();
		            Image aux1= aux.getScaledInstance(image1label.getHeight(), image1label.getWidth(), java.awt.Image.SCALE_SMOOTH);
		            image1label.setIcon(new ImageIcon(aux1));
					images.add(imagenElegida);
					modeloImg.addElement(Integer.toString(images.size()));
					comBoxImg.setSelectedIndex(images.size()-1);
           		} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        }
	}	
	
	public static void CopiarImagen(File FOrigen,File FDestino){  
        try {  

        if(FOrigen.exists()){  
            String copiar="S";  
            if(FDestino.exists()){  
               //System.out.println("La imagen ya existe, Desea Sobre Escribir:S/N ");  
               copiar = "N";//(new BufferedReader(new InputStreamReader(System.in))).readLine();  
            }  
            if(copiar.toUpperCase().equals("S")){            
                FileInputStream LeeOrigen= new FileInputStream(FOrigen);  
                OutputStream Salida = new FileOutputStream(FDestino);  
                byte[] buffer = new byte[1024];  
                int tamaño;  
                while ((tamaño = LeeOrigen.read(buffer)) > 0) {  
                Salida.write(buffer, 0, tamaño);  
                }  
                System.out.println(FOrigen.getName()+" Copiado con Exito!!");  
                Salida.close();  
                LeeOrigen.close();  
            }  
              
        }else{//l fichero a copiar no existe                  
            System.out.println("El fichero a copiar no existe..."+FOrigen.getAbsolutePath());  
        }  
          
    } catch (Exception ex) {  
        System.out.println(ex.getMessage());  
      
   }  
	}
	
	public void eliminarImagen(){
		System.out.println(comBoxImg.getSelectedIndex());
		String s = images.elementAt(comBoxImg.getSelectedIndex()).getPath();
		comBoxImg.removeItem(comBoxImg.getSelectedItem());
		images.removeElementAt(comBoxImg.getSelectedIndex());	
		File d = new File(s);
        d.delete();
        
	}
}