package eg.edu.alexu.csd.oop.draw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Map;

public class Rectangle implements Shape {
	public double width, height;
	public Point position = new Point(0, 0);
	public Rectangle2D outrec = new Rectangle2D.Double(0, 0, 0, 0);
	public Rectangle2D inrec = new Rectangle2D.Double(0, 0, 0, 0);
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

	public Boolean contains(Point p) {
		return outrec.contains(p);
	}

	@Override
	public void setProperties(Map<String, Double> properties) {
		this.properties = properties;

		double x1 = this.properties.get("X1");
		double x2 = this.properties.get("X2");
		double y1 = this.properties.get("Y1");
		double y2 = this.properties.get("Y2");
		/*try {
			width = properties.get("width");
			height = properties.get("height");
		} catch (Exception e) {*/
			width = Math.abs(x2 - x1);
			height = Math.abs(y2 - y1);
		//}
		outrec.setRect(Math.min(x1, x2), Math.min(y1, y2), width, height);
		inrec.setRect(Math.min(x1, x2) + 1, Math.min(y1, y2) + 1, width - 1, height - 1);
		position = new Point((int) (Math.min(x1, x2) + width / 2), (int) (Math.min(y1, y2) + height / 2));

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
		g2.fill(inrec);
		g2.setColor(this.getColor());
		g2.draw(outrec);
	}

	public Object clone() throws CloneNotSupportedException {
		Rectangle temp = new Rectangle();
		temp.setProperties(new HashMap<String, Double>(properties));
		temp.setColor(new Color(Outcolor.getRGB()));
		temp.setFillColor(new Color(incolor.getRGB()));
		temp.setPosition(new Point(position));
		return (Object)temp;
	}

}
