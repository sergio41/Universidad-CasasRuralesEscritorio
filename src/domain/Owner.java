package domain;

import java.io.Serializable;
import java.util.Vector;

@SuppressWarnings("serial")
public class Owner implements Serializable {
	private String bankAccount = "";
	private String tipo;
	private Vector<String> idiomas;
	private String profesion;
	private String moneda;
	private Vector<RuralHouse> ruralHouses;
	
	public Owner(String bA, String t, Vector<String> i, String p, String m){
		bankAccount=bA;
		tipo=t;
		idiomas=i;
		profesion=p;
		moneda=m;
		ruralHouses=new Vector<RuralHouse>();	
	}

	
	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bA) {
		bankAccount = bA;
	}
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String t) {
		tipo = t;
	}
	public Vector<String> getIdiomas() {
		return idiomas;
	}

	public void actualizarIdiomas(Vector<String> i) {
		idiomas = i;
	}

	public String getProfesion() {
		return profesion;
	}


	public void setProfesion(String p) {
		profesion = p;
	}


	public String getMoneda() {
		return moneda;
	}


	public void setMoneda(String m) {
		moneda = m;
	}
	
	
	public Vector<RuralHouse> getRuralHouses() {
		return ruralHouses;
	}
	
	public RuralHouse addRuralHouse(int h, String d, String c, int r, int k, int b, int l, int p) {

     RuralHouse rh=new RuralHouse(h, this, d, c, r, k, b, l, p);
	 ruralHouses.add(rh);
	 return rh;
	 
	}
}