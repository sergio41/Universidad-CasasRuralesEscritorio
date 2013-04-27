package domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
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
	private Vector<Fechas> vectorFechas;
	private Vector<Offer> vectorOfertas;
	private Vector<Book> vectorReservas;

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
		vectorFechas = new Vector<Fechas>();
		vectorOfertas = new Vector<Offer>();
		vectorReservas = new Vector<Book>();
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
	
	public Vector<Book> getReservas(){return vectorReservas;}
	
	public Vector<Offer> getOfertas(){ return vectorOfertas;}
	public void añadirOferta(Date primerDia, Date ultimoDia, float precio, boolean obligatorio){
		Vector<Fechas> auxVectorFechas = new Vector<Fechas>();
		Date aux = primerDia;
		Date auxFin = ultimoDia;
		aux.setTime(auxFin.getTime()+1*24*60*60*1000);
		while (aux.compareTo(auxFin) !=0 ){
			Fechas auxFecha = null;
			Iterator<Fechas> i = vectorFechas.iterator();
			while (i.hasNext()){
				auxFecha = i.next(); 
				if (auxFecha.getFecha().compareTo(aux) == 0) break; 
			}
			if (auxFecha == null || auxFecha.getFecha().compareTo(aux) != 0){
				auxFecha = new Fechas(aux, 0, this, 0);
				vectorFechas.add(auxFecha);
			}
			auxVectorFechas.add(auxFecha);
			aux.setTime(aux.getTime()+1*24*60*60*1000);
		}
		vectorOfertas.add(new Offer(primerDia, ultimoDia, precio, this, auxVectorFechas, obligatorio));
	}
	
	public Vector<Fechas> getFechas(){return vectorFechas;}
	public boolean anadirFecha( Date date, float precio, int minimoDias){
		Fechas fecha = new Fechas(date, precio, this, minimoDias);
		Iterator<Fechas> i = vectorFechas.iterator();
		while (i.hasNext()){
			Fechas aux = i.next();
			if (aux.getFecha().compareTo(date)==0){
				if (aux.isReservado()) return false;
				aux.setPrecio(precio);
				aux.setMinimodias(minimoDias);
				return true;
			}
		}
		vectorFechas.add(fecha);
		return true;
	}
	
	private boolean disponibleFecha(Date date){
		Iterator<Fechas> i = vectorFechas.iterator();
		while (i.hasNext()){
			Fechas aux = i.next();
			if (aux.getFecha().compareTo(date)==0 && !aux.isReservado()) return true;
			else if (aux.getFecha().compareTo(date)==0 && aux.isReservado()) return false;
		}
		return false;
	}
	
	public boolean disponibleFechas(Date inicio, Date fin){
		Date aux = inicio;
		while (aux.compareTo(fin) !=0 && disponibleFecha(aux)) aux.setTime(aux.getTime()+1*24*60*60*1000);
		if (aux.compareTo(fin) ==0 && disponibleFecha(aux)) return true;
		return disponibleFechaOferta(inicio, fin);
	}
	
	private boolean disponibleFechaOferta(Date inicio, Date fin){
		Iterator<Offer> i = vectorOfertas.iterator();
		while (i.hasNext()){
			Offer aux = i.next();
			if (aux.getPrimerDia().compareTo(inicio) == 0 && aux.getUltimoDia().compareTo(fin) == 0) return true;
		}
		return false;
	}
	
	private Offer ofertaContieneTalDia( Date fecha){
		
	}
	public boolean hacerReserva(UserAplication cliente, int numeroDeReserva, Date inicio, Date fin){
		if (!disponibleFechas(inicio, fin)) return false;
		Date aux = inicio;
		aux.setTime(aux.getTime()+1*24*60*60*1000);
		while (aux.compareTo(fin) !=0 ){
			hacerReservaFecha(cliente, numeroDeReserva, aux);
			aux.setTime(aux.getTime()+1*24*60*60*1000);
		}
		if (aux.compareTo(fin) ==0 && disponibleFecha(aux)) hacerReservaFecha(cliente, numeroDeReserva, aux);
		return true;
	}
	
	private void hacerReservaFecha(UserAplication cliente ,int numeroDeReserva, Date date){
		Iterator<Fechas> i = vectorFechas.iterator();
		while (i.hasNext()){
			Fechas aux = i.next();
			if (aux.getFecha().compareTo(date)==0){
				//aux.hacerReserva(cliente, numeroDeReserva);
				break;
			}
		}
	}
}
