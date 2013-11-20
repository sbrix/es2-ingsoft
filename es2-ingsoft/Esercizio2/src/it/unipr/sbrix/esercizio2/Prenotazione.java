package it.unipr.sbrix.esercizio2;

import java.io.Serializable;

public class Prenotazione extends ViaggioOrganizzato implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1530929609848261724L;

	int id;

	Utente cliente;
	int idOperatore;
	long scadenza;

	public Prenotazione(Agenzia ag) {
		super(ag);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {

		return "Prenotazione [id=" + id + ", cliente=" + cliente.toString()
				+ ", hotel=" + hotel.toString() + ", giorniPernottamento="
				+ durataPernottamento + ", andata=" + andata.toString()
				+ ", ritorno=" + ritorno.toString() + ", operatore="
				+ idOperatore + ", scadenza=" + scadenza + "]";
	}

}
