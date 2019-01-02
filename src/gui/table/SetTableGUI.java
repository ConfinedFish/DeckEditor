package gui.table;

import cards.CardSet;
import gui.FilterCards;
import io.magicthegathering.javasdk.api.SetAPI;
import io.magicthegathering.javasdk.resource.MtgSet;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SetTableGUI extends TableGUI{
	private static final long serialVersionUID = 2294119736240909555L;
//	private XMLParse parse;
	
	public SetTableGUI(String title){
		super(title);
		setSize(new Dimension(1050, 600));
		drawTable();
	}
	
	private void drawTable(){
		List<MtgSet> sets = SetAPI.getAllSets();
		ArrayList<Field> fields = new ArrayList<>(Arrays.asList(CardSet.class.getDeclaredFields()));
		ArrayList<String> colnames = new ArrayList<>();
		fields.forEach(c -> colnames.add(c.getName()));
		DefaultTableModel model = new DefaultTableModel(colnames.toArray(), 0){
			private static final long serialVersionUID = 4914153800432984346L;
			
			@Override
			public boolean isCellEditable(int row, int col){
				int cardCol = 0;
				for (int i = 0; i < columnIdentifiers.size(); i++){
					if (columnIdentifiers.get(i).equals("cards")){
						cardCol = i;
					}
				}
				return col == cardCol;
			}
		};
		JTable table = new JTable(model);
		model.setColumnIdentifiers(colnames.toArray());
		table.setModel(model);
		applySearch(table, new FilterAction());
		try{
			for (MtgSet set : sets){
				ArrayList<Method> methods = new ArrayList<>();
				ArrayList<Object> values = new ArrayList<>();
				for (String name : colnames){
					if (name.startsWith("is"))
						methods.add(CardSet.class.getMethod(name));
					else{
						methods.add(CardSet.class
								.getMethod("get" + name.substring(0, 1).toUpperCase() + name.substring(1)));
					}
				}
				for (Method meth : methods){
					values.add(meth.invoke(set));
				}
				model.addRow(values.toArray());
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		resizeTable(table);
		table.getColumn("cards").setCellRenderer(new ButtonRenderer("cards"));
		table.getColumn("cards").setCellEditor(new SetButtonEditor(new JCheckBox()));
		JScrollPane scrollPane = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
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
