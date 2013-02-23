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
		RuralHouse proto = new RuralHouse(ruralHouse.getHouseNumber(),null,ruralHouse.getDescription(),ruralHouse.getCity());
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
			RuralHouse proto = new RuralHouse(ruralHouse.getHouseNumber(),null,ruralHouse.getDescription(),ruralHouse.getCity());
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
			 Owner proto = new Owner(null);
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
			 RuralHouse proto = new RuralHouse(0,null,null,null);
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

	}

