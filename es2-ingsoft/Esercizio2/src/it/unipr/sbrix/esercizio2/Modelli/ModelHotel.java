package it.unipr.sbrix.esercizio2.Modelli;

import java.util.Arrays;

import it.unipr.sbrix.esercizio2.Hotel;

public class ModelHotel extends RowTableModel<Hotel> {

	private final static String[] COLUMN_NAMES = { "Id", "Nome", "Via",
			"Città", "Nazione" };

	public ModelHotel() {
		super(Arrays.asList(COLUMN_NAMES));
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

}
