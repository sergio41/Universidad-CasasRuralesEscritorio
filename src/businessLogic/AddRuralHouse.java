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
						rh = new RuralHouse(getNumeroCR(), Login.getUser(),
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

	public static void eliminarCasaRural (RuralHouse rh){
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
