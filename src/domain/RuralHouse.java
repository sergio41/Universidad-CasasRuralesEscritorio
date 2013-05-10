package domain;

import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import externalDataSend.EnviarCorreo;

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
	private Vector<File> vectorImage;
	
	public RuralHouse(int hNumber, UserAplication usuario, String descripcion, String ciudad, int cuartos, int cocina, int banos, int salon, int aparcamiento,  Vector<File> images) {
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
		vectorImage = images;
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
	/*public void anadirOferta(Date primerDia, Date ultimoDia, float precio, boolean obligatorio){
		Vector<Fechas> auxVectorFechas = new Vector<Fechas>();
		Fechas auxFecha = new Fechas(primerDia, precio, this, houseNumber);
		Date aux = primerDia;
		try{
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
				auxVectorFechas.add(auxFecha);
			}
			System.out.println("Vector: " + auxFecha.getFecha().toString());
			//auxVectorFechas.add(auxVectorFechas.size(),auxFecha);//add(auxFecha);
			//Iterator<Fechas> j = auxVectorFechas.iterator();
			//while(j.hasNext()){ System.out.println("Vectorjjjj: " + j.next().getFecha().toString());}
			
			aux.setTime(aux.getTime()+1*24*60*60*1000);
		}
		Iterator<Fechas> i = auxVectorFechas.iterator();
		while(i.hasNext()){
			System.out.println("Vector1: " + i.next().getFecha().toString());
		}
		vectorOfertas.add(new Offer(primerDia, ultimoDia, precio, this, auxVectorFechas, obligatorio));}
		catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}*/
	public void anadirOferta(Date primerDia, Date ultimoDia, float precio, boolean obligatorio) throws Exception{
		Vector<Fechas> auxVectorFechas = new Vector<Fechas>();
		Vector<Fechas> auxVectorFechasNuevas = new Vector<Fechas>();
		Date primero = (Date) primerDia.clone();
		Date primero1 = (Date) primerDia.clone();
		Date auxp = primerDia;
		Fechas auxFecha = new Fechas(null, 0, null, 0);
		while (auxp.compareTo(ultimoDia)<=0){
			Iterator<Fechas> i = vectorFechas.iterator();
			while (i.hasNext()){
				auxFecha = i.next();				
				if (auxp.compareTo(auxFecha.getFecha())==0) {
					vectorFechas.remove(primero);
					while (primero.compareTo(auxp)!=0){
						primero.setTime(primero.getTime()+1*24*60*60*1000);
					}
					throw new Exception ("Colision de fechas. Reintroduce las fechas de la nueva oferta.");
				}
			}
			if(!vectorFechas.contains(auxp)){
				//if (auxFecha.(null) || auxp.compareTo(auxFecha.getFecha())!=0)
				//(aux.getDay() != auxFecha.getFecha().getDay() || aux.getMonth() != auxFecha.getFecha().getMonth() || aux.getYear() != auxFecha.getFecha().getYear())){
				Object aux5 = auxp.clone();
				Date aux1 = (Date) aux5;
				auxFecha = new Fechas(aux1, 0, this, 0);
				auxVectorFechasNuevas.add(auxFecha);
				vectorFechas.add(auxFecha);
				auxVectorFechas.add(auxFecha);
			}
			auxp.setTime(auxp.getTime()+1*24*60*60*1000);
		}
		Offer oferta = new Offer(primero, ultimoDia, precio, this, auxVectorFechas, obligatorio);
		vectorOfertas.add(oferta);
		System.out.println("holaaaaaa:   " + primerDia.toString() + ultimoDia.toString());
		hacerReserva(getUserAplication(), 0, primero1, (Date) ultimoDia.clone());
		//vectorOfertas.get(0).hacerReserva(new Book(5, 1000, this.getUserAplication(), auxVectorFechas));
		
		Iterator<Fechas> i = auxVectorFechasNuevas.iterator();
		while(obligatorio && i.hasNext()){
			Fechas auxi = i.next();
			Iterator<Fechas> j = vectorFechas.iterator();
			while(j.hasNext()){
				Fechas auxj = j.next();
				if (auxj.equals(auxi)){
					auxj.setOferta(oferta, true);
				}
			}
		}
	}
	
	public Vector<Fechas> getFechas(){return vectorFechas;}
	
	@SuppressWarnings("deprecation")
	private boolean anadirFecha( Date date, float precio, int minimoDias){
		Date auxDate = new Date(date.getYear(), date.getMonth(), date.getDate());
		Fechas fecha = new Fechas(auxDate, precio, this, minimoDias);
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
	@SuppressWarnings("deprecation")
	public boolean anadirFechas( Date primerDia, Date ultimoDia,  float precio, int minimoDias){
		Date auxDate = new Date(primerDia.getYear(), primerDia.getMonth(), primerDia.getDate());
		Date auxUltimoDia = new Date(ultimoDia.getYear(), ultimoDia.getMonth(), ultimoDia.getDate());
		boolean auxB = true;
		if(auxDate.equals(auxUltimoDia))
			if (!anadirFecha(auxDate, precio, minimoDias)) auxB = false;
		while (!auxDate.equals(auxUltimoDia)){
			System.out.println("22");
			if (!anadirFecha(auxDate, precio, minimoDias)) auxB = false;
			
			auxDate.setTime(auxDate.getTime()+1*24*60*60*1000);
		}
		return auxB;
	}
	
	public Vector<File> getImages(){
		return vectorImage;
	}
	
	public void añadirImagen(File image) throws Exception {
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
		System.out.println("holccccc:   " + inicio.toString() + fin.toString());
		Iterator<Offer> i = vectorOfertas.iterator();
		while (i.hasNext()){
			Offer aux = i.next();
			System.out.println("hola:               " + aux.getPrimerDia().toString() + inicio.toString());
			System.out.println("hola:               " + aux.getUltimoDia().toString() + fin.toString());
			System.out.println("hola:               " + aux.isReservado());
			if (aux.getPrimerDia().equals(inicio) && aux.getUltimoDia().equals(fin) && !aux.isReservado()) return aux;
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
		System.out.println("holbbbbbbb:   " + inicio.toString() + fin.toString());
		System.out.println("hA");
		Offer auxOferta = disponibleFechaOferta(inicio, fin);
		Book reserva = null;
		System.out.println("hB");
		if (disponibleFechas(inicio, fin) || auxOferta != null){
			System.out.println("hC");
			Vector<Fechas> auxFechas = getFechas(inicio, fin);
			System.out.println("hD");
			if (auxOferta != null && auxFechas != null){
				System.out.println("hE");
				reserva = new Book(numeroDeReserva, auxOferta.getPrice(), cliente, auxOferta, auxFechas);
				vectorReservas.add(reserva);
				System.out.println("hF");
			} else if (auxOferta == null && auxFechas != null){
				float precio = 0;
				Iterator<Fechas> i = auxFechas.iterator();
				System.out.println("hG");
				while (i.hasNext()) precio = precio + i.next().getPrecio();
				System.out.println("hH");
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

	public void setImages(Vector<File> images) {
		vectorImage= new Vector<File>();
		for(int i =0; i<images.size();i++){
			vectorImage.add(images.get(i));
		}
	}
	
	public Vector<Fechas> eliminarTodasFechas(){
		Vector<Fechas> auxVectorFechas = new Vector<Fechas>();
		auxVectorFechas = vectorFechas;
		vectorFechas = new Vector<Fechas>();
		return auxVectorFechas;
	}

	public Vector<Offer> eliminarTodasOfertas(){
		Vector<Offer> auxVectorOffer = new Vector<Offer>();
		auxVectorOffer = vectorOfertas;
		vectorOfertas = new Vector<Offer>();
		return auxVectorOffer;
	}
	
	public Vector<Book> eliminarTodasReserva(){
		Vector<Book> auxVectorBook = new Vector<Book>();
		auxVectorBook = vectorReservas;
		for (int i = 0; i<vectorReservas.size(); i++){
			try {
				EnviarCorreo.enviarCorreos(vectorReservas.get(i).getCliente().getEmail(), "Reserva: " + vectorReservas.get(i).getNumeroDeReserva() , "Lamentablemente, su reserva ha sido cancelada debido a que el propietario de la casa rural ha eliminado ésta. En caso de haber desembolsado el pago de la reserva, se le devolverá en muy poco tiempo.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.getMessage();
			}
		}
		vectorReservas = new Vector<Book>();
		return auxVectorBook;
	}
}
