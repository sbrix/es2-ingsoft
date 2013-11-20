package it.unipr.sbrix.esercizio2.Modelli;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;

import it.unipr.sbrix.esercizio2.Agenzia;
import it.unipr.sbrix.esercizio2.Volo;

@SuppressWarnings("unchecked")
public class ModelVoli extends RowTableModel<Volo> implements InitModel,
		EditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1531108120961122693L;
	private final static String[] COLUMN_NAMES = { "Id", "Partenza",
			"Destinazione" };

	private final File fileVoli = new File(Agenzia.pathRoot + "voli.dat");
	private final File fileIdVoli = new File(Agenzia.pathRoot + "idVoli.dat");
	private FileInputStream voliIn = null;
	private ArrayList<Volo> listaVoli = new ArrayList<Volo>(0);
	private int idVoli = 0;
	private FileInputStream idVoliIn = null;

	public ModelVoli() {
		super(Arrays.asList(COLUMN_NAMES));

		setRowClass(Volo.class);
		initFromFile();
		initModel();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addItem(Object item) {
		Volo volo = (Volo) item;
		volo.setId(getNewId());
		listaVoli.add(volo);
		addRow(volo);
		Agenzia.saveToFile(fileVoli, listaVoli);
		Agenzia.saveToFile(fileIdVoli, idVoli);

	}

	@Override
	public Object getItem(int id) {
		// TODO Auto-generated method stub
		for (Volo i : listaVoli) {
			if (i.getId() == id)
				return i;
		}
		return null;
	}

	public int getNewId() {
		return idVoli++;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		Volo volo = getRow(rowIndex);
		switch (columnIndex) {
		case 0:
			return volo.getId();
		case 1:
			return volo.partenza;
		case 2:
			return volo.destinazione;
		default:
			return null;
		}

	}

	@Override
	public void initFromFile() {
		// TODO Auto-generated method stub
		if (!fileVoli.exists()) {

			try {
				fileVoli.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (!fileIdVoli.exists()) {
			try {
				fileIdVoli.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		try {
			voliIn = new FileInputStream(fileVoli);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {

			ObjectInputStream objInputStream = new ObjectInputStream(voliIn);
			listaVoli = (ArrayList<Volo>) objInputStream.readObject();
			objInputStream.close();
		}

		catch (EOFException e) {
			System.out.println("file voli vuoto");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			idVoliIn = new FileInputStream(fileIdVoli);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ObjectInputStream obj = new ObjectInputStream(idVoliIn);
			idVoli = (int) obj.readObject();
			obj.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EOFException e) {
			System.out.println("file Id voli vuoto");
			idVoli = 0;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void initModel() {
		// TODO Auto-generated method stub
		if (this.getRowCount() > 0) {

			this.removeRows(this.getRowCount() - 1);

		}
		for (Volo i : listaVoli) {
			this.addRow(i);
		}

	}

	@Override
	public void removeItem(int id, int row) {
		// TODO Auto-generated method stub
		int index = 0;
		for (Volo i : listaVoli) {
			if (i.getId() == id) {
				listaVoli.remove(index);
				Agenzia.saveToFile(fileVoli, listaVoli);
				break;
			}
			index++;
		}
		removeRowRange(row, row);

	}

}
