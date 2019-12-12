package eg.edu.alexu.csd.oop.draw;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import java.awt.event.InputEvent;

public class Frame extends JFrame {
	int idx = 0, ZO = -1;
	Boolean b = true;
	Point pointStart = null;
	Point pointEnd = null;
	Shape shapeSelected = null;
	Color FillColor = Color.red;
	Color StrokeColor = Color.blue;
	ButtonGroup bg = new ButtonGroup();
	Map<String, Double> p;
	DEngine de = new DEngine();
	ArrayList<Rectangle2D.Double> resizePoints = new ArrayList<Rectangle2D.Double>();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame frame = new Frame();
					frame.setVisible(true);
					frame.setTitle("hello");
					frame.setResizable(false);
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
		setBounds(0, 0, 1920,1080);
		getContentPane().setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 622, 433);
		getContentPane().add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 622, 0 };
		gbl_panel.rowHeights = new int[] { 121, 371, 0 };
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
		gbl_p1.columnWidths = new int[] { 71, 89, 98, 142, 212, 0 };
		gbl_p1.rowHeights = new int[] { 35, 35, 0 };
		gbl_p1.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_p1.rowWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		p1.setLayout(gbl_p1);

		JRadioButton r1 = new JRadioButton("Line");
		GridBagConstraints gbc_r1 = new GridBagConstraints();
		gbc_r1.fill = GridBagConstraints.BOTH;
		gbc_r1.insets = new Insets(0, 0, 5, 5);
		gbc_r1.gridx = 0;
		gbc_r1.gridy = 0;
		p1.add(r1, gbc_r1);
		r1.setBackground(Color.RED);
		r1.setSelected(true);
		bg.add(r1);
		r1.setActionCommand("Line");

		JRadioButton r3 = new JRadioButton("Circle");
		r3.setActionCommand("Circle");
		GridBagConstraints gbc_r3 = new GridBagConstraints();
		gbc_r3.fill = GridBagConstraints.BOTH;
		gbc_r3.insets = new Insets(0, 0, 5, 5);
		gbc_r3.gridx = 1;
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
		gbc_r5.insets = new Insets(0, 0, 5, 5);
		gbc_r5.gridx = 2;
		gbc_r5.gridy = 0;
		p1.add(r5, gbc_r5);
		r5.setActionCommand("Ellipse");

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.ORANGE);
		panel_3.setLayout(null);
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.insets = new Insets(0, 0, 5, 5);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 3;
		gbc_panel_3.gridy = 0;
		p1.add(panel_3, gbc_panel_3);

		JLabel Label_2 = new JLabel("Stroke Color");
		Label_2.setHorizontalAlignment(SwingConstants.CENTER);
		Label_2.setBounds(25, 0, 105, 25);
		panel_3.add(Label_2);

		JButton bCh_1 = new JButton("");
		bCh_1.setBounds(0, 0, 33, 25);
		panel_3.add(bCh_1);
		bCh_1.setBackground(FillColor);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.ORANGE);
		panel_1.setLayout(null);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridheight = 2;
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 4;
		gbc_panel_1.gridy = 0;
		p1.add(panel_1, gbc_panel_1);
		JRadioButton r7 = new JRadioButton("Select");
		r7.setBackground(new Color(255, 255, 255));
		r7.setBounds(8, 0, 73, 30);
		panel_1.add(r7);
		bg.add(r7);
		r7.setActionCommand("Select");

		JRadioButton r9 = new JRadioButton("Painter");
		r9.setActionCommand("Painter");
		bg.add(r9);
		r9.setBackground(Color.WHITE);
		r9.setBounds(8, 35, 73, 30);
		panel_1.add(r9);

		/*
		 * JRadioButton r8 = new JRadioButton("Resize"); bg.add(r8);
		 * r8.setActionCommand("Resize"); r8.setBackground(Color.WHITE); r8.setBounds(8,
		 * 35, 73, 30); panel_1.add(r8);
		 */
		JRadioButton r4 = new JRadioButton("Square");
		r4.setBackground(Color.BLUE);
		bg.add(r4);
		r4.setActionCommand("Square");
		GridBagConstraints gbc_r4 = new GridBagConstraints();
		gbc_r4.fill = GridBagConstraints.BOTH;
		gbc_r4.insets = new Insets(0, 0, 0, 5);
		gbc_r4.gridx = 0;
		gbc_r4.gridy = 1;
		p1.add(r4, gbc_r4);

		JRadioButton r2 = new JRadioButton("Rectangle");
		GridBagConstraints gbc_r2 = new GridBagConstraints();
		gbc_r2.fill = GridBagConstraints.BOTH;
		gbc_r2.insets = new Insets(0, 0, 0, 5);
		gbc_r2.gridx = 1;
		gbc_r2.gridy = 1;
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
		gbc_r6.gridx = 2;
		gbc_r6.gridy = 1;
		p1.add(r6, gbc_r6);
		r6.setActionCommand("Triangle");

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.ORANGE);
		panel_4.setLayout(null);
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.insets = new Insets(0, 0, 0, 5);
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.gridx = 3;
		gbc_panel_4.gridy = 1;
		p1.add(panel_4, gbc_panel_4);

		JLabel Label_1 = new JLabel("Fill Color");
		Label_1.setHorizontalAlignment(SwingConstants.CENTER);
		Label_1.setBounds(35, 0, 83, 25);
		panel_4.add(Label_1);

		JButton bCh_2 = new JButton("");
		bCh_2.setBounds(0, 0, 33, 25);
		panel_4.add(bCh_2);
		bCh_2.setBackground(FillColor);
		bCh_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color tempC = JColorChooser.showDialog(null, "This is a Test", FillColor);
				if (tempC != null)
					FillColor = tempC;
				bCh_2.setBackground(FillColor);

			}
		});
		bCh_1.setBackground(StrokeColor);

		/*
		 * JSlider slider = new JSlider(); GridBagConstraints gbc_slider = new
		 * GridBagConstraints(); gbc_slider.gridwidth = 5; gbc_slider.fill =
		 * GridBagConstraints.HORIZONTAL; gbc_slider.insets = new Insets(0, 0, 0, 5);
		 * gbc_slider.gridx = 0; gbc_slider.gridy = 3; p1.add(slider, gbc_slider);
		 * slider.setMaximum(200); slider.setMinorTickSpacing(5);
		 * slider.setMajorTickSpacing(10); slider.setPaintTicks(true);
		 * slider.setPaintLabels(true);
		 */
		bCh_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color tempC = JColorChooser.showDialog(null, "This is a Test", StrokeColor);
				if (tempC != null)
					StrokeColor = tempC;
				bCh_1.setBackground(StrokeColor);

			}
		});
		r4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newShapeOption();
			}
		});
		r1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newShapeOption();
			}
		});
		r2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newShapeOption();
			}
		});
		r3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newShapeOption();
			}
		});
		r5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newShapeOption();
			}
		});
		r6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newShapeOption();
			}
		});
		r7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newShapeOption();
			}
		});
		JPanel p3 = new JPanel() {
			{
				addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						String type = bg.getSelection().getActionCommand();

						/*
						 * if (type.equals("Resize")) {
						 * 
						 * initializeResize(e); Point pp = e.getPoint(); for (int i = 0; i <
						 * resizePoints.size(); i++) { if (resizePoints.get(i).contains(pp)) ZO = i; }
						 * 
						 * } else
						 */
						if (type.equals("Painter")) {
							shapeSelected = de.ShapesContains(e.getPoint());
							if (shapeSelected != null) {
								shapeSelected.setColor(StrokeColor);
								shapeSelected.setFillColor(FillColor);
								de.updateShape(shapeSelected, shapeSelected);
								repaint();
							}
						} else if (type.equals("Select")) {
							pointStart = e.getPoint();
							shapeSelected = de.ShapesContains(pointStart);

						} else if (b) {
							idx++;
							pointStart = e.getPoint();
							pointEnd = e.getPoint();
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
						String type = bg.getSelection().getActionCommand();
						if (type.equals("Select") && shapeSelected != null) {
							de.updateShape(shapeSelected, shapeSelected);
						} else if (type.equals("Resize")) {
							/*
							 * ZO = -1; repaint();
							 */
						}

					}
				});
				addMouseMotionListener(new MouseMotionAdapter() {
					public void mouseMoved(MouseEvent e) {
						if (idx >= 1) {
							pointEnd = e.getPoint();
							repaint();
						}

					}

					public void mouseDragged(MouseEvent e) {
						String type = bg.getSelection().getActionCommand();
						if (type.equals("Resize") && shapeSelected != null) {
							/* moveResize(e); */
						} else if (type.equals("Select") && shapeSelected != null) {
							Point pp = e.getPoint();
							if (shapeSelected.contains(pp)) {
								double dx = pp.x - pointStart.x, dy = pp.y - pointStart.y;
								p = shapeSelected.getProperties();
								p.put("X1", p.get("X1") + dx);
								p.put("Y1", p.get("Y1") + dy);
								p.put("X2", p.get("X2") + dx);
								p.put("Y2", p.get("Y2") + dy);
								p.put("X3", p.get("X3") + dx);
								p.put("Y3", p.get("Y3") + dy);
								shapeSelected.setProperties(p);
								repaint();
								pointStart = pp;
							}

						}
					}
				});
			}

			private void DrawShape(Graphics g) {
				String s, type;
				type = bg.getSelection().getActionCommand();
				if (pointStart != null) {
					Shape lo = null;
					try {
						s = "eg.edu.alexu.csd.oop.draw." + type;
						lo = (Shape) Class.forName(s).newInstance();
					} catch (Exception e) {
						return;
					}
					if (idx == 1) {
						p = initializeProperties(p);
					}
					p.put("X" + idx, pointStart.getX());
					p.put("Y" + idx, pointStart.getY());
					p.put("Y" + (idx + 1), pointEnd.getY());
					p.put("X" + (idx + 1), pointEnd.getX());
					if (((type.equals("Triangle") || type.equals("Ellipse")) && idx == 1))
						return;
					lo.setProperties(p);
					lo.setFillColor(super.getBackground());
					lo.setColor(StrokeColor);
					if (b) {
						idx = 0;
						lo.setFillColor(FillColor);
						lo.setColor(StrokeColor);
						de.addShape(lo);
						p = initializeProperties(p);
					}
					lo.draw(g);
				}
			}

			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				de.refresh(g);
				Graphics2D g2 = (Graphics2D) g;
				g.setColor(Color.green);
				String type;
				type = bg.getSelection().getActionCommand();
				if (idx > 0)
					DrawShape(g);
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

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem m5 = new JMenuItem("Save as Xml");
		m5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newShapeOption();
				JFileChooser jeff = new JFileChooser();
				jeff = new JFileChooser();
				jeff.setCurrentDirectory(new File(System.getProperty("user.home")));
				jeff.setDialogTitle("Save File");
				jeff.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				JFrame jf = new JFrame();
				jf.setBounds(0, 0, 1280, 720);
				jf.setResizable(false);
				if (jeff.showSaveDialog(jf) == JFileChooser.APPROVE_OPTION) {
					//System.out.println(jeff.getSelectedFile().getAbsolutePath());
					try {
						de.save(jeff.getSelectedFile().getAbsolutePath()+".xml");
					} catch (Exception E) {
						JOptionPane jop = new JOptionPane("Error,Invalid Path", JOptionPane.ERROR_MESSAGE);
						JDialog jd = jop.createDialog("Save Failed");
						jd.setAlwaysOnTop(true);
						jd.setVisible(true);
					}
					// de.refresh(p3.getGraphics());
				}
			}
		});
		m5.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mnFile.add(m5);

		JMenuItem m6 = new JMenuItem("Load");
		m6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jeff = new JFileChooser();
				jeff = new JFileChooser();
				jeff.setCurrentDirectory(new File(System.getProperty("user.home")));
				jeff.setDialogTitle("Load File");
				jeff.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				JFrame jf = new JFrame();
				jf.setBounds(0, 0, 1280, 720);
				jf.setResizable(false);
				newShapeOption();
				if(jeff.showOpenDialog(jf)==JFileChooser.APPROVE_OPTION) {
				try {
					de.load(jeff.getSelectedFile().getAbsolutePath());
				} catch (Exception E) {
					JOptionPane jop = new JOptionPane("Error,Invalid Path", JOptionPane.ERROR_MESSAGE);
					JDialog jd = jop.createDialog("Load Failed");
					jd.setAlwaysOnTop(true);
					jd.setVisible(true);
				}
				try {
					de.undo();
					de.redo();
				} catch (Exception E) {

				}
				}
				// de.refresh(p3.getGraphics());
			}
		});
		m6.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK));
		mnFile.add(m6);
		JMenuItem m3 = new JMenuItem("Close");
		mnFile.add(m3);
		m3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));

		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);

		JMenuItem m4 = new JMenuItem("Delete");
		m4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				newShapeOption();
				String type = bg.getSelection().getActionCommand();
				if (type.equals("Select") && shapeSelected != null) {
					de.removeShape(shapeSelected);
					shapeSelected = null;
					de.refresh(p3.getGraphics());
				}
			}
		});
		m4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
		mnEdit.add(m4);

		JMenuItem m2 = new JMenuItem("Undo");
		mnEdit.add(m2);
		m2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK));
		m2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newShapeOption();
				try {
					de.undo();
				} catch (Exception E) {
					JOptionPane optionPane = new JOptionPane("Nothing Found To Undo", JOptionPane.ERROR_MESSAGE);
					JDialog dialog = optionPane.createDialog("Invalid Undo");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);
				}
				de.refresh(p3.getGraphics());
			}
		});

		JMenuItem m1 = new JMenuItem("Redo");
		mnEdit.add(m1);
		m1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, KeyEvent.CTRL_DOWN_MASK));
		m1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newShapeOption();
				try {
					de.redo();
				} catch (Exception E) {
					JOptionPane optionPane = new JOptionPane("Nothing Found To Redo", JOptionPane.ERROR_MESSAGE);
					JDialog dialog = optionPane.createDialog("Invalid Redo");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);
				}
				de.refresh(p3.getGraphics());
			}
		});
		m3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

	}

	Map<String, Double> initializeProperties(Map<String, Double> pT) {
		pT = new HashMap<String, Double>();
		for (int i = 1; i <= 3; i++) {
			pT.put("X" + i, 0.0);
			pT.put("Y" + i, 0.0);
		}
		return pT;
	}

	private void newShapeOption() {
		idx = 0;
		shapeSelected = null;
		pointStart = null;
		pointEnd = null;
		p = initializeProperties(p);
		b = true;
		repaint();
	}
}
