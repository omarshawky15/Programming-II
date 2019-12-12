package eg.edu.alexu.csd.oop.draw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.beans.ExceptionListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.ServiceLoader;

public class DEngine implements DrawingEngine {
	public ArrayList<Shape> shapes = new ArrayList<Shape>();
	public Deque<ArrayList<Shape>> CurrentOp = new LinkedList<ArrayList<Shape>>();
	public Deque<ArrayList<Shape>> Qu = new LinkedList<ArrayList<Shape>>();
	public Deque<ArrayList<Shape>> Qr = new LinkedList<ArrayList<Shape>>();

	@Override
	public void refresh(Graphics canvas) {
		Graphics2D g2 = (Graphics2D) canvas;
		g2.setColor(Color.white);
		g2.fillRect(0, 0, 4096, 2160);
		for (Shape shape : shapes) {
			shape.draw(canvas);
		}
	}

	public Shape ShapesContains(java.awt.Point p) {
		for (int i = shapes.size() - 1; i > -1; i--)
			if (shapes.get(i).contains(p))
				return shapes.get(i);
		return null;
	}

	@Override
	public void addShape(Shape shape) {
		try {
			addOperation(Qu, CurrentOp.getFirst());
		} catch (Exception e) {
			CurrentOp.addFirst(new ArrayList<Shape>());
			addOperation(Qu, CurrentOp.getFirst());
		}
		CurrentOp.clear();
		shapes.add(shape);
		addOperation(CurrentOp, shapes);
		Qr.clear();
	}

	@Override
	public void removeShape(Shape shape) {
		try {
			addOperation(Qu, CurrentOp.getFirst());
		} catch (Exception e) {
			CurrentOp.addFirst(new ArrayList<Shape>());
			addOperation(Qu, CurrentOp.getFirst());
		}
		CurrentOp.clear();
		shapes.remove(shape);
		addOperation(CurrentOp, shapes);
		Qr.clear();

	}

	@Override
	public void updateShape(Shape oldShape, Shape newShape) {
		try {
			addOperation(Qu, CurrentOp.getFirst());
		} catch (Exception e) {
			CurrentOp.addFirst(new ArrayList<Shape>());
			addOperation(Qu, CurrentOp.getFirst());
		}
		CurrentOp.clear();
		int id = shapes.indexOf(oldShape);
		shapes.add(id, newShape);
		shapes.remove(id + 1);
		addOperation(CurrentOp, shapes);
		Qr.clear();

	}

	@Override
	public Shape[] getShapes() {
		try {
			return (shapes.toArray(new Shape[shapes.size()]));
		} catch (Exception e) {
		}
		return null;
	}

	@Override
	public List<Class<? extends Shape>> getSupportedShapes() {
		List<Class<? extends Shape>> list = new ArrayList<Class<? extends Shape>>();
		ServiceLoader<Shape> loader;
		loader = ServiceLoader.load(Shape.class, null);
		for (Shape s : loader) {
			list.add((Class<? extends Shape>) s.getClass());
		}
		return list;
	}

	@Override
	public void undo() {
		shapes = getOperation(Qu, Qr);
	}

	@Override
	public void redo() {
		shapes = getOperation(Qr, Qu);

	}

	private void addOperation(Deque<ArrayList<Shape>> Q1, ArrayList<Shape> op) {
		ArrayList<Shape> temp = new ArrayList<Shape>();
		for (int i = 0; i < op.size(); i++) {
			try {
				temp.add((Shape) op.get(i).clone());
			} catch (Exception e) {
			}
		}
		Q1.addLast(temp);
	}

	private ArrayList<Shape> getOperation(Deque<ArrayList<Shape>> Q1, Deque<ArrayList<Shape>> Q2) {
		while (Q1.size() > 20)
			Q1.pollFirst();
		while (Q2.size() > 20)
			Q2.pollFirst();
		ArrayList<Shape> org = Q1.pollLast();

		if (org == null)
			throw new NullPointerException();
		addOperation(Q2, CurrentOp.getFirst());
		CurrentOp.clear();
		addOperation(CurrentOp, org);
		return org;
	}

	@Override
	public void save(String path) {
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(path);
		} catch (FileNotFoundException e) {
			throw new NullPointerException();
		}
		XMLEncoder xml = new XMLEncoder(fos);
		xml.writeObject(this);
		xml.close();
		try {
			fos.close();
		} catch (IOException e) {
			throw new NullPointerException();
		}
	}

	@Override
	public void load(String path) {
		FileInputStream fos;
		try {
			fos = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			throw new NullPointerException();
		}
		XMLDecoder xml = new XMLDecoder(fos);
		DEngine e = (DEngine) xml.readObject();
		this.Qu = e.Qu;
		this.Qr = e.Qr;
		this.CurrentOp = e.CurrentOp;
		this.shapes = e.shapes;
		xml.close();
		try {
			fos.close();
		} catch (IOException E) {
			throw new NullPointerException();
		}
	}
	public DEngine() {
		
	}
}
