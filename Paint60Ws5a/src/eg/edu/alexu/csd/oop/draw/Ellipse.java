package eg.edu.alexu.csd.oop.draw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.util.HashMap;
import java.util.Map;

public class Ellipse implements Shape {
	double a , b;
	Point position = new Point(0, 0);
	Ellipse2D ellipse;
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
		double x3 = properties.get("X3");
		double y1 = properties.get("Y1");
		double y2 = properties.get("Y2");
		double y3 = properties.get("Y3");
//		System.out.println("x1 = " + x1+"x2 = " + x2+"x3 = " + x3);
//		System.out.println("y1 = " + y1+"y2 = " + y2+"y3 = " + y3);

		double a =Math.abs(x1 - x2) , b=Math.abs(y3 - y2) ;
		ellipse = new Ellipse2D.Double(x1-a, y1-b, a*2, b*2) ;
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
		incolor = color;
	}

	@Override
	public Color getFillColor() {
		return incolor;
	}

	@Override
	public void draw(Graphics canvas) {
		Graphics2D g2 = (Graphics2D) canvas;
		g2.setColor(this.getFillColor());
		g2.draw(ellipse);
	}

	public Object clone() throws CloneNotSupportedException {
		return null;
		// create a deep clone of the shape
	}

}
