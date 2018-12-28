package gui.table;

import cards.Card;
import gui.FilterCards;
import json.Jason;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class CardTableGUI extends TableGUI{
	private static final long serialVersionUID = -8543861956071891649L;
	@SuppressWarnings("unchecked")
	public CardTableGUI(String title, Object data) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		drawTable(data instanceof ArrayList ? (ArrayList<Card>) data : new ArrayList<>());
		setSize(new Dimension(1050, 600));
	}
	private void drawTable(ArrayList<Card> cards) {
		ArrayList<String> colnames = Jason.cardColumnNames;
		DefaultTableModel model = new DefaultTableModel(colnames.toArray(), 0){
			private static final long serialVersionUID = -6550280855835102010L;
			@Override
			public boolean isCellEditable(int row, int col) {
				int subCol = 0, legCol = 0;
				for (int i = 0; i < columnIdentifiers.size(); i++) {
					if (columnIdentifiers.get(i).equals("subtypes"))
						subCol = i;
					if (columnIdentifiers.get(i).equals("legalities"))
						legCol = i;
				}
				return col == subCol || col == legCol;
			}
		};
		JTable table = new JTable(model);
		model.setColumnIdentifiers(colnames.toArray());
		table.setModel(model);
		applySearch(table, new FilterAction());
		try {
			for (Card card : cards){
				ArrayList<Method> methods = new ArrayList<>();
				ArrayList<Object> values = new ArrayList<>();
				for (String name : colnames){
					methods.add(Card.class.getMethod("get" + name.substring(0, 1).toUpperCase() + name.substring(1)));
				}
				for (Method meth : methods){
					values.add(meth.invoke(card));
				}
				model.addRow(values.toArray());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		table.getColumn("legalities").setCellRenderer(new ButtonRenderer("legalities"));
		table.getColumn("legalities").setCellEditor(new CardButtonEditor(new JCheckBox()));
		table.getColumn("subtypes").setCellRenderer(new ButtonRenderer("subtypes"));
		table.getColumn("subtypes").setCellEditor(new CardButtonEditor(new JCheckBox()));
		JScrollPane scrollPane = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		setSize(new Dimension(500, 600));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		resizeTable(table);
		add(scrollPane);
	}
	private class FilterAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e){
			new FilterCards();
			dispose();
		}
	}
}
