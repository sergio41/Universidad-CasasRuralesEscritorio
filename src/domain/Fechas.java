package domain;

import java.util.Date;

public class Fechas {

	private Date fecha;
	private RuralHouse casaRural;
	private int precio;
	private boolean reservado;
	private UserAplication reservante;
	
	public Fechas (Date date, RuralHouse RuralHouse, int cost){
		fecha = date;
		casaRural = RuralHouse;
		precio = cost;
		reservado = false;
		reservante = null;
	}
	
	public Fechas (Date date, RuralHouse RuralHouse, int cost, boolean estado, UserAplication cliente){
		fecha = date;
		casaRural = RuralHouse;
		precio = cost;
		reservado = true;
		reservante = cliente;
	}
	
	public void hacerReserva(UserAplication cliente){
		reservado = true;
		reservante = cliente;
	}
	
	public Date getFecha() {
		return fecha;
	}

	public RuralHouse getCasaRural() {
		return casaRural;
	}

	public int getPrecio() {
		return precio;
	}

	public void cambiarPrecio(int cost){
		precio = cost;
	}

	public boolean isReservado() {
		return reservado;
	}

	public UserAplication getCliente() {
		return reservante;
	}
	
}
