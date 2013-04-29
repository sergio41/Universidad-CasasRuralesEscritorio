package businessLogic;

import java.awt.Image;
import java.rmi.*;
import java.util.Vector;
import java.util.Date;

import javax.swing.ImageIcon;

import domain.Owner;
import domain.RuralHouse;
import domain.UserAplication;

public interface ApplicationFacadeInterface extends Remote {
	public Vector<RuralHouse> getAllRuralHouses()throws RemoteException,
	Exception;
	
	public void close() throws RemoteException;

	public void anadirRuralHouse(String description, String city, int nRooms, int nKitchen, int nBaths, int nLiving, int nPark, Vector<Image> images) throws Exception;
	
	public void modificarRuralHouse( int numero,
			String description, String city, int nRooms, int nKitchen,
			int nBaths, int nLiving, int nPark) throws Exception;

	public void eliminarCasaRural (int numero) throws Exception;
	
	public void nuevoUsuario(String email, String pass, String estadoCivil, String nombre, String apellidos, String telefono, String pais, String edad) throws Exception;
	
	public void modificarPerfil( String estadoCivil, String nombre, String apellidos, String telefono, String pais, String edad) throws Exception;
	
	public UserAplication getUsuario() throws Exception;
	
	public void hacerLogin(String email, String pass) throws Exception;
	
	public boolean estadoLogin () throws Exception;
	
	public void logout() throws Exception;
	
	public void recuperarContrasena(String email) throws Exception;
	
	public Owner getOwner() throws Exception;
	
	public void modificarOwner(String bA, String t, Vector<String> i, String p, String m) throws Exception;
	
	public void modificarContraseña(String pass) throws Exception;
	
	public Vector<String> Ultimos10Tweets() throws Exception;
	
	public void hacerReserva(int numeroRH, Date inicio, Date fin) throws Exception;
	
	public Vector<RuralHouse> casasRuralesDisponibles(Date inicio, Date fin) throws Exception;
	
	public Vector<RuralHouse> casasRuralesDisponibles(Date inicio, Date fin, String Ciudad) throws Exception;
	
	public void anadirOferta(int numero, Date inicio, Date fin, float precio, boolean obligatorio) throws Exception;
}