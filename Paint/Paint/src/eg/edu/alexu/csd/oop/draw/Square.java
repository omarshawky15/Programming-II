package eg.edu.alexu.csd.oop.draw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Map;

public class Square implements Shape {
	double width, height, diagonalLength;
	Point position = new Point(0, 0);
	Rectangle2D outsquare = new Rectangle2D.Double();
	Rectangle2D insquare = new Rectangle2D.Double();
	Color Outcolor = Color.white;
	Color incolor = Color.white;
	Map<String, Double> properties = new HashMap<String, Double>();

	@Override
	public void setPosition(Point position) {
		this.position = position;
	}

	@Override
	public Point getPosition() {
		return position;
	}

	public Boolean contains(Point p) {
		return outsquare.contains(p);
	}

	@Override
	public void setProperties(Map<String, Double> properties) {
		if (properties != null)
			this.properties = properties;
		double x1 = this.properties.get("X1");
		double x2 = this.properties.get("X2");
		double y1 = this.properties.get("Y1");
		double y2 = this.properties.get("Y2");
		diagonalLength = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
		height = diagonalLength / Math.sqrt(2); width = height;
		double newX = (Math.min(x1, x2) != x1) ? (x1 - height) : x1;
		double newY = (Math.min(y1, y2) != y1) ? (y1 - height) : y1;
		this.properties.put("X1", newX);
		this.properties.put("X2" , newX + width);
		this.properties.put("Y1", newY);
		this.properties.put("Y2" , newY + height);
		outsquare.setRect(newX, newY, height, height);
		insquare.setRect(newX, newY, height, height);
		position = new Point((int) (newX + height / 2), (int) (newY + height / 2));
		this.properties.put("width", width);
		this.properties.put("height", height);
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
		g2.fill(insquare);
		g2.setColor(this.getColor());
		g2.draw(outsquare);
	}

	public Object clone() throws CloneNotSupportedException {
		Square temp = new Square();
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
		return (Object) temp;
	}

}
