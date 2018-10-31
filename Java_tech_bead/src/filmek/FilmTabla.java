package filmek;

import javax.swing.table.DefaultTableModel;

public class FilmTabla extends DefaultTableModel {


	public FilmTabla(Object fildNames[], int rows) {
		super(fildNames, rows);
	}

	public boolean szerkeszthetoE(int row, int col) {
		if (col == 0) {
			return true;
		}
		return false;
	}

	public Class<?> getColumnClass(int index) {
		if (index == 0)
			return (Boolean.class);
		else if (index == 1 || index == 5)
			return (Integer.class);
		return (String.class);
	}

}
