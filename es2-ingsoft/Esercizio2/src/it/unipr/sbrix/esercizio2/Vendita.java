package it.unipr.sbrix.esercizio2;

import java.io.Serializable;

public class Vendita extends ViaggioOrganizzato implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2808661019581646478L;

	int id;

	Utente cliente;
	int idOperatore;

	public Vendita(Agenzia ag) {
		super(ag);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Vendite [id=" + id + ", cliente=" + cliente.toString()
				+ ", hotel=" + hotel.toString() + ", giorniPernottamento="
				+ durataPernottamento + ", andata=" + andata.toString()
				+ ", ritorno=" + ritorno.toString() + ", operatore="
				+ idOperatore + "]";
	}
}
