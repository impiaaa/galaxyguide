import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import java.awt.List;
import java.awt.ScrollPane;
import java.awt.Label;
import javax.swing.JSlider;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JSplitPane;
import java.awt.FlowLayout;
import java.awt.BorderLayout;


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
	 * @param args 
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
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
		this.frmGuideToThe = new JFrame();
		this.frmGuideToThe.setTitle("Guide to the Galaxy");
		this.frmGuideToThe.setBounds(100, 100, 594, 409);
		this.frmGuideToThe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frmGuideToThe.getContentPane().setLayout(new BorderLayout(0, 0));

		JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerLocation(290);
		this.frmGuideToThe.getContentPane().add(splitPane);
		
		JPanel starMapPanel = new JPanel();
		splitPane.setRightComponent(starMapPanel);
		SpringLayout sl_starMapPanel = new SpringLayout();
		starMapPanel.setLayout(sl_starMapPanel);
		
		ScrollPane starMap = new ScrollPane();
		sl_starMapPanel.putConstraint(SpringLayout.WEST, starMap, 0, SpringLayout.WEST, starMapPanel);
		sl_starMapPanel.putConstraint(SpringLayout.SOUTH, starMap, 0, SpringLayout.SOUTH, starMapPanel);
		sl_starMapPanel.putConstraint(SpringLayout.EAST, starMap, 0, SpringLayout.EAST, starMapPanel);
		starMap.setBounds(306, 38, 262, 323);
		starMapPanel.add(starMap);
		
		Label starMapLabel = new Label("Star Map");
		sl_starMapPanel.putConstraint(SpringLayout.NORTH, starMap, 6, SpringLayout.SOUTH, starMapLabel);
		sl_starMapPanel.putConstraint(SpringLayout.NORTH, starMapLabel, 0, SpringLayout.NORTH, starMapPanel);
		sl_starMapPanel.putConstraint(SpringLayout.WEST, starMapLabel, 0, SpringLayout.WEST, starMapPanel);
		starMapLabel.setBounds(306, 10, 57, 22);
		starMapPanel.add(starMapLabel);
		
		JButton btnGo = new JButton("Go!");
		sl_starMapPanel.putConstraint(SpringLayout.NORTH, btnGo, 0, SpringLayout.NORTH, starMapPanel);
		sl_starMapPanel.putConstraint(SpringLayout.EAST, btnGo, 0, SpringLayout.EAST, starMapPanel);
		btnGo.setBounds(519, 10, 49, 23);
		starMapPanel.add(btnGo);
		
		JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.TOP);
		splitPane.setLeftComponent(tabbedPane);
		
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
		
		this.starSearch = new JTextField();
		sl_routePanel.putConstraint(SpringLayout.WEST, this.starSearch, 10, SpringLayout.WEST, routePanel);
		sl_routePanel.putConstraint(SpringLayout.EAST, this.starSearch, -10, SpringLayout.EAST, routePanel);
		sl_routePanel.putConstraint(SpringLayout.SOUTH, this.starSearch, -10, SpringLayout.SOUTH, routePanel);
		routePanel.add(this.starSearch);
		this.starSearch.setColumns(10);
		
		List fromList = new List();
		sl_routePanel.putConstraint(SpringLayout.NORTH, fromList, 10, SpringLayout.NORTH, routePanel);
		sl_routePanel.putConstraint(SpringLayout.WEST, fromList, 10, SpringLayout.WEST, routePanel);
		sl_routePanel.putConstraint(SpringLayout.SOUTH, fromList, -6, SpringLayout.NORTH, this.starSearch);
		sl_routePanel.putConstraint(SpringLayout.EAST, fromList, -3, SpringLayout.HORIZONTAL_CENTER, routePanel);
		routePanel.add(fromList);
		
		List toList = new List();
		sl_routePanel.putConstraint(SpringLayout.NORTH, toList, 10, SpringLayout.NORTH, routePanel);
		sl_routePanel.putConstraint(SpringLayout.WEST, toList, 3, SpringLayout.HORIZONTAL_CENTER, routePanel);
		sl_routePanel.putConstraint(SpringLayout.SOUTH, toList, -6, SpringLayout.NORTH, this.starSearch);
		sl_routePanel.putConstraint(SpringLayout.EAST, toList, -10, SpringLayout.EAST, routePanel);
		routePanel.add(toList);
				
		for (char i = 'A'; i < 'Z'; i++) {
			tripList.add("Trip "+i);
			fromList.add("Star "+i);
			toList.add("Star "+i);
		}
	}
}
