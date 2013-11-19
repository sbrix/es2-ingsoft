package it.unipr.sbrix.esercizio2.Modelli;

import it.unipr.sbrix.esercizio2.Volo;

public interface EditModel {
	void addItem(Object item);

	void removeItem(int id, int row);

	Object getItem(int id);

}
