package domain;

import java.io.*;
//import java.util.Vector;
import java.util.Date;

import businessLogic.OfferManager;

import com.db4o.ObjectContainer;


import dataAccess.DB4oManager;

@SuppressWarnings("serial")
public class Offer implements Serializable {
	

	private int offerNumber;
	private Date firstDay;
	private Date lastDay;
	private float price;
	private Book book;
	private RuralHouse ruralHouse;

	
	public Offer(RuralHouse rh, Date fd, Date ld, float p){
		  firstDay=fd;
		  lastDay=ld;
		  price=p;
		  ruralHouse=rh;
		  offerNumber=OfferManager.getNumber();
	}
	/**
	 * Get the house number of the offer
	 * 
	 * @return the house number
	 */
	public RuralHouse getRuralHouse() {
		return ruralHouse;
	}

	/**
	 * Set the house number to a offer
	 * 
	 * @param house number
	 */
	public void setRuralHouse(RuralHouse rh) {
		ruralHouse = rh;
	}


	/**
	 * Get the offer number
	 * 
	 * @return offer number
	 */
	public int getOfferNumber() {
		return offerNumber;
	}

	

	/**
	 * Get the first day of the offer
	 * 
	 * @return the first day
	 */
	public Date getFirstDay() {
		return firstDay;
	}

	/**
	 * Set the first day of the offer
	 * 
	 * @param firstDay
	 *            The first day
	 */
	public void setFirstDay(Date fd) {
		firstDay = fd;
	}

	/**
	 * Get the last day of the offer
	 * 
	 * @return the last day
	 */
	public Date getLastDay() {
		return lastDay;
	}

	/**
	 * Set the last day of the offer
	 * 
	 * @param lastDay
	 *            The last day
	 */
	public void setLastDay(Date ld) {
		lastDay = ld;
	}

	/**
	 * Get the price
	 * 
	 * @return price
	 */
	public float getPrice() {
		return price;
	}

	/**
	 * Set the price
	 * 
	 * @param price
	 */
	public void setPrice(float p) {
		price = p;
	}

	/**
	 * Get the book number
	 * 
	 * @return book object
	 */
	public Book getBook() {
		return book;
	}

	/**
	 * Set the book object
	 * 
	 * @param book
	 *            Book object
	 * @return None
	 */
	public void setBook(Book b) {
		book = b;
	}
	
	/**
	 * This method creates a book with a corresponding parameters
	 * 
	 * @param First
	 *            day, last day, house number and telephone
	 * @return a book
	 */
	public Book createBook(java.sql.Date firstDate, java.sql.Date lastDate,String bookTelephoneNumber) {

		ObjectContainer db=DB4oManager.getContainer();

		Book b=new Book(bookTelephoneNumber, this);
		db.store(this);
		book=b;
		return book;
		
			
	}
}