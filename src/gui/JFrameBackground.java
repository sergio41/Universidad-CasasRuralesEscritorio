package gui;
import java.awt.Graphics;  
import javax.swing.ImageIcon;  
import javax.swing.JFrame;  
  
/** 
 * 
 * @author www.javafrikitutorials.com 
 */  
public class JFrameBackground extends JFrame {  
  
    private ImageIcon imageIcon = new ImageIcon(getClass().getResource("/imagenes/fondo.jpg"));  
  
    public JFrameBackground() {  
        super("JFrame con imagen de fondo");  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());  
    }  
  
    @Override  
    public void paint(Graphics g) {  
        if (imageIcon != null) {  
            g.drawImage(imageIcon.getImage(), 0, 0, getWidth(), getHeight(), null);  
        }  
    }  
  
    public static void main(String[] args) {  
        new JFrameBackground().setVisible(true);  
    }  
}