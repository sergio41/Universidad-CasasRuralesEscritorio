package businessLogic;

import java.util.Vector;

import javax.swing.text.html.HTMLDocument.Iterator;

import dataAccess.DB4oManager;
import domain.Owner;
import domain.RuralHouse;
import businessLogic.Login;

public class AddRuralHouse {
	private AddRuralHouse() {}

	public static void gestionRuralHouse( RuralHouse rh,
			String description, String city, String nRooms, String nKitchen,
			String nBaths, String nLiving, String nPark) throws Exception {
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
					if (rh == null){
						rh = new RuralHouse(getNumeroCR(), Login.getPropietario(),
							description, city, r, k, b, l, p);
						own.addRuralHouse(rh);
					} else {
						rh.setBaths(b);
						rh.setCity(city);
						rh.setDescription(description);
						rh.setKitchen(k);
						rh.setLiving(l);
						rh.setPark(p);
						rh.setRooms(r);					
					}
					Login.setPropietario(own);
					javax.swing.JOptionPane.showMessageDialog(null,"Casa a�adida correctamente.", "Bien....",javax.swing.JOptionPane.NO_OPTION);
				} catch (Exception e) {
					javax.swing.JOptionPane.showMessageDialog(null,e.toString(),"Alguna casilla ha sido mal rellenada.",javax.swing.JOptionPane.ERROR_MESSAGE);
				}
		}
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
	
	
	// Metodos Datos

	/*public int getHouseNumber() {
		return ruralHouse.getHouseNumber();
	}

	public void setHouseNumber(int houseNumber) {
		ruralHouse.setHouseNumber(houseNumber);
	}

	public String getDescription() {
		return ruralHouse.getDescription();
	}

	public void setDescription(String description) {
		ruralHouse.setDescription(description);
	}

	public Owner getOwner() {
		return ruralHouse.getOwner();
	}

	public void setOwner(Owner owner) {
		ruralHouse.setOwner(owner);
	}

	public String getCity() {
		return ruralHouse.getCity();
	}

	public void setCity(String city) {
		ruralHouse.setCity(city);
	}

	public int getRooms() {
		return ruralHouse.getRooms();
	}

	public void setRooms(int r) {
		ruralHouse.setRooms(r);
	}

	public int getKitchen() {
		return ruralHouse.getKitchen();
	}

	public void setKitchen(int k) {
		ruralHouse.setKitchen(k);
	}

	public int getBaths() {
		return ruralHouse.getBaths();
	}

	public void setBaths(int b) {
		ruralHouse.setBaths(b);
	}

	public int getLiving() {
		return ruralHouse.getLiving();
	}

	public void setLiving(int l) {
		ruralHouse.setLiving(l);
	}

	public int getPark() {
		return ruralHouse.getPark();
	}

	public void setPark(int p) {
		ruralHouse.setPark(p);
	}*/
}
