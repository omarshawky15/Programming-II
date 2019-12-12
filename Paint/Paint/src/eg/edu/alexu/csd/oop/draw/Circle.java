package eg.edu.alexu.csd.oop.draw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.util.HashMap;
import java.util.Map;

public class Circle implements Shape {
	public double height , width;
	public Point position = new Point(0, 0);
	public Ellipse2D outcircle=new Ellipse2D.Double(0,0,0,0),incircle=new Ellipse2D.Double(0,0,0,0) ;
	public Color Outcolor = Color.red;
	public Color incolor = Color.red;
	public Map<String, Double> properties = new HashMap<String, Double>();

	@Override
	public void setPosition(Point position) {
		this.position = position;
	}

	@Override
	public Point getPosition() {
		return position;
	}

	public Boolean contains(Point p) {
		return outcircle.contains(p);
	}

	@Override
	public void setProperties(Map<String, Double> properties) {
		this.properties = properties;
		double x1 = properties.get("X1");
		double x2 = properties.get("X2");
		double y1 = properties.get("Y1");
		double y2 = properties.get("Y2");
		height = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
		outcircle = new Ellipse2D.Double(x1 - height , y1 - height , height * 2 , height * 2 );
		incircle = new Ellipse2D.Double(x1 - height+0.5 , y1 - height+0.5, height * 2-1, height * 2-1);
		height *=2;
		width = height;
		this.properties.put("diameter",height );
		this.properties.put("height",height );
		this.properties.put("width",height );
		this.position = new Point((int)x1,(int)y1);
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
		g2.fill(incircle);
		g2.setColor(this.getColor());
		g2.draw(outcircle);
	}

	public Object clone() throws CloneNotSupportedException {
		Circle temp = new Circle();
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
