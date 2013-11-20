package it.unipr.sbrix.esercizio2;

import java.io.Serializable;

public class ViaggioOrganizzato implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3650137362366764777L;

	private int id;

	Volo andata, ritorno;

	Hotel hotel;

	int durataPernottamento;

	public ViaggioOrganizzato(Agenzia ag) {
		id = ag.idGlobaleViaggiOrganizzati++;
	}
	public int getId() {
		return id;
	}
	@Override
	public String toString() {
		return "ViaggiOrganizzati [andata=" + andata.toString() + ", ritorno="
				+ ritorno.toString() + ", hotel=" + hotel.toString()
				+ ", durataPernottamento=" + durataPernottamento + "]";
	}

}
