package viejoGUI;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JPanel;

public class prueba extends JPanel {

	/**
	 * Create the panel.
	 */
	public prueba() {
		URLConnection con;
		try {
			con = new URL("http://maps...").openConnection();
			InputStream is = con.getInputStream();
			byte bytes[] = new byte[con.getContentLength()];
			is.read(bytes);
			is.close();
			Toolkit tk = getToolkit();
			Image map = tk.createImage(bytes);
			tk.prepareImage(map, -1, -1, null);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
