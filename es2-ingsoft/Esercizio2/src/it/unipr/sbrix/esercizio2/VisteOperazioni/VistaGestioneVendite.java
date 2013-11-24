package it.unipr.sbrix.esercizio2.VisteOperazioni;

import it.unipr.sbrix.esercizio2.Agenzia;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VistaGestioneVendite extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -423610775413026112L;
	/**
	 * 
	 */

	private JPanel panelLista = new JPanel();
	private JPanel panelButtons = new JPanel();

	private JButton btnRimuovi = new JButton("Rimuovi");
	private final JLabel lblGestioneVendite = new JLabel("Gestione vendite");

	private JTable table = null;
	private final JScrollPane scrollPane = new JScrollPane();

	/**
	 * Create the panel.
	 */
	public VistaGestioneVendite(int uType, int id, final Agenzia ag) {

		table = new JTable(ag.modelVendite);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 780, 70, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0 };
		setLayout(gridBagLayout);
		GridBagConstraints gbc_panelLista = new GridBagConstraints();
		gbc_panelLista.fill = GridBagConstraints.BOTH;
		gbc_panelLista.insets = new Insets(0, 0, 5, 5);
		gbc_panelLista.gridx = 0;
		gbc_panelLista.gridy = 0;
		add(panelLista, gbc_panelLista);
		panelLista.setLayout(new BoxLayout(panelLista, BoxLayout.Y_AXIS));
		lblGestioneVendite.setAlignmentX(Component.CENTER_ALIGNMENT);

		panelLista.add(lblGestioneVendite);

		panelLista.add(scrollPane);
		scrollPane.setViewportView(table);

		GridBagConstraints gbc_panelButtons = new GridBagConstraints();
		gbc_panelButtons.insets = new Insets(0, 0, 5, 0);
		gbc_panelButtons.anchor = GridBagConstraints.NORTH;
		gbc_panelButtons.gridx = 1;
		gbc_panelButtons.gridy = 0;
		add(panelButtons, gbc_panelButtons);

		panelButtons.setLayout(new GridLayout(10, 1, 0, 0));
		btnRimuovi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1) {
					ag.modelVendite.removeItem(
							(int) ag.modelVendite.getValueAt(
									table.getSelectedRow(), 0),
							table.getSelectedRow());

				}
			}
		});

		panelButtons.add(btnRimuovi);

		this.revalidate();
		this.repaint();
		this.setVisible(true);

	}

}
