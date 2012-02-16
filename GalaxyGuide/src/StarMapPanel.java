import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;


/**
 * TODO Put here a description of what this class does.
 *
 * @author alvessr.
 *         Created Feb 12, 2012.
 */
public class StarMapPanel extends JPanel {
	private Iterable<Star> stars;
	private Star highlight;
	private Iterable<Star> path;
	/**
	 * TODO Put here a description of what this constructor does.
	 *
	 * @param s
	 */
	public StarMapPanel(Iterable<Star> s) {
		super();
		this.stars = s;
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setBackground(Color.black);
		g2.clearRect(0, 0, this.getWidth(), this.getHeight());
		if (this.stars != null) {
			for (Star s : this.stars) {
				if (this.highlight == s) {
					g.setColor(Color.green);
				}else if(s.getName().charAt(0) == 'A'){
					g.setColor(new Color(255,255,255));
				}else if(s.getName().charAt(0) == 'B'){
					g.setColor(new Color(200,200,200));
				}else if(s.getName().charAt(0) == 'C'){
					g.setColor(new Color(150,150,150));
				} else {
					g.setColor(new Color(100,100,100));
				}
				g2.fillOval((int)(s.getPosition().x-2), (int)(s.getPosition().y-2), 4, 4);
			}
		}
		g2.setColor(Color.white);
		if (this.path != null) {
			Vector3D last = null;
			for (Star s : this.path) {
				if (last == null) {
					last = s.getPosition();
					continue;
				}
				g2.drawLine((int)last.x, (int)last.y, (int)s.getPosition().x, (int)s.getPosition().y);
				last = s.getPosition();
			}
		}
	}
	/**
	 * Returns the value of the field called 'highlight'.
	 * @return Returns the highlight.
	 */
	public Star getHighlight() {
		return this.highlight;
	}
	/**
	 * Sets the field called 'highlight' to the given value.
	 * @param highlight The highlight to set.
	 */
	public void setHighlight(Star highlight) {
		this.highlight = highlight;
	}
	/**
	 * Returns the value of the field called 'path'.
	 * @return Returns the path.
	 */
	public Iterable<Star> getPath() {
		return this.path;
	}
	/**
	 * Sets the field called 'path' to the given value.
	 * @param path The path to set.
	 */
	public void setPath(Iterable<Star> path) {
		this.path = path;
	}
}
