package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import businessLogic.ApplicationFacadeInterface;
import domain.Book;
import domain.RuralHouse;

public class GestionarReservasGUI extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTable tableCasas;
	private JScrollPane scrollPane;
	private DefaultComboBoxModel<String> modeloEC = new DefaultComboBoxModel<String>();
	private DefaultTableModel modelTb= new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Numero","FechaInicio", "FechaFin", "Precio", "FechaReserva"
			}
		);
	private JButton btnTodas;
	private JButton btnPorPagar;
	private JButton btnVerReservasPagadas;
	private JButton btnEliminarReserva;
	private JButton btnPagar;
	
	@SuppressWarnings("serial")
	public GestionarReservasGUI() {
		setLayout(null);
		
		tableCasas = new JTable(){
	        public boolean isCellEditable(int rowIndex, int vColIndex) {
	            return false;
	        }};
		tableCasas.setModel(modelTb);
		tableCasas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableCasas.setBounds(434, 38, 551, 229);
		ListSelectionModel cellSelectionModel = tableCasas.getSelectionModel();
	    cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	    cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
	      public void valueChanged(ListSelectionEvent e) {

	      }

	    });
		add(tableCasas);
		
		scrollPane = new JScrollPane(tableCasas, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(437, 68, 571, 291);
		add(scrollPane);
		
		JButton btnNewButton = new JButton("Volver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					JPanel temp1= new PantallaPrincipalGUI();
					Start.modificarPanelAbajo(temp1);
			}
		});
		btnNewButton.setBounds(843, 370, 165, 34);
		add(btnNewButton);
		
		btnTodas = new JButton("Ver todas las reservas");
		btnTodas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actualizarTabla(0);
			}
		});
		btnTodas.setBounds(62, 68, 365, 80);
		add(btnTodas);
		
		btnPorPagar = new JButton("Ver reservas por pagar");
		btnPorPagar.setBounds(62, 175, 365, 80);
		add(btnPorPagar);
		
		btnVerReservasPagadas = new JButton("Ver reservas pagadas");
		btnVerReservasPagadas.setBounds(62, 279, 365, 80);
		add(btnVerReservasPagadas);
		
		btnEliminarReserva = new JButton("Eliminar reserva");
		btnEliminarReserva.setBounds(643, 370, 165, 34);
		add(btnEliminarReserva);
		
		btnPagar = new JButton("Pagar");
		btnPagar.setBounds(437, 370, 165, 34);
		add(btnPagar);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/localData/fondoAbajo.jpg")));
		lblNewLabel.setBounds(0, 0, 1018, 465);
		add(lblNewLabel);
		
		inicializarCampos();
	}
	
	private void inicializarCampos() {
		ApplicationFacadeInterface facade = Start.getBusinessLogic();
		java.util.Iterator<RuralHouse> i;
		try {
			i = facade.getOwner(Start.getUsuario()).getRuralHouses().iterator();
			while (i.hasNext()){
				modeloEC.addElement(Integer.toString(i.next().getHouseNumber()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void actualizarTabla(int tipo){
		
		ApplicationFacadeInterface facade = Start.getBusinessLogic();
		if(tipo ==0){
			try {
				Iterator<Book> iter = facade.getUsuario(Start.getUsuario()).getReservas().iterator();
				while(iter.hasNext()){
					Book reservaConcreta = iter.next();
					Vector<Object> vector = new Vector<Object>();
					vector.add(/*reservaConcreta.getNumeroDeReserva()*/1);
					vector.add(reservaConcreta.getFechas().get(0));
					vector.add(reservaConcreta.getFechas().get(reservaConcreta.getFechas().size()-1));
					vector.add(reservaConcreta.getPrecio());
					vector.add(reservaConcreta.getFechaDeReserva());
					modelTb.addRow(vector);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void ajustarColumnas(){
		int anchoTabla = 551;
		int anchoColumna = 0, anchoColumnaMin = 0, anchoColumnaMax = 0;
		TableColumn columnaTabla = null;
		for(int i=0; i<tableCasas.getColumnCount(); i++) {
			columnaTabla = tableCasas.getColumnModel().getColumn(i);
			switch(i) {
				case 0: anchoColumna = (10*anchoTabla)/100;
				anchoColumnaMin = (10*anchoTabla)/100;
				anchoColumnaMax = (10*anchoTabla)/100;
				break;
				case 1: anchoColumna = (20*anchoTabla)/100;
				anchoColumnaMin = (20*anchoTabla)/100;
				anchoColumnaMax = (20*anchoTabla)/100;
				break;
				case 2: anchoColumna = (20*anchoTabla)/100;
				anchoColumnaMin = (20*anchoTabla)/100;
				anchoColumnaMax = (20*anchoTabla)/100;
				break;
				case 3: anchoColumna = (10*anchoTabla)/100;
				anchoColumnaMin = (10*anchoTabla)/100;
				anchoColumnaMax = (10*anchoTabla)/100;
				break;
				case 4: anchoColumna = (20*anchoTabla)/100;
				anchoColumnaMin = (20*anchoTabla)/100;
				anchoColumnaMax = (20*anchoTabla)/100;
				break;
			}
			columnaTabla.setPreferredWidth(anchoColumna);
			columnaTabla.setMinWidth(anchoColumnaMin);
			columnaTabla.setMaxWidth(anchoColumnaMax);
		}
	}

	private void borrarTabla(){ while (modelTb.getRowCount() > 0) modelTb.removeRow(modelTb.getRowCount()-1); }

}