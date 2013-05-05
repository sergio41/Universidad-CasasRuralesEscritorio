package gui;

import javax.swing.ImageIcon;

public class PantallaCargandoLanzadora {
	PantallaCargando screen;
	
	public PantallaCargandoLanzadora() {
		inicioPantalla();
		screen.velocidadDeCarga();
	}
	
	private void inicioPantalla() {
		ImageIcon myImage = new ImageIcon("imagen/Logo.jpg");
		screen = new PantallaCargando(myImage);
		screen.setLocationRelativeTo(null);
		screen.setProgresoMax(100);
		screen.setVisible(true);
	}
}
