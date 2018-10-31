package filmek;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Window.Type;

public class FilmLista extends JDialog implements ActionListener {

	private final JPanel filmlista = new JPanel();
	private JTable table;
	private FilmTabla ftm;
	private JButton bezar;

	public FilmLista(JFrame f, FilmTabla ft) {
		super(f, "Filmek listája", true);
		setType(Type.UTILITY);
		ftm = ft;

		setBounds(100, 100, 500, 300);
		getContentPane().setLayout(new BorderLayout());
		filmlista.setBackground(new Color(112, 128, 144));
		filmlista.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(filmlista, BorderLayout.CENTER);
		filmlista.setLayout(null);

		bezar = new JButton("Bez\u00E1r\u00E1s");
		bezar.setActionCommand("Bezar");
		bezar.setBounds(171, 218, 97, 25);
		filmlista.add(bezar);

		JScrollPane beolvasottFilm = new JScrollPane();
		beolvasottFilm.setBounds(12, 13, 458, 194);
		filmlista.add(beolvasottFilm);

		table = new JTable(ftm);
		beolvasottFilm.setViewportView(table);

		TableColumn tc = null;
		for (int i = 0; i < 6; i++) {
			tc = table.getColumnModel().getColumn(i);
			if (i == 0 || i == 1 || i == 5)
				tc.setPreferredWidth(30);
			else {
				tc.setPreferredWidth(100);
			}
		}

		table.setAutoCreateRowSorter(true);
		TableRowSorter<FilmTabla> trs = (TableRowSorter<FilmTabla>) table.getRowSorter();
		trs.setSortable(0, false);

		this.bezar.addActionListener(this);
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Bezar")) {
		      dispose(); 
		      setVisible(false);
		}
		
	}
}
