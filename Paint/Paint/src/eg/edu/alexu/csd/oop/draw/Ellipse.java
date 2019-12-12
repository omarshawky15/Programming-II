package eg.edu.alexu.csd.oop.draw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.util.HashMap;
import java.util.Map;

public class Ellipse implements Shape {
	public double width , height;
	public Point position = new Point(0, 0);
	public Ellipse2D outellipse;
	public Ellipse2D inellipse;
	public Color Outcolor = Color.red;
	public Color incolor = Color.red;
	Map<String, Double> properties = new HashMap<String, Double>();

	@Override
	public void setPosition(Point position) {
		this.position = position;
	}
	public Boolean contains(Point p){
		return outellipse.contains(p);
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
		
		width =Math.abs(x1 - x2) ; height=Math.abs(y3 - y2) ;
		outellipse = new Ellipse2D.Double(x1-width, y1-height, width*2, height*2) ;
		inellipse = new Ellipse2D.Double(x1-width+0.5, y1-height+0.5, width*2-1, height*2-1) ;
		this.position = new Point((int)x1,(int)y1);
		width *=2 ; height*=2 ;
		this.properties.put("a",width );
		this.properties.put("b",height );
		this.properties.put("height",height );
		this.properties.put("width",width );

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
		g2.fill(inellipse);
		g2.setColor(this.getColor());
		g2.draw(outellipse);
	}

	public Object clone() throws CloneNotSupportedException {
		Ellipse temp = new Ellipse();
		Map<String, Double> proTemp = new HashMap<String, Double>();
		proTemp.put("X1", new Double(properties.get("X1")));
		proTemp.put("X2", new Double(properties.get("X2")));
		proTemp.put("X3", new Double(properties.get("X3")));
		proTemp.put("Y1", new Double(properties.get("Y1")));
		proTemp.put("Y2", new Double(properties.get("Y2")));
		proTemp.put("Y3", new Double(properties.get("Y3")));
		temp.setProperties(proTemp);
		temp.setColor(new Color(Outcolor.getRGB()));
		temp.setFillColor(new Color(incolor.getRGB()));
		temp.setPosition(new Point(position));
		return (Object)temp;
	}

}
