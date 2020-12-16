package day16;

import java.util.ArrayList;
import java.util.List;

import util.Util;

public class Input {

	private List<String> rules;
	
	private String myTicket;
	
	private List<String> nearbyTickets;
	
	public Input(String resource) {
		List<List<String>> groups = Util.readGroupedLineInputFile(resource);
		
		rules = new ArrayList<>(groups.get(0).size());
		for (String line : groups.get(0)) {
			rules.add(line);
		}
		
		myTicket = groups.get(1).get(1);
		
		nearbyTickets = new ArrayList<>(groups.get(2).size() - 1);
		for (int i = 1; i < groups.get(2).size(); i++) {
			nearbyTickets.add(groups.get(2).get(i));
		}
	}
	
	public List<String> getRules() {
		return rules;
	}
	
	public String getMyTicket() {
		return myTicket;
	}
	
	public List<String> getNearbyTickets() {
		return nearbyTickets;
	}
	
}
