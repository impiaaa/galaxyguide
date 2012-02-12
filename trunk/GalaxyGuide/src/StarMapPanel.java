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
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setBackground(Color.black);
		g2.setColor(Color.white);
		g2.clearRect(0, 0, this.getWidth(), this.getHeight());
		for (Star s : this.stars) {
			g2.drawOval((int)(s.getPosition().x-2), (int)(s.getPosition().y-2), 4, 4);
		}
	}
}
