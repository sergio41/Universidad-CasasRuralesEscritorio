package gui;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import java.awt.Rectangle;
import java.util.Vector;

import javax.swing.JButton;

import businessLogic.ApplicationFacadeInterface;
import domain.Owner;
import domain.RuralHouse;


public class SetAvailabilityGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JComboBox jComboBox = null;
	private JButton jButton = null;

	/**
	 * This is the default constructor
	 */
	public SetAvailabilityGUI() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(449, 293);
		this.setContentPane(getJContentPane());
		this.setTitle("JFrame");
		
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
			jContentPane.add(getJComboBox(), null);
			jContentPane.add(getJButton(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBox() {
		
		if (jComboBox == null) {
			try {
			ApplicationFacadeInterface facade=StartWindow.getBusinessLogic();
				Vector<Owner> owners=facade.getOwners();
			jComboBox = new JComboBox(owners);
			jComboBox.setBounds(new Rectangle(63, 38, 175, 44));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			}
		
		return jComboBox;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(113, 146, 95, 59));
			jButton.setText("Aceptar");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//Owner owner=(Owner)jComboBox.getSelectedItem();
					//System.out.println(owner.getLogin());
					Vector<RuralHouse> houseList=null;
		  			try {
		  			    //Obtain the business logic from a StartWindow class (local or remote)
		  	    		ApplicationFacadeInterface facade=StartWindow.getBusinessLogic();
		  	    		
		  	    		
						//houseList=facade.getRuralHouses(owner);
					
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					if (houseList.isEmpty()!=true) {
						JFrame a = new SetAvailability2GUI(houseList);
						a.setVisible(true);
					} else if (houseList.isEmpty()==true){
						System.out.print("Owner does not exist or without houses ");
					} 		
					
				}
			});
		}
		return jButton;
	}

}  //  @jve:decl-index=0:visual-constraint="222,33"
