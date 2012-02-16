import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.TreeSet;


/**
 * TODO Put here a description of what this class does.
 *
 * @author alvessr.
 *         Created Feb 6, 2012.
 */
public class GalaxyGuideGUI implements DocumentListener, ListSelectionListener, ActionListener {
	private JFrame frmGuideToThe;
	private FilteredListModel<Star> starModel;
	private FilteredListModel<Trip> tripModel;
	private JList fromList;
	private JList toList;
	private JList tripList;
	private JTextField tripSearch;
	private JTextField starSearch;
	private StarMapPanel canvas;
	private JTabbedPane tabbedPane;
	private JList starTripList;
	private JButton btnGo;
	private JButton btnFindTrips;
	private JSlider rangeSlider;

	/**
	 * Launch the application.
	 * @param args 
	 */
	public static void main(String[] args) {
		try {
			// Set System L&F
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException e) {
			// handle exception
		} catch (ClassNotFoundException e) {
			// handle exception
		} catch (InstantiationException e) {
			// handle exception
		} catch (IllegalAccessException e) {
			// handle exception
		}
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
		GalaxyGenerator galGen = new GalaxyGenerator((int) System.currentTimeMillis());
		galGen.genStars();
		TreeSet<Star> stars = galGen.getBinaryTree();
		ConnectionGenerator conGen = new ConnectionGenerator();
		conGen.generateConnectionRange(stars);
		this.starModel = new FilteredListModel<Star>(stars);

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
		
		JScrollPane starMapPane = new JScrollPane();
		sl_starMapPanel.putConstraint(SpringLayout.WEST, starMapPane, 0, SpringLayout.WEST, starMapPanel);
		sl_starMapPanel.putConstraint(SpringLayout.SOUTH, starMapPane, 0, SpringLayout.SOUTH, starMapPanel);
		sl_starMapPanel.putConstraint(SpringLayout.EAST, starMapPane, 0, SpringLayout.EAST, starMapPanel);
		starMapPanel.add(starMapPane);
		this.canvas = new StarMapPanel(stars);
		this.canvas.setPreferredSize(new Dimension(1024, 1024));
		starMapPane.setViewportView(this.canvas);
		
		JLabel starMapLabel = new JLabel("Star Map");
		sl_starMapPanel.putConstraint(SpringLayout.NORTH, starMapLabel, 6, SpringLayout.NORTH, starMapPanel);
		sl_starMapPanel.putConstraint(SpringLayout.WEST, starMapLabel, 6, SpringLayout.WEST, starMapPanel);
		starMapPanel.add(starMapLabel);
		
		this.btnGo = new JButton("Go!");
		this.btnGo.addActionListener(this);
		sl_starMapPanel.putConstraint(SpringLayout.NORTH, starMapPane, 6, SpringLayout.SOUTH, this.btnGo);
		sl_starMapPanel.putConstraint(SpringLayout.NORTH, this.btnGo, 6, SpringLayout.NORTH, starMapPanel);
		sl_starMapPanel.putConstraint(SpringLayout.EAST, this.btnGo, -6, SpringLayout.EAST, starMapPanel);
		starMapPanel.add(this.btnGo);
		
		this.tabbedPane = new JTabbedPane(SwingConstants.TOP);
		splitPane.setLeftComponent(this.tabbedPane);
		
		JPanel tripPanel = new JPanel();
		this.tabbedPane.addTab("Trip Planner", null, tripPanel, null);
		SpringLayout sl_tripPanel = new SpringLayout();
		tripPanel.setLayout(sl_tripPanel);
		
		JLabel rangeLabel = new JLabel("Range");
		sl_tripPanel.putConstraint(SpringLayout.NORTH, rangeLabel, 10, SpringLayout.NORTH, tripPanel);
		sl_tripPanel.putConstraint(SpringLayout.WEST, rangeLabel, 10, SpringLayout.WEST, tripPanel);
		tripPanel.add(rangeLabel);
		
		this.rangeSlider = new JSlider();
		this.rangeSlider.setMinimum(0);
		this.rangeSlider.setMaximum(512);
		this.rangeSlider.setValue(256);
		sl_tripPanel.putConstraint(SpringLayout.SOUTH, rangeLabel, 0, SpringLayout.SOUTH, this.rangeSlider);
		sl_tripPanel.putConstraint(SpringLayout.NORTH, this.rangeSlider, 10, SpringLayout.NORTH, tripPanel);
		sl_tripPanel.putConstraint(SpringLayout.WEST, this.rangeSlider, 6, SpringLayout.EAST, rangeLabel);
		tripPanel.add(this.rangeSlider);
		
		this.btnFindTrips = new JButton("Find trips");
		this.btnFindTrips.addActionListener(this);
		sl_tripPanel.putConstraint(SpringLayout.EAST, this.rangeSlider, -6, SpringLayout.WEST, this.btnFindTrips);
		sl_tripPanel.putConstraint(SpringLayout.NORTH, this.btnFindTrips, 10, SpringLayout.NORTH, tripPanel);
		sl_tripPanel.putConstraint(SpringLayout.EAST, this.btnFindTrips, -10, SpringLayout.EAST, tripPanel);
		tripPanel.add(this.btnFindTrips);
		
		this.tripList = new JList();
		this.starTripList = new JList(this.starModel);
		this.starTripList.addListSelectionListener(this);
		JScrollPane s1 = new JScrollPane(this.starTripList);
		sl_tripPanel.putConstraint(SpringLayout.NORTH, s1, 6, SpringLayout.SOUTH, this.btnFindTrips);
		sl_tripPanel.putConstraint(SpringLayout.WEST, s1, 10, SpringLayout.WEST, tripPanel);
		sl_tripPanel.putConstraint(SpringLayout.EAST, s1, -10, SpringLayout.EAST, tripPanel);
		sl_tripPanel.putConstraint(SpringLayout.SOUTH, s1, -6, SpringLayout.VERTICAL_CENTER, tripPanel);
		tripPanel.add(s1);
				
		this.tripSearch = new JTextField();
		sl_tripPanel.putConstraint(SpringLayout.WEST, this.tripSearch, 10, SpringLayout.WEST, tripPanel);
		sl_tripPanel.putConstraint(SpringLayout.SOUTH, this.tripSearch, -10, SpringLayout.SOUTH, tripPanel);
		sl_tripPanel.putConstraint(SpringLayout.EAST, this.tripSearch, -10, SpringLayout.EAST, tripPanel);
		tripPanel.add(this.tripSearch);
				
		JScrollPane s2 = new JScrollPane(this.tripList);
		sl_tripPanel.putConstraint(SpringLayout.NORTH, s2, 6, SpringLayout.VERTICAL_CENTER, tripPanel);
		sl_tripPanel.putConstraint(SpringLayout.WEST, s2, 10, SpringLayout.WEST, tripPanel);
		sl_tripPanel.putConstraint(SpringLayout.SOUTH, s2, -6, SpringLayout.NORTH, this.tripSearch);
		sl_tripPanel.putConstraint(SpringLayout.EAST, s2, -10, SpringLayout.EAST, tripPanel);
		tripPanel.add(s2);
				
		this.tripSearch.getDocument().addDocumentListener(this);
		
		JPanel routePanel = new JPanel();
		this.tabbedPane.addTab("Route Finder", null, routePanel, null);
		SpringLayout sl_routePanel = new SpringLayout();
		routePanel.setLayout(sl_routePanel);
		
		this.starSearch = new JTextField();
		sl_routePanel.putConstraint(SpringLayout.WEST, this.starSearch, 10, SpringLayout.WEST, routePanel);
		sl_routePanel.putConstraint(SpringLayout.EAST, this.starSearch, -10, SpringLayout.EAST, routePanel);
		sl_routePanel.putConstraint(SpringLayout.SOUTH, this.starSearch, -10, SpringLayout.SOUTH, routePanel);
		routePanel.add(this.starSearch);
		this.starSearch.getDocument().addDocumentListener(this);
		
		this.fromList = new JList();
		this.fromList.addListSelectionListener(this);
		JScrollPane p2 = new JScrollPane(this.fromList);
		sl_routePanel.putConstraint(SpringLayout.NORTH, p2, 10, SpringLayout.NORTH, routePanel);
		sl_routePanel.putConstraint(SpringLayout.WEST, p2, 10, SpringLayout.WEST, routePanel);
		sl_routePanel.putConstraint(SpringLayout.SOUTH, p2, -6, SpringLayout.NORTH, this.starSearch);
		sl_routePanel.putConstraint(SpringLayout.EAST, p2, -3, SpringLayout.HORIZONTAL_CENTER, routePanel);
		routePanel.add(p2);
		
		this.toList = new JList();
		this.toList.addListSelectionListener(this);
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
		this.filter(arg0);
	}

