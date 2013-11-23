package it.unipr.sbrix.esercizio2.Modelli;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import it.unipr.sbrix.esercizio2.Agenzia;
import it.unipr.sbrix.esercizio2.Prenotazione;

@SuppressWarnings("unchecked")
public class ModelPrenotazioni extends RowTableModel<Prenotazione> implements EditModel,InitModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -30781717066247948L;
	private final static String[] COLUMN_NAMES = { "Id", "Partenza andata",
			"Arrivo andata", "Durata Pernottamento", "Nome hotel", "Via Hotel",
			"Città hotel", "Nazione hotel", "Partenza ritorno",
			"Arrivo ritorno", "Nome operatore", "Nome cliente", "Scadenza" };
	private ArrayList<Prenotazione> listaPrenotazioni = new ArrayList<Prenotazione>(
			0);
	private int idGlobalePrenotazioni = 0;
	private final File filePrenotazioni = new File(Agenzia.pathRoot + "prenotazioni.dat");
	private final File fileIdPrenotazioni = new File(Agenzia.pathRoot
			+ "idPrenotazioni.dat");
	private FileInputStream prenotazioniIn = null;
	private FileInputStream idPrenotazioniIn = null;
	private ObjectInputStream objInputStream = null;

	public ModelPrenotazioni() {
		super(Arrays.asList(COLUMN_NAMES));
		// TODO Auto-generated constructor stub
	}
	
	private int getNewId(){
		return idGlobalePrenotazioni++;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void controllaScadenzaPrenotazioni() {

		// Controllo scadenza prenotazioni
		int indice = 0;
		boolean flag = false;

		for (Prenotazione i : listaPrenotazioni) {

			if (i.scadenza < (Calendar.getInstance().getTimeInMillis())) {
				listaPrenotazioni.remove(indice);
				flag = true;
				// break;
				// controllaScadenzaPrenotazioni();

			}
			if (flag)
				break;
			indice++;

		}
		// listaPrenotazioni = (ArrayList<Prenotazione>) listaTemp.clone();
		Agenzia.saveToFile(filePrenotazioni, listaPrenotazioni);
		if (flag)
			controllaScadenzaPrenotazioni();
	}

	@Override
	public void initFromFile() {
		// TODO Auto-generated method stub
		if (!filePrenotazioni.exists()) {

			try {
				filePrenotazioni.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (!fileIdPrenotazioni.exists()) {
			idGlobalePrenotazioni = 0;
			try {
				fileIdPrenotazioni.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		try {
			prenotazioniIn = new FileInputStream(filePrenotazioni);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			idPrenotazioniIn = new FileInputStream(fileIdPrenotazioni);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			objInputStream = new ObjectInputStream(prenotazioniIn);
			listaPrenotazioni = (ArrayList<Prenotazione>) objInputStream
					.readObject();
			objInputStream.close();
		} catch (EOFException e) {
			System.out.println("file prenotazioni vuoto");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			objInputStream = new ObjectInputStream(idPrenotazioniIn);
			idGlobalePrenotazioni = (int) objInputStream.readObject();

			objInputStream.close();
		} catch (EOFException e) {
			idGlobalePrenotazioni = 0;
			System.out.println("file id prenotazioni vuoto");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void initModel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addItem(Object item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getItem(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeItem(int id, int row) {
		// TODO Auto-generated method stub
		
	}

}
