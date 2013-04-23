package domain;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

public class Disponibilidad {
	//Date, CasaRural, disponibilidad
	private Hashtable<Date, Hashtable<Integer, Boolean>> dispo;
	//CasaRural, Date, Precio
	private Hashtable<Integer, Hashtable<Date, Integer>> dispo1;
	
	public Disponibilidad(){
		dispo = new Hashtable<Date, Hashtable<Integer, Boolean>>();
		dispo1 = new Hashtable<Integer, Hashtable<Date, Integer>>();
	}
	
	public Vector<Integer> disponibi(Date inicio, Date fin){
		Vector<Integer> v = new Vector<Integer>();
		if (dispo.containsKey(inicio)){
			Enumeration<Integer> e = dispo.get(inicio).keys();
			
			Date aux = inicio;
			while(aux.compareTo(fin) != 0){
				
			}
		}
		
		return v;
	}
}
