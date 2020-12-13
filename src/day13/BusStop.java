package day13;

import java.util.LinkedList;
import java.util.List;

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
	
	// greatest common divisor
	public static long gcd(long a, long b) {
		long z1 = Math.max(a, b);
		long z2 = Math.min(a, b);
		if (z2 == 0) {
			return z1;
		}
		return gcd(z2, z1 % z2);
	}
	
	// least common multiple
	public static long lcm(long a, long b) {
		return Math.abs(a * b) / gcd(a, b);
	}
	
	public long getEarliestSequenceStart() {
		long t = 0;
		long increase = 1;
		for (int i = 0; i < ids.size(); i++) {
			int id = ids.get(i);
			if (id != X) {
				// increase t until it is correct for this bus
				// i.e.: t plus the number of the bus must be a multiple of the ID
				while ((t + i) % id != 0) {
					t += increase;
				}
				
				// now modify the increase steps, to ensure that all future
				// increases don't "break" the solution for this bus
				increase = lcm(increase, id);
			}
		}
		
		return t;
	}
	
	public static void main(String[] args) {
		List<String> input = Util.readInputFile("/day13/input.txt");
		
		int timestamp = Integer.parseInt(input.get(0));
		BusStop bs = new BusStop(input.get(1));
		
		System.out.println(bs.getEarliestDepartureAfter(timestamp));
		System.out.println(bs.getEarliestSequenceStart());
	}

}
