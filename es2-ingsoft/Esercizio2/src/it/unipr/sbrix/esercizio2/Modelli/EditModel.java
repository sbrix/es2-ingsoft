package it.unipr.sbrix.esercizio2.Modelli;

public interface EditModel {
	void addItem(Object item);

	void removeItem(int id, int row);

	Object getItem(int id);

}
