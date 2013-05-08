package externalDataSend;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class LeerMail {
	
	public LeerMail(){
		
	}
	public  String leerEmail(){
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		String aux = "";
		try {
			String aarchivo = this.getClass().getResource("/imagenes/email.html").getFile();
			archivo = new File (aarchivo);
			fr = new FileReader (archivo);
			br = new BufferedReader(fr);
			String linea;
			while((linea=br.readLine())!=null){
				//System.out.println(linea);
				aux = aux.concat(linea);
			}
		} catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if( null != fr ){
					fr.close();
				}
			}catch (Exception e2){
				e2.printStackTrace();
			}
		}
		return aux;
	}	
}
