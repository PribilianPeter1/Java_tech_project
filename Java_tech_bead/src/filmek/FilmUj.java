package filmek;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import java.awt.Window.Type;

public class FilmUj extends JDialog implements ActionListener {

	private final JPanel filmuj = new JPanel();
	private JTextField rendezoBeker;
	private JTextField hosszBeker;
	private JButton beszur;
	private JButton bezar;
	private JButton lekerdez;
	private JTextField azonBeker;
	private JTextField cimBeker;
	private JTextField evszamBeker;
	private SimpleDateFormat dform = new SimpleDateFormat("yyyy");
	private String uzenet = "Programüzenet!";

	private int maxAzon;
	private int kilep;
	private Film adat;
	private JLabel hatter;

	public FilmUj(JFrame f, int maxAzon) {
		super(f, true);
		setType(Type.UTILITY);
		setTitle("Film felvitel");

		setBounds(100, 100, 498, 430);
		getContentPane().setLayout(new BorderLayout());
		filmuj.setBackground(Color.WHITE);
		filmuj.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(filmuj, BorderLayout.CENTER);
		filmuj.setLayout(null);

		JLabel azonosito = new JLabel("Azonosító:");
		azonosito.setForeground(Color.WHITE);
		azonosito.setFont(new Font("Tahoma", Font.BOLD, 13));
		azonosito.setBounds(45, 193, 109, 16);
		filmuj.add(azonosito);

		azonBeker = new JTextField();
		azonBeker.setEditable(false);
		azonBeker.setBounds(190, 191, 74, 22);
		filmuj.add(azonBeker);
		azonBeker.setColumns(10);
		{
			lekerdez = new JButton("Azonos\u00EDt\u00F3 lek\u00E9r\u00E9s");
			lekerdez.setFont(new Font("Arial", Font.PLAIN, 11));
			lekerdez.setActionCommand("Leker");
			lekerdez.setBounds(291, 190, 163, 25);
			filmuj.add(lekerdez);
		}

		JLabel cim = new JLabel("Cím:");
		cim.setForeground(Color.WHITE);
		cim.setFont(new Font("Tahoma", Font.BOLD, 13));
		cim.setBounds(45, 226, 56, 16);
		filmuj.add(cim);

		cimBeker = new JTextField();
		cimBeker.setBounds(190, 224, 182, 22);
		filmuj.add(cimBeker);
		cimBeker.setColumns(10);

		JLabel evszam = new JLabel("Évszám:");
		evszam.setForeground(Color.WHITE);
		evszam.setFont(new Font("Tahoma", Font.BOLD, 13));
		evszam.setBounds(45, 259, 88, 16);
		filmuj.add(evszam);

		evszamBeker = new JTextField();
		evszamBeker.setBounds(188, 257, 149, 22);
		filmuj.add(evszamBeker);
		evszamBeker.setColumns(10);

		JLabel ev = new JLabel("yyyy");
		ev.setForeground(Color.WHITE);
		ev.setBackground(Color.WHITE);
		ev.setFont(new Font("Tahoma", Font.BOLD, 13));
		ev.setBounds(375, 259, 97, 16);
		filmuj.add(ev);

		JLabel rendezo = new JLabel("Rendezõ:");
		rendezo.setForeground(Color.WHITE);
		rendezo.setFont(new Font("Tahoma", Font.BOLD, 13));
		rendezo.setBounds(45, 292, 109, 16);
		filmuj.add(rendezo);

		rendezoBeker = new JTextField();
		rendezoBeker.setBounds(188, 290, 163, 22);
		filmuj.add(rendezoBeker);
		rendezoBeker.setColumns(10);

		JLabel hossz = new JLabel("Hossz:");
		hossz.setForeground(Color.WHITE);
		hossz.setFont(new Font("Tahoma", Font.BOLD, 13));
		hossz.setBounds(45, 325, 56, 16);
		filmuj.add(hossz);

		hosszBeker = new JTextField();
		hosszBeker.setBounds(188, 323, 163, 22);
		filmuj.add(hosszBeker);
		hosszBeker.setColumns(10);

		beszur = new JButton("Besz\u00FAr\u00E1s");
		beszur.setFont(new Font("Arial", Font.PLAIN, 11));
		beszur.setActionCommand("Beszur");
		beszur.setBounds(57, 356, 97, 25);
		filmuj.add(beszur);

		bezar = new JButton("Bezár");
		bezar.setFont(new Font("Arial", Font.PLAIN, 11));
		bezar.setActionCommand("Bezar");
		bezar.setBounds(234, 356, 97, 25);
		filmuj.add(bezar);
		
		hatter = new JLabel("");
		hatter.setBackground(Color.WHITE);
		hatter.setIcon(new ImageIcon("I:\\Java_tech_bead\\clapperboard-311792_640.png"));
		hatter.setBounds(-15, 0, 497, 394);
		filmuj.add(hatter);

		this.beszur.addActionListener(this);
		this.bezar.addActionListener(this);
		this.lekerdez.addActionListener(this);

		this.maxAzon = maxAzon;
	}

	public String RF(JTextField a) {
		return a.getText().toString();
	}

	public boolean kitoltottE(JTextField a) {
		String s = RF(a);
		if (s.length() > 0)
			return true;
		else
			return false;
	}

	public boolean datumE(JTextField a) {
		String s = RF(a);
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

	public boolean egeszE(JTextField a) {
		String s = RF(a);
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public void uzenetVisszaad(String s, int i) {
		JOptionPane.showMessageDialog(null, s, uzenet, i);
	}

	public Date StringDatum(String s) {
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

	public int StoI(String s) {
		int x = -49;
		x = Integer.parseInt(s);
		return x;
	}

	public Film getFilm() {
		return adat;
	}

	public int KiLep() {
		return kilep;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Bezar")) {
			dispose();
			setVisible(false);
		}

		if (e.getActionCommand().equals("Leker")) {
			this.azonBeker.setText("" + (maxAzon + 1));
		}

		if (e.getActionCommand().equals("Beszur")) {
			if (!kitoltottE(this.azonBeker))
				this.azonBeker.setText("" + (maxAzon + 1));
			if (!kitoltottE(this.cimBeker))
				uzenetVisszaad("A cím mezõ üres!", 0);
			else if (!kitoltottE(this.evszamBeker))
				uzenetVisszaad("At évszám mezõ üres!", 0);
			else if (!datumE(this.evszamBeker))
				uzenetVisszaad("Az évszámban rossz adat van!", 0);
			else if (!kitoltottE(this.rendezoBeker))
				uzenetVisszaad("A A rendezõ mezõ üres!", 0);
			else if (!kitoltottE(this.hosszBeker))
				uzenetVisszaad("A hossz mezõ üres!", 0);
			else if (!egeszE(this.hosszBeker))
				uzenetVisszaad("A hossz mezõben rossz adat van!", 0);
			else {
				adat = new Film(StoI(RF(this.azonBeker)), RF(this.cimBeker), StringDatum(RF(this.evszamBeker)),
						RF(this.rendezoBeker), StoI(RF(this.hosszBeker)));
				uzenetVisszaad("Adat beszúrva!", 1);
				kilep = 1;
				dispose();
				setVisible(false);
			}

		}

	}
}