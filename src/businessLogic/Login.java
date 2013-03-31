package businessLogic;

import dataAccess.DB4oManager;
import domain.Owner;
import domain.UserAplication;

public class Login {
	private static boolean estado = false; //SinLogin = 0; //Login = 1
	private static UserAplication usuario;
	public static boolean estadoLogin (){
		return estado;
	}
	public static boolean hacerLogin(String email, String pass) throws Exception{
		if (email.compareTo("")==0 || pass.compareTo("")==0) throw new Exception("Algunos datos obligatorios faltan.");
		usuario = DB4oManager.getUser(email, pass);
		if (usuario == null) return false;
		else {
			estado = true;
			return true;
		}
		//return (DB4oManager.comprobarEmailAndPass(email, pass));
	}
	
	public static void nuevoUsuario(String email, String pass, String estadoCivil, String nombre, String apellidos, String telefono, String pais, String edad) throws Exception{
		if (email.compareTo("")==0 || pass.compareTo("")==0 || nombre.compareTo("")==0 || pais.compareTo("")==0 || estadoCivil.compareTo("")==0) throw new Exception("Algunos datos obligatorios faltan.");
		else {
			if (edad.compareTo("")==0) edad = null;
			if (apellidos.compareTo("")==0) apellidos = null;
			if (telefono.compareTo("")==0) telefono = null;
			if (DB4oManager.comprobarEmail(email)) throw new Exception("Email ya usado. Logueate");
			else{
				DB4oManager.storeUser(new UserAplication(email, pass, estadoCivil, nombre, apellidos, telefono, pais, edad));
				hacerLogin(email, pass);
			}
		}
	}
	
	public static void logout() throws Exception{
		if (!estado) throw new Exception("No estas logueado.");
		estado = false;
		usuario = null;
	}

	public static UserAplication getUser() throws Exception {
		if (!estado) throw new Exception("No estas logueado.");
		return usuario;
	}
	public static void modificarPerfil(String email, String pass, String estadoCivil, String nombre, String apellidos, String telefono, String pais, String edad) throws Exception{
		if (email.compareTo("")==0 ||  nombre.compareTo("")==0 || pais.compareTo("")==0 || estadoCivil.compareTo("")==0) throw new Exception("Algunos datos obligatorios faltan.");
		else {
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
	
	//Metodos Datos
	
	public static String getEmail() {
		return usuario.getEmail();
	}
	
	public static void setEmail(String e) {
		usuario.setEmail(e);
	}
	
	public static void setPass(String p) {
		usuario.setPass(p);
	}
	
	public static String getEstadoCivil() {
		return usuario.getEstadoCivil();
	}
	
	public static void setEstadoCivil(String e) {
		usuario.setEstadoCivil(e);
	}
	
	public static String getName() {
		return usuario.getName();
	}

	public static void setName(String name) {
		usuario.setName(name);
	}

	public static String getApellidos() {
		return usuario.getApellidos();
	}

	public static void setApellidos(String a) {
		usuario.setApellidos(a);
	}
	
	public static String getEdad(){
		return usuario.getEdad();
	}
	
	public static void setEdad(String e) {
		usuario.setEdad(e);
	}
	
	public static String getTelefono(){
		return usuario.getTelefono();
	}
	
	public static void setTelefono(String t) {
		usuario.setTelefono(t);
	}
	
	public static String getPais(){
		return usuario.getPais();
	}
	
	public static void setPais(String p) {
		usuario.setPais(p);
	}
	
	public String toString(){
		return usuario.toString();
	}	

	public static void setPropietario(Owner own){
		usuario.setPropietario(own);
		DB4oManager.storeUser(usuario);
	}
	
	public static Owner getPropietario(){
		return usuario.getPropietario();
	}
}
