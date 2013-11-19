package it.unipr.sbrix.esercizio2.Modelli;

import it.unipr.sbrix.esercizio2.Volo;

public interface EditModel {
	<T> void addItem(T item);
	void removeItem(int id);

}
