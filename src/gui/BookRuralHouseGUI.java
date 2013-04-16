package gui;

import businessLogic.ApplicationFacadeInterface;

import com.toedter.calendar.*;

import domain.Book;
import domain.RuralHouse;

import exceptions.OfferCanNotBeBooked;

import java.beans.*;
 
import java.sql.Date;
import java.text.*;
import java.util.*;

import javax.swing.*;

import viejoGUI.StartWindow;

import java.awt.*;
import java.awt.event.*;



public class BookRuralHouseGUI extends JFrame {
private static final long serialVersionUID = 1L;

  private JLabel jLabel1 = new JLabel();
  private JComboBox jComboBox1;
  private JLabel jLabel2 = new JLabel();
  private JLabel jLabel3 = new JLabel();
  private JLabel jLabel4 = new JLabel();
  private JTextField jTextField2 = new JTextField();
  private JTextField jTextField3 = new JTextField();
  private JTextField jTextField4 = new JTextField();
  private JButton jButton2 = new JButton();
  private JButton jButton3 = new JButton();
  
  // Code for JCalendar
  private JCalendar jCalendar1 = new JCalendar();
  private Calendar calendarMio = null;
  private JLabel jLabel5 = new JLabel();
  
  

public BookRuralHouseGUI()
 {
    try
    {
      jbInit();
      
     
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }
  public BookRuralHouseGUI(int houseNumber,Date firstDay,Date lastDay)
  {
    try
    {
      jbInit();
      
 /*jTextField1.setText(Integer.toString(houseNumber));
 long nights=(lastDay.getTime()-firstDay.getTime())/(1000*60*60*24);
 jTextField3.setText(Long.toString(nights));
 DateFormat dateformat1 = DateFormat.getDateInstance(1);

 Date first= new Date((long)(firstDay.getTime()));
  jTextField2.setText(dateformat1.format(first));
GregorianCalendar cal=new GregorianCalendar();
cal.setTime(first);
int year=cal.get(Calendar.YEAR);
int month=cal.get(Calendar.MONTH);
int day=cal.get(Calendar.DAY_OF_MONTH);




  JYearChooser yc=jCalendar1.getYearChooser();
    JMonthChooser mc=   jCalendar1.getMonthChooser();
    JDayChooser dc= jCalendar1.getDayChooser();
  
    
    yc.setYear(year);
    mc.setMonth(month);
    dc.setDay(day);

    */


    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }

  private void jbInit() throws Exception
  {
    this.getContentPane().setLayout(null);
    this.setSize(new Dimension(410, 413));
    this.setTitle("Book Rural House");
    jLabel1.setText("Rural house:");
    ApplicationFacadeInterface facade=StartWindow.getBusinessLogic();
	Vector<RuralHouse> ruralHouses=facade.getAllRuralHouses();

	jComboBox1 = new JComboBox(ruralHouses);

    jLabel1.setBounds(new Rectangle(15, 10, 115, 20));
    jComboBox1.setBounds(new Rectangle(120, 10, 175, 20));

    
    jTextField3.addFocusListener(new FocusListener()
      {
          public void focusGained(FocusEvent e)
          {
          }
  
          public void focusLost(FocusEvent e)
          {
            jTextField3_focusLost();
          }
      });
    jTextField4.addFocusListener(new FocusListener()
      {
          public void focusGained(FocusEvent e)
          {
          }
  
          public void focusLost(FocusEvent e)
          {
            jTextField4_focusLost();
          }
      });
    jLabel2.setText("Arrival day:");
    jLabel2.setBounds(new Rectangle(15, 40, 115, 20));
    jLabel3.setText("Number of nights:");
    jLabel3.setBounds(new Rectangle(15, 240, 115, 20));
    jLabel4.setText("Telephone contact number:");
    jLabel4.setBounds(new Rectangle(15, 270, 140, 20));
    jTextField2.setBounds(new Rectangle(155, 205, 140, 20));
    jTextField2.setEditable(false);
    jTextField3.setBounds(new Rectangle(155, 240, 140, 20));
    jTextField3.setText("0");
    jTextField4.setBounds(new Rectangle(155, 270, 140, 20));
    jTextField4.setText("0");
    jButton2.setText("Accept");
    jButton2.setBounds(new Rectangle(50, 345, 130, 30));
    jButton2.setSize(new Dimension(130, 30));
    jButton2.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
        	// House code
        	RuralHouse house=(RuralHouse)jComboBox1.getSelectedItem();
        	// Arrival date
        	Date firstDay= new Date(jCalendar1.getCalendar().getTime().getTime());

        	firstDay=Date.valueOf(firstDay.toString());
        	// Last day
        	// Number of days expressed in milliseconds
        	long nights=1000*60*60*24* Integer.parseInt(jTextField3.getText());
        	Date lastDay= new Date((long)(firstDay.getTime()+nights));
        	// Telephone contact
        	String telephone=jTextField4.getText();
        	try {
				
        		//Obtain the business logic from a StartWindow class (local or remote)
        		ApplicationFacadeInterface facade=StartWindow.getBusinessLogic();
        		        		
        		Book book=facade.createBook(house, firstDay, lastDay, telephone);
				if (book!=null) {
				BookRuralHouseConfirmationWindow confirmWindow=new BookRuralHouseConfirmationWindow(book);
				confirmWindow.setVisible(true);
				}
			} catch (OfferCanNotBeBooked e1) {
				jLabel5.setText("Error: It is not possible to book");
				JFrame a = new QueryAvailabilityGUI();
			    a.setVisible(true);
			
        } catch (Exception e1) {
			
			e1.printStackTrace();
        }}
      });
    jButton3.setText("Cancel");
    jButton3.setBounds(new Rectangle(220, 345, 130, 30));
    jButton3.setSize(new Dimension(130, 30));
    jButton3.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          jButton3_actionPerformed(e);
        }
      });
    jLabel5.setBounds(new Rectangle(50, 310, 300, 20));
    jLabel5.setForeground(Color.red);
    jCalendar1.setBounds(new Rectangle(155, 50, 235, 145));
    this.getContentPane().add(jCalendar1, null);
    this.getContentPane().add(jLabel5, null);
    this.getContentPane().add(jButton3, null);
    this.getContentPane().add(jButton2, null);
    this.getContentPane().add(jTextField4, null);
    this.getContentPane().add(jTextField3, null);
    this.getContentPane().add(jTextField2, null);
    this.getContentPane().add(jLabel4, null);
    this.getContentPane().add(jLabel3, null);
    this.getContentPane().add(jLabel2, null);
    this.getContentPane().add(jComboBox1, null);
    this.getContentPane().add(jLabel1, null);
    
    // Code for JCalendar
    this.jCalendar1.addPropertyChangeListener(new PropertyChangeListener()
    {
      public void propertyChange(PropertyChangeEvent propertychangeevent)
      {
        if (propertychangeevent.getPropertyName().equals("locale"))
        {
          jCalendar1.setLocale((Locale) propertychangeevent.getNewValue());
          DateFormat dateformat = DateFormat.getDateInstance(1, jCalendar1.getLocale());
          jTextField2.setText(dateformat.format(calendarMio.getTime()));
          
        }
        else if (propertychangeevent.getPropertyName().equals("calendar"))
        {
          calendarMio = (Calendar) propertychangeevent.getNewValue();
          DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar1.getLocale());
          jTextField2.setText(dateformat1.format(calendarMio.getTime()));
          jCalendar1.setCalendar(calendarMio);
        }
      } 
    });
  }
 

  private void jButton3_actionPerformed(ActionEvent e)
  {
    this.setVisible(false);
  }

 
 private void jTextField3_focusLost()
 {
   try
  {
    new Integer (jTextField3.getText());
    jLabel5.setText("");
  }
  catch (NumberFormatException ex)
  {
    jLabel5.setText("Error: Introduce a number");
  }
 }
 
 private void jTextField4_focusLost()
 {
   try
  {
    new Integer (jTextField4.getText());
    jLabel5.setText("");
  }
  catch (NumberFormatException ex)
  {
    jLabel5.setText("Error: Introduce a number");
  }
 }
 
}