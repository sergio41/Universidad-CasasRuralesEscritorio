package businessLogic;
import java.awt.Image;
import java.rmi.*;
import java.util.Vector;
import java.util.Date;

import domain.*;

public interface ApplicationFacadeInterface extends Remote {
	public Vector<RuralHouse> getAllRuralHouses()throws RemoteException, Exception;
	
	//public void close() throws RemoteException;

	public void anadirRuralHouse(UserAplication usuario, String description, String city, int nRooms, int nKitchen, int nBaths, int nLiving, int nPark, Vector<Image> imagenes) throws Exception;
	
	public void modificarRuralHouse(UserAplication usuario,  int numero,
			String description, String city, int nRooms, int nKitchen,
			int nBaths, int nLiving, int nPark, Vector<Image> imagenes) throws Exception;

	public void eliminarCasaRural (UserAplication usuario, int numero) throws Exception;
	
	public Vector<RuralHouse>  getCasas( String ciudad,int Banos,int Habita,int Cocina,int Estar,int Park) throws Exception;
	
	public void nuevoUsuario( String email, String pass, String estadoCivil, String nombre, String apellidos, String telefono, String pais, String edad, Image perfil) throws Exception;
	
	public void modificarPerfil(UserAplication usuario, String estadoCivil, String nombre, String apellidos, String telefono, String pais, String edad, Image perfil) throws Exception;
	
	public Image getFotoPerfil( String email) throws Exception;
	
	public UserAplication getUsuario(UserAplication usuario) throws Exception;
	
	public UserAplication hacerLogin(String email, String pass) throws Exception;
	

	
	public void recuperarContrasena( String email) throws Exception;
	
	public Owner getOwner(UserAplication usuario) throws Exception;
	
	public void modificarOwner(UserAplication usuario, String bA, String t, Vector<String> i, String p, String m) throws Exception;
	
	public void modificarContraseņa(UserAplication usuario, String pass) throws Exception;
	
	public int getNumeroCR() throws Exception;
	
	public Vector<String> Ultimos10Tweets() throws Exception;
	
	public Book hacerReserva(UserAplication usuario, int numeroRH, Date inicio, Date fin) throws Exception;
	
	public Vector<RuralHouse> casasRuralesDisponibles( Date inicio, Date fin) throws Exception;
	
	public Vector<RuralHouse> casasRuralesDisponibles(Date inicio, Date fin, String Ciudad) throws Exception;
	
	public void anadirOferta(UserAplication usuario, int numero, Date inicio, Date fin, float precio, boolean obligatorio) throws Exception;

	public Vector<Offer> getOfertas(UserAplication usuario, int numeroRH) throws Exception;
	
	public RuralHouse getCasas(int num) throws Exception;
	
	public Vector<Image> getFotosRH( int numeroDeCasa) throws Exception;

	public Vector<RuralHouse> casasRuralesDisponibles( Date ini, Date fin,
			String city, int banos, int habita, int cocina, int estar, int park)throws Exception;
	
	public void anadirFechas (UserAplication usuario,  int numero, Date inicio, Date fin, float precio, int minimoDeDias) throws Exception; 

	public Vector<Fechas> getFechas(UserAplication usuario, int numeroRH) throws Exception;

	public void eliminarOferta(UserAplication usuario, int parseInt, Date ini, Date fin) throws Exception;

	public void eliminarReserva(int num) throws Exception;

	public void eliminarFecha(UserAplication usuario, int parseInt, Date ini) throws Exception;

	public Book pagar(int num, UserAplication user) throws Exception;
	
	public void anadirCalificacionACasaRural(int numero, String comentario, int puntuacion) throws Exception;

	public Book getReserva(int num) throws Exception;

	public void cambiarContra(UserAplication usuario, String text)throws Exception;
}