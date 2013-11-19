package it.unipr.sbrix.esercizio2.Modelli;

import java.util.Arrays;

import it.unipr.sbrix.esercizio2.Prenotazione;

@SuppressWarnings("unchecked")
public class ModelPrenotazioni extends RowTableModel<Prenotazione> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -30781717066247948L;
	private final static String[] COLUMN_NAMES = { "Id", "Partenza andata",
			"Arrivo andata", "Durata Pernottamento", "Nome hotel", "Via Hotel",
			"Città hotel", "Nazione hotel", "Partenza ritorno",
			"Arrivo ritorno", "Nome operatore", "Nome cliente", "Scadenza" };

	public ModelPrenotazioni() {
		super(Arrays.asList(COLUMN_NAMES));
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}
