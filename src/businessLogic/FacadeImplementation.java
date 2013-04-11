package businessLogic;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Calendar;
import java.util.Date;
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
	BookManager theBookMngr;
	
 

	public FacadeImplementation() throws RemoteException, InstantiationException,
			IllegalAccessException, ClassNotFoundException, SQLException {
		Config c=Config.getInstance();
		String dataBaseOpenMode=c.getDataBaseOpenMode();
		DB4oManager.openDatabase(dataBaseOpenMode);
		theBookMngr = BookManager.getInstance();
		//dbMngr = DB4oManager.getInstance();
	}

	/**
	 * This method obtains owner rural houses 
	 * 
	 * @param owner object
	 *            
	 * @return a vector of Rural Houses
	 */
	public Vector<RuralHouse> getRuralHouses(Owner owner)
			throws RemoteException {
		
		return owner.getRuralHouses();
		
	}

	

	/**
	 * This method creates an offer with a house number, first day, last day and price
	 * 
	 * @param House
	 *            number, start day, last day and price
	 * @return None
	 */
	public Offer createOffer(RuralHouse ruralHouse, Date firstDay, Date lastDay,
			float price) throws RemoteException, Exception {
		ObjectContainer db=DB4oManager.getContainer();
		RuralHouse proto = new RuralHouse(ruralHouse.getHouseNumber(),null,ruralHouse.getDescription(),ruralHouse.getCity(), 
				ruralHouse.getRooms(), ruralHouse.getKitchen(), ruralHouse.getBaths(), ruralHouse.getLiving(), ruralHouse.getPark());
		 ObjectSet result = db.queryByExample(proto);
		 RuralHouse rh=(RuralHouse)result.next();
		Offer o=rh.createOffer(firstDay, lastDay, price);
		db.store(o);
		db.commit(); 
		return o;
	}

	/**
	 * This method creates a book with a corresponding parameters
	 * 
	 * @param First
	 *            day, last day, house number and telephone
	 * @return a book
	 */
	public Book createBook(RuralHouse ruralHouse, Date firstDate, Date lastDate, String bookTelephoneNumber)
			throws OfferCanNotBeBooked {

		
		try {
			ObjectContainer db=DB4oManager.getContainer();
			RuralHouse proto = new RuralHouse(ruralHouse.getHouseNumber(),null,ruralHouse.getDescription(),ruralHouse.getCity(), 
					ruralHouse.getRooms(), ruralHouse.getKitchen(), ruralHouse.getBaths(), ruralHouse.getLiving(), ruralHouse.getPark());
			 ObjectSet result = db.queryByExample(proto);
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

	
	/**
	 * This method obtains available offers for a concrete house in a certain period 
	 * 
	 * @param houseNumber, the house number where the offers must be obtained 
	 * @param firstDay, first day in a period range 
	 * @param lastDay, last day in a period range
	 * @return a vector of offers(Offer class)  available  in this period
	 */
	public Vector<Offer> getOffers(RuralHouse house,Date firstDay, Date lastDay) throws RemoteException,
			Exception {
		
		return house.getOffers(firstDay, lastDay);

	}
	/**
	 * This method existing  owners 
	 * 
	 */
	public Vector<Owner> getOwners() throws RemoteException,
			Exception {
		ObjectContainer db=DB4oManager.getContainer();

		 try {
			 Owner proto = new Owner(null, null, null, null, null);
			 ObjectSet result = db.queryByExample(proto);
			 Vector<Owner> owners=new Vector<Owner>();
			 while(result.hasNext())				 
				 owners.add((Owner)result.next());
			 return owners;
	     } finally {
	         //db.close();
	     }
	} 
	public Vector<RuralHouse> getAllRuralHouses() throws RemoteException,
	Exception {
		ObjectContainer db=DB4oManager.getContainer();

		 try {
			 RuralHouse proto = new RuralHouse(0,null,null,null,0,0,0,0,0);
			 ObjectSet result = db.queryByExample(proto);
			 Vector<RuralHouse> ruralHouses=new Vector<RuralHouse>();
			 while(result.hasNext()) 
				 ruralHouses.add((RuralHouse)result.next());
			 return ruralHouses;
	     } finally {
	         //db.close();
	     }
	}
	public void close() throws RemoteException{
		DB4oManager.close();

	}

	@Override
	public void anadirRuralHouse(String description, String city,
			String nRooms, String nKitchen, String nBaths, String nLiving,
			String nPark) throws Exception {
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
				RuralHouse rh = new RuralHouse(getNumeroCR(), usuario,
						description, city, r, k, b, l, p);
				usuario.getPropietario().addRuralHouse(rh);
				Login.setPropietario(usuario.getPropietario());
				javax.swing.JOptionPane.showMessageDialog(null,"Casa a�adida correctamente.", "Bien....",javax.swing.JOptionPane.NO_OPTION);
			} catch (Exception e) {
				javax.swing.JOptionPane.showMessageDialog(null,e.toString(),"Alguna casilla ha sido mal rellenada.",javax.swing.JOptionPane.ERROR_MESSAGE);
			}
	}
		
	}

	@Override
	public void modficarRuralHouse(int numero, String description,
			String city, String nRooms, String nKitchen, String nBaths,
			String nLiving, String nPark) throws Exception {
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
					//Owner own =Login.getPropietario();
					

					
					ObjectContainer db=DB4oManager.getContainer();
					RuralHouse proto = new RuralHouse(numero,null,null,null,0,0,0,0,0);
					ObjectSet result = db.queryByExample(proto);
					
					RuralHouse rh = (RuralHouse) result.next();
					rh.setBaths(b);
					rh.setCity(city);
					rh.setDescription(description);
					rh.setKitchen(k);
					rh.setLiving(l);
					rh.setPark(p);
					rh.setRooms(r);					
					Login.setPropietario(usuario.getPropietario());
					javax.swing.JOptionPane.showMessageDialog(null,"Casa a�adida correctamente.", "Bien....",javax.swing.JOptionPane.NO_OPTION);
				} catch (Exception e) {
					javax.swing.JOptionPane.showMessageDialog(null,e.toString(),"Alguna casilla ha sido mal rellenada.",javax.swing.JOptionPane.ERROR_MESSAGE);
				}
		}
		
	}

	public void eliminarCasaRural(int numero) {
		ObjectContainer db=DB4oManager.getContainer();
		RuralHouse proto = new RuralHouse(numero,null,null,null,0,0,0,0,0);
		ObjectSet result = db.queryByExample(proto);
		
		RuralHouse rh = (RuralHouse) result.next();
		
		DB4oManager.getContainer().delete(rh);
		usuario.getPropietario().getRuralHouses().remove(rh);
		Login.setPropietario(usuario.getPropietario());
		
	}
	
	private int getNumeroCR(){
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


	public Vector<RuralHouse> getRuralHouses() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}


	public void nuevoUsuario(String email, String pass, String estadoCivil,
			String nombre, String apellidos, String telefono, String pais,
			String edad) throws Exception {
		if (email.compareTo("")==0 || pass.compareTo("")==0 || nombre.compareTo("")==0 || pais.compareTo("")==0 || estadoCivil.compareTo("")==0) throw new Exception("Algunos datos obligatorios faltan.");
		else {
			if (edad.compareTo("")==0) edad = null;
			if (apellidos.compareTo("")==0) apellidos = null;
			if (telefono.compareTo("")==0) telefono = null;
			if (DB4oManager.comprobarEmail(email)) throw new Exception("Email ya usado. Logueate");
			else{
				EnviarCorreo.enviarCorreos(email, "Registro en Villatripas de Arriba", "Te has registrado en villatripas de arribacon el email " + email);
				GestionTwitter.enviarTweet("Bienvenid@: " + nombre + " " + Calendar.getInstance().getTime().toString());
				DB4oManager.storeUser(new UserAplication(email, pass, estadoCivil, nombre, apellidos, telefono, pais, edad));
				hacerLogin(email, pass);
			}
		}
	}


	public void modificarPerfil(String email, String pass, String estadoCivil,
			String nombre, String apellidos, String telefono, String pais,
			String edad) throws Exception {
		if (email.compareTo("")==0 ||  nombre.compareTo("")==0 || pais.compareTo("")==0 || estadoCivil.compareTo("")==0) throw new Exception("Algunos datos obligatorios faltan.");
		else {
			//DB4oManager.deleteUser(usuario);
			usuario.setEstadoCivil(estadoCivil);
			usuario.setName(nombre);
			usuario.setApellidos(apellidos);
			usuario.setTelefono(telefono);
			usuario.setPais(pais);
			usuario.setEdad(edad);
			if(pass.compareTo("")!=0) usuario.setPass(pass);	
			DB4oManager.storeUser(usuario);
		}
	}


	public boolean hacerLogin(String email, String pass) throws Exception {
		if (email.compareTo("")==0 || pass.compareTo("")==0) throw new Exception("Algunos datos obligatorios faltan.");
		usuario = DB4oManager.getUser(email, pass);
		if (usuario == null) return false;
		else {
			estado = true;
			return true;
		}
	}


	public void logout() throws Exception {
		if (!estado) throw new Exception("No estas logueado.");
		estado = false;
		usuario = null;
	}


	public void recuperarContrasena(String email) throws Exception {
		UserAplication user = DB4oManager.getUser(email, null);
		try {
			EnviarCorreo.enviarCorreos(user.getEmail(), "Contraseña", "Tu contraseña es " + user.getPass());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.toString());
		}
	}

	public boolean estadoLogin() throws Exception {
		return estado;
	}

	@Override
	public UserAplication getUsuario() throws Exception {
		return usuario;
	}

	public Owner getOwner() throws Exception {
		return usuario.getPropietario();
	}

	@Override
	public void modificarOwner(String bA, String t, Vector<String> i, String p,
			String m) throws Exception {

		
	}
	
}

