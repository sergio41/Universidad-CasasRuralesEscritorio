package domain;

import java.io.Serializable;
import java.util.Vector;

public class RuralHouse implements Serializable {
	private static final long serialVersionUID = 1L;

	private int houseNumber;
	private String description;
	private UserAplication user;
	private String city; 
	private int nRooms;
	private int nKitchen;
	private int nBaths;
	private int nLiving;
	private int nPark;
	private Vector<Fechas> fechas;
	public Vector<Offer> offers;

	/*public RuralHouse() {
		super();
	}*/

	public RuralHouse(int hNumber, UserAplication usuario, String descripcion, String ciudad, int cuartos, int cocina, int banos, int salon, int aparcamiento) {
		houseNumber = hNumber;
		description = descripcion;
		user = usuario;
		city = ciudad;
		nRooms = cuartos;
		nKitchen = cocina;
		nBaths = banos;
		nLiving = salon;
		nPark = aparcamiento;
		fechas = new Vector<Fechas>();
		//offers=new Vector<Offer>();
	}

	public int getHouseNumber() {return houseNumber;}
	public void setHouseNumber(int h) {houseNumber = h;}

	public String getDescription() {return description;}	
	public void setDescription(String d) {description=d;}

	public UserAplication getUserAplication() {return user;}
	public void setUser(UserAplication o) {user =o;}
	
	public String getCity() {return city;}	
	public void setCity(String c) {city=c;}

	public int getRooms() {return nRooms;}
	public void setRooms(int r) {nRooms = r;}

	public int getKitchen() {return nKitchen;}
	public void setKitchen(int k) {nKitchen = k;}
	
	public int getBaths() {return nBaths;}
	public void setBaths(int b) {nBaths = b;}
	
	public int getLiving() {return nLiving;}
	public void setLiving(int l) {nLiving = l;}
	
	public int getPark() {return nPark;}
	public void setPark(int p) {nPark = p;}
	
	public String toString() {return this.houseNumber + ": " + this.city;}
	
	/**
	 * This method creates an offer with a house number, first day, last day and price
	 * 
	 * @param House
	 *            number, start day, last day and price
	 * @return None
	 */
	/*public Offer createOffer(Date firstDay, Date lastDay, float price)  {
        Offer off=new Offer(this,firstDay,lastDay,price);
        offers.add(off);
        return off;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + houseNumber;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RuralHouse other = (RuralHouse) obj;
		if (houseNumber != other.houseNumber)
			return false;
		return true;
	}
	
	*/
	/**
	 * This method obtains the account number of the owner of the  house number
	 * 
	 * @param houseNumber
	 *            Number of the house
	 * @return Owner account number of the house
	 */
	
	/*public String getAccountNumber(int houseNumber) {
		try {
			dbMngr=DBManager.getInstance();
			return dbMngr.getOwner(houseNumber).getBankAccount();

		} catch (Exception e) {
			System.out.println("Error, accessing to DB Manager: "
					+ e.toString());
			return null;
		} return null;
	}
	*/
	/**
	 * This method obtains available offers for a concrete house in a certain period 
	 * 
	 * @param houseNumber, the house number where the offers must be obtained 
	 * @param firstDay, first day in a period range 
	 * @param lastDay, last day in a period range
	 * @return a vector of offers(Offer class)  available  in this period
	 */
	/*public Vector<Offer> getOffers( Date firstDay,  Date lastDay) {
		
		Vector<Offer> availableOffers=new Vector<Offer>();
		Iterator<Offer> e=offers.iterator();
		Offer offer;
		while (e.hasNext()){
			offer=e.next();
			if ( (offer.getFirstDay().compareTo(firstDay)>=0) && (offer.getLastDay().compareTo(lastDay)<=0) && (offer.getBook()==null) )
				availableOffers.add(offer);
		}
		return availableOffers;
		
	}
	*/
	/**
	 * This method obtains the offer that match exactly with a given dates that has not been booked
	 * 
	 * @param firstDay, first day in a period range 
	 * @param lastDay, last day in a period range
	 * @return the  offer(Offer class)  available  for a this period
	 */
	/*public Offer findOffer( Date firstDay,  Date lastDay) {
		
		Iterator<Offer> e=offers.iterator();
		Offer offer=null;
		while (e.hasNext()){
			offer=e.next();
			if ( (offer.getFirstDay().compareTo(firstDay)==0) && (offer.getLastDay().compareTo(lastDay)==0) && (offer.getBook()==null) )
				return offer;
		}
		return null;
		
	}

	public Offer addOffer(Offer o) {offers.add(o); return o;}*/
}
