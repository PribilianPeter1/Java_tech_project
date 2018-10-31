package filmek;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintStream;

import javax.swing.JOptionPane;

public class FajlKezelo {
	private static String uzenet = "Programüzenet!";

	public static void CsvBeolv(File fnev, FilmTabla ft) {
		String azon = "", cim = "",  evszam= "", rendezo = "", hossz = "", s = "";

		try {
			FileInputStream f = new FileInputStream(fnev);
			LineNumberReader in = new LineNumberReader(new InputStreamReader(f));

			s = in.readLine(); 
			s = in.readLine(); 
			while (s != null) {
				String[] splitS = s.split(";");
				azon = splitS[0];
				cim = splitS[1];
				evszam = splitS[2];
				rendezo = splitS[3];
				hossz = splitS[4];

				ft.addRow(new Object[] { new Boolean(false), EgeszSzam(azon), cim, evszam, rendezo, EgeszSzam(hossz) });
				s = in.readLine();
			}
			in.close();
			JOptionPane.showMessageDialog(null, "Adatok beolvasva!", uzenet, 1);
		} catch (IOException ioe) {
			JOptionPane.showMessageDialog(null, "CsvReader: " + ioe.getMessage(), uzenet, 2);
		}
		}
		public static void TxtBeolv(File fnev, FilmTabla etm) {
			String azon = "", cim = "",  evszam= "", rendezo = "", hossz = "", s = "";

			try {
				FileInputStream f = new FileInputStream(fnev);
				LineNumberReader in = new LineNumberReader(new InputStreamReader(f));

				s = in.readLine(); 
				s = in.readLine(); 
				while (s != null) {
					String[] splitS = s.split(",");
					azon = splitS[0];
					cim = splitS[1];
					evszam = splitS[2];
					rendezo = splitS[3];
					hossz = splitS[4];

					etm.addRow(new Object[] { new Boolean(false), EgeszSzam(azon), cim, evszam, rendezo, EgeszSzam(hossz) });
					s = in.readLine();
				}
				in.close();
				JOptionPane.showMessageDialog(null, "Adatok beolvasva!", uzenet, 1);
			} catch (IOException ioe) {
				JOptionPane.showMessageDialog(null, "TxtReader: " + ioe.getMessage(), uzenet, 2);
			}
	}

	public static int EgeszSzam(String s) {
		int x = -55;
		x = Integer.parseInt(s);
		return x;
	}

	public static void CsvKiir(String fnev, FilmTabla etm) {
		try {
			PrintStream out = new PrintStream(new FileOutputStream(fnev));
			out.println("Azon;Cím;Évszám;Rendezõ;Hossz");
			int rdb = etm.getRowCount();
			int cdb = etm.getColumnCount();
			for (int i = 0; i < rdb; i++) {
				for (int j = 1; j < cdb - 1; j++) {
					out.print("" + etm.getValueAt(i, j) + ";");
				}
				out.println("" + etm.getValueAt(i, cdb - 1));
			}
			out.close();
			JOptionPane.showMessageDialog(null, "Adatok kiírva!", uzenet, 1);
		} catch (IOException ioe) {
			JOptionPane.showMessageDialog(null, "CsvWriter: " + ioe.getMessage(), uzenet, 2);
		}
	}
	public static void TxtKiir(String fnev, FilmTabla etm) {
		try {
			PrintStream out = new PrintStream(new FileOutputStream(fnev));
			out.println("Azon;Cím;Évszám;Rendezõ;Hossz");
			int rdb = etm.getRowCount();
			int cdb = etm.getColumnCount();
			for (int i = 0; i < rdb; i++) {
				for (int j = 1; j < cdb - 1; j++) {
					out.print("" + etm.getValueAt(i, j) + ",");
				}
				out.println("" + etm.getValueAt(i, cdb - 1));
			}
			out.close();
			JOptionPane.showMessageDialog(null, "Adatok kiírva!", uzenet, 1);
		} catch (IOException ioe) {
			JOptionPane.showMessageDialog(null, "TxtWriter: " + ioe.getMessage(), uzenet, 2);
		}
	}

}