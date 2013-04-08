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
		
	public  static ObjectContainer getContainer(){
	  return db;
    }
	
	public static void close(){
		db.close();
		System.out.println("DataBase closed");
	}
	
	public static boolean comprobarEmail(String email){	
		UserAplication u = new UserAplication(email, null, null, null, null, null, null, null);
		ObjectSet userConcretos = db.queryByExample(u);	
		while (userConcretos.hasNext()) return true;
		return false;
	}
	
	public static boolean comprobarEmailAndPass(String email, String pass){	
		UserAplication u = new UserAplication(email, pass, null, null, null, null, null, null);
		ObjectSet userConcretos = db.queryByExample(u);	
		while (userConcretos.hasNext()) return true;
		return false;
	}
	
	public static UserAplication getUser(String email, String pass){
		UserAplication u = new UserAplication(email, pass, null, null, null, null, null, null);
		ObjectSet userConcretos = db.queryByExample(u);	
		while (userConcretos.hasNext()) return (UserAplication) userConcretos.next();
		return null;
	}
	
	public static void storeUser(UserAplication uA){
		db.store(uA);
	}
	
	public static RuralHouse getRuralHouse(int houseNumber){
		RuralHouse rh = new RuralHouse(houseNumber, null,null,null,0,0,0,0,0);
		ObjectSet ruralHouseConcretos = db.queryByExample(rh);
		while (ruralHouseConcretos.hasNext()) return (RuralHouse) ruralHouseConcretos.next();
		return null;
	}

	public static Vector<RuralHouse> getCR(){
		Vector<RuralHouse> vector = new Vector<RuralHouse>();
		RuralHouse rh = new RuralHouse(0, null,null,null,0,0,0,0,0);
		ObjectSet ruralHouseConcretos = db.queryByExample(rh);
		while (ruralHouseConcretos.hasNext()){
			vector.add( (RuralHouse) ruralHouseConcretos.next());
		}
		return vector;
	}
	public static void storeRuralHouse(RuralHouse rh){
		db.store(rh);
	}

	public static void deleteUser(UserAplication usuario) {
		db.delete(usuario);
		
	}
}
	