	@Override
	public void insertUpdate(DocumentEvent arg0) {
		this.filter(arg0);
	}

	@Override
	public void removeUpdate(DocumentEvent arg0) {
		this.filter(arg0);
	}

	private void filter(DocumentEvent arg0) {
		if (arg0.getDocument() == this.starSearch.getDocument()) {
			this.starModel.setFilter(this.starSearch.getText());
			this.starModel.update();
		}
		else {
			if (this.tripModel != null) {
				this.tripModel.setFilter(this.tripSearch.getText());
				this.tripModel.update();
			}
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		if (arg0.getValueIsAdjusting()) {
			return;
		}
		Star s = (Star) ((JList)arg0.getSource()).getSelectedValue();
		this.canvas.setHighlight(s);
		this.canvas.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == this.btnGo) {
			// Go button clicked
			switch (this.tabbedPane.getSelectedIndex()) {
			case 0:
				// Trip planner
				Trip t = (Trip) this.tripList.getSelectedValue();
				this.canvas.setPath(t.getConnections());
				this.canvas.repaint();
				break;
			case 1:
				// Path search
				AStarSearch search = new AStarSearch((Star)this.fromList.getSelectedValue(), (Star)this.toList.getSelectedValue());
				AStarSearch.State state = search.search();
				if (state == null) {
					JOptionPane.showMessageDialog(this.frmGuideToThe, "No path found", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else {
					this.canvas.setPath(state.getPath());
					this.canvas.repaint();
				}
			}
		}
		else if (arg0.getSource() == this.btnFindTrips) {
			Star s = (Star) this.starTripList.getSelectedValue();
			double r = this.rangeSlider.getValue();
			
			TripPlanner plan = new TripPlanner();
			plan.compileTrips(s, r);
			this.tripModel = new FilteredListModel<Trip>(plan.getPossibleTrips());
			this.tripList.setModel(this.tripModel);
		}
	}
}
