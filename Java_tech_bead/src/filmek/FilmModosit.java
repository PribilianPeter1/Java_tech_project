package filmek;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Window.Type;
import java.awt.Font;

public class FilmModosit extends JDialog implements ActionListener, ListSelectionListener {

	private final JPanel filmmodosit = new JPanel();
	private JTable table;
	private FilmTabla tb;
	private JButton bezaras;
	private JTextField cimModosit;
	private JTextField evszamModosit;
	private JTextField rendezoModosit;
	private JTextField hosszModosit;
	private JButton modositas;

	public FilmModosit(JFrame f, FilmTabla tabla) {
		super(f, "Filmek listája", true);
		setType(Type.UTILITY);
		setTitle("Film adatainak m\u00F3dos\u00EDt\u00E1sa");
		tb = tabla;

		setBounds(100, 100, 500, 300);
		getContentPane().setLayout(new BorderLayout());
		filmmodosit.setBackground(new Color(112, 128, 144));
		filmmodosit.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(filmmodosit, BorderLayout.CENTER);
		filmmodosit.setLayout(null);

		bezaras = new JButton("Bezárás");
		bezaras.setFont(new Font("Arial", Font.PLAIN, 11));
		bezaras.setActionCommand("Bezar");
		bezaras.setBounds(260, 221, 97, 25);
		filmmodosit.add(bezaras);

		JScrollPane tablaModositas = new JScrollPane();
		tablaModositas.setBounds(12, 13, 458, 160);
		filmmodosit.add(tablaModositas);

		table = new JTable(tb);
		tablaModositas.setViewportView(table);

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

		cimModosit = new JTextField();
		cimModosit.setBounds(47, 186, 116, 22);
		filmmodosit.add(cimModosit);
		cimModosit.setColumns(10);

		evszamModosit = new JTextField();
		evszamModosit.setBounds(175, 186, 78, 22);
		filmmodosit.add(evszamModosit);
		evszamModosit.setColumns(10);

		rendezoModosit = new JTextField();
		rendezoModosit.setBounds(265, 186, 116, 22);
		filmmodosit.add(rendezoModosit);
		rendezoModosit.setColumns(10);

		hosszModosit = new JTextField();
		hosszModosit.setBounds(393, 186, 77, 22);
		filmmodosit.add(hosszModosit);
		hosszModosit.setColumns(10);

		modositas = new JButton("M\u00F3dos\u00EDt\u00E1s");
		modositas.setFont(new Font("Arial", Font.PLAIN, 11));
		modositas.setActionCommand("Modosit");
		modositas.setBounds(138, 221, 110, 25);
		filmmodosit.add(modositas);
		TableRowSorter<FilmTabla> trs = (TableRowSorter<FilmTabla>) table.getRowSorter();
		trs.setSortable(0, false);

		
		
		this.bezaras.addActionListener(this);
		this.modositas.addActionListener(this);
		table.getSelectionModel().addListSelectionListener(this);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Bezar")) {
			dispose();
			setVisible(false);
		}

		if (e.getActionCommand().equals("Modosit")) {
			
			
			if (!Ellenorzes.kitoltottE(this.cimModosit) && !Ellenorzes.kitoltottE(this.evszamModosit) && !Ellenorzes.kitoltottE(this.rendezoModosit)
					&& !Ellenorzes.kitoltottE(this.hosszModosit))
				Ellenorzes.uzenetMegjelenites("Egyetlen adat sincs beírva!", 0);
			else if (Ellenorzes.kitoltottE(this.evszamModosit) && !Ellenorzes.datumE(this.evszamModosit))
				Ellenorzes.uzenetMegjelenites("Az évszámban hibás adat van", 0);
			else if (Ellenorzes.kitoltottE(this.hosszModosit) && !Ellenorzes.egeszE(this.hosszModosit))
				Ellenorzes.uzenetMegjelenites("A Hossz mezõben rossz adat van!", 0);
			else {
				int db = 0, jel = 0, x = 0;
				for (x = 0; x < tb.getRowCount(); x++)
					if ((Boolean) tb.getValueAt(x, 0)) {
						db++;
						jel = x;
					}
				if (db == 0)
					Ellenorzes.uzenetMegjelenites("Nincs kijelölve a módosítandó rekord!", 0);
				if (db > 1)
					Ellenorzes.uzenetMegjelenites("Több rekord van kijelölve!\nEgyszerre csak egy rekord módosítható!", 0);
				if (db == 1) {
					if (Ellenorzes.kitoltottE(this.cimModosit))
						tb.setValueAt(Ellenorzes.SzovegBeker(this.cimModosit), jel, 2);
					if (Ellenorzes.kitoltottE(this.evszamModosit))
						tb.setValueAt(Ellenorzes.SzovegBeker(this.evszamModosit), jel, 3);
					if (Ellenorzes.kitoltottE(this.rendezoModosit))
						tb.setValueAt(Ellenorzes.SzovegBeker(this.rendezoModosit), jel, 4);
					if (Ellenorzes.kitoltottE(this.hosszModosit))
						tb.setValueAt(Ellenorzes.SzovegBeker(this.hosszModosit), jel, 5);
					Ellenorzes.uzenetMegjelenites("A rekord módosítva!", 1);
					Ellenorzes.SzovegBeallit(this.cimModosit);
					Ellenorzes.SzovegBeallit(this.evszamModosit);
					Ellenorzes.SzovegBeallit(this.rendezoModosit);
					Ellenorzes.SzovegBeallit(this.hosszModosit);
					tb.setValueAt(new Boolean(false), jel, 0);
				}
			}
		}

	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
        if(!table.getSelectionModel().isSelectionEmpty()) {
			 int row = table.getSelectedRow();
	         
	         this.cimModosit.setText(table.getModel().getValueAt(row, 2).toString());
		     this.evszamModosit.setText(table.getModel().getValueAt(row, 3).toString());
		     this.rendezoModosit.setText(table.getModel().getValueAt(row, 4).toString());
		     this.hosszModosit.setText(table.getModel().getValueAt(row, 5).toString());
        }
	}
}
