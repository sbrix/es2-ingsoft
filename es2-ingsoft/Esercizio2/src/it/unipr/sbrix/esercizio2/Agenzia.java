package it.unipr.sbrix.esercizio2;

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
	public ArrayList<Utente> listaUtenti = new ArrayList<Utente>(0);
	public ArrayList<Hotel> listaHotel = new ArrayList<Hotel>(0);

	public ArrayList<Prenotazione> listaPrenotazioni = new ArrayList<Prenotazione>(
			0);
	public ArrayList<Vendita> listaVendite = new ArrayList<Vendita>(0);
	public ArrayList<ViaggioOrganizzato> listaViaggiOrganizzati = new ArrayList<ViaggioOrganizzato>(
			0);

	public ModelVoli modelVoli = new ModelVoli();
	// ArrayList<Operatore> listaOperatori = new ArrayList<Operatore>(0);

	// gestione input/output su file
	/*
	 * private final String pathRoot = File.separator + "esercizio1" +
	 * File.separator + "data" + File.separator;
	 */
	public final static String pathRoot = Agenzia.class.getProtectionDomain()
			.getCodeSource().getLocation().getPath();
	private final File rootDir = new File(pathRoot);

	public int idGlobaleUtenti = 0;
	public int idGlobalePrenotazioni = 0;
	public int idGlobaleVendite = 0;
	public int idGlobaleViaggiOrganizzati = 0;

	public BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();

	public final File fileUtenti = new File(pathRoot + "utenti.dat");
	public final File fileHotel = new File(pathRoot + "hotel.dat");

	public final File filePrenotazioni = new File(pathRoot + "prenotazioni.dat");
	public final File fileVendite = new File(pathRoot + "vendite.dat");
	public final File fileViaggiOrganizzati = new File(pathRoot + "viaggi.dat");
	public final File fileIdUtenti = new File(pathRoot + "idUtenti.dat");
	// final File fileOperatori = new File(pathRoot + "operatori.dat");
	public final File fileIdPrenotazioni = new File(pathRoot
			+ "idPrenotazioni.dat");
	public final File fileIdVendite = new File(pathRoot + "idVendite.dat");
	public final File fileIdViaggiOrganizzati = new File(pathRoot
			+ "idViaggiOrg.dat");

	public FileInputStream utentiIn = null;
	public FileInputStream hotelIn = null;

	public FileInputStream prenotazioniIn = null;
	public FileInputStream venditeIn = null;
	public FileInputStream viaggiIn = null;
	public FileInputStream idUtentiIn = null;
	public FileInputStream idPrenotazioniIn = null;
	public FileInputStream idVenditeIn = null;
	public FileInputStream idViaggiOrgIn = null;

	public FileOutputStream utentiOut = null;
	public FileOutputStream hotelOut = null;

	public FileOutputStream prenotazioniOut = null;
	public FileOutputStream venditeOut = null;
	public FileOutputStream viaggiOut = null;
	public FileOutputStream idUtentiOut = null;
	public FileOutputStream idPrenotazioniOut = null;
	public FileOutputStream idVenditeOut = null;
	public FileOutputStream idViaggiOrgOut = null;

	public ObjectInputStream objInputStream = null;

	// console per lettura input utente
	public Scanner consoleInput = new Scanner(System.in);

	public Agenzia() throws ClassNotFoundException, IOException {
		initFiles();
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
		rootDir.mkdirs();// creo la dir se non esiste
		if (!fileUtenti.exists()) {

			fileUtenti.createNewFile();

		}
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

		if (!fileIdUtenti.exists()) {
			fileIdUtenti.createNewFile();
			idGlobaleUtenti = 0;
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

		utentiIn = new FileInputStream(fileUtenti);
		hotelIn = new FileInputStream(fileHotel);

		prenotazioniIn = new FileInputStream(filePrenotazioni);
		venditeIn = new FileInputStream(fileVendite);
		viaggiIn = new FileInputStream(fileViaggiOrganizzati);
		idUtentiIn = new FileInputStream(fileIdUtenti);
		idPrenotazioniIn = new FileInputStream(fileIdPrenotazioni);
		idVenditeIn = new FileInputStream(fileIdVendite);
		idViaggiOrgIn = new FileInputStream(fileIdViaggiOrganizzati);

		// inizializzo liste da file
		try {
			objInputStream = new ObjectInputStream(utentiIn);
			listaUtenti = (ArrayList<Utente>) objInputStream.readObject();
			objInputStream.close();
		} catch (EOFException e) {
			// System.out.println("file clienti vuoto");
			// se lista vuota devo creare l accound admin di default
			Utente admin = new Utente("admin", "admin", "admin", "admin", this);
			admin.setUserType(Utente.ADMIN);
			listaUtenti.add(admin);
			this.saveToFile(fileUtenti, this.listaUtenti);
			System.out.println("Utente admin creato");

		}

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
			objInputStream = new ObjectInputStream(idUtentiIn);
			idGlobaleUtenti = (int) objInputStream.readObject();

			objInputStream.close();
		} catch (EOFException e) {
			idGlobaleUtenti = 0;
			this.saveToFile(fileIdUtenti, this.idGlobaleUtenti);

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

}