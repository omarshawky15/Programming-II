package eg.edu.alexu.csd.oop.draw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Map;

public class Triangle  implements Shape {
	double sL1 , sL2 ,sL3;
	Point position = new Point(0, 0) ;
	Polygon triangle = new Polygon();
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
		System.out.println("x1 = " + x1+"x2 = " + x2+"x3 = " + x3);
		System.out.println("y1 = " + y1+"y2 = " + y2+"y3 = " + y3);
		triangle= new Polygon(new int [] {(int) x1,(int)x2,(int) x3},new int [] {(int)y1,(int)y2,(int) y3},3);
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
		g2.draw(triangle);
	}

	public Object clone() throws CloneNotSupportedException {
		return null;
		// create a deep clone of the shape
	}

}
