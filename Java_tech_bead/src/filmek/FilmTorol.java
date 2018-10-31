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
import javax.swing.JCheckBox;
import java.awt.Font;
import java.awt.Color;
import java.awt.Window.Type;

public class FilmTorol extends JDialog implements ActionListener {

	private final JPanel filmtorol = new JPanel();
	private JTable table;
	private FilmTabla tb;
	private JButton torol;
	private JCheckBox torolTobb;
	private boolean kijelol = false;

	public FilmTorol(JFrame f, FilmTabla tabla) {
		super(f, "Filmek törlése", true);
		setType(Type.UTILITY);
		setBackground(new Color(112, 128, 144));
		tb = tabla;

		setBounds(100, 100, 500, 300);
		getContentPane().setLayout(new BorderLayout());
		filmtorol.setBackground(new Color(112, 128, 144));
		filmtorol.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(filmtorol, BorderLayout.CENTER);
		filmtorol.setLayout(null);

		torol = new JButton("T\u00F6rl\u00E9s");
		torol.setFont(new Font("Arial", Font.PLAIN, 11));
		torol.setActionCommand("Torol");
		torol.setBounds(99, 230, 97, 25);
		filmtorol.add(torol);

		JScrollPane torolTabla = new JScrollPane();
		torolTabla.setBounds(12, 13, 458, 165);
		filmtorol.add(torolTabla);

		table = new JTable(tb);
		torolTabla.setViewportView(table);

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

		torolTobb = new JCheckBox("Több rekord is törölhetõ!");
		torolTobb.setBackground(new Color(112, 128, 144));
		torolTobb.setActionCommand("tobb");
		torolTobb.setFont(new Font("Tahoma", Font.BOLD, 13));
		torolTobb.setBounds(12, 189, 458, 25);
		filmtorol.add(torolTobb);
		
		JButton bezar = new JButton("Bez\u00E1r\u00E1s");
		bezar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		bezar.setFont(new Font("Arial", Font.PLAIN, 11));
		bezar.setActionCommand("bezar");
		bezar.setBounds(294, 231, 97, 25);
		filmtorol.add(bezar);
		
		TableRowSorter<FilmTabla> trs = (TableRowSorter<FilmTabla>) table.getRowSorter();
		trs.setSortable(0, false);

		this.torol.addActionListener(this);
		this.torolTobb.addActionListener(this);
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Torol")) {
			int db = 0, jel = 0, x = 0;
			for (x = 0; x < tb.getRowCount(); x++)
				if ((Boolean) tb.getValueAt(x, 0)) {
					db++;
					jel = x;
				}
			if (db == 0)
				Ellenorzes.uzenetMegjelenites("Nincs kijelölve a rekord!", 0);
			if (!kijelol) {
				if (db > 1)
					Ellenorzes.uzenetMegjelenites("Több rekord van kijelölve!", 0);
				if (db == 1) {
					tb.removeRow(jel);
					Ellenorzes.uzenetMegjelenites("A rekord törölve!", 1);
				}
			} else {
				for (int i = 0; i < tb.getRowCount(); i++)
					if ((Boolean) tb.getValueAt(i, 0)) {
						tb.removeRow(i);
						i--;
					}
				Ellenorzes.uzenetMegjelenites("Rekord(ok) törölve!", 1);
			}
		}

		if (e.getActionCommand().equals("tobb")) {
			kijelol = torolTobb.isSelected();
		}
		

	}
}
