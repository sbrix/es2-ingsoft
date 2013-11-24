package it.unipr.sbrix.esercizio2.Modelli;

import it.unipr.sbrix.esercizio2.Prenotazione;

import javax.swing.RowFilter;

public class FilterPrenotazioniByUserId extends
		RowFilter<ModelPrenotazioni, Integer> {

	private int userId = 0;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public boolean include(
			javax.swing.RowFilter.Entry<? extends ModelPrenotazioni, ? extends Integer> entry) {
		// TODO Auto-generated method stub

		ModelPrenotazioni model = entry.getModel();
		Prenotazione prenotazione = (Prenotazione) model.getItem(entry
				.getIdentifier());
		if (prenotazione.cliente.getId() == this.userId)
			return true;
		// if(prenotazione.cliente.getId()==entry.getIdentifier()) return true;
		return false;
	}

}
