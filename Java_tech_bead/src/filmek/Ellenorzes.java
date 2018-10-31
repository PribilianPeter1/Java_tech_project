package filmek;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Ellenorzes {
	private static SimpleDateFormat dform = new SimpleDateFormat("yyyy");
	private static String uzenet = "Programüzenet!";

	public static String SzovegBeker(JTextField a) {
		return a.getText().toString();
	}

	public static boolean kitoltottE(JTextField a) {
		String s = SzovegBeker(a);
		if (s.length() > 0)
			return true;
		else
			return false;
	}

	public static boolean datumE(JTextField a) {
		String s = SzovegBeker(a);
		Date testDate = null;
		try {
			testDate = dform.parse(s);
		} catch (ParseException e) {
			return false;
		}
		if (dform.format(testDate).equals(s))
			return true;
		else
			return false;
	}

	public static boolean egeszE(JTextField a) {
		String s = SzovegBeker(a);
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static void uzenetMegjelenites(String s, int i) {
		JOptionPane.showMessageDialog(null, s, uzenet, i);
	}

	public static Date StringDatum(String s) {
		Date testDate = null, vid = null;
		try {
			testDate = dform.parse(s);
		} catch (ParseException e) {
			return vid;
		}
		if (!dform.format(testDate).equals(s)) {
			return vid;
		}
		return testDate;
	}

	public static int StringInt(String s) {
		int x = -55;
		x = Integer.parseInt(s);
		return x;
	}

	public static void SzovegBeallit(JTextField a) {
		a.setText("");
	}

}
