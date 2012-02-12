import java.awt.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.TreeSet;


/**
 * TODO Put here a description of what this class does.
 *
 * @author alvessr.
 *         Created Feb 6, 2012.
 */
public class GalaxyGuideGUI implements DocumentListener {
	private JFrame frmGuideToThe;
	private FilteredListModel<String> starModel;
	private FilteredListModel<String> tripModel;
	private JList<String> fromList;
	private JList<String> toList;
	private JList<String> tripList;
	private JTextField tripSearch;
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
		TreeSet<String> stars = new TreeSet<String>();
		TreeSet<String> trips = new TreeSet<String>();
		for (char i = 'A'; i < 'Z'; i++) {
			trips.add("Trip "+i);
			stars.add("Star "+i);
		}
		this.starModel = new FilteredListModel<String>(stars);
		this.tripModel = new FilteredListModel<String>(trips);

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
		
		JScrollPane starMap = new JScrollPane();
		sl_starMapPanel.putConstraint(SpringLayout.WEST, starMap, 0, SpringLayout.WEST, starMapPanel);
		sl_starMapPanel.putConstraint(SpringLayout.SOUTH, starMap, 0, SpringLayout.SOUTH, starMapPanel);
		sl_starMapPanel.putConstraint(SpringLayout.EAST, starMap, 0, SpringLayout.EAST, starMapPanel);
		starMapPanel.add(starMap);
		
		JLabel starMapLabel = new JLabel("Star Map");
		sl_starMapPanel.putConstraint(SpringLayout.NORTH, starMapLabel, 6, SpringLayout.NORTH, starMapPanel);
		sl_starMapPanel.putConstraint(SpringLayout.WEST, starMapLabel, 6, SpringLayout.WEST, starMapPanel);
		starMapPanel.add(starMapLabel);
		
		JButton btnGo = new JButton("Go!");
		sl_starMapPanel.putConstraint(SpringLayout.NORTH, starMap, 6, SpringLayout.SOUTH, btnGo);
		sl_starMapPanel.putConstraint(SpringLayout.NORTH, btnGo, 6, SpringLayout.NORTH, starMapPanel);
		sl_starMapPanel.putConstraint(SpringLayout.EAST, btnGo, -6, SpringLayout.EAST, starMapPanel);
		starMapPanel.add(btnGo);
		
		JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.TOP);
		splitPane.setLeftComponent(tabbedPane);
		
		JPanel tripPanel = new JPanel();
		tabbedPane.addTab("Trip Planner", null, tripPanel, null);
		SpringLayout sl_tripPanel = new SpringLayout();
		tripPanel.setLayout(sl_tripPanel);
		
		JLabel rangeLabel = new JLabel("Range");
		sl_tripPanel.putConstraint(SpringLayout.NORTH, rangeLabel, 10, SpringLayout.NORTH, tripPanel);
		sl_tripPanel.putConstraint(SpringLayout.WEST, rangeLabel, 10, SpringLayout.WEST, tripPanel);
		tripPanel.add(rangeLabel);
		
		this.tripList = new JList<String>(this.tripModel);
		JScrollPane s1 = new JScrollPane(this.tripList);
		sl_tripPanel.putConstraint(SpringLayout.NORTH, s1, 6, SpringLayout.SOUTH, rangeLabel);
		sl_tripPanel.putConstraint(SpringLayout.WEST, s1, 10, SpringLayout.WEST, tripPanel);
		sl_tripPanel.putConstraint(SpringLayout.EAST, s1, -10, SpringLayout.EAST, tripPanel);
		tripPanel.add(s1);
		
		JSlider rangeSlider = new JSlider();
		sl_tripPanel.putConstraint(SpringLayout.SOUTH, rangeLabel, 0, SpringLayout.SOUTH, rangeSlider);
		sl_tripPanel.putConstraint(SpringLayout.NORTH, rangeSlider, 10, SpringLayout.NORTH, tripPanel);
		sl_tripPanel.putConstraint(SpringLayout.WEST, rangeSlider, 6, SpringLayout.EAST, rangeLabel);
		sl_tripPanel.putConstraint(SpringLayout.EAST, rangeSlider, -10, SpringLayout.EAST, tripPanel);
		tripPanel.add(rangeSlider);
		
		this.tripSearch = new JTextField();
		sl_tripPanel.putConstraint(SpringLayout.SOUTH, s1, -6, SpringLayout.NORTH, this.tripSearch);
		sl_tripPanel.putConstraint(SpringLayout.WEST, this.tripSearch, 10, SpringLayout.WEST, tripPanel);
		sl_tripPanel.putConstraint(SpringLayout.SOUTH, this.tripSearch, -10, SpringLayout.SOUTH, tripPanel);
		sl_tripPanel.putConstraint(SpringLayout.EAST, this.tripSearch, -10, SpringLayout.EAST, tripPanel);
		tripPanel.add(this.tripSearch);
		this.tripSearch.getDocument().addDocumentListener(this);
		
		JPanel routePanel = new JPanel();
		tabbedPane.addTab("Route Finder", null, routePanel, null);
		SpringLayout sl_routePanel = new SpringLayout();
		routePanel.setLayout(sl_routePanel);
		
		this.starSearch = new JTextField();
		sl_routePanel.putConstraint(SpringLayout.WEST, this.starSearch, 10, SpringLayout.WEST, routePanel);
		sl_routePanel.putConstraint(SpringLayout.EAST, this.starSearch, -10, SpringLayout.EAST, routePanel);
		sl_routePanel.putConstraint(SpringLayout.SOUTH, this.starSearch, -10, SpringLayout.SOUTH, routePanel);
		routePanel.add(this.starSearch);
		this.starSearch.getDocument().addDocumentListener(this);
		
		this.fromList = new JList<String>();
		JScrollPane p2 = new JScrollPane(this.fromList);
		sl_routePanel.putConstraint(SpringLayout.NORTH, p2, 10, SpringLayout.NORTH, routePanel);
		sl_routePanel.putConstraint(SpringLayout.WEST, p2, 10, SpringLayout.WEST, routePanel);
		sl_routePanel.putConstraint(SpringLayout.SOUTH, p2, -6, SpringLayout.NORTH, this.starSearch);
		sl_routePanel.putConstraint(SpringLayout.EAST, p2, -3, SpringLayout.HORIZONTAL_CENTER, routePanel);
		routePanel.add(p2);
		
		this.toList = new JList<String>();
		JScrollPane p3 = new JScrollPane(this.toList);
		sl_routePanel.putConstraint(SpringLayout.NORTH, p3, 10, SpringLayout.NORTH, routePanel);
		sl_routePanel.putConstraint(SpringLayout.WEST, p3, 3, SpringLayout.HORIZONTAL_CENTER, routePanel);
		sl_routePanel.putConstraint(SpringLayout.SOUTH, p3, -6, SpringLayout.NORTH, this.starSearch);
		sl_routePanel.putConstraint(SpringLayout.EAST, p3, -10, SpringLayout.EAST, routePanel);
		routePanel.add(p3);
		
		this.fromList.setModel(this.starModel);
		this.toList.setModel(this.starModel);
	}

	@Override
	public void changedUpdate(DocumentEvent arg0) {
		if (arg0.getDocument() == this.starSearch.getDocument()) {
			this.starModel.setFilter(this.starSearch.getText());
			this.starModel.update();
		}
		else {
			this.tripModel.setFilter(this.tripSearch.getText());
			this.tripModel.update();
		}
	}

	@Override
	public void insertUpdate(DocumentEvent arg0) {
		if (arg0.getDocument() == this.starSearch.getDocument()) {
			this.starModel.setFilter(this.starSearch.getText());
			this.starModel.update();
		}
		else {
			this.tripModel.setFilter(this.tripSearch.getText());
			this.tripModel.update();
		}
	}

	@Override
	public void removeUpdate(DocumentEvent arg0) {
		if (arg0.getDocument() == this.starSearch.getDocument()) {
			this.starModel.setFilter(this.starSearch.getText());
			this.starModel.update();
		}
		else {
			this.tripModel.setFilter(this.tripSearch.getText());
			this.tripModel.update();
		}
	}

}
