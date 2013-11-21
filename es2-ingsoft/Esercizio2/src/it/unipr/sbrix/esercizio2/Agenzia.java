package it.unipr.sbrix.esercizio2;

import it.unipr.sbrix.esercizio2.Modelli.ModelUtenteListener;
import it.unipr.sbrix.esercizio2.Modelli.ModelUtenti;
import it.unipr.sbrix.esercizio2.Modelli.ModelUtentiEvent;
import it.unipr.sbrix.esercizio2.Modelli.ModelVoli;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import org.jasypt.util.password.*;

/**
 * @author Luca Sbrissa Matricola 182736 ,Moreno Varoli Matricola ??????
 * 
 */
public class Agenzia {
	// creazione liste gestione agenzia

	public static Boolean saveToFile(File file, Object obj) {
		try {
			FileOutputStream outFile = new FileOutputStream(file);
			ObjectOutputStream objOutputStream = new ObjectOutputStream(outFile);
			objOutputStream.writeObject(obj);
			objOutputStream.flush();
			objOutputStream.close();
			return true;
		} catch (IOException e) {
			System.out.println("Errore scrittura file");
			return false;
		}

	}

	public ArrayList<Hotel> listaHotel = new ArrayList<Hotel>(0);
	public ArrayList<Prenotazione> listaPrenotazioni = new ArrayList<Prenotazione>(
			0);
	public ArrayList<Vendita> listaVendite = new ArrayList<Vendita>(0);

	public ArrayList<ViaggioOrganizzato> listaViaggiOrganizzati = new ArrayList<ViaggioOrganizzato>(
			0);
	public ModelUtenti modelUtenti = null;
	public ModelUtenti modelClienti = null;

	public ModelVoli modelVoli = null;
	// ArrayList<Operatore> listaOperatori = new ArrayList<Operatore>(0);
	// gestione input/output su file
	/*
	 * private final String pathRoot = File.separator + "esercizio1" +
	 * File.separator + "data" + File.separator;
	 */
	public final static String pathRoot = new String(Agenzia.class
			.getProtectionDomain().getCodeSource().getLocation().getPath())
			+ File.separator + "data" + File.separator;

	private final File rootDir = new File(pathRoot);
	public int idGlobalePrenotazioni = 0;
	public int idGlobaleVendite = 0;

	public int idGlobaleViaggiOrganizzati = 0;

	public static BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();

	public final File fileHotel = new File(pathRoot + "hotel.dat");
	public final File filePrenotazioni = new File(pathRoot + "prenotazioni.dat");
	public final File fileVendite = new File(pathRoot + "vendite.dat");

	public final File fileViaggiOrganizzati = new File(pathRoot + "viaggi.dat");
	// final File fileOperatori = new File(pathRoot + "operatori.dat");
	public final File fileIdPrenotazioni = new File(pathRoot
			+ "idPrenotazioni.dat");
	public final File fileIdVendite = new File(pathRoot + "idVendite.dat");

	public final File fileIdViaggiOrganizzati = new File(pathRoot
			+ "idViaggiOrg.dat");

	public FileInputStream hotelIn = null;
	public FileInputStream prenotazioniIn = null;
	public FileInputStream venditeIn = null;

	public FileInputStream viaggiIn = null;
	public FileInputStream idPrenotazioniIn = null;
	public FileInputStream idVenditeIn = null;

	public FileInputStream idViaggiOrgIn = null;

	public ObjectInputStream objInputStream = null;

	// console per lettura input utente
	public Scanner consoleInput = new Scanner(System.in);

	public Agenzia() throws ClassNotFoundException, IOException {
		rootDir.mkdirs();// creo la dir se non esiste
		initFiles();
		modelUtenti = new ModelUtenti(ModelUtenti.INIT_UTENTE);
		modelClienti = new ModelUtenti(ModelUtenti.INIT_CLIENTE);
		modelVoli = new ModelVoli();
		modelUtenti.addListener(new ModelUtenteListener() {
			
			@Override
			public void myEventOccurred(ModelUtentiEvent evt) {
				// TODO Auto-generated method stub
				modelClienti.removeRowRange(0, modelClienti.getRowCount()-1);
				modelClienti.initFromFile();
				modelClienti.initModel();
				
			}
		});
		
		modelClienti.addListener(new ModelUtenteListener() {
			
			@Override
			public void myEventOccurred(ModelUtentiEvent evt) {
				// TODO Auto-generated method stub
				modelUtenti.removeRowRange(0, modelUtenti.getRowCount()-1);
				modelUtenti.initFromFile();
				modelUtenti.initModel();
				
			}
		});
		// TODO Auto-generated constructor stub
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
		saveToFile(filePrenotazioni, listaPrenotazioni);
		if (flag)
			controllaScadenzaPrenotazioni();
	}

