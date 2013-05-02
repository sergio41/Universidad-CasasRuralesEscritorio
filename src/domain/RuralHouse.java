package domain;

import java.awt.Image;
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
	private Vector<Image> vectorImage;
	
	public RuralHouse(int hNumber, UserAplication usuario, String descripcion, String ciudad, int cuartos, int cocina, int banos, int salon, int aparcamiento,  Vector<Image> images) {
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
		System.out.print("estoy");
		vectorImage = images;
		System.out.print("estoy");
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
	@SuppressWarnings("deprecation")
	public void anadirOferta(Date primerDia, Date ultimoDia, float precio, boolean obligatorio){
		Vector<Fechas> auxVectorFechas = new Vector<Fechas>();
		Date aux = primerDia;
		while (aux.getDay() != ultimoDia.getDay()+1 || aux.getMonth() != ultimoDia.getMonth() || aux.getYear() != ultimoDia.getYear() ){			
			Fechas auxFecha = new Fechas(null, 0, null, 0);
			Iterator<Fechas> i = vectorFechas.iterator();
			while (i.hasNext()){
				auxFecha = i.next();
				if (aux.getDay() == auxFecha.getFecha().getDay() && aux.getMonth() == auxFecha.getFecha().getMonth() && aux.getYear() == auxFecha.getFecha().getYear() ) break;
			}
			if (auxFecha == null || (aux.getDay() != auxFecha.getFecha().getDay() || aux.getMonth() != auxFecha.getFecha().getMonth() || aux.getYear() != auxFecha.getFecha().getYear())){
				Date aux1 = aux;
				auxFecha = new Fechas(aux1, 0, this, 0);
				vectorFechas.add(auxFecha);
			}
			System.out.println("Vector: " + auxFecha.getFecha().toString());
			auxVectorFechas.add(auxVectorFechas.size(),auxFecha);//add(auxFecha);
			Iterator<Fechas> j = auxVectorFechas.iterator();
			while(j.hasNext()){ System.out.println("Vectorjjjj: " + j.next().getFecha().toString());}
			
			aux.setTime(aux.getTime()+1*24*60*60*1000);
		}
		Iterator<Fechas> i = auxVectorFechas.iterator();
		while(i.hasNext()){
			System.out.println("Vector1: " + i.next().getFecha().toString());
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
	
	public Vector<Image> getImages(){
		return vectorImage;
	}
	
	public void añadirImagen(Image image) throws Exception {
			vectorImage.add(image);
	}
	
	
	
	private boolean disponibleFecha(Date date){
		Iterator<Fechas> i = vectorFechas.iterator();
		while (i.hasNext()){
			Fechas aux = i.next();
			if (aux.getFecha().compareTo(date)==0 && !aux.isReservado() && !aux.isUnidoOferta()) return true;
			else if (aux.getFecha().compareTo(date)==0 && (aux.isReservado() || aux.isUnidoOferta())) return false;
		}
		return false;
	}
	
	public boolean disponibleFechas(Date inicio, Date fin){
		Date aux = inicio;
		while (aux.compareTo(fin) !=0 && disponibleFecha(aux)) aux.setTime(aux.getTime()+1*24*60*60*1000);
		if (aux.compareTo(fin) ==0 && disponibleFecha(aux)) return true;
		return (disponibleFechaOferta(inicio, fin) != null);
	}
	
	private Offer disponibleFechaOferta(Date inicio, Date fin){
		Iterator<Offer> i = vectorOfertas.iterator();
		while (i.hasNext()){
			Offer aux = i.next();
			if (aux.getPrimerDia().compareTo(inicio) == 0 && aux.getUltimoDia().compareTo(fin) == 0 && !aux.isReservado()) return aux;
		}
		return null;
	}
	
	private Fechas getFechaConcreta(Date date){
		Iterator<Fechas> i = vectorFechas.iterator();
		while (i.hasNext()){
			Fechas aux = i.next();
			if (aux.getFecha().compareTo(date)==0 && !aux.isReservado()) return aux;
		}
		return null;
	}
	
	private Vector<Fechas> getFechas(Date inicio, Date fin){
		Vector<Fechas> aux = new Vector<Fechas>();
		Date auxInicio = inicio;
		auxInicio.setTime(auxInicio.getTime()+1*24*60*60*1000);
		Date auxFin = fin;
		auxFin.setTime(auxFin.getTime()+1*24*60*60*1000);
		while (auxInicio.compareTo(auxFin) !=0){
			Fechas auxFecha = getFechaConcreta(auxInicio);
			if (auxFecha == null) break;
			aux.add(auxFecha);
			auxInicio.setTime(auxInicio.getTime()+1*24*60*60*1000);
		}
		if(auxInicio.compareTo(auxFin) !=0 || aux.isEmpty()) return null;
		return aux;
	}
	
	public Book hacerReserva(UserAplication cliente, int numeroDeReserva, Date inicio, Date fin){
		System.out.println("A");
		Offer auxOferta = disponibleFechaOferta(inicio, fin);
		Book reserva = null;
		System.out.println("B");
		if (disponibleFechas(inicio, fin) || auxOferta != null){
			System.out.println("C");
			Vector<Fechas> auxFechas = getFechas(inicio, fin);
			System.out.println("D");
			if (auxOferta != null && auxFechas != null){
				System.out.println("E");
				reserva = new Book(numeroDeReserva, auxOferta.getPrice(), cliente, auxOferta, auxFechas);
				
				vectorReservas.add(reserva);
				System.out.println("F");
			} else if (auxOferta == null && auxFechas != null){
				float precio = 0;
				Iterator<Fechas> i = auxFechas.iterator();
				System.out.println("G");
				while (i.hasNext()) precio = precio + i.next().getPrecio();
				System.out.println("H");
				reserva = new Book(numeroDeReserva, precio, cliente, auxFechas);
				vectorReservas.add(reserva);
				eliminarOfertaQueContenga(vectorFechas);
			}
		} 
		return reserva;		
	}
	
	private void eliminarOfertaQueContenga(Vector<Fechas> vectorFe){
		Iterator<Fechas> i = vectorFe.iterator();
		while (i.hasNext()) eliminarOfertaQueContenga(i.next().getFecha());
	}
	
	private void eliminarOfertaQueContenga(Date fecha){
		Iterator<Offer> i = vectorOfertas.iterator();
		while (i.hasNext()){
			Offer aux = i.next();
			if (aux.contiene(fecha)) vectorOfertas.remove(aux);
		}
	}

	public void setImages(Vector<Image> images) {
		vectorImage= images;
	}

}
