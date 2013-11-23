package it.unipr.sbrix.esercizio2;

import it.unipr.sbrix.esercizio2.Modelli.ModelHotel;
import it.unipr.sbrix.esercizio2.Modelli.ModelPrenotazioni;
import it.unipr.sbrix.esercizio2.Modelli.ModelUtenti;
import it.unipr.sbrix.esercizio2.Modelli.ModelViaggiOrganizzati;
import it.unipr.sbrix.esercizio2.Modelli.ModelVoli;
import it.unipr.sbrix.esercizio2.Modelli.ModelEvent;
import it.unipr.sbrix.esercizio2.Modelli.ModelListener;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
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

	
	
	public ArrayList<Vendita> listaVendite = new ArrayList<Vendita>(0);

	
	
	public ModelUtenti modelUtenti = null;
	public ModelUtenti modelClienti = null;
	public ModelVoli modelVoli = null;
	public ModelHotel modelHotel = null;
	public ModelViaggiOrganizzati modelViaggi = null;
	public ModelPrenotazioni modelPrenotazioni = null;
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
	
	public int idGlobaleVendite = 0;

	

	public static BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();

	
	
	public final File fileVendite = new File(pathRoot + "vendite.dat");

	
	// final File fileOperatori = new File(pathRoot + "operatori.dat");
	
	public final File fileIdVendite = new File(pathRoot + "idVendite.dat");

	

	
	
	public FileInputStream venditeIn = null;

	
	public FileInputStream idVenditeIn = null;

	

	public ObjectInputStream objInputStream = null;

	// console per lettura input utente
	public Scanner consoleInput = new Scanner(System.in);

	public Agenzia() throws ClassNotFoundException, IOException {
		rootDir.mkdirs();// creo la dir se non esiste
		initFiles();
		modelUtenti = new ModelUtenti(ModelUtenti.INIT_UTENTE);
		modelClienti = new ModelUtenti(ModelUtenti.INIT_CLIENTE);
		modelVoli = new ModelVoli();
		modelHotel = new ModelHotel();
		modelViaggi = new ModelViaggiOrganizzati();
		modelPrenotazioni = new ModelPrenotazioni();
		modelUtenti.addUpdateEventListener(new ModelListener() {

			@Override
			public void updateEventOccurred(ModelEvent evt) {
				// TODO Auto-generated method stub
				
				modelClienti.initFromFile();
				modelClienti.initModel();

			}
		});

		modelClienti.addUpdateEventListener(new ModelListener() {

			@Override
			public void updateEventOccurred(ModelEvent evt) {
				// TODO Auto-generated method stub
				
				modelUtenti.initFromFile();
				modelUtenti.initModel();

			}
		});
		
		modelVoli.addUpdateEventListener(new ModelListener() {
			
			@Override
			public void updateEventOccurred(ModelEvent evt) {
				// TODO Auto-generated method stub
				
				modelVoli.initFromFile();
				modelVoli.initModel();
				
			}
		});
		
		modelHotel.addUpdateEventListener(new ModelListener() {
			
			@Override
			public void updateEventOccurred(ModelEvent evt) {
				// TODO Auto-generated method stub
				modelHotel.initFromFile();
				modelHotel.initModel();
				
			}
		});	
		
		modelViaggi.addUpdateEventListener(new ModelListener() {
			
			@Override
			public void updateEventOccurred(ModelEvent evt) {
				// TODO Auto-generated method stub
				modelViaggi.initFromFile();
				modelViaggi.initModel();
				
			}
		});
	}

	

	@SuppressWarnings("unchecked")
	void initFiles() throws IOException, ClassNotFoundException {

		

		
		if (!fileVendite.exists()) {

			fileVendite.createNewFile();
		}
		

		/*
		 * if (!fileOperatori.exists()) {
		 * 
		 * fileOperatori.createNewFile(); }
		 */

		
		if (!fileIdVendite.exists()) {
			idGlobaleVendite = 0;
			fileIdVendite.createNewFile();

		}

		

		

		
		venditeIn = new FileInputStream(fileVendite);
		

		
		idVenditeIn = new FileInputStream(fileIdVendite);
		

		// inizializzo liste da file

		

		

		

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
		

		

	}

}