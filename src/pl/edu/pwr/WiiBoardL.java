package pl.edu.pwr;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Timer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import edu.unsw.cse.wiiboard.event.WiiBoardButtonEvent;
import edu.unsw.cse.wiiboard.event.WiiBoardListener;
import edu.unsw.cse.wiiboard.event.WiiBoardMassEvent;
import edu.unsw.cse.wiiboard.event.WiiBoardStatusEvent;
import javax.swing.JButton;

public class WiiBoardL implements WiiBoardListener, ActionListener {

	public static double bl, br, tl, tr, rbl, rbr, rtl, rtr;
	private long timeStart, time, calStartTime;
	private static JFrame graphFrame;
	static JPanel graph;
	private static JPanel contentPane;
	private static SaveOptionsPanel savePanel;
	private static GraphPanel graphPanel;
	private static DataPanel dataPanel;
	public static double massX;
	public static double massY;
	private PrintWriter out;
	private boolean firstSave = true;
	private double[] calVec = { 0, 0, 0, 0 };
	private boolean calibration = false;
	task zadanie = new task();
	private static Timer timer = new Timer();
	public static JButton btnStart;
	private JButton btnStop;
	static boolean startBadania = false;
	static boolean reset = true;
	private JButton btnReset;
	static long a = 0, b = 0, c = 0, d = 0, e = 0, f = 0, g = 0, h = 0;
	static long t1 = 0, t2 = 0, t3 = 0, t4 = 0, t5 = 0, t6 = 0, t7 = 0, t8 = 0;
	static long rt1 = 0, rt2 = 0, rt3 = 0, rt4 = 0, rt5 = 0, rt6 = 0, rt7 = 0,
			rt8 = 0;
	public static double COPx, COPy;
	ImageIcon icon = new ImageIcon(
			"/home/grzesiek/Pobrane/balanceBoardWiiSyncBatRem.png");

	@SuppressWarnings("serial")
	public WiiBoardL() {
		try {
			graphFrame = new JFrame();
			graphFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			graphFrame.setTitle("Mass Graph");
			graphFrame.setSize(800, 600);
			graphFrame.setResizable(true);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane.setLayout(new BorderLayout(0, 0));
			graphFrame.setContentPane(contentPane);

			JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.TOP);
			contentPane.add(tabbedPane, BorderLayout.CENTER);

			timer.schedule(zadanie, 1, 8000);

			graphFrame.setVisible(true);
			graph = new JPanel() {
				@Override
				public void paintComponent(Graphics graphics) {
					graphics.clearRect(0, 0, graph.getWidth(),
							graph.getHeight());
					graphics.fillRect(0, 0, graph.getWidth(), graph.getHeight());
					graphics.setColor(Color.WHITE);
					graphics.drawLine(0, graph.getHeight() / 2,
							graph.getWidth(), graph.getHeight() / 2);
					graphics.drawLine(graph.getWidth() / 2, 0,
							graph.getWidth() / 2, graph.getHeight());

					/*
					 * //siatka graphics.setColor(Color.ORANGE); for(int k=160;
					 * k<=640; k+=30){ graphics.drawLine(k,0,k,600); }
					 * 
					 * for(int k=60; k<=510; k+=30){
					 * graphics.drawLine(0,k,800,k); }
					 */
					/*
					 * graphics.setColor(Color.WHITE);
					 * graphics.drawRect((graph.getWidth()/2) - 45,
					 * (graph.getHeight()/2)-45, 90, 90);
					 * 
					 * graphics.setColor(Color.GREEN); graphics.drawRect(385,
					 * 60, 30, 30); //górny kwadrat graphics.drawRect(535, 135,
					 * 30, 30); //prawy górny kwadrat graphics.drawRect(610,
					 * 285, 30, 30); //prawy kwadrat graphics.drawRect(535, 435,
					 * 30, 30); //prawy dolny kwadrat graphics.drawRect(385,
					 * 510, 30, 30); //dolny kwadrat graphics.drawRect(235, 435,
					 * 30, 30); //lewy dolny kwadrat graphics.drawRect(160, 285,
					 * 30, 30); //lewy kwadrat graphics.drawRect(235, 135, 30,
					 * 30); //lewy górny kwadrat
					 */
					/*
					 * if(startBadania == true){ graphics.setColor(Color.GREEN);
					 * 
					 * graphics.fillRect(zadanie.x, zadanie.y, 30, 30);
					 * //podświetlanie kolejnego kwadratu
					 * 
					 * if(task.k1 == true){ a = System.currentTimeMillis();
					 * task.k1 = false; } if(task.r1 == true){ t1 =
					 * System.currentTimeMillis(); task.r1 = false; } rt1 =
					 * t1-a;
					 * 
					 * if(task.k2 == true){ b = System.currentTimeMillis();
					 * task.k2 = false; } if(task.r2 == true){ t2 =
					 * System.currentTimeMillis(); task.r2 = false; } rt2 =
					 * t2-b;
					 * 
					 * if(task.k3 == true){ c = System.currentTimeMillis();
					 * task.k3 = false; } if(task.r3 == true){ t3 =
					 * System.currentTimeMillis(); task.r3 = false; } rt3 =
					 * t3-c; }
					 */
					graphics.setColor(Color.RED);
					graphics.fillOval(
							(int) (massX * graph.getWidth() + graph.getWidth()
									/ 2 - 15), (int) (massY * graph.getHeight()
									+ graph.getHeight() / 2 - 15), 30, 30);
					graphics.setColor(Color.WHITE);
					graphics.drawString(
							"Mass: " + Double.toString(roundd(tl+tr+bl+br, 2))
									+ " kg",
							(int) (graph.getWidth() / 2 - graph.getWidth() * 0.3),
							graph.getHeight() / 5);
					graphics.drawString(
							"tl: " + roundd(tl, 2) + "       tr: "
									+ roundd(tr, 2),
							(int) (graph.getWidth() / 2 + graph.getWidth() * 0.3),
							graph.getHeight() / 5);
					graphics.drawString(
							"bl: " + roundd(bl, 2) + "       br: "
									+ roundd(br, 2),
							(int) (graph.getWidth() / 2 + graph.getWidth() * 0.3),
							graph.getHeight() / 5 + 20);
					/*
					 * graphics.drawString( import sun.applet.Main; "CT1: " +
					 * rt1, (int) (graph.getWidth() / 2 + graph.getWidth() *
					 * 0.3), graph.getHeight() / 5 + 40); graphics.drawString(
					 * "CT2: " + rt2, (int) (graph.getWidth() / 2 +
					 * graph.getWidth() * 0.3), graph.getHeight() / 5 + 60);
					 * graphics.drawString( "CT3: " + rt3, (int)
					 * (graph.getWidth() / 2 + graph.getWidth() * 0.3),
					 * graph.getHeight() / 5 + 80); graphics.drawString( "A: " +
					 * roundd(a, 2), (int) (graph.getWidth() / 2 +
					 * graph.getWidth() * 0.3), graph.getHeight() / 5 + 100);
					 * graphics.drawString( "T1: " + roundd(t1, 2), (int)
					 * (graph.getWidth() / 2 + graph.getWidth() * 0.3),
					 * graph.getHeight() / 5 + 120); graphics.drawString( "T2: "
					 * + roundd(t2, 2), (int) (graph.getWidth() / 2 +
					 * graph.getWidth() * 0.3), graph.getHeight() / 5 + 140);
					 * graphics.drawString( "T3: " + roundd(t3, 2), (int)
					 * (graph.getWidth() / 2 + graph.getWidth() * 0.3),
					 * graph.getHeight() / 5 + 160); graphics.drawString(
					 * "MASSX: " + roundd(massX, 2), (int) (graph.getWidth() / 2
					 * + graph.getWidth() * 0.3), graph.getHeight() / 5 + 180);
					 * graphics.drawString( "MASSY: " + roundd(massY, 2), (int)
					 * (graph.getWidth() / 2 + graph.getWidth() * 0.3),
					 * graph.getHeight() / 5 + 200);
					 */

				}
			};

			graph.setDoubleBuffered(true);
			tabbedPane.addTab("COP visualization", null, graph,
					"See where the center of pressure is");

			btnStart = new JButton("Start");
			btnStart.setActionCommand("start");
			btnStart.addActionListener(this);
			// graph.add(btnStart);

			btnStop = new JButton("Stop");
			btnStop.setActionCommand("stop");
			btnStop.addActionListener(this);
			// graph.add(btnStop);

			btnReset = new JButton("Reset");
			btnReset.setActionCommand("reset");
			btnReset.addActionListener(this);
			// graph.add(btnReset);

			dataPanel = new DataPanel();
			tabbedPane.addTab("Personal data", null, dataPanel, "Patient data");

			savePanel = new SaveOptionsPanel();
			tabbedPane.addTab("Save to file", null, savePanel,
					"Save acquired data to raw text file");

			graphPanel = new GraphPanel();
			tabbedPane.addTab("Graph", null, graphPanel,
					"Display a graph in real time");

			JOptionPane
					.showMessageDialog(
							graph,
							"To synchronize your Wii Balance Board:\n"
									+ "1. Press OK now.\n2. Turn around your Wii Balance Board.\n"
									+ "3. Take off battery cover.\n"
									+ "4. Press the red sync button.",
							"Synchronization", JOptionPane.INFORMATION_MESSAGE,
							icon);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void wiiBoardButtonEvent(WiiBoardButtonEvent buttonEvent) {
		if (buttonEvent.isPressed()) {
			// JOptionPane.showMessageDialog(graph, "Calibration started...");
			System.out.println("Calibration started...");
			calStartTime = System.currentTimeMillis();
			calibration = true;
		}

	}

	@Override
	public void wiiBoardDisconnected() {
		System.out.println("Board disconnected");

	}

	@Override
	public void wiiBoardMassReceived(WiiBoardMassEvent massEvent) {
		rbl = massEvent.getBottomLeft();
		rbr = massEvent.getBottomRight();
		rtl = massEvent.getTopLeft();
		rtr = massEvent.getTopRight();

		bl = rbl - calVec[0];
		br = rbr - calVec[1];
		tl = rtl - calVec[2];
		tr = rtr - calVec[3];

		if (massEvent.getTotalWeight() > 3) {
			double topMass = tl + tr;
			double bottomMass = bl + br;
			double leftMass = tl + bl;
			double rightMass = tr + br;

			double vertRange = topMass + bottomMass;
			double horizRange = rightMass + leftMass;
			massX = (rightMass - leftMass) / horizRange;
			massY = -(topMass - bottomMass) / vertRange;
		} else {
			massX = 0;
			massY = 0;
		}
		COPx = 21 * ((tr + br) - (tl + bl)) / (tl + tr + bl + br);
		COPy = 12 * ((tl + tr) - (bl + br)) / (tl + tr + bl + br);

		graph.repaint();

		if ((tl + tr + bl + br) != 0) {
			if (savePanel.isSave()) {
				try {
					if (firstSave) {
						out = new PrintWriter(savePanel.getFileSave());
						timeStart = System.currentTimeMillis();
						out.println("Name:" + "\t" + DataPanel.imie + "\n"
								+ "Surname:" + "\t" + DataPanel.nazwisko + "\n"
								+ "Date of Birth:" + "\t" + DataPanel.dzien
								+ "." + DataPanel.miesiac + "." + DataPanel.rok
								+ "r.\n" + "Age:" + "\t" + DataPanel.wiek1
								+ "\n" + "Sex:" + "\t" + DataPanel.plec + "\n"
								+ "Height:" + "\t" + DataPanel.wzrost + "\n");
						out.println("t [ms]" + "\t" + "bl [kg]" + "\t\t"
								+ "br [kg]" + "\t\t" + "tl [kg]" + "\t\t"
								+ "tr [kg]" + "\t\t" + "COPx [cm]" + "\t "
								+ "COPy [cm]");
						firstSave = false;
					}
					time = System.currentTimeMillis() - timeStart;
					out.println(time + "\t" + roundd(bl, 6) + "\t"
							+ roundd(br, 6) + "\t" + roundd(tl, 6) + "\t"
							+ roundd(tr, 6) + "\t" + roundd(COPx, 6) + "\t"
							+ "  " + roundd(COPy, 6));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} finally {
					if (out != null && savePanel.isStopClicked()) {
						out.close();
						System.out.println("File closed");
						savePanel.setStopClicked(false);
						firstSave = true;
					}
				}
			} else if (savePanel.isStopClicked()) {
				out.close();
				System.out.println("File closed");
				savePanel.setStopClicked(false);
				firstSave = true;
			}
			if (calibration) {

				int licznik = 0;
				double blsum = 0;
				double brsum = 0;
				double tlsum = 0;
				double trsum = 0;

				if ((int) (System.currentTimeMillis() - calStartTime) < 10000) {
					blsum = +rbl;
					brsum = +rbr;
					tlsum = +rtl;
					trsum = +rtr;
					licznik = +1;

					if (licznik > 0) {
						calVec[0] = blsum / licznik;
						calVec[1] = brsum / licznik;
						calVec[2] = tlsum / licznik;
						calVec[3] = trsum / licznik;
					}
				} else {
					calibration = false;
					System.out.println("Calibration completed!");
				}

			}
		}
	}

	@Override
	public void wiiBoardStatusReceived(WiiBoardStatusEvent status) {
	}

	private static double roundd(double value, int places) {
		return (double) Math.round(value * Math.pow(10, places))
				/ Math.pow(10, places);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("start")) {
			startBadania = true;
			/*
			 * reset = false; btnReset.setForeground(Color.BLACK);
			 */
		} else if (e.getActionCommand().equals("stop")) {
			startBadania = false;
		} else if (e.getActionCommand().equals("reset")) {
			reset = true;
			btnReset.setForeground(Color.RED);
		}
	}
}
