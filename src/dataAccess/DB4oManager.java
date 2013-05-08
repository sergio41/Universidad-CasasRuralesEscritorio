package dataAccess;
import java.io.File;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;
import com.db4o.*;
import com.db4o.query.Query;

import configuration.Config;
import domain.*;
import externalDataSend.EnviarCorreo;

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
		RuralHouse rh = new RuralHouse(houseNumber, null,null,null,0,0,0,0,0,null);
		ObjectSet<RuralHouse> ruralHouseConcretos = db.queryByExample(rh);
		if (ruralHouseConcretos.hasNext()) return ruralHouseConcretos.next();
		else throw new Exception("La casa rural no existe.");
	}
	
	public static Vector<RuralHouse> getCasasRuralesTodas(){
		Vector<RuralHouse> vector = new Vector<RuralHouse>();
		RuralHouse rh = new RuralHouse(0, null,null,null,0,0,0,0,0, null);
		ObjectSet<RuralHouse> ruralHouseConcretos = db.queryByExample(rh);
		while (ruralHouseConcretos.hasNext())vector.add(ruralHouseConcretos.next());
		return vector;
	}
	
	public static UserAplication nuevoUsuario(String email, String pass, String estadoCivil, String nombre, String apellidos, String telefono, String pais, String edad, String perfil) throws Exception {
		UserAplication u = new UserAplication(email, null, null, null, null, null, null, null);
		ObjectSet<UserAplication> userConcretos = db.queryByExample(u);	
		if (userConcretos.hasNext()) throw new Exception("El email ya esta usado. Logueate");
		UserAplication user = new UserAplication(email, pass, estadoCivil, nombre, apellidos, telefono, pais, edad);
		user.setPerfil(perfil);
		db.store(user);
		db.commit();
		return user;
	}
	
	public static UserAplication modificarRuralHouse(String email, int numero, String description, String city, int nRooms, int nKitchen, int nBaths, int nLiving, int nPark, Vector<File> images) throws Exception {
		ObjectSet<RuralHouse> RHConcreto = db.queryByExample(new RuralHouse(numero, null, null, null, 0, 0, 0, 0, 0, null));
		if (RHConcreto.hasNext()){
			RuralHouse casa = RHConcreto.next();
			casa.setBaths(nBaths);
			casa.setCity(city);
			casa.setDescription(description);
			casa.setRooms(nRooms);
			casa.setKitchen(nKitchen);
			casa.setLiving(nLiving);
			casa.setPark(nPark);
			casa.setImages(images);
			db.store(casa);
			db.commit();
			return getUser(email);
		} else throw new Exception("La casa rural no se puede modificar. No se ha encontrado en la base de datos.");
	}
	
	public static UserAplication modificarUsuario(UserAplication user, String estadoCivil, String nombre, String apellidos, String telefono, String pais, String edad, String perfil) throws Exception {
		UserAplication u = new UserAplication(user.getEmail(), null, null, null, null, null, null, null);
		ObjectSet<UserAplication> userConcretos = db.queryByExample(u);	
		if (userConcretos.hasNext()){ 
			UserAplication use = userConcretos.next();
			use.setEstadoCivil(estadoCivil);
			use.setName(nombre);
			use.setApellidos(apellidos);
			use.setTelefono(telefono);
			use.setPais(pais);
			use.setEdad(edad);
			use.setPerfil(perfil);
			db.store(user);
			db.commit();
			return user;
		}else throw new Exception("El usuario no existe");
	}

	public static UserAplication nuevoOwner(UserAplication user, String email, String bA, String t, Vector<String> i, String p, String m) throws Exception{
		Owner owner = new Owner(bA, t, i, p, m);
		ObjectSet<UserAplication> userConcretos = db.queryByExample(user);	
		if (userConcretos.hasNext()){
			UserAplication userConcreto = userConcretos.next();
			if(userConcreto.getPropietario()==null){
			userConcreto.setPropietario(owner);
			db.store(userConcreto);
			db.commit();
			return getUser(email);
			}else throw new Exception("El usuario ya es propietario");				
		} else throw new Exception("El usuario no se ha encontrado.");
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
			db.commit();
			return getUser(email);
		} else throw new Exception("El usuario no se ha encontrado.");
	}
	
	public static UserAplication modificarContrasena(UserAplication user, String pass) throws Exception {
		ObjectSet<UserAplication> userConcretos = db.queryByExample(user);	
		if (userConcretos.hasNext()){
			UserAplication userConcreto = userConcretos.next();
			userConcreto.setPass(pass);
			db.store(userConcreto);
			db.commit();
			return getUser(user.getEmail());
		} else throw new Exception("El usuario no se ha encontrado.");
	}
	
	public static UserAplication anadirRuralHouse(UserAplication user,int numero, String description, String city, int nRooms, int nKitchen, int nBaths, int nLiving, int nPark, Vector<File> images) throws Exception{
		ObjectSet<UserAplication> userConcretos = db.queryByExample(user);	
		if (userConcretos.hasNext()){
			UserAplication userConcreto = userConcretos.next();
			userConcreto.getPropietario().addRuralHouse(new RuralHouse(numero, user, description, city, nRooms, nKitchen, nBaths, nLiving, nPark, images));
			db.store(userConcreto);
			db.commit();
			return getUser(user.getEmail());
		} else throw new Exception("El usuario no se ha encontrado.");
	}
	
	public static UserAplication eliminarCasaRural(UserAplication user, int numero) throws Exception {
		ObjectSet<UserAplication> userConcretos = db.queryByExample(user);	
		if (userConcretos.hasNext()){
			UserAplication userConcreto = userConcretos.next();
			Iterator<RuralHouse> i = userConcreto.getPropietario().getRuralHouses().iterator();
			while (i.hasNext()){
				RuralHouse casa = i.next();
				if(casa.getHouseNumber() == numero){
					if(casa.getOfertas().size()==0){
						if(casa.getFechas().size()==0){
							if(casa.getReservas().size()==0){
								System.out.println("Caso simple");
							}else{
								throw new Exception("La casa rural no ha podido ser eliminada.");}
						}else{
							if(casa.getReservas().size()==0){
								System.out.println("Hay fechas.");
								ObjectSet<Fechas> fechasConcretas = db.queryByExample(Fechas.class);
								Iterator<Fechas> j = fechasConcretas.iterator();
								while (j.hasNext()){
									Fechas fecha = j.next();
									if(fecha.getCasaRural().getHouseNumber()==numero){
										casa.getFechas().remove(fecha);
										db.delete(fecha);
										db.commit();
									}
								}
							}else{
								System.out.println("Hay fechas y reservas.");
								ObjectSet<Fechas> fechasConcretas = db.queryByExample(Fechas.class);
								Iterator<Fechas> j = fechasConcretas.iterator();
								while (j.hasNext()){
									Fechas fecha = j.next();
									if(fecha.getCasaRural().getHouseNumber()==numero){
										EnviarCorreo.enviarCorreos(fecha.getReserva().getCliente().getEmail(), "Su reserva", "Lamentablemente, su reserva ha sido cancelada debido a que el propietario de la casa rural ha eliminado �sta. En caso de haber desembolsado el pago de la reserva, se le devolver� en muy poco tiempo.");
										ObjectSet<Book> reservaConcretas = db.queryByExample(Book.class);
										Iterator<Book> k = reservaConcretas.iterator();
										while (k.hasNext()){
											Book reserva = k.next();
											if(reserva.getNumeroDeReserva()==fecha.getReserva().getNumeroDeReserva()){
												casa.getReservas().remove(reserva);
												db.delete(reserva);
												db.commit();
											}
										}
										casa.getFechas().remove(fecha);
										db.delete(fecha);
										db.commit();
									}
								}
							}
						}
					}else{
						if(casa.getFechas().size()==0){
							if(casa.getReservas().size()==0){
								//no va a entrar nunca pero por si acaso.
								System.out.println("Hay ofertas.");
								ObjectSet<Offer> ofertasConcretas = db.queryByExample(Offer.class);
								Iterator<Offer> j = ofertasConcretas.iterator();
								while (j.hasNext()){
									Offer offer = j.next();
									if(offer.getRuralHouse().getHouseNumber()==numero){
										casa.getOfertas().remove(Offer.class);
										db.delete(offer);
										db.commit();
									}
								}
							}else{
								System.out.println("Hay ofertas y reservas.");
								ObjectSet<Offer> ofertasConcretas = db.queryByExample(Offer.class);
								Iterator<Offer> j = ofertasConcretas.iterator();
								while (j.hasNext()){
									Offer offer = j.next();
									if(offer.getRuralHouse().getHouseNumber()==numero){
										EnviarCorreo.enviarCorreos(offer.getReserva().getCliente().getEmail(), "Su reserva", "Lamentablemente, su reserva ha sido cancelada debido a que el propietario de la casa rural ha eliminado �sta. En caso de haber desembolsado el pago de la reserva, se le devolver� en muy poco tiempo.");
										ObjectSet<Book> reservaConcretas = db.queryByExample(Book.class);
										Iterator<Book> k = reservaConcretas.iterator();
										while (k.hasNext()){
											Book reserva = k.next();
											if(reserva.getNumeroDeReserva()==offer.getReserva().getNumeroDeReserva()){
												casa.getReservas().remove(reserva);
												db.delete(reserva);
												db.commit();
											}
										}
										casa.getOfertas().remove(offer);
										db.delete(offer);
										db.commit();
									}
								}
							}
						}else{
							if(casa.getReservas().size()==0){
								System.out.println("Hay ofertas y fechas.");
								ObjectSet<Offer> ofertasConcretas = db.queryByExample(Offer.class);
								Iterator<Offer> j = ofertasConcretas.iterator();
								while (j.hasNext()){
									Offer offer = j.next();
									if(offer.getRuralHouse().getHouseNumber()==numero){
										casa.getOfertas().remove(offer);
										db.delete(offer);
										db.commit();
									}
								}
								ObjectSet<Fechas> fechasConcretas = db.queryByExample(Fechas.class);
								Iterator<Fechas> k = fechasConcretas.iterator();
								while (k.hasNext()){
									Fechas fecha = k.next();
									if(fecha.getCasaRural().getHouseNumber()==numero){
										casa.getFechas().remove(fecha);
										db.delete(fecha);
										db.commit();
									}
								}
							}else{
								System.out.println("Hay ofertas, fechas y reservas.");
								ObjectSet<Fechas> fechasConcretas = db.queryByExample(Fechas.class);
								Iterator<Fechas> j = fechasConcretas.iterator();
								while (j.hasNext()){
									Fechas fecha = j.next();
									if(fecha.getCasaRural().getHouseNumber()==numero){
										EnviarCorreo.enviarCorreos(fecha.getReserva().getCliente().getEmail(), "Su reserva", "Lamentablemente, su reserva ha sido cancelada debido a que el propietario de la casa rural ha eliminado �sta. En caso de haber desembolsado el pago de la reserva, se le devolver� en muy poco tiempo.");
										ObjectSet<Book> reservaConcretas = db.queryByExample(user);
										Iterator<Book> k = reservaConcretas.iterator();
										while (k.hasNext()){
											Book reserva = k.next();
											if(reserva.getNumeroDeReserva()==fecha.getReserva().getNumeroDeReserva()){
												casa.getReservas().remove(reserva);
												db.delete(reserva);
												db.commit();
											}
										}
										casa.getFechas().remove(fecha);
										db.delete(fecha);
										db.commit();
									}
								}
								ObjectSet<Offer> ofertasConcretas = db.queryByExample(Offer.class);
								Iterator<Offer> l = ofertasConcretas.iterator();
								while (l.hasNext()){
									Offer offer = l.next();
									if(offer.getRuralHouse().getHouseNumber()==numero){
										EnviarCorreo.enviarCorreos(offer.getReserva().getCliente().getEmail(), "Su reserva", "Lamentablemente, su reserva ha sido cancelada debido a que el propietario de la casa rural ha eliminado �sta. En caso de haber desembolsado el pago de la reserva, se le devolver� en muy poco tiempo.");
										ObjectSet<Book> reservaConcretas = db.queryByExample(user);
										Iterator<Book> m = reservaConcretas.iterator();
										while (m.hasNext()){
											Book reserva = m.next();
											if(reserva.getNumeroDeReserva()==offer.getReserva().getNumeroDeReserva()){
												casa.getReservas().remove(reserva);
												db.delete(reserva);
												db.commit();
											}
										}
										casa.getOfertas().remove(offer);
										db.delete(offer);
										db.commit();
									}
								}
							}
						}
						
					}
					userConcreto.getPropietario().getRuralHouses().remove(casa);
					db.store(userConcreto);
					db.delete(casa);
					db.commit();
					return getUser(user.getEmail());
				}
			}
			throw new Exception("La casa rural no ha podido ser eliminada.");
		} else throw new Exception("El usuario no se ha encontrado.");
	}
	

	public static Vector<RuralHouse> getHouse(String ciudad, int banos,
			int habita, int cocina, int estar, int park) {
		Vector<RuralHouse> result = new Vector<RuralHouse>();
		ObjectSet<RuralHouse> casasConcretas = db.queryByExample(new RuralHouse(0, null, null, ciudad, habita, cocina, banos, estar, park, null));	
		while (casasConcretas.hasNext())
			result.add(casasConcretas.next());
		return result;	
	}	
	
	public static Vector<RuralHouse> casasRuralesDisponibles(Date inicio, Date fin){
		Vector<RuralHouse> result = new Vector<RuralHouse>();
		ObjectSet<Fechas> fechasConcretas = db.queryByExample(new Fechas(inicio, 0, false, null, 0));	
		while (fechasConcretas.hasNext()){
			RuralHouse rh = fechasConcretas.next().getCasaRural();
			if (rh.disponibleFechas(inicio, fin)) result.add(rh);
		}
		return result;		
	}
	
	public static Vector<RuralHouse> casasRuralesDisponibles(Date inicio, Date fin, String Ciudad){
		Vector<RuralHouse> result = new Vector<RuralHouse>();
		ObjectSet<Fechas> fechasConcretas = db.queryByExample(new Fechas(inicio, 0, false, null, 0));	
		while (fechasConcretas.hasNext()){
			RuralHouse rh = fechasConcretas.next().getCasaRural();
			if (rh.getCity().equalsIgnoreCase(Ciudad) && rh.disponibleFechas(inicio, fin)) result.add(rh);
		}
		return result;	
	}
	
	public static Vector<RuralHouse> casasRuralesDisponibles(Date inicio, Date fin, String ciudad, int banos, int habita, int cocina, int sala, int park){
		Vector<RuralHouse> result = new Vector<RuralHouse>();
		Fechas fechas= new Fechas(inicio, 0, false, null, 0, true);
		ObjectSet<Fechas> fechasConcretas = db.queryByExample(fechas);	
		while (fechasConcretas.hasNext()){
			RuralHouse rh = fechasConcretas.next().getCasaRural();
			if(ciudad==null)
				ciudad=rh.getCity();
			if(banos==-1)
				banos=rh.getBaths();
			if(habita==-1)
				habita=rh.getRooms();
			if(cocina==-1)
				cocina=rh.getKitchen();
			if(sala==-1)
				sala=rh.getLiving();
			if(park==-1)
				park=rh.getPark();
			if (rh.getCity().equalsIgnoreCase(ciudad) && 
					rh.getBaths()==banos && 
					rh.getRooms()==habita && 
					rh.getKitchen()==cocina && 
					rh.getLiving()==sala && 
					rh.getPark()==park && rh.disponibleFechas(inicio, fin)){ 
						result.add(rh);
					}
			}
		return result;	
	}
	
	public static UserAplication hacerReserva(UserAplication user, int numero, Date inicio, Date fin) throws Exception{
		ObjectSet<UserAplication> userConcretos = db.queryByExample(user);	
		if (userConcretos.hasNext()){
			UserAplication userConcreto = userConcretos.next();
			ObjectSet<RuralHouse> rHConcretos = db.queryByExample(new RuralHouse(numero, null, null, null, 0, 0, 0, 0, 0, null));	
			if (rHConcretos.hasNext()){
				RuralHouse rHConcreto = rHConcretos.next();
				rHConcreto.hacerReserva(userConcreto, 0, inicio, fin);
				db.store(rHConcreto);
				db.store(userConcreto);
				db.commit();
				return getUser(user.getEmail());
			}
		}
		throw new Exception("La reserva no se ha podido realizar. Lo sentimos");
	}
	
	public static UserAplication anadirOferta(UserAplication user, int numero, Date inicio, Date fin, float precio, boolean obligatorio) throws Exception{
		ObjectSet<UserAplication> userConcretos = db.queryByExample(user);	
		if (userConcretos.hasNext()){
			UserAplication userConcreto = userConcretos.next();
			System.out.println("numero: " + numero);
			ObjectSet<RuralHouse> rHConcretos = db.queryByExample(new RuralHouse(numero, null, null, null, 0, 0, 0, 0, 0, null));	
			if (rHConcretos.hasNext() ){
				RuralHouse rHConcreto = rHConcretos.next();
				System.out.println("Inicio: " + inicio.toString());
				System.out.println("Fin: " + fin.toString());
				rHConcreto.anadirOferta(inicio, fin, precio, obligatorio);
				db.store(rHConcreto);
				db.store(userConcreto);
				db.commit();
				return getUser(user.getEmail());
			}
		}
		throw new Exception("La oferta no se ha podido a�adir correctamente. Lo sentimos");
	}
	
	public static UserAplication anadirFechas(UserAplication user, int numero, Date inicio, Date fin, float precio, int minimoDeDias) throws Exception{
		ObjectSet<UserAplication> userConcretos = db.queryByExample(user);	
		if (userConcretos.hasNext()){
			UserAplication userConcreto = userConcretos.next();
			System.out.println("numero: " + numero);
			ObjectSet<RuralHouse> rHConcretos = db.queryByExample(new RuralHouse(numero, null, null, null, 0, 0, 0, 0, 0, null));	
			if (rHConcretos.hasNext() ){
				RuralHouse rHConcreto = rHConcretos.next();
				System.out.println("Inicio: " + inicio.toString());
				System.out.println("Fin: " + fin.toString());
				if (!rHConcreto.anadirFechas(inicio, fin, precio, minimoDeDias)) throw new Exception("La oferta no se ha podido a�adir correctamente. Lo sentimos");
				db.store(rHConcreto);
				db.store(userConcreto);
				db.commit();
				return getUser(user.getEmail());
			}
		}
		throw new Exception("La oferta no se ha podido a�adir correctamente. Lo sentimos");
	}

}
	
