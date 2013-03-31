package domain;

import java.util.Vector;

public class UserAplication {
	private String email;
	private String pass;
	private String estadoCivil;
	private String nombre;
	private String apellidos;
	private String telefono;
	private String pais;
	private String edad;
	private Owner propietario;

	public UserAplication(String e, String p, String eC, String nom, String ape, String tel, String ps, String edd){
		email=e;
		pass=p;
		estadoCivil=eC;
		nombre=nom;
		apellidos=ape;
		telefono=tel;
		pais=ps;
		edad=edd;
		propietario=null;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String e) {
		email = e;
	}
	
	public void setPass(String p) {
		pass = p;
	}
	
	public String getPass() {
		return pass;
	}
	
	public String getEstadoCivil() {
		return estadoCivil;
	}
	
	public void setEstadoCivil(String e) {
		estadoCivil = e;
	}
	
	public String getName() {
		return nombre;
	}

	public void setName(String name) {
		nombre = name;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String a) {
		apellidos= a;
	}
	
	public String getEdad(){
		return edad;
	}
	
	public void setEdad(String e){
		edad= e;
	}
	
	public String getTelefono(){
		return telefono;
	}
	
	public void setTelefono(String t){
		telefono= t;
	}
	
	public String getPais(){
		return pais;
	}
	
	public void setPais(String p){
		pais= p;
	}
	
	public String toString(){
		return nombre;
	}	

	public void setPropietario(Owner own){
		propietario=own;
	}
	
	public Owner getPropietario(){
		return propietario;
	}
}
