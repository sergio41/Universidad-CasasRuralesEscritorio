package businessLogic;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.sql.SQLException;

import java.util.Vector;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import configuration.Config;

import dataAccess.DB4oManager;
import domain.Book;
import domain.Offer;
import domain.Owner;
import domain.RuralHouse;


import exceptions.OfferCanNotBeBooked;


public class FacadeImplementation extends UnicastRemoteObject implements ApplicationFacadeInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BookManager theBookMngr;
	
 

	public FacadeImplementation() throws RemoteException, InstantiationException,
			IllegalAccessException, ClassNotFoundException, SQLException {
		Config c=Config.getInstance();
		String dataBaseOpenMode=c.getDataBaseOpenMode();
		DB4oManager.openDatabase(dataBaseOpenMode);
		theBookMngr = BookManager.getInstance();
		//dbMngr = DB4oManager.getInstance();
	}

	/**
	 * This method obtains owner rural houses 
	 * 
	 * @param owner object
	 *            
	 * @return a vector of Rural Houses
	 */
	public Vector<RuralHouse> getRuralHouses(Owner owner)
			throws RemoteException {
		
		return owner.getRuralHouses();
		
	}

	

	/**
	 * This method creates an offer with a house number, first day, last day and price
	 * 
	 * @param House
	 *            number, start day, last day and price
	 * @return None
	 */
	public Offer createOffer(RuralHouse ruralHouse, Date firstDay, Date lastDay,
			float price) throws RemoteException, Exception {
		ObjectContainer db=DB4oManager.getContainer();
		RuralHouse proto = new RuralHouse(ruralHouse.getHouseNumber(),null,ruralHouse.getDescription(),ruralHouse.getCity(), 
				ruralHouse.getRooms(), ruralHouse.getKitchen(), ruralHouse.getBaths(), ruralHouse.getLiving(), ruralHouse.getPark());
		 ObjectSet result = db.queryByExample(proto);
		 RuralHouse rh=(RuralHouse)result.next();
		Offer o=rh.createOffer(firstDay, lastDay, price);
		db.store(o);
		db.commit(); 
		return o;
	}

	/**
	 * This method creates a book with a corresponding parameters
	 * 
	 * @param First
	 *            day, last day, house number and telephone
	 * @return a book
	 */
	public Book createBook(RuralHouse ruralHouse, Date firstDate, Date lastDate, String bookTelephoneNumber)
			throws OfferCanNotBeBooked {

		
		try {
			ObjectContainer db=DB4oManager.getContainer();
			RuralHouse proto = new RuralHouse(ruralHouse.getHouseNumber(),null,ruralHouse.getDescription(),ruralHouse.getCity(), 
					ruralHouse.getRooms(), ruralHouse.getKitchen(), ruralHouse.getBaths(), ruralHouse.getLiving(), ruralHouse.getPark());
			 ObjectSet result = db.queryByExample(proto);
			 RuralHouse rh=(RuralHouse)result.next();
			Book b=null;
			Offer offer;
			offer = rh.findOffer(firstDate, lastDate);

			if (offer!=null) {
				 b=new Book(bookTelephoneNumber,offer);
				offer.setBook(b);
				db.store(b);
				db.store(offer);
				db.commit();
			}
			return b;
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}
	}

	
	/**
	 * This method obtains available offers for a concrete house in a certain period 
	 * 
	 * @param houseNumber, the house number where the offers must be obtained 
	 * @param firstDay, first day in a period range 
	 * @param lastDay, last day in a period range
	 * @return a vector of offers(Offer class)  available  in this period
	 */
	public Vector<Offer> getOffers(RuralHouse house,Date firstDay, Date lastDay) throws RemoteException,
			Exception {
		
		return house.getOffers(firstDay, lastDay);

	}
	/**
	 * This method existing  owners 
	 * 
	 */
	public Vector<Owner> getOwners() throws RemoteException,
			Exception {
		ObjectContainer db=DB4oManager.getContainer();

		 try {
			 Owner proto = new Owner(null, null, null, null, null);
			 ObjectSet result = db.queryByExample(proto);
			 Vector<Owner> owners=new Vector<Owner>();
			 while(result.hasNext())				 
				 owners.add((Owner)result.next());
			 return owners;
	     } finally {
	         //db.close();
	     }
	} 
	public Vector<RuralHouse> getAllRuralHouses() throws RemoteException,
	Exception {
		ObjectContainer db=DB4oManager.getContainer();

		 try {
			 RuralHouse proto = new RuralHouse(0,null,null,null,0,0,0,0,0);
			 ObjectSet result = db.queryByExample(proto);
			 Vector<RuralHouse> ruralHouses=new Vector<RuralHouse>();
			 while(result.hasNext()) 
				 ruralHouses.add((RuralHouse)result.next());
			 return ruralHouses;
	     } finally {
	         //db.close();
	     }
	}
	public void close() throws RemoteException{
		DB4oManager.close();

	}

	@Override
	public void anadirRuralHouse(String description, String city,
			String nRooms, String nKitchen, String nBaths, String nLiving,
			String nPark) throws Exception {
	if (city.compareTo("") == 0 || nRooms.compareTo("") == 0
			|| nKitchen.compareTo("") == 0 || nBaths.compareTo("") == 0
			|| nLiving.compareTo("") == 0 || nPark.compareTo("") == 0)
		throw new Exception("Algunos datos obligatorios faltan.");
	else {
			try {
				int r = Integer.parseInt(nRooms);
				int k = Integer.parseInt(nKitchen);
				int b = Integer.parseInt(nBaths);
				int l = Integer.parseInt(nLiving);
				int p = Integer.parseInt(nPark);
				
				if (r<3) throw new Exception("La casa debe tener m�nimo 3 habitaciones.");
				if (k<1) throw new Exception("La casa debe tener m�nimo 1 cocina.");
				if (b<2) throw new Exception("La casa debe tener m�nimo 2 ba�os.");
				Owner own =Login.getPropietario();
				RuralHouse rh = new RuralHouse(getNumeroCR(), Login.getUser(),
						description, city, r, k, b, l, p);
				own.addRuralHouse(rh);
				Login.setPropietario(own);
				javax.swing.JOptionPane.showMessageDialog(null,"Casa a�adida correctamente.", "Bien....",javax.swing.JOptionPane.NO_OPTION);
			} catch (Exception e) {
				javax.swing.JOptionPane.showMessageDialog(null,e.toString(),"Alguna casilla ha sido mal rellenada.",javax.swing.JOptionPane.ERROR_MESSAGE);
			}
	}
		
	}

	@Override
	public void modficarRuralHouse(int numero, String description,
			String city, String nRooms, String nKitchen, String nBaths,
			String nLiving, String nPark) throws Exception {
		if (city.compareTo("") == 0 || nRooms.compareTo("") == 0
				|| nKitchen.compareTo("") == 0 || nBaths.compareTo("") == 0
				|| nLiving.compareTo("") == 0 || nPark.compareTo("") == 0)
			throw new Exception("Algunos datos obligatorios faltan.");
		else {
				try {
					int r = Integer.parseInt(nRooms);
					int k = Integer.parseInt(nKitchen);
					int b = Integer.parseInt(nBaths);
					int l = Integer.parseInt(nLiving);
					int p = Integer.parseInt(nPark);
					
					if (r<3) throw new Exception("La casa debe tener m�nimo 3 habitaciones.");
					if (k<1) throw new Exception("La casa debe tener m�nimo 1 cocina.");
					if (b<2) throw new Exception("La casa debe tener m�nimo 2 ba�os.");
					Owner own =Login.getPropietario();
					

					
					ObjectContainer db=DB4oManager.getContainer();
					RuralHouse proto = new RuralHouse(numero,null,null,null,0,0,0,0,0);
					ObjectSet result = db.queryByExample(proto);
					
					RuralHouse rh = (RuralHouse) result.next();
					rh.setBaths(b);
					rh.setCity(city);
					rh.setDescription(description);
					rh.setKitchen(k);
					rh.setLiving(l);
					rh.setPark(p);
					rh.setRooms(r);					
					Login.setPropietario(own);
					javax.swing.JOptionPane.showMessageDialog(null,"Casa a�adida correctamente.", "Bien....",javax.swing.JOptionPane.NO_OPTION);
				} catch (Exception e) {
					javax.swing.JOptionPane.showMessageDialog(null,e.toString(),"Alguna casilla ha sido mal rellenada.",javax.swing.JOptionPane.ERROR_MESSAGE);
				}
		}
		
	}

	@Override
	public void eliminarCasaRural(int numero) {
		ObjectContainer db=DB4oManager.getContainer();
		RuralHouse proto = new RuralHouse(numero,null,null,null,0,0,0,0,0);
		ObjectSet result = db.queryByExample(proto);
		
		RuralHouse rh = (RuralHouse) result.next();
		
		DB4oManager.getContainer().delete(rh);
		Login.getPropietario().getRuralHouses().remove(rh);
		Login.setPropietario(Login.getPropietario());
		
	}
	
	public static int getNumeroCR(){
		Vector<RuralHouse> vector = new Vector<RuralHouse>();
		vector = DB4oManager.getCR();
		int max = 0;
		java.util.Iterator<RuralHouse> i = vector.iterator();
		while (i.hasNext()) {
			int aux = i.next().getHouseNumber();
			if( max < aux) max = aux;
		}
		max++;
		return max;
	}

	}

