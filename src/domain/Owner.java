package domain;

import java.io.Serializable;
import java.util.Vector;

@SuppressWarnings("serial")
public class Owner implements Serializable {
	private String bankAccount = "";
	private Vector<RuralHouse> ruralHouses;
	
	public Owner(String bankAccount){
		this.bankAccount=bankAccount;
		ruralHouses=new Vector<RuralHouse>();	
	}

	
	public String getBankAccount() {
		return this.bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	
	public Vector<RuralHouse> getRuralHouses() {
		return ruralHouses;
	}
	
	public RuralHouse addRuralHouse(int houseNumber, String description, String city) {

     RuralHouse rh=new RuralHouse( houseNumber,  this,  description,  city);
	 ruralHouses.add(rh);
	 return rh;
	 
	}
}