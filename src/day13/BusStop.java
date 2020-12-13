package day13;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.LongStream;

import util.Util;

public class BusStop {

	private static final int X = -1;
	
	private List<Integer> ids;
	
	public BusStop(String notes) {
		ids = new LinkedList<>();
		for (String bus : notes.split(",")) {
			if (!bus.equals("x")) {
				ids.add(Integer.parseInt(bus));
			} else {
				ids.add(X);
			}
		}
	}
	
	public int getEarliestDepartureAfter(int timestamp) {
		int earliestId = 0;
		int earliestDeparture = Integer.MAX_VALUE;
		
		for (Integer id : ids) {
			if (id == X) {
				continue;
			}
			
			int departure = (int) (Math.ceil((double) timestamp / id) * id);
			if (departure < earliestDeparture) {
				earliestId = id;
				earliestDeparture = departure;
			}
		}
		
		return (earliestDeparture - timestamp) * earliestId;
	}
	
	public long getEarliestSequenceStart() {
		int firstId = ids.get(0);
		LongStream possibleTs = LongStream.iterate(0, l -> l + firstId);
		
		for (int j = 1; j < ids.size(); j++) {
			int i = j;
			int id = ids.get(i);
			if (ids.get(i) != X) {
				possibleTs = possibleTs.filter(possibleT -> (possibleT + i) % id == 0);
			}
		}
		
		return possibleTs.parallel().findFirst().getAsLong();
	}
	
	public String createConstraintSystem() {
		StringBuilder r = new StringBuilder();
		
		r.append("((t%").append(ids.get(0)).append(")=0)");
		for (int i = 1; i < ids.size(); i++) {
			if (ids.get(i) != X) {
				r.append(" and ((t+").append(i).append(")%").append(ids.get(i)).append("=0)");
			}
		}
		
		return r.toString();
	}
	
	public static void main(String[] args) {
		List<String> input = Util.readInputFile("/day13/input.txt");
		
		int timestamp = Integer.parseInt(input.get(0));
		BusStop bs = new BusStop(input.get(1));
		
		System.out.println(bs.getEarliestDepartureAfter(timestamp));
		System.out.print("paste this into WolframAlpha: ");
		System.out.println(bs.createConstraintSystem());
	}

}
