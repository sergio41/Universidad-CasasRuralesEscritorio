package businessLogic;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.sql.SQLException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import configuration.Config;
import dataAccess.DB4oManager;
import domain.Book;
import domain.Fechas;
import domain.Offer;
import domain.Owner;
import domain.RuralHouse;
import domain.UserAplication;
import externalDataSend.EnviarCorreo;
import externalDataSend.GestionTwitter;


public class FacadeImplementation extends UnicastRemoteObject implements ApplicationFacadeInterface {
	private static final long serialVersionUID = 1L;
	//private static boolean estado = false; //SinLogin = 0; //Login = 1
	//private static UserAplication usuario;
	//private static String emailUser;
	private static Vector<String> twitter10;
	
	public FacadeImplementation() throws RemoteException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Config c=Config.getInstance();
		String dataBaseOpenMode=c.getDataBaseOpenMode();
		DB4oManager.openDatabase(dataBaseOpenMode);
	}
	
	public void eliminarCasaRural(UserAplication usuario, int numero) throws Exception {
		DB4oManager.eliminarCasaRural(usuario, numero);
		File carpet = new File("\\imagenes\\"+usuario.getEmail()+"\\"+numero);
		if(!eliminarCarpetaConfotos(carpet))
			System.out.println("No se han eliminado guay las fotos");
	}
	
	public boolean eliminarCarpetaConfotos(File directory) {
		// System.out.println("removeDirectory " + directory);
		if (directory == null)
			return false;
		if (!directory.exists())
			return true;
		if (!directory.isDirectory())
			return false;
		String[] list = directory.list();
		if (list != null) {
			for (int i = 0; i < list.length; i++) {
				File entry = new File(directory, list[i]);
				if (entry.isDirectory()) {
					if (!eliminarCarpetaConfotos(entry))
						return false;
				} else {
					if (!entry.delete())
						return false;
				}
			}
		}

		return directory.delete();
	}
		

	public void anadirRuralHouse(UserAplication usuario, String description, String city, int nRooms, int nKitchen, int nBaths, int nLiving, int nPark, Vector<Image> imagenes) throws Exception{
	if (city.compareTo("") == 0) throw new Exception("Algunos datos obligatorios faltan.");
	else {			
			if (nRooms<3) throw new Exception("La casa debe tener m�nimo 3 habitaciones.");
			if (nKitchen<1) throw new Exception("La casa debe tener m�nimo 1 cocina.");
			if (nBaths<2) throw new Exception("La casa debe tener m�nimo 2 ba�os.");
			int numero = getNumeroCR();
			DB4oManager.anadirRuralHouse(usuario, numero, description, city, nRooms, nKitchen, nBaths, nLiving, nPark, setGuardarImagenes(usuario.getEmail(), imagenes, numero));
		}
	}
	
	public void modificarContrase�a(UserAplication usuario, String pass) throws Exception {
		DB4oManager.modificarContrasena(usuario, pass);
	}
	
	public Vector<RuralHouse> getAllRuralHouses() throws RemoteException, Exception {
		return DB4oManager.getCasasRuralesTodas();
	}
	
	public void close() throws RemoteException{
		DB4oManager.close();
	}
	
	public void modificarOwner(UserAplication usuario, String bA, String t, Vector<String> i, String p,	String m) throws Exception {
		if(usuario.getPropietario()!=null)
			usuario= DB4oManager.modificarOwner(usuario, usuario.getEmail(), bA, t, i, p, m);
		else 
			usuario= DB4oManager.nuevoOwner(usuario, usuario.getEmail(), bA, t, i, p, m);
	}
	
	public void modificarRuralHouse(UserAplication usuario, int numero, String description, String city, int nRooms, int nKitchen, int nBaths, int nLiving, int nPark, Vector<Image> imagenes) throws Exception {
		if (city.compareTo("") == 0) throw new Exception("Algunos datos obligatorios faltan.");
		else {					
			if (nRooms<3) throw new Exception("La casa debe tener m�nimo 3 habitaciones.");
			if (nKitchen<1) throw new Exception("La casa debe tener m�nimo 1 cocina.");
			if (nBaths<2) throw new Exception("La casa debe tener m�nimo 2 ba�os.");
			DB4oManager.modificarRuralHouse(usuario.getEmail(), numero, description, city, nRooms, nKitchen, nBaths, nLiving, nPark, setGuardarImagenes(usuario.getEmail(), imagenes, numero));
		}
	}
	
	public void nuevoUsuario(String email, String pass, String estadoCivil, String nombre, String apellidos, String telefono, String pais, String edad, Image perfil) throws Exception {
		if (email.compareTo("")==0 || pass.compareTo("")==0 || nombre.compareTo("")==0 || pais.compareTo("")==0 || estadoCivil.compareTo("")==0) throw new Exception("Algunos datos obligatorios faltan.");
		else {
			if (edad.compareTo("")==0) edad = null;
			if (apellidos.compareTo("")==0) apellidos = null;
			if (telefono.compareTo("")==0) telefono = null;
			DB4oManager.nuevoUsuario(email, pass, estadoCivil, nombre, apellidos, telefono, pais, edad, setGuardarPerfil(email, perfil));
			try {
				EnviarCorreo.enviarCorreos(email, "Registro en Villatripas de Arriba", "Te has registrado en villatripas de arriba con el email: " + email);
				GestionTwitter.enviarTweet("Bienvenid@: " + nombre + " " + Calendar.getInstance().getTime().toString());
			} catch (Exception e) {
				javax.swing.JOptionPane.showMessageDialog(null, e.getMessage(), "Este error no es significativo.", javax.swing.JOptionPane.ERROR_MESSAGE);
				//throw new Exception("Error al enviar el email de registro o envio de tweet automatico.");
			}
		}
	}	
	
	public UserAplication getUsuario(UserAplication usuario) throws Exception {
		return usuario;
	}

	public Owner getOwner(UserAplication usuario) throws Exception {
		return usuario.getPropietario();
	}	
	
	
	public void recuperarContrasena(String email) throws Exception {
		UserAplication user = DB4oManager.getUser(email);
		if (user == null) throw new Exception("El email no existe.");
		else {
			try {
				EnviarCorreo.enviarCorreos(user.getEmail(), "Contrase�a", "Tu contrase�a es " + user.getPass());
			} catch (Exception e) {
				throw new Exception("Error al enviar el email de recuperacion de contrase�a. Intentelo mas tarde.");
			}
		}
	}
	
	public UserAplication  hacerLogin(String email, String pass) throws Exception {
		if (email.compareTo("")==0 || pass.compareTo("")==0) throw new Exception("Algunos datos obligatorios faltan.");
		UserAplication usuario = DB4oManager.getUser(email, pass);
		if (usuario == null){
			throw new Exception("No se ha podido hacer Login");
		} else {
			return usuario;
		}
	}


	public void modificarPerfil(UserAplication usuario, String estadoCivil, String nombre,String apellidos, String telefono, String pais, String edad, Image perfil) throws Exception {
		if (nombre.compareTo("")==0 || pais.compareTo("")==0 || estadoCivil.compareTo("")==0) throw new Exception("Algunos datos obligatorios faltan.");
		else usuario = DB4oManager.modificarUsuario(usuario, estadoCivil, nombre, apellidos, telefono, pais, edad, setGuardarPerfil(usuario.getEmail(), perfil));		
	}
	
	public int getNumeroCR() throws Exception{
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
	
	public int getNumeroReserva() throws Exception{
		Vector<Book> vector = DB4oManager.getTodasLasReservas();
		int max = 0;
		java.util.Iterator<Book> i = vector.iterator();
		while (i.hasNext()) {
			int aux = i.next().getNumeroDeReserva();
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

	public void hacerReserva(UserAplication usuario, int numeroRH, Date inicio, Date fin) throws Exception{
		DB4oManager.hacerReserva(usuario, getNumeroReserva(), numeroRH, inicio, fin);
	}
	
	public Vector<RuralHouse> casasRuralesDisponibles(Date inicio, Date fin) throws Exception{
		return DB4oManager.casasRuralesDisponibles(inicio, fin);
	}
	
	public Vector<RuralHouse> casasRuralesDisponibles(Date inicio, Date fin,
			String Ciudad) throws Exception {
		return DB4oManager.casasRuralesDisponibles(inicio, fin, Ciudad);
	}
	
	public Vector<RuralHouse> casasRuralesDisponibles(Date inicio, Date fin, String Ciudad, int Banos, int habita, int cocina, int sala, int park) throws Exception{
		return DB4oManager.casasRuralesDisponibles(inicio, fin, Ciudad, Banos, habita, cocina, sala, park);
	}
	
	public void anadirOferta(UserAplication usuario, int numero, Date inicio, Date fin, float precio, boolean obligatorio) throws Exception{
		DB4oManager.anadirOferta(usuario, numero, inicio, fin, precio, obligatorio);
	}


	public Vector<Offer> getOfertas(UserAplication usuario, int numeroRH) throws Exception {
		Iterator<RuralHouse> i = usuario.getPropietario().getRuralHouses().iterator();
		while (i.hasNext()){
			RuralHouse aux = i.next();
			if (aux.getHouseNumber() == numeroRH) return aux.getOfertas();
		}
		throw new Exception("Ha ocurrido un error a la hora de encontrar ofertas");
	}

	@Override
	public Vector<RuralHouse> getCasas(String ciudad, int Banos, int Habita,
			int Cocina, int Estar, int Park) throws Exception {
		Vector<RuralHouse> vector;
		vector = DB4oManager.getHouse(ciudad, Banos, Habita,
					Cocina, Estar, Park);
		if(vector==null){
			throw new Exception("No existen casas con esos datos.");
		}
		return vector;
	}
	
	public RuralHouse getCasas(int num) throws Exception {
		RuralHouse vector;
		vector = DB4oManager.getRuralHouse(num);
		if(vector==null){
			throw new Exception("No existen casas con esos datos.");
		}
		return vector;
	}

	public Vector<Image> getFotosRH(int numeroDeCasa) throws Exception {
		Vector<Image> aux = new Vector<Image>();
		RuralHouse casa = DB4oManager.getRuralHouse(numeroDeCasa);
		Iterator<String> i = casa.getImages().iterator();
		while (i.hasNext()) aux.add((new ImageIcon(i.next())).getImage());
		return aux;
	}


	public Image getFotoPerfil(String email) throws Exception {
		if (DB4oManager.getUser(email).getPerfil() == null) return null;
		return new ImageIcon(DB4oManager.getUser(email).getPerfil()).getImage();
	}
	
	private Vector<String> setGuardarImagenes(String email, Vector<Image> imagenes, int numeroCasaRural) throws Exception {
		File fCarpeta = new File("\\imagenes\\"+email+"\\"+numeroCasaRural);
		if (fCarpeta.exists()) fCarpeta.delete();
		else fCarpeta.mkdirs();
		Vector<String> aux = new Vector<String>();
		Iterator<Image> i = imagenes.iterator();
		while (i.hasNext()){
			try {
				Image auxi = i.next();
				File fDestino = new File("\\imagenes\\"+email+"\\"+numeroCasaRural+"\\"+auxi.toString());
				if(fDestino.exists())fDestino.delete();
				BufferedImage bi = toBufferedImage(auxi);
				ImageIO.write(bi, "png", fDestino);
				aux.add(fDestino.getPath());
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		}
		return aux;
	}
	
	private String setGuardarPerfil(String email, Image imagen) throws Exception {
		if (imagen == null) return null;
		try { 
			File fCarpeta = new File("\\imagenes\\"+email+"\\Perfil");
			if (fCarpeta.exists()) fCarpeta.delete();
			else fCarpeta.mkdirs();
			File fDestino = new File("\\imagenes\\"+email+"\\Perfil\\perfil");
			BufferedImage bi = toBufferedImage(imagen);
			ImageIO.write(bi, "png", fDestino);
			return fDestino.getPath();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}
	private static BufferedImage toBufferedImage(Image src) {
		int w = src.getWidth(null);
		int h = src.getHeight(null);
		int type = BufferedImage.TYPE_INT_RGB;  // other options
		BufferedImage dest = new BufferedImage(w, h, type);
		Graphics2D g2 = dest.createGraphics();
		g2.drawImage(src, 0, 0, null);
		g2.dispose();
		return dest;
	}

	public void anadirFechas(UserAplication usuario, int numero, Date inicio, Date fin, float precio, int minimoDeDias) throws Exception {
		DB4oManager.anadirFechas(usuario, numero, inicio, fin, precio, minimoDeDias);
	}

	public Vector<Fechas> getFechas(UserAplication usuario, int numeroRH) throws Exception{
		Iterator<RuralHouse> i = usuario.getPropietario().getRuralHouses().iterator();
		while (i.hasNext()){
			RuralHouse aux = i.next();
			if (aux.getHouseNumber() == numeroRH) return aux.getFechas();
		}
		throw new Exception("Ha ocurrido un error a la hora de encontrar fechas");
	}
	
	public void eliminarOferta(UserAplication usuario, int nRH, Date ini, Date fin) throws Exception{
		DB4oManager.eliminarOferta(usuario, nRH, ini, fin);
	}

	public void eliminarReserva(int num) throws Exception{
		DB4oManager.eliminarReserva(num);
	}
	

	
	

	


	
	




	

	

	

	

	

	

	
	
}

