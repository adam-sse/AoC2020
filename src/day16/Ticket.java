package day16;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Ticket implements Iterable<Integer> {

	private List<Integer> values;

	public Ticket(String line) {
		String[] parts = line.split(",");
		values = new ArrayList<>(parts.length);
		for (String part : parts) {
			values.add(Integer.parseInt(part));
		}
	}
	
	public int getValue(int index) {
		return values.get(index);
	}
	
	public int getNumValues() {
		return values.size();
	}
	
	@Override
	public Iterator<Integer> iterator() {
		return values.iterator();
	}
	
}
