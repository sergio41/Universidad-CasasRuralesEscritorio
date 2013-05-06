package viejoGUI;

import javax.swing.JPanel;
import javax.swing.JButton;

public class pruebaa extends JPanel {

	/**
	 * Create the panel.
	 */
	public pruebaa() {
		setLayout(null);
		
		String html = "<html><script type=\"text/javascript\" src=\"http://cdn.dev.skype.com/uri/skype-uri.js\"></script><div id=\"SkypeButton_Call_UrkoAM_1\">  <script type=\"text/javascript\">    Skype.ui({      \"name\": \"call\",      \"element\": \"SkypeButton_Call_UrkoAM_1\",      \"participants\": [\"UrkoAM\"],\"imageSize\": 32});</script></div></html>";
		JButton btnNewButton = new JButton(html);
		btnNewButton.setBounds(176, 5, 203, 152);
		add(btnNewButton);

	}

}