	@SuppressWarnings("unchecked")
	void initFiles() throws IOException, ClassNotFoundException {

		if (!fileHotel.exists()) {

			fileHotel.createNewFile();
		}

		if (!filePrenotazioni.exists()) {

			filePrenotazioni.createNewFile();
		}
		if (!fileVendite.exists()) {

			fileVendite.createNewFile();
		}
		if (!fileViaggiOrganizzati.exists()) {

			fileViaggiOrganizzati.createNewFile();
		}

		/*
		 * if (!fileOperatori.exists()) {
		 * 
		 * fileOperatori.createNewFile(); }
		 */

		if (!fileIdPrenotazioni.exists()) {
			idGlobalePrenotazioni = 0;
			fileIdPrenotazioni.createNewFile();

		}
		if (!fileIdVendite.exists()) {
			idGlobaleVendite = 0;
			fileIdVendite.createNewFile();

		}

		if (!fileIdViaggiOrganizzati.exists()) {
			idGlobaleViaggiOrganizzati = 0;
			fileIdViaggiOrganizzati.createNewFile();
		}

		hotelIn = new FileInputStream(fileHotel);

		prenotazioniIn = new FileInputStream(filePrenotazioni);
		venditeIn = new FileInputStream(fileVendite);
		viaggiIn = new FileInputStream(fileViaggiOrganizzati);

		idPrenotazioniIn = new FileInputStream(fileIdPrenotazioni);
		idVenditeIn = new FileInputStream(fileIdVendite);
		idViaggiOrgIn = new FileInputStream(fileIdViaggiOrganizzati);

		// inizializzo liste da file

		try {
			objInputStream = new ObjectInputStream(hotelIn);
			listaHotel = (ArrayList<Hotel>) objInputStream.readObject();
			objInputStream.close();
		} catch (EOFException e) {
			System.out.println("file hotel vuoto");
		}

		try {
			objInputStream = new ObjectInputStream(prenotazioniIn);
			listaPrenotazioni = (ArrayList<Prenotazione>) objInputStream
					.readObject();
			objInputStream.close();
		} catch (EOFException e) {
			System.out.println("file prenotazioni vuoto");
		}

		try {
			objInputStream = new ObjectInputStream(viaggiIn);
			listaViaggiOrganizzati = (ArrayList<ViaggioOrganizzato>) objInputStream
					.readObject();
			objInputStream.close();
		} catch (EOFException e) {
			System.out.println("file viaggi organizzati vuoto");
		}

		try {
			objInputStream = new ObjectInputStream(venditeIn);
			listaVendite = (ArrayList<Vendita>) objInputStream.readObject();
			objInputStream.close();
		} catch (EOFException e) {
			System.out.println("file vendite vuoto");
		}

		try {
			objInputStream = new ObjectInputStream(idVenditeIn);
			idGlobaleVendite = (int) objInputStream.readObject();

			objInputStream.close();
		} catch (EOFException e) {
			idGlobaleVendite = 0;
			System.out.println("file id vendite vuoto");
		}
		try {
			objInputStream = new ObjectInputStream(idPrenotazioniIn);
			idGlobalePrenotazioni = (int) objInputStream.readObject();

			objInputStream.close();
		} catch (EOFException e) {
			idGlobalePrenotazioni = 0;
			System.out.println("file id prenotazioni vuoto");
		}

		try {
			objInputStream = new ObjectInputStream(idViaggiOrgIn);
			idGlobaleViaggiOrganizzati = (int) objInputStream.readObject();
			objInputStream.close();
		} catch (EOFException e) {
			idGlobaleViaggiOrganizzati = 0;
			System.out.println("file id viaggi organizzati vuoto");
		}

	}

}