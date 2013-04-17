package dataAccess;

import java.io.File;
import java.util.Vector;

import com.db4o.*;
import configuration.Config;
import domain.*;

public class DB4oManager { 
	private static ObjectContainer  db;
	
    public static void openDatabase(String mode){
		Config c=Config.getInstance();
		String db4oFileName=c.getDb4oFilename();
		if (mode.compareTo("open")==0) {
			db=Db4o.openFile(Db4o.newConfiguration(), db4oFileName);
			db.ext().configure().updateDepth(5);
			System.out.println("DataBase Opened");
		} else if (mode.compareTo("initialize")==0){
			new File(db4oFileName).delete();
			db=Db4o.openFile(Db4o.newConfiguration(), db4oFileName);
			db.ext().configure().updateDepth(5);
			db.commit();
			System.out.println("DataBase Initialized");
		}
	}
	
	public static void close(){
		db.close();
		System.out.println("DataBase closed");
	}
	
	public static ObjectContainer getContainer(){
		return db;
	}
	
	public static boolean comprobarEmail(String email){	
		UserAplication u = new UserAplication(email, null, null, null, null, null, null, null);
		ObjectSet<RuralHouse> userConcretos = db.queryByExample(u);	
		while (userConcretos.hasNext()) return true;
		return false;
	}
	
	public static boolean comprobarEmailAndPass(String email, String pass){	
		UserAplication u = new UserAplication(email, pass, null, null, null, null, null, null);
		ObjectSet<RuralHouse> userConcretos = db.queryByExample(u);	
		while (userConcretos.hasNext()) return true;
		return false;
	}
	
	public static UserAplication getUser(String email){
		UserAplication u = new UserAplication(email, null, null, null, null, null, null, null);
		ObjectSet<UserAplication> userConcretos = db.queryByExample(u);	
		if (userConcretos.hasNext()) return userConcretos.next();
		else return null;
	}
	
	public static UserAplication getUser(String email, String pass){
		UserAplication u = new UserAplication(email, pass, null, null, null, null, null, null);
		ObjectSet<UserAplication> userConcretos = db.queryByExample(u);	
		if (userConcretos.hasNext()) return  userConcretos.next();
		else return null;
	}	
	
	public static RuralHouse getRuralHouse(int houseNumber) throws Exception{
		RuralHouse rh = new RuralHouse(houseNumber, null,null,null,0,0,0,0,0);
		ObjectSet<RuralHouse> ruralHouseConcretos = db.queryByExample(rh);
		if (ruralHouseConcretos.hasNext()) return ruralHouseConcretos.next();
		else throw new Exception("La casa rural no existe.");
	}
	
	public static Vector<RuralHouse> getCasasRuralesTodas(){
		Vector<RuralHouse> vector = new Vector<RuralHouse>();
		RuralHouse rh = new RuralHouse(0, null,null,null,0,0,0,0,0);
		ObjectSet<RuralHouse> ruralHouseConcretos = db.queryByExample(rh);
		while (ruralHouseConcretos.hasNext())vector.add(ruralHouseConcretos.next());
		return vector;
	}
	
	public static UserAplication nuevoUsuario(String email, String pass, String estadoCivil, String nombre, String apellidos, String telefono, String pais, String edad) throws Exception {
		UserAplication u = new UserAplication(email, null, null, null, null, null, null, null);
		ObjectSet<UserAplication> userConcretos = db.queryByExample(u);	
		if (userConcretos.hasNext()) throw new Exception("El email ya esta usado. Logueate");
		UserAplication user = new UserAplication(email, pass, estadoCivil, nombre, apellidos, telefono, pais, edad);
		db.store(user);
		return user;
	}
	
	public static UserAplication modificarRuralHouse(String email, int numero, String description, String city, int nRooms, int nKitchen, int nBaths, int nLiving, int nPark) throws Exception {
		ObjectSet<RuralHouse> RHConcreto = db.queryByExample(new RuralHouse(numero, null, null, null, 0, 0, 0, 0, 0));
		if (RHConcreto.hasNext()){
			RuralHouse casa = RHConcreto.next();
			casa.setBaths(nBaths);
			casa.setCity(city);
			casa.setDescription(description);
			casa.setRooms(nRooms);
			casa.setKitchen(nKitchen);
			casa.setLiving(nLiving);
			casa.setPark(nPark);
			db.store(casa);
			return getUser(email);
		} else throw new Exception("La casa rural no se puede modificar. No se ha encontrado en la base de datos.");
	}
	
	public static UserAplication modificarOwner(UserAplication user, String email, String bA, String t, Vector<String> i, String p, String m) throws Exception{
		ObjectSet<UserAplication> userConcretos = db.queryByExample(user);	
		if (userConcretos.hasNext()){
			UserAplication userConcreto = userConcretos.next();
			userConcreto.getPropietario().setBankAccount(bA);
			userConcreto.getPropietario().setIdiomas(i);
			userConcreto.getPropietario().setMoneda(m);
			userConcreto.getPropietario().setProfesion(p);
			userConcreto.getPropietario().setTipo(t);
			db.store(userConcreto);
			return getUser(email);
		} else throw new Exception("El usuario no se ha encontrado.");
	}
}
	
