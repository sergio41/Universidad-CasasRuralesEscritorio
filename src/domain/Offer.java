package domain;

import java.io.*;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

public class Offer implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Date firstDay;
	private Date lastDay;
	private boolean reservado;
	private float price;
	private Book reserva;
	private RuralHouse ruralHouse;
	private Vector<Fechas> vectorFechas;
	private boolean unidoOferta;

	public Offer(Date primerDia, Date ultimoDia, float cost, RuralHouse casaRural, Vector<Fechas> fechas, boolean unidoAFecha){
		firstDay = primerDia;
		lastDay = ultimoDia;
		reservado = false;
		price = cost;
		reserva = null;
		ruralHouse = casaRural;
		vectorFechas = fechas;
		unidoOferta = unidoAFecha;
		setUnidoAFechaExternamente();
	}
	
	public Offer(Date primerDia, Date ultimoDia, boolean estado,  float cost, RuralHouse casaRural, Vector<Fechas> fechas, boolean unidoAFecha){
		firstDay = primerDia;
		lastDay = ultimoDia;
		reservado = estado;
		price = cost;
		reserva = null;
		ruralHouse = casaRural;
		vectorFechas = fechas;
		unidoOferta = unidoAFecha;
		setUnidoAFechaExternamente();
	}
	
	private void setUnidoAFechaExternamente(){
		if(vectorFechas != null){
			Iterator<Fechas> i = vectorFechas.iterator();
			while (i.hasNext()) i.next().setOferta(this, unidoOferta);
		}
		
	}
	
	public Date getPrimerDia(){return firstDay;}
	public Date getUltimoDia(){return lastDay;}
	
	public boolean isReservado(){return reservado;}
	
	public float getPrice(){return price;}
	public void setPrice(float precio){ price = precio;}
	
	public Book getReserva(){return reserva;}
	
	public RuralHouse getRuralHouse() {return ruralHouse;}

	public Vector<Fechas> getFechas(){return vectorFechas;}
	
	public boolean isUnidoAFechas(){return unidoOferta;}
	public void setUnidoAFechas(boolean unidoAfecha){unidoOferta = unidoAfecha;}
	
	protected void hacerReserva(Book reservaBook){
		reserva = reservaBook;
		reservado = true;
	}
	
	public boolean contiene(Date fecha){
		Iterator<Fechas> i = vectorFechas.iterator();
		while (i.hasNext()) if (i.next().getFecha().compareTo(fecha) == 0) return true;
		return false;
	}
}