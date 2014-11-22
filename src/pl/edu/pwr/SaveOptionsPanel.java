package pl.edu.pwr;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.SwingConstants;

import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class SaveOptionsPanel extends JPanel implements ActionListener {
	static boolean save = false;
	private boolean stopClicked = false;
	private static JTextField tfPath;
	private static JTextField tfFileName;
	private static JButton btnBrowse, btnStart;
	private static File filePath, fileSave;
	private static JFileChooser fc;
	private static Date date;
	private static SimpleDateFormat sdf;

	private static JLabel lblTestTime;
	static JTextField tfTestTime;

	static JLabel lblTimeRemaining;
	private static JLabel lblCountdown;

	static int czasPomiaru, minuty, sekundy, licznik;
	private Timer timer;

	TimerTask odliczanie;

	/**
	 * Create the panel.
	 */
	public SaveOptionsPanel() {

		JLabel lblFilePath = new JLabel("File path:");
		lblFilePath.setHorizontalAlignment(SwingConstants.CENTER);

		tfPath = new JTextField();
		tfPath.setColumns(10);
		setLayout(new MigLayout("", "[58px,grow][134px,grow]",
				"[28px][29px][][][28px][28px]"));
		add(lblFilePath, "cell 0 0,alignx center,aligny center");
		add(tfPath, "cell 1 0,growx,aligny center");

		JLabel lblFileName = new JLabel("File name:");
		lblFileName.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblFileName, "cell 0 1,alignx center,aligny center");

		tfFileName = new JTextField();
		tfFileName.setColumns(10);
		add(tfFileName, "cell 1 1,growx,aligny center");

		btnBrowse = new JButton("Browse");
		btnBrowse.setActionCommand("browse");
		btnBrowse.addActionListener(this);
		add(btnBrowse, "flowx,cell 1 2,alignx center,aligny top");

		btnStart = new JButton("Start");
		btnStart.setActionCommand("start");
		btnStart.addActionListener(this);
		add(btnStart, "cell 1 2,growx,aligny top");
		btnStart.setEnabled(false);

		lblTestTime = new JLabel("Test time [min]:");
		add(lblTestTime, "cell 0 4,alignx center,aligny center");

		tfTestTime = new JTextField();
		add(tfTestTime, "flowx,cell 1 4,alignx left,aligny center");
		tfTestTime.setColumns(10);

		lblCountdown = new JLabel("Countdown:");
		add(lblCountdown, "cell 0 5,alignx center,aligny center");

		lblTimeRemaining = new JLabel("");
		add(lblTimeRemaining, "flowx,cell 1 5,alignx left,aligny center");

		fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("start")) {

			if (!save) {
				btnStart.setText("Stop");
				fileSave = new File(tfPath.getText(), tfFileName.getText());
				System.out.println("File for saving: " + fileSave.getPath());
				save = true;

				licznik = 0;
				czasPomiaru = (int) Double.parseDouble(tfTestTime.getText());
				czasPomiaru = czasPomiaru * 60; //konwersja z minut na sekundy
				timer = new Timer();
				odliczanie = new TimerTask() {

					@Override
					public void run() {
						if (czasPomiaru != 0) {
							if (czasPomiaru >= 60) {
								if (licznik == 0) {
									minuty = czasPomiaru / 60;
									lblTimeRemaining.setText(Integer.toString(minuty)
											+ ":00");
									licznik++;
									--czasPomiaru;
								} else {
									minuty = czasPomiaru / 60;
									sekundy = czasPomiaru - (minuty * 60);
									if (sekundy < 10) {
										lblTimeRemaining.setText(Integer.toString(minuty)
												+ ":0"
												+ Integer.toString(sekundy));
										--czasPomiaru;
									} else if (sekundy >= 10) {
										lblTimeRemaining.setText(Integer.toString(minuty)
												+ ":"
												+ Integer.toString(sekundy));
										--czasPomiaru;
									}
								}
							} else if (czasPomiaru < 60) {
								sekundy = czasPomiaru;
								if (sekundy < 10) {
									lblTimeRemaining.setText("0:0"
											+ Integer.toString(sekundy));
									--czasPomiaru;
								} else if (sekundy >= 10) {
									lblTimeRemaining.setText("0:"
											+ Integer.toString(sekundy));
									--czasPomiaru;
								}
							}
						} else {
							lblTimeRemaining.setText("End of the test. PRESS STOP NOW.");
							licznik = 0;
							timer.cancel();
							timer.purge();
						}
					}

				};

				timer.schedule(odliczanie, 0, 1000);

				TimerTask drawGraph = new TimerTask() {

					private long startTime = System.currentTimeMillis();

					@Override
					public void run() {
						if (czasPomiaru != 0) {
							GraphPanel.trace
									.addPoint(
											((double) System
													.currentTimeMillis() - this.startTime),
											WiiBoardL.COPx);
							GraphPanel.trace2
									.addPoint(
											((double) System
													.currentTimeMillis() - this.startTime),
											WiiBoardL.COPy);
						} else {
//							GraphPanel.timer.cancel();
//							GraphPanel.timer.purge();
						}
					}
				};

				GraphPanel.timer.schedule(drawGraph, 1000, 500);

			} else if (save) {
				stopClicked = true;
				btnStart.setEnabled(false);
				System.out.println("Stop clicked");
				btnStart.setText("Start");
				save = false;
			}

		} else if (e.getActionCommand().equals("browse")) {
			int returnVal = fc.showOpenDialog(SaveOptionsPanel.this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				filePath = fc.getSelectedFile();
				tfPath.setText(filePath.getPath());
				date = new Date();
				sdf = new SimpleDateFormat("yyMMdd_HHmmss"); // SSS is
																// miliseconds
				String formattedDate = sdf.format(date);
				tfFileName.setText(formattedDate + ".txt");
				btnStart.setEnabled(true);
			}
		}

	}

	public void reschedule() {
		/*
		 * odliczanie.cancel(); timer.purge();
		 * 
		 * odliczanie = new TimerTask(){
		 * 
		 * @Override public void run() { if(czasPomiaru!=0){
		 * if(czasPomiaru>=60){ if(licznik == 0){ minuty = czasPomiaru/60;
		 * label.setText(Integer.toString(minuty) + ":00"); licznik++;
		 * --czasPomiaru; } else{ minuty = czasPomiaru / 60; sekundy =
		 * czasPomiaru-(minuty*60); if(sekundy<10){
		 * label.setText(Integer.toString(minuty) + ":0" +
		 * Integer.toString(sekundy)); --czasPomiaru; } else if(sekundy>=10){
		 * label.setText(Integer.toString(minuty) + ":" +
		 * Integer.toString(sekundy)); --czasPomiaru; } } } else
		 * if(czasPomiaru<60){ sekundy = czasPomiaru; if(sekundy<10){
		 * label.setText("0:0" + Integer.toString(sekundy)); --czasPomiaru; }
		 * else if(sekundy>=10){ label.setText("0:" +
		 * Integer.toString(sekundy)); --czasPomiaru; } } } else {
		 * label.setText("End of the test"); licznik = 0; } } };
		 * timer.schedule(odliczanie, 0, 1000);
		 */
	}

	public boolean isSave() {
		return save;
	}

	public void setSave(boolean save) {
		SaveOptionsPanel.save = save;
	}

	public File getFileSave() {
		return fileSave;
	}

	public void setFileSave(File fileSave) {
		SaveOptionsPanel.fileSave = fileSave;
	}

	public boolean isStopClicked() {
		return stopClicked;
	}

	public void setStopClicked(boolean stopClicked) {
		this.stopClicked = stopClicked;
	}

}
