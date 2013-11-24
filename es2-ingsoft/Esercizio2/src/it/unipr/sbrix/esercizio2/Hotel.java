package it.unipr.sbrix.esercizio2;

import java.io.Serializable;

public class Hotel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6823533540175825734L;

	private int id;
	public String nome;

	public String via;

	public String citta;
	public String nazione;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Hotel [nome=" + nome + ", via=" + via + ", citta=" + citta
				+ ", nazione=" + nazione + "]";
	}

}
