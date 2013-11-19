package it.unipr.sbrix.esercizio2.Modelli;

import java.util.Arrays;

import it.unipr.sbrix.esercizio2.Hotel;

@SuppressWarnings("unchecked")
public class ModelHotel extends RowTableModel<Hotel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2339574117133691992L;
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
