package filmek;

import java.awt.EventQueue;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.LineBorder;
import javax.swing.JRadioButton;
import java.awt.Window.Type;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

public class FilmProg extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel filmprog;
	private JButton bezar;
	private JButton listaz;
	private JButton betolt;
	private JComboBox betoltForras;
	private JTextField fajlNev;
	private JTextField adatSzam;
	

	private String forras = "Nincs forr�s!";
	private String forraslista[] = { "Nincs forr�s!", "Helyi .csv f�jl", "Helyi .txt f�jl" };
	private String uzenet = "Program�zenet";
	private File fajlbe;
	private FilmTabla tb;
	private JButton ujadat;
	private SimpleDateFormat dform = new SimpleDateFormat("yyyy");
	private JButton modosit;
	private JButton torol;
	private JButton kiir;
	private JComboBox kiirCel;
	private JTextField kiirFajlnev;

	private String cel = "Nincs c�l";
	String cellista[] = { "Nincs c�l", "Helyi .csv f�jl", "Helyi .txt f�jl"};
	private JLabel hatter;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FilmProg frame = new FilmProg();

					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FilmProg() {
		setType(Type.UTILITY);
		setTitle("Film nyilv�ntart�s");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 719, 350);
		filmprog = new JPanel();
		filmprog.setBackground(new Color(205, 133, 63));
		filmprog.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(filmprog);
		filmprog.setLayout(null);

		betolt = new JButton("Adatbet\u00F6lt\u00E9s");
		betolt.setToolTipText("Adatok bet\u00F6lt\u00E9se.");
		betolt.setActionCommand("Betoltes");
		betolt.setBounds(93, 193, 124, 25);
		filmprog.add(betolt);

		listaz = new JButton("Adatlista");
		listaz.setBounds(310, 193, 97, 25);
		filmprog.add(listaz);

		bezar = new JButton("Bez\u00E1r");
		bezar.setActionCommand("Bezar");
		bezar.setBounds(323, 265, 97, 25);
		filmprog.add(bezar);

		JLabel fajlForras = new JLabel("Forr\u00E1s:");
		fajlForras.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		fajlForras.setForeground(Color.WHITE);
		fajlForras.setBounds(145, 37, 97, 16);
		filmprog.add(fajlForras);

		JLabel adatokSzama = new JLabel("Adatok sz\u00E1ma:");
		adatokSzama.setForeground(Color.WHITE);
		adatokSzama.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		adatokSzama.setBackground(Color.WHITE);
		adatokSzama.setBounds(323, 68, 97, 16);
		filmprog.add(adatokSzama);

		betoltForras = new JComboBox();
		for (String s : forraslista)
			this.betoltForras.addItem(s);

		betoltForras.setBounds(242, 35, 184, 22);
		filmprog.add(betoltForras);

		fajlNev = new JTextField();
		fajlNev.setEditable(false);
		fajlNev.setBounds(455, 35, 229, 22);
		filmprog.add(fajlNev);
		fajlNev.setColumns(10);

		adatSzam = new JTextField();
		adatSzam.setHorizontalAlignment(SwingConstants.RIGHT);
		adatSzam.setText("0");
		adatSzam.setBounds(455, 66, 184, 22);
		filmprog.add(adatSzam);
		adatSzam.setColumns(10);

		ujadat = new JButton("\u00DAj adat bevitele");
		ujadat.setActionCommand("ujAdat");
		ujadat.setBounds(487, 193, 173, 25);
		filmprog.add(ujadat);

		modosit = new JButton("Adatm�dos�t�s");
		modosit.setActionCommand("Modositas");
		modosit.setBounds(477, 229, 195, 25);
		filmprog.add(modosit);

		torol = new JButton("Adatok t�rl�se");
		torol.setActionCommand("Torles");
		torol.setBounds(277, 229, 173, 25);
		filmprog.add(torol);

		kiir = new JButton("Adatok ki�r�sa");
		kiir.setActionCommand("Kiiras");
		kiir.setBounds(69, 229, 185, 25);
		filmprog.add(kiir);

		JLabel kiirasiCel = new JLabel("Ki�r�si c�l:");
		kiirasiCel.setForeground(Color.WHITE);
		kiirasiCel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		kiirasiCel.setBounds(145, 101, 87, 16);
		filmprog.add(kiirasiCel);

		kiirCel = new JComboBox();
		kiirCel.setActionCommand("comboBoxChangedGoal");
		kiirCel.setBounds(242, 99, 184, 22);

		for (String s : cellista)
			kiirCel.addItem(s);

		filmprog.add(kiirCel);

		kiirFajlnev = new JTextField();
		kiirFajlnev.setBounds(455, 99, 229, 22);
		filmprog.add(kiirFajlnev);
		kiirFajlnev.setColumns(10);
		
		hatter = new JLabel(new ImageIcon("I:\\Java_tech_bead\\movie.jpg"));
		getContentPane().add(hatter);
		hatter.setLayout(new FlowLayout());
		hatter.setBounds(0, 0, 703, 316);
		filmprog.add(hatter);

		addListeners();

		
		Object elsosor[] = { "Jel", "Azon", "C�m", "�vsz�m", "Rendez�", "Hossz" };
		tb = new FilmTabla(elsosor, 0);

		
	}

	public void SMD(String s) {
		JOptionPane.showMessageDialog(null, s, uzenet, 0);
	}

	public void addListeners() {
		this.bezar.addActionListener(this);
		this.betolt.addActionListener(this);
		this.listaz.addActionListener(this);
		this.betoltForras.addActionListener(this);
		this.ujadat.addActionListener(this);
		this.kiirCel.addActionListener(this);
		this.kiir.addActionListener(this);
		this.modosit.addActionListener(this);
		this.torol.addActionListener(this);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("Bezar")) {
			System.exit(0);
		}

		if (e.getActionCommand().equals("Betoltes")) {
			if (forras.equals("Nincs forr�s!")) {
				JOptionPane.showMessageDialog(null, "Nincs kiv�lasztva forr�s", uzenet, 0);
			}

			if (forras.equals("Helyi .csv f�jl")) {
				FileDialog fd = new FileDialog(new FilmProg(), "F�jlkiv�laszt�s", FileDialog.LOAD);

				fd.setFile("*.csv");
				fd.show();

				if (fd.getFile() != null) {
					fajlbe = new File(fd.getDirectory(), fd.getFile());
					String forrasnev = fd.getFile();
					this.fajlNev.setText(forrasnev);
					FajlKezelo.CsvBeolv(fajlbe, tb);
					adatSzam.setText("" + tb.getRowCount());
				}
			}
			else if (forras.equals("Helyi .txt f�jl")) {
				FileDialog fd = new FileDialog(new FilmProg(), "F�jlkiv�laszt�s", FileDialog.LOAD);

				fd.setFile("*.txt");
				fd.show();

				if (fd.getFile() != null) {
					fajlbe = new File(fd.getDirectory(), fd.getFile());
					String forrasnev = fd.getFile();
					this.fajlNev.setText(forrasnev);
					FajlKezelo.TxtBeolv(fajlbe, tb);
					adatSzam.setText("" + tb.getRowCount());
				}
			}

		}

		if (e.getActionCommand().equals("Adatlista")) {
			FilmLista el = new FilmLista(FilmProg.this, tb);
			el.setVisible(true);
		}

		if (e.getActionCommand().equals("comboBoxChanged")) {
			forras = (String) this.betoltForras.getSelectedItem();
			this.fajlNev.setText(forras);
			// this.textFieldDataNumber.setText("4");
		}

		if (e.getActionCommand().equals("ujAdat")) {
			int kodv = 0;

			if (tb.getRowCount() == 0)
				kodv = 49;
			else
				kodv = (int) tb.getValueAt(tb.getRowCount() - 1, 1);
			FilmUj en = new FilmUj(FilmProg.this, kodv);
			en.setVisible(true);
			int kilep = en.KiLep();
			if (kilep == 1) {
				Film newEmp = en.getFilm();
				Date d = newEmp.getevszam();
				String ddd = dform.format(d).toString();
				tb.addRow(new Object[] { new Boolean(false), newEmp.getazon(), newEmp.getcim(), ddd,
						newEmp.getrendezo(), newEmp.gethossz() });
				this.adatSzam.setText("" + tb.getRowCount());
			}

		}

		if (e.getActionCommand().equals("comboBoxChangedGoal")) {
			cel = (String) kiirCel.getSelectedItem();
		}

		if (e.getActionCommand().equals("Kiiras")) {
			if (cel.equals("V�lasszon!"))
				SMD("V�lassza ki a c�lt!");
			else if (tb.getRowCount() == 0)
				SMD("Nincs ki�rhat� adat");
			else if (cel.equals("Helyi .csv f�jl")) {
				if (this.kiirFajlnev.getText().length() == 0)
					SMD("Nincs megadva a c�lf�jl neve!");
				else {
					FajlKezelo.CsvKiir(this.kiirFajlnev.getText().toString(), tb);
				}
				}
				else if (cel.equals("Helyi .txt f�jl")) {
						if (this.kiirFajlnev.getText().length() == 0)
							SMD("Nincs megadva a c�l f�jl neve!");
						else {
							FajlKezelo.TxtKiir(this.kiirFajlnev.getText().toString(), tb);
				}
			}
		}

		if (e.getActionCommand().equals("Modositas")) {
			if (tb.getRowCount() == 0)
				Ellenorzes.uzenetMegjelenites("Nincs m�dos�that� adat!", 0);
			else {
				FilmModosit em = new FilmModosit(FilmProg.this, tb);
				em.setVisible(true);
			}
		}

		if (e.getActionCommand().equals("Torles")) {
			if (tb.getRowCount() == 0)
				Ellenorzes.uzenetMegjelenites("Nincs t�r�lhet� adat!", 0);
			else {
				FilmTorol ed = new FilmTorol(FilmProg.this, tb);
				ed.setVisible(true);
				this.adatSzam.setText("" + tb.getRowCount());
			}
		}
		if (e.getActionCommand().equals("Betolt")) {
			forras = (String) this.betoltForras.getSelectedItem();
			this.fajlNev.setText(forras);
					}
		
		}
	}

