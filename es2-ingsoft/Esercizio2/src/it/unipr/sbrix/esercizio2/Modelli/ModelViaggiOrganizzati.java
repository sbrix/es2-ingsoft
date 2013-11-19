package it.unipr.sbrix.esercizio2.Modelli;

import java.util.Arrays;

import it.unipr.sbrix.esercizio2.ViaggioOrganizzato;
import it.unipr.sbrix.esercizio2.Volo;

@SuppressWarnings("unchecked")
public class ModelViaggiOrganizzati extends RowTableModel<ViaggioOrganizzato> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2251662406643181544L;
	private final static String[] COLUMN_NAMES = { "Id", "Partenza andata",
			"Arrivo andata", "Durata Pernottamento", "Nome hotel", "Via Hotel",
			"Città hotel", "Nazione hotel", "Partenza ritorno",
			"Arrivo ritorno" };

	public ModelViaggiOrganizzati() {
		super(Arrays.asList(COLUMN_NAMES));
		setRowClass(ViaggioOrganizzato.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

}
