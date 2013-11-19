package it.unipr.sbrix.esercizio2.Modelli;

import java.util.Arrays;

import it.unipr.sbrix.esercizio2.Volo;

public class ModelVoli extends RowTableModel<Volo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1531108120961122693L;
	private final static String[] COLUMN_NAMES = { "Id", "Partenza",
			"Destinazione" };

	public ModelVoli() {
		super(Arrays.asList(COLUMN_NAMES));
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}
