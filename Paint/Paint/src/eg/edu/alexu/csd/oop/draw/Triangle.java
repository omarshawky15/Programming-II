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
	public double sL1 , sL2 ,sL3;
	public Point position = new Point(0, 0) ;
	public Polygon outtriangle = new Polygon(new int[] {0,0,0} , new int[] {0,0,0} , 3);
	public Polygon intriangle = new Polygon(new int[] {0,0,0} , new int[] {0,0,0} , 3);
	public Color Outcolor = Color.red;
	public Color incolor = Color.red;
	Map<String, Double> properties = new HashMap<String, Double>();
	@Override
	public void setPosition(Point position) {
		this.position = position;
	}

	@Override
	public Point getPosition() {
		return position;
	}
	public Boolean contains(Point p){
		return outtriangle.contains(p);
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
		outtriangle= new Polygon(new int [] {(int) x1,(int)x2,(int) x3},new int [] {(int)y1,(int)y2,(int) y3},3);
		intriangle= new Polygon(new int [] {(int) x1,(int)x2,(int) x3},new int [] {(int)y1,(int)y2,(int) y3},3);
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
		g2.fill(intriangle);
		g2.setColor(this.getColor());
		g2.draw(outtriangle);
	}

	public Object clone() throws CloneNotSupportedException {
		Triangle temp = new Triangle();
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
