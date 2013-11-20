package it.unipr.sbrix.esercizio2;

import java.io.Serializable;

public class Volo implements Serializable {
	/**
	 * 
	 */

	private static final long serialVersionUID = -5381232667756946635L;

	private int id;
	public String partenza;

	public String destinazione;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Volo [partenza=" + partenza + ", destinazione=" + destinazione
				+ "]";
	}

}
