package businessLogic;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.sql.SQLException;

import java.util.Vector;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;


import configuration.Config;

import dataAccess.DB4oManager;
import domain.Book;
import domain.Offer;
import domain.Owner;
import domain.RuralHouse;
import domain.UserAplication;


import exceptions.OfferCanNotBeBooked;
import externalDataSend.EnviarCorreo;
import externalDataSend.GestionTwitter;


public class FacadeImplementation extends UnicastRemoteObject implements ApplicationFacadeInterface {
	private static final long serialVersionUID = 1L;
	private static boolean estado = false; //SinLogin = 0; //Login = 1
	private static UserAplication usuario;
	private static String emailUser;
	private static Vector<String> twitter10;
	BookManager theBookMngr;
	
 

	public FacadeImplementation() throws RemoteException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Config c=Config.getInstance();
		String dataBaseOpenMode=c.getDataBaseOpenMode();
		DB4oManager.openDatabase(dataBaseOpenMode);
		theBookMngr = BookManager.getInstance();
		//dbMngr = DB4oManager.getInstance();
	}

	/**
	 * This method creates an offer with a house number, first day, last day and price
	 * 
	 * @param House
	 *            number, start day, last day and price
	 * @return None
	 */
	/*public Offer createOffer(RuralHouse ruralHouse, Date firstDay, Date lastDay,
			float price) throws RemoteException, Exception {
		ObjectContainer db=DB4oManager.getContainer();
		RuralHouse proto = new RuralHouse(ruralHouse.getHouseNumber(),null,ruralHouse.getDescription(),ruralHouse.getCity(), 
				ruralHouse.getRooms(), ruralHouse.getKitchen(), ruralHouse.getBaths(), ruralHouse.getLiving(), ruralHouse.getPark());
		 ObjectSet<RuralHouse> result = db.queryByExample(proto);
		 RuralHouse rh=(RuralHouse)result.next();
		Offer o=rh.createOffer(firstDay, lastDay, price);
		db.store(o);
		db.commit(); 
		return null;
	}*/

	/**
	 * This method creates a book with a corresponding parameters
	 * 
	 * @param First
	 *            day, last day, house number and telephone
	 * @return a book
	 */
	/*public Book createBook(RuralHouse ruralHouse, Date firstDate, Date lastDate, String bookTelephoneNumber) throws OfferCanNotBeBooked {

		
		try {
			ObjectContainer db=DB4oManager.getContainer();
			RuralHouse proto = new RuralHouse(ruralHouse.getHouseNumber(),null,ruralHouse.getDescription(),ruralHouse.getCity(), 
					ruralHouse.getRooms(), ruralHouse.getKitchen(), ruralHouse.getBaths(), ruralHouse.getLiving(), ruralHouse.getPark());
			 ObjectSet<RuralHouse> result = db.queryByExample(proto);
			 RuralHouse rh=(RuralHouse)result.next();
			Book b=null;
			Offer offer;
			offer = rh.findOffer(firstDate, lastDate);

			if (offer!=null) {
				 b=new Book(bookTelephoneNumber,offer);
				offer.setBook(b);
				db.store(b);
				db.store(offer);
				db.commit();
			}
			return b;
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}
	}

	
	

	//Desde aqui esta bien.
	
	public boolean colisionOfertas(RuralHouse rh, Date firstDay, Date lastDay){
		Iterator<Offer> i = rh.getOffers(firstDay, lastDay).iterator();
		while (i.hasNext()){
			Offer of = i.next();
			if (of.getRuralHouse().equals(rh) && of.getFirstDay().compareTo(firstDay)>=0 && of.getLastDay().compareTo(lastDay)<=0);
				return true;
		}
		return false;
	}
	

	//Desde aqui esta bien.
	
	public Vector<Offer> getOffers(RuralHouse house,Date firstDay, Date lastDay) throws RemoteException, Exception {
		return house.getOffers(firstDay, lastDay);
	}*/
	
	public void eliminarCasaRural(int numero) throws Exception {
		usuario = DB4oManager.eliminarCasaRural(usuario, numero);
	}
	
	public void anadirRuralHouse(String description, String city, int nRooms, int nKitchen, int nBaths, int nLiving, int nPark) throws Exception {
	if (city.compareTo("") == 0) throw new Exception("Algunos datos obligatorios faltan.");
	else {			
			if (nRooms<3) throw new Exception("La casa debe tener mínimo 3 habitaciones.");
			if (nKitchen<1) throw new Exception("La casa debe tener mínimo 1 cocina.");
			if (nBaths<2) throw new Exception("La casa debe tener mínimo 2 baños.");
			DB4oManager.anadirRuralHouse(usuario, getNumeroCR(), description, city, nRooms, nKitchen, nBaths, nLiving, nPark);
		}
	}
	
	public void modificarContraseña(String pass) throws Exception {
		usuario = DB4oManager.modificarContrasena(usuario, pass);
	}
	
	public Vector<RuralHouse> getAllRuralHouses() throws RemoteException, Exception {
		return DB4oManager.getCasasRuralesTodas();
	}
	
	public void close() throws RemoteException{
		DB4oManager.close();
	}
	
	public void modificarOwner(String bA, String t, Vector<String> i, String p,	String m) throws Exception {
		if(usuario.getPropietario()!=null)
			usuario= DB4oManager.modificarOwner(usuario, usuario.getEmail(), bA, t, i, p, m);
		else 
			usuario= DB4oManager.nuevoOwner(usuario, usuario.getEmail(), bA, t, i, p, m);
	}
	
	public void modificarRuralHouse(int numero, String description, String city, int nRooms, int nKitchen, int nBaths, int nLiving, int nPark) throws Exception {
		if (city.compareTo("") == 0) throw new Exception("Algunos datos obligatorios faltan.");
		else {					
			if (nRooms<3) throw new Exception("La casa debe tener mínimo 3 habitaciones.");
			if (nKitchen<1) throw new Exception("La casa debe tener mínimo 1 cocina.");
			if (nBaths<2) throw new Exception("La casa debe tener mínimo 2 baños.");
			DB4oManager.modificarRuralHouse(emailUser, numero, description, city, nRooms, nKitchen, nBaths, nLiving, nPark);
		}
	}
	
	public void nuevoUsuario(String email, String pass, String estadoCivil, String nombre, String apellidos, String telefono, String pais, String edad) throws Exception {
		if (email.compareTo("")==0 || pass.compareTo("")==0 || nombre.compareTo("")==0 || pais.compareTo("")==0 || estadoCivil.compareTo("")==0) throw new Exception("Algunos datos obligatorios faltan.");
		else {
			if (edad.compareTo("")==0) edad = null;
			if (apellidos.compareTo("")==0) apellidos = null;
			if (telefono.compareTo("")==0) telefono = null;
			usuario = DB4oManager.nuevoUsuario(email, pass, estadoCivil, nombre, apellidos, telefono, pais, edad);
			try {
				EnviarCorreo.enviarCorreos(email, "Registro en Villatripas de Arriba", "Te has registrado en villatripas de arriba con el email: " + email);
				GestionTwitter.enviarTweet("Bienvenid@: " + nombre + " " + Calendar.getInstance().getTime().toString());
			} catch (Exception e) {
				throw new Exception("Error al enviar el email de registro o envio de tweet automatico.");
			}
		}
	}	
	
	public UserAplication getUsuario() throws Exception {
		return usuario;
	}

	public Owner getOwner() throws Exception {
		return usuario.getPropietario();
	}	
	
	public boolean estadoLogin() throws Exception {
		return estado;
	}
	
	public void recuperarContrasena(String email) throws Exception {
		UserAplication user = DB4oManager.getUser(email);
		if (user == null) throw new Exception("El email no existe.");
		else {
			try {
				EnviarCorreo.enviarCorreos(user.getEmail(), "Contraseña", "Tu contraseña es " + user.getPass());
			} catch (Exception e) {
				throw new Exception("Error al enviar el email de recuperacion de contraseña. Intentelo mas tarde.");
			}
		}
	}
	
	public void hacerLogin(String email, String pass) throws Exception {
		if (email.compareTo("")==0 || pass.compareTo("")==0) throw new Exception("Algunos datos obligatorios faltan.");
		usuario = DB4oManager.getUser(email, pass);
		if (usuario == null){
			throw new Exception("No se ha podido hacer Login");
		} else {
			estado = true;
			emailUser = email;
		}
	}
	
	public void logout() throws Exception {
		if (!estado) throw new Exception("No estas logueado.");
		estado = false;
		usuario = null;
		emailUser = null;
	}


	public void modificarPerfil(String estadoCivil, String nombre,String apellidos, String telefono, String pais, String edad) throws Exception {
		if (nombre.compareTo("")==0 || pais.compareTo("")==0 || estadoCivil.compareTo("")==0) throw new Exception("Algunos datos obligatorios faltan.");
		else usuario= DB4oManager.modificarUsuario(usuario, estadoCivil, nombre, apellidos, telefono, pais, edad);		
	}
	
	private int getNumeroCR(){
		Vector<RuralHouse> vector = DB4oManager.getCasasRuralesTodas();
		int max = 0;
		java.util.Iterator<RuralHouse> i = vector.iterator();
		while (i.hasNext()) {
			int aux = i.next().getHouseNumber();
			if( max < aux) max = aux;
		}
		max++;
		return max;
	}

	public Vector<String> Ultimos10Tweets() throws Exception {
		try {
			Iterator<String> i = GestionTwitter.getTodosTweets().iterator();
			twitter10 = new Vector<String>();
			int x = 0;
			while (i.hasNext()){
				twitter10.add(i.next());
				x++;
				if(x==10) break;
			}
			return twitter10;
		} catch (Exception e) {
			System.out.println("error");
			if (twitter10 == null)throw new Exception();			
			return twitter10;
		}		
	}
	
	public void setPrecios(Date inicio, Date fin){
		
		
	}

	@Override
	public Offer createOffer(RuralHouse ruralHouse, Date firstDay,
			Date lastDay, float price) throws RemoteException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book createBook(RuralHouse ruralHouse, Date firstDay, Date lastDay,
			String telephoneNumber) throws RemoteException, OfferCanNotBeBooked {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<Offer> getOffers(RuralHouse houseNumber, Date firstDay,
			Date lastDay) throws RemoteException, Exception {
		// TODO Auto-generated method stub
		return null;
	}
}

