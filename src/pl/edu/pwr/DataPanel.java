package pl.edu.pwr;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class DataPanel extends JPanel implements ActionListener, MouseListener {

	public static JTextField textFieldImie;
	public static JTextField textFieldNazwisko;
	public static JTextField txtDd;
	public static JTextField txtMm;
	public static JTextField txtRrrr;
	public static JLabel lblWiek;
	public static JLabel label;
	public static JLabel lblWzrost;
	public static JTextField textFieldWzrost;
	public static JLabel lblPlec;
	public static JRadioButton rdbtnKobieta;
	public static JRadioButton rdbtnMezczyzna;
	static boolean kobieta, mezczyzna = false;
	static String imie, nazwisko, wiekS, plec;
	static int dzien, miesiac, rok, wzrost, wiek1;
	private GregorianCalendar kalendarz;
	private double aktualnyRok, wiek;
	private JButton btnZapiszDane;
	private JButton btnResetdata;
	public static char i,n;

	public DataPanel() {
		setLayout(new MigLayout("", "[][grow]", "[][][][][][][][][]"));

		JLabel lblImie = new JLabel("Name:");
		add(lblImie, "cell 0 1,alignx trailing");

		textFieldImie = new JTextField("");
		add(textFieldImie, "cell 1 1,alignx left");
		textFieldImie.setColumns(10);

		JLabel lblNazwisko = new JLabel("Surname:");
		add(lblNazwisko, "cell 0 2,alignx trailing");

		textFieldNazwisko = new JTextField("");
		add(textFieldNazwisko, "cell 1 2,alignx left");
		textFieldNazwisko.setColumns(10);

		lblPlec = new JLabel("Sex:");
		add(lblPlec, "cell 0 3,alignx right");

		rdbtnKobieta = new JRadioButton("Female");
		add(rdbtnKobieta, "flowx,cell 1 3,alignx left");
		rdbtnKobieta.setActionCommand("kobieta");
		rdbtnKobieta.addActionListener(this);

		JLabel lblDataUrodzenia = new JLabel("Date of Birth:");
		add(lblDataUrodzenia, "cell 0 4,alignx trailing");

		txtDd = new JTextField("DD");
		add(txtDd, "flowx,cell 1 4,alignx left");
		txtDd.setColumns(2);
		txtDd.addMouseListener(this);
		txtDd.setActionCommand("dd");

		txtMm = new JTextField("MM");
		add(txtMm, "cell 1 4");
		txtMm.setColumns(2);
		txtMm.addMouseListener(this);
		txtMm.setActionCommand("mm");

		txtRrrr = new JTextField("YYYY");
		add(txtRrrr, "cell 1 4");
		txtRrrr.setColumns(4);
		txtRrrr.addMouseListener(this);
		txtRrrr.setActionCommand("rok");

		lblWiek = new JLabel("Age:");
		add(lblWiek, "cell 0 5,alignx right");

		label = new JLabel("");
		add(label, "cell 1 5,alignx left");

		lblWzrost = new JLabel("Height:");
		add(lblWzrost, "cell 0 6,alignx trailing");

		textFieldWzrost = new JTextField("");
		add(textFieldWzrost, "cell 1 6,alignx left");
		textFieldWzrost.setColumns(3);

		rdbtnMezczyzna = new JRadioButton("Male");
		add(rdbtnMezczyzna, "cell 1 3");
		rdbtnMezczyzna.setActionCommand("mezczyzna");
		rdbtnMezczyzna.addActionListener(this);

		kalendarz = new GregorianCalendar();
		aktualnyRok = kalendarz.get(Calendar.YEAR);

		btnZapiszDane = new JButton("Save");
		add(btnZapiszDane, "flowx,cell 1 7");
		btnZapiszDane.setActionCommand("zapisz");
		btnZapiszDane.addActionListener(this);
		
		btnResetdata = new JButton("Reset data");
		add(btnResetdata, "cell 1 7");
		btnResetdata.setActionCommand("resetData");
		btnResetdata.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("mezczyzna")) {
			rdbtnKobieta.setSelected(false);
			mezczyzna = true;
			plec = "Male";
		} else if (e.getActionCommand().equals("kobieta")) {
			rdbtnMezczyzna.setSelected(false);
			kobieta = true;
			plec = "Female";
		} else if (e.getActionCommand().equals("zapisz")) {
			/*
			 * if(textFieldImie.getText().equals("")){ String msg; msg =
			 * "Nie podano imienia pacjenta";
			 * JOptionPane.showMessageDialog(null, msg); }
			 * if(textFieldNazwisko.getText().equals("")){ String msg; msg =
			 * "Nie podano nazwiska pacjenta";
			 * JOptionPane.showMessageDialog(null, msg); }
			 * if(txtDd.getText().equals("") || (int)
			 * Double.parseDouble(txtDd.getText()) > 31){ String msg; msg =
			 * "Nie podano dnia urodzin pacjenta";
			 * JOptionPane.showMessageDialog(null, msg); }
			 * if(txtMm.getText().equals("") || (int)
			 * Double.parseDouble(txtMm.getText()) > 12){ String msg; msg =
			 * "Podano niepoprawny miesiąc urodzin pacjenta";
			 * JOptionPane.showMessageDialog(null, msg); }
			 * if(txtRrrr.getText().equals("")){ String msg; msg =
			 * "Nie podano roku urodzin pacjenta";
			 * JOptionPane.showMessageDialog(null, msg); }
			 * if(textFieldWzrost.getText().equals("")){ String msg; msg =
			 * "Nie podano wzrostu pacjenta";
			 * JOptionPane.showMessageDialog(null, msg); }
			 */
			imie = textFieldImie.getText();
			nazwisko = textFieldNazwisko.getText();
			dzien = (int) Double.parseDouble(txtDd.getText());
			miesiac = (int) Double.parseDouble(txtMm.getText());
			rok = (int) Double.parseDouble(txtRrrr.getText());
			wzrost = (int) Double.parseDouble(textFieldWzrost.getText());
			wiek = aktualnyRok - rok;
			wiek1 = (int) wiek;
			wiekS = Integer.toString(wiek1);
			label.setText(wiekS);
			String msg;
			msg = "Save completed";
			JOptionPane.showMessageDialog(null, msg);
			
			i = imie.charAt(0);
			n = nazwisko.charAt(0);
			
			/*
			textFieldImie.setText("");
			textFieldNazwisko.setText("");
			textFieldWzrost.setText("");
			txtDd.setText("DD");
			txtMm.setText("MM");
			txtRrrr.setText("YYYY");
			rdbtnMezczyzna.setSelected(false);
			rdbtnKobieta.setSelected(false);
			label.setText("");
			*/
		}
		else if (e.getActionCommand().equals("resetData")) {
			textFieldImie.setText("");
			textFieldNazwisko.setText("");
			textFieldWzrost.setText("");
			txtDd.setText("DD");
			txtMm.setText("MM");
			txtRrrr.setText("YYYY");
			rdbtnMezczyzna.setSelected(false);
			rdbtnKobieta.setSelected(false);
			label.setText("");
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == txtDd) {
			if (txtDd.getText().equals("DD")) {
				txtDd.setText("");
			}
		} else if (e.getSource() == txtMm) {
			if (txtMm.getText().equals("MM")) {
				txtMm.setText("");
			}
		} else if (e.getSource() == txtRrrr) {
			if (txtRrrr.getText().equals("YYYY")) {
				txtRrrr.setText("");
			}
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}
