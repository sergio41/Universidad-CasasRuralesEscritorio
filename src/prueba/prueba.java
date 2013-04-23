package prueba;

import gui.Start;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import com.db4o.objectManager.v2.MainFrame;

public class prueba extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					prueba frame = new prueba();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public prueba() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		// construct the menuList as a JList
		JList menuList = new JList();
		menuList.setCellRenderer(new ImageListCellRenderer());

		// get our images
		Icon pingImage = new ImageIcon(Start.class.getResource("/imagenes/logo100x100.png"));
		Icon tracerouteImage = new ImageIcon(Start.class.getResource("/imagenes/logo100x100.png"));
		Icon netstatImage = new ImageIcon(Start.class.getResource("/imagenes/logo100x100.png"));

		// add the images to jlabels with text
		JLabel pingLabel = new JLabel("Ping", pingImage, JLabel.LEFT);
		JLabel tracerouteLabel = new JLabel("Traceroute", tracerouteImage, JLabel.LEFT);
		JLabel netstatLabel = new JLabel("Netstat", netstatImage, JLabel.LEFT);

		// create the corresponding panels
		JPanel pingPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel traceroutePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel netstatPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		// add the labels onto the panels
		pingPanel.add(pingLabel);
		traceroutePanel.add(tracerouteLabel);
		netstatPanel.add(netstatLabel);

		// create a panel array
		Object[] panels = {pingPanel, traceroutePanel, netstatPanel};

		// tell the jlist to use the panel array for its data
		menuList.setListData(panels);
		
		menuList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		menuList.setLayoutOrientation(JList.VERTICAL);
		menuList.setFixedCellHeight(46);

		// put our JList in a JScrollPane
		JScrollPane menuScrollPane = new JScrollPane(menuList);
		menuScrollPane.setMinimumSize(new Dimension(150, 50));

		// put our JList and JScrollPane in the left hand side of a JSplitPane
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, 
		                           menuScrollPane, 
		                           contentPane);
		
		
	}

}
