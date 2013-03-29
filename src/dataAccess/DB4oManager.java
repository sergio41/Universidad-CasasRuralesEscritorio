package dataAccess;

import java.io.File;
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
			//Owner jon = new Owner("Jon", "Jonlog", "passJon");
			//Owner alfredo = new Owner("Alfredo","AlfredoLog", "passAlfredo");
		    //jon.addRuralHouse(1, "Ezkioko etxea","Ezkio");
			//jon.addRuralHouse(2, "Eskiatzeko etxea","Jaca");
			//jon.setBankAccount("12345677");
			//db.store(jon);
			//db.store(alfredo);
			//
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
	
	public static void storeRuralHouse(RuralHouse rh){
		db.store(rh);
	}
}
	
