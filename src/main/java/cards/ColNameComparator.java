package main.java.cards;

import java.util.Comparator;
import java.util.Map;

public class ColNameComparator implements Comparator<String> {
	private Map<String, Integer> sortorder;
	public ColNameComparator(Map<String, Integer> sortOrder) {
		this.sortorder = sortOrder;
	}
	@Override
	public int compare(String name1, String name2) {
		Integer pos1 = sortorder.get(name1);
		if (pos1 == null) {
			throw new IllegalArgumentException("Bad name encountered: " + name1);
		}
		Integer pos2 = sortorder.get(name2);
		if (pos2 == null) {
			throw new IllegalArgumentException("Bad name encountered: " + name1);
		}
		return pos1.compareTo(pos2);
	}
}
