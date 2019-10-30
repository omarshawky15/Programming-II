package eg.edu.alexu.csd.oop.draw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

public class Panel extends JPanel {

	int index = 0;
	ArrayList<eg.edu.alexu.csd.oop.draw.Shape> l = new ArrayList<eg.edu.alexu.csd.oop.draw.Shape>();
	Boolean b = true;
	Point pointStart = null;
	Point pointEnd = null;
	{
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (b) {
					pointStart = e.getPoint();
					pointEnd = e.getPoint();
					b = false;
				} else {
					pointEnd = e.getPoint();
					b = true;
					repaint();
				}
			}
			public void mouseReleased(MouseEvent e) {
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseMoved(MouseEvent e) {
				if (!b) {
					pointEnd = e.getPoint();
					repaint();
				}

			}
			public void mouseDragged(MouseEvent e) {
			}
		});
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Map<String, Double> p = new HashMap<String, Double>();
		/*if (!b) {
			g.setColor(Color.decode("#0000FF"));
		}
		else {
			g.setColor(Color.decode("#ff0000"));
		}*/
		if (pointStart != null) {
			eg.edu.alexu.csd.oop.draw.Shape lo = new Circle();
			p.put("X1", pointStart.getX());
			p.put("Y1", pointStart.getY());
			p.put("Y2", pointEnd.getY());
			p.put("X2", pointEnd.getX());
			lo.setProperties(p);	
			lo.draw(g);
			if(b) {
				lo.setFillColor(Color.blue);
				l.add(lo);
			}
		}
		for(eg.edu.alexu.csd.oop.draw.Shape s : l ) {
			g.setColor(s.getFillColor());
			s.draw(g);
		}
		
	}

	public Panel() {
	}

}
