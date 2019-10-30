package eg.edu.alexu.csd.oop.draw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.util.HashMap;
import java.util.Map;

public class Line implements Shape {
	double length;
	Point position = new Point(0, 0);
	Line2D l = new Line2D.Double();
	Color Outcolor = Color.red;
	Color incolor = Color.red;
	Map<String, Double> properties = new HashMap<String, Double>();

	@Override
	public void setPosition(Point position) {
		this.position = position;
	}

	@Override
	public Point getPosition() {
		return position;
	}

	@Override
	public void setProperties(Map<String, Double> properties) {
		this.properties = properties;
		double x1 = properties.get("X1");
		double x2 = properties.get("X2");
		double y1 = properties.get("Y1");
		double y2 = properties.get("Y2");
		l.setLine(x1, y1, x2, y2);
		position = new Point((int) (x2 - x1), (int) (y2 - y1));
	}

	@Override
	public Map<String, Double> getProperties() {
		return properties;
	}

	@Override
	public void setColor(Color color) {
		this.Outcolor = color;
	}

	@Override
	public Color getColor() {
		return Outcolor;
	}

	@Override
	public void setFillColor(Color color) {
		incolor = color ;
	}

	@Override
	public Color getFillColor() {
		return incolor;
	}

	@Override
	public void draw(Graphics canvas) {
		Graphics2D g2 = (Graphics2D) canvas;
		g2.setColor(this.getFillColor());
		g2.draw(l);
	}

	public Object clone() throws CloneNotSupportedException {
		return null;
		// create a deep clone of the shape
	}

}
