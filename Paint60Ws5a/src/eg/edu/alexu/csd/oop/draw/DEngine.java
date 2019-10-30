package eg.edu.alexu.csd.oop.draw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class DEngine implements DrawingEngine {
	ArrayList<eg.edu.alexu.csd.oop.draw.Shape> shapes = new ArrayList<eg.edu.alexu.csd.oop.draw.Shape>();

	@Override
	public void refresh(Graphics canvas) {
		//canvas.dispose();
		canvas.setColor(Color.black);
		Graphics2D g =(Graphics2D) canvas ;
		g.
		canvas = g;
		for (Shape shape : shapes)
			shape.draw(canvas);
	}

	@Override
	public void addShape(Shape shape) {
		shapes.add(shape);
	}

	@Override
	public void removeShape(Shape shape) {
		shapes.remove(shape);
	}

	@Override
	public void updateShape(Shape oldShape, Shape newShape) {
		int idx = shapes.indexOf(oldShape);
		shapes.add(idx, newShape);
		shapes.remove(idx + 1);
	}

	@Override
	public Shape[] getShapes() {
		return shapes.toArray(new Shape[shapes.size()]);
	}

	@Override
	public List<Class<? extends Shape>> getSupportedShapes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub

	}

	@Override
	public void redo() {
		// TODO Auto-generated method stub

	}

	@Override
	public void save(String path) {
		// TODO Auto-generated method stub

	}

	@Override
	public void load(String path) {
		// TODO Auto-generated method stub

	}

}
