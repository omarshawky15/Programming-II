package eg.edu.alexu.csd.oop.draw;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

public class Frame extends JFrame {
	int idx = 0;
	ArrayList<eg.edu.alexu.csd.oop.draw.Shape> shapes = new ArrayList<eg.edu.alexu.csd.oop.draw.Shape>();
	Boolean b = true;
	Point pointStart = null;
	Point pointEnd = null;
	Color c = Color.blue;
	ButtonGroup bg = new ButtonGroup();
	Map<String, Double> p;
	DEngine de = new DEngine();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame frame = new Frame();
					frame.setVisible(true);
					frame.setTitle("hello");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Frame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 640, 480);
		getContentPane().setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 622, 433);
		getContentPane().add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 622, 0 };
		gbl_panel.rowHeights = new int[] { 52, 371, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JPanel p1 = new JPanel();
		p1.setBorder(new EmptyBorder(0, 0, 0, 0));
		p1.setBackground(Color.ORANGE);
		GridBagConstraints gbc_p1 = new GridBagConstraints();
		gbc_p1.fill = GridBagConstraints.BOTH;
		gbc_p1.insets = new Insets(0, 0, 5, 0);
		gbc_p1.gridx = 0;
		gbc_p1.gridy = 0;
		panel.add(p1, gbc_p1);
		GridBagLayout gbl_p1 = new GridBagLayout();
		gbl_p1.columnWidths = new int[] { 51, 69, 85, 0, 61, 65, 0 };
		gbl_p1.rowHeights = new int[] { 25, 0 };
		gbl_p1.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_p1.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		p1.setLayout(gbl_p1);

		JRadioButton r1 = new JRadioButton("Line");
		GridBagConstraints gbc_r1 = new GridBagConstraints();
		gbc_r1.fill = GridBagConstraints.BOTH;
		gbc_r1.insets = new Insets(0, 0, 0, 5);
		gbc_r1.gridx = 0;
		gbc_r1.gridy = 0;
		p1.add(r1, gbc_r1);
		r1.setBackground(Color.RED);
		r1.setSelected(true);
		bg.add(r1);
		r1.setActionCommand("Line");
		JRadioButton r4 = new JRadioButton("Square");
		r4.setBackground(Color.BLUE);
		bg.add(r4);
		r4.setActionCommand("Square");
		GridBagConstraints gbc_r4 = new GridBagConstraints();
		gbc_r4.fill = GridBagConstraints.BOTH;
		gbc_r4.insets = new Insets(0, 0, 0, 5);
		gbc_r4.gridx = 1;
		gbc_r4.gridy = 0;
		p1.add(r4, gbc_r4);

		JRadioButton r2 = new JRadioButton("Rectangle");
		GridBagConstraints gbc_r2 = new GridBagConstraints();
		gbc_r2.fill = GridBagConstraints.BOTH;
		gbc_r2.insets = new Insets(0, 0, 0, 5);
		gbc_r2.gridx = 2;
		gbc_r2.gridy = 0;
		p1.add(r2, gbc_r2);
		r2.setBackground(Color.PINK);
		bg.add(r2);
		r2.setActionCommand("Rectangle");
		
		JRadioButton r6 = new JRadioButton("Triangle");
		bg.add(r6);
		r6.setBackground(Color.GREEN);
		GridBagConstraints gbc_r6 = new GridBagConstraints();
		gbc_r6.fill = GridBagConstraints.BOTH;
		gbc_r6.insets = new Insets(0, 0, 0, 5);
		gbc_r6.gridx = 3;
		gbc_r6.gridy = 0;
		p1.add(r6, gbc_r6);
		r6.setActionCommand("Triangle");


		JRadioButton r3 = new JRadioButton("Circle");
		r3.setActionCommand("Circle");
		GridBagConstraints gbc_r3 = new GridBagConstraints();
		gbc_r3.fill = GridBagConstraints.BOTH;
		gbc_r3.insets = new Insets(0, 0, 0, 5);
		gbc_r3.gridx = 4;
		gbc_r3.gridy = 0;
		p1.add(r3, gbc_r3);
		r3.setBackground(Color.MAGENTA);
		bg.add(r3);
		r3.setActionCommand("Circle");

		JRadioButton r5 = new JRadioButton("Ellipse");
		bg.add(r5);
		r5.setBackground(Color.CYAN);
		GridBagConstraints gbc_r5 = new GridBagConstraints();
		gbc_r5.fill = GridBagConstraints.BOTH;
		gbc_r5.gridx = 5;
		gbc_r5.gridy = 0;
		p1.add(r5, gbc_r5);
		r5.setActionCommand("Ellipse");
		JPanel p3 = new JPanel() {
			{
				addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						if (b) {
							System.out.println("here" + idx);
							idx++;
							pointStart = e.getPoint();
							pointEnd = e.getPoint();
							String type = bg.getSelection().getActionCommand();
							if (((type.equals("Triangle") || type.equals("Ellipse")) && idx < 2)) {
								b = true;
							} else
								b = false;
						} else {
							pointEnd = e.getPoint();
							b = true;
							repaint();
						}
					}

					public void mouseReleased(MouseEvent e) {
					}
				});
				addMouseMotionListener(new MouseMotionAdapter() {
					public void mouseMoved(MouseEvent e) {
						if (idx>=1) {
							pointEnd = e.getPoint();
							System.out.println(pointEnd.x+" " + pointEnd.y);
							repaint();
						}

					}

					public void mouseDragged(MouseEvent e) {
					}
				});
			}

			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				String s, type;
				if (pointStart != null) {
					eg.edu.alexu.csd.oop.draw.Shape lo = new Line();
					try {
						type = bg.getSelection().getActionCommand();
						s = "eg.edu.alexu.csd.oop.draw." + type;
						System.out.println(s);
						lo = (Shape) Class.forName(s).newInstance();
					} catch (Exception e) {
						return;
					}
					/*for (eg.edu.alexu.csd.oop.draw.Shape sh : l) {
						g.setColor(sh.getFillColor());
						sh.draw(g);
					}*/
					de.refresh(g);
					if (idx == 1)
						p = new HashMap<String, Double>();
					p.put("X" + idx, pointStart.getX());
					p.put("Y" + idx, pointStart.getY());
					p.put("Y" + (idx + 1), pointEnd.getY());
					p.put("X" + (idx + 1), pointEnd.getX());
					System.out.println("here" + idx);
					if (((type.equals("Triangle") || type.equals("Ellipse")) && idx == 1))
						return;
					lo.setProperties(p);
					g.setColor(c);
					if (b) {
						idx = 0;
						lo.setFillColor(Color.blue);
						g.setColor(Color.blue);
						//shapes.add(lo);
						de.addShape(lo);
					}
					lo.draw(g);
				}
			}
		};
		p3.setBackground(Color.WHITE);
		GridBagConstraints gbc_p3 = new GridBagConstraints();
		gbc_p3.fill = GridBagConstraints.BOTH;
		gbc_p3.gridx = 0;
		gbc_p3.gridy = 1;
		panel.add(p3, gbc_p3);
		GridBagLayout gbl_p3 = new GridBagLayout();
		gbl_p3.columnWidths = new int[] { 0 };
		gbl_p3.rowHeights = new int[] { 0 };
		gbl_p3.columnWeights = new double[] { Double.MIN_VALUE };
		gbl_p3.rowWeights = new double[] { Double.MIN_VALUE };
		p3.setLayout(gbl_p3);
	}
}
