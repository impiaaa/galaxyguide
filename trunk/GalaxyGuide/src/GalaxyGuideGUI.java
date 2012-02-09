import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import java.awt.List;
import java.awt.ScrollPane;
import java.awt.TextField;
import java.awt.Label;
import javax.swing.JSlider;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JSplitPane;
import java.awt.Canvas;


/**
 * TODO Put here a description of what this class does.
 *
 * @author alvessr.
 *         Created Feb 6, 2012.
 */
public class GalaxyGuideGUI {

	private JFrame frmGuideToThe;
	private JTextField starSearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GalaxyGuideGUI window = new GalaxyGuideGUI();
					window.frmGuideToThe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GalaxyGuideGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGuideToThe = new JFrame();
		frmGuideToThe.setTitle("Guide to the Galaxy");
		frmGuideToThe.setBounds(100, 100, 594, 409);
		frmGuideToThe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frmGuideToThe.getContentPane().setLayout(springLayout);
		
		ScrollPane starMap = new ScrollPane();
		springLayout.putConstraint(SpringLayout.SOUTH, starMap, -10, SpringLayout.SOUTH, frmGuideToThe.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, starMap, -10, SpringLayout.EAST, frmGuideToThe.getContentPane());
		frmGuideToThe.getContentPane().add(starMap);
		
		Label starMapLabel = new Label("Star Map");
		springLayout.putConstraint(SpringLayout.NORTH, starMapLabel, 10, SpringLayout.NORTH, frmGuideToThe.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, starMap, 6, SpringLayout.SOUTH, starMapLabel);
		frmGuideToThe.getContentPane().add(starMapLabel);
		
		JButton btnGo = new JButton("Go!");
		springLayout.putConstraint(SpringLayout.NORTH, btnGo, 0, SpringLayout.NORTH, starMapLabel);
		springLayout.putConstraint(SpringLayout.EAST, btnGo, -10, SpringLayout.EAST, frmGuideToThe.getContentPane());
		frmGuideToThe.getContentPane().add(btnGo);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		springLayout.putConstraint(SpringLayout.WEST, starMapLabel, 6, SpringLayout.EAST, tabbedPane);
		springLayout.putConstraint(SpringLayout.WEST, starMap, 6, SpringLayout.EAST, tabbedPane);
		springLayout.putConstraint(SpringLayout.NORTH, tabbedPane, 10, SpringLayout.NORTH, frmGuideToThe.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, tabbedPane, 10, SpringLayout.WEST, frmGuideToThe.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, tabbedPane, -10, SpringLayout.SOUTH, frmGuideToThe.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, tabbedPane, 300, SpringLayout.WEST, frmGuideToThe.getContentPane());
		frmGuideToThe.getContentPane().add(tabbedPane);
		
		JPanel tripPanel = new JPanel();
		tabbedPane.addTab("Trip Planner", null, tripPanel, null);
		SpringLayout sl_tripPanel = new SpringLayout();
		tripPanel.setLayout(sl_tripPanel);
		
		Label rangeLabel = new Label("Range");
		sl_tripPanel.putConstraint(SpringLayout.NORTH, rangeLabel, 10, SpringLayout.NORTH, tripPanel);
		sl_tripPanel.putConstraint(SpringLayout.WEST, rangeLabel, 10, SpringLayout.WEST, tripPanel);
		rangeLabel.setFont(new Font("Dialog", Font.PLAIN, 11));
		tripPanel.add(rangeLabel);
		
		List tripList = new List();
		sl_tripPanel.putConstraint(SpringLayout.NORTH, tripList, 6, SpringLayout.SOUTH, rangeLabel);
		sl_tripPanel.putConstraint(SpringLayout.WEST, tripList, 10, SpringLayout.WEST, tripPanel);
		sl_tripPanel.putConstraint(SpringLayout.EAST, tripList, -10, SpringLayout.EAST, tripPanel);
		tripPanel.add(tripList);
		
		JSlider rangeSlider = new JSlider();
		sl_tripPanel.putConstraint(SpringLayout.SOUTH, rangeLabel, 0, SpringLayout.SOUTH, rangeSlider);
		sl_tripPanel.putConstraint(SpringLayout.NORTH, rangeSlider, 10, SpringLayout.NORTH, tripPanel);
		sl_tripPanel.putConstraint(SpringLayout.WEST, rangeSlider, 6, SpringLayout.EAST, rangeLabel);
		sl_tripPanel.putConstraint(SpringLayout.EAST, rangeSlider, -10, SpringLayout.EAST, tripPanel);
		tripPanel.add(rangeSlider);
		
		JTextField tripSearch = new JTextField();
		sl_tripPanel.putConstraint(SpringLayout.SOUTH, tripList, -6, SpringLayout.NORTH, tripSearch);
		sl_tripPanel.putConstraint(SpringLayout.WEST, tripSearch, 10, SpringLayout.WEST, tripPanel);
		sl_tripPanel.putConstraint(SpringLayout.SOUTH, tripSearch, -10, SpringLayout.SOUTH, tripPanel);
		sl_tripPanel.putConstraint(SpringLayout.EAST, tripSearch, -10, SpringLayout.EAST, tripPanel);
		tripPanel.add(tripSearch);
		
		JPanel routePanel = new JPanel();
		tabbedPane.addTab("Route Finder", null, routePanel, null);
		SpringLayout sl_routePanel = new SpringLayout();
		routePanel.setLayout(sl_routePanel);
		
		starSearch = new JTextField();
		sl_routePanel.putConstraint(SpringLayout.WEST, starSearch, 10, SpringLayout.WEST, routePanel);
		sl_routePanel.putConstraint(SpringLayout.EAST, starSearch, -10, SpringLayout.EAST, routePanel);
		sl_routePanel.putConstraint(SpringLayout.SOUTH, starSearch, -10, SpringLayout.SOUTH, routePanel);
		routePanel.add(starSearch);
		starSearch.setColumns(10);
		
		List fromList = new List();
		sl_routePanel.putConstraint(SpringLayout.NORTH, fromList, 10, SpringLayout.NORTH, routePanel);
		sl_routePanel.putConstraint(SpringLayout.WEST, fromList, 10, SpringLayout.WEST, routePanel);
		sl_routePanel.putConstraint(SpringLayout.SOUTH, fromList, -6, SpringLayout.NORTH, starSearch);
		sl_routePanel.putConstraint(SpringLayout.EAST, fromList, 139, SpringLayout.WEST, routePanel);
		routePanel.add(fromList);
		
		List toList = new List();
		sl_routePanel.putConstraint(SpringLayout.NORTH, toList, 10, SpringLayout.NORTH, routePanel);
		sl_routePanel.putConstraint(SpringLayout.WEST, toList, -139, SpringLayout.EAST, routePanel);
		sl_routePanel.putConstraint(SpringLayout.SOUTH, toList, -6, SpringLayout.NORTH, starSearch);
		sl_routePanel.putConstraint(SpringLayout.EAST, toList, -10, SpringLayout.EAST, routePanel);
		routePanel.add(toList);
		
		for (char i = 'A'; i < 'Z'; i++) {
			tripList.add("Trip "+i);
			fromList.add("Star "+i);
			toList.add("Star "+i);
		}
	}
}
