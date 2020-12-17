package day17;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import util.Util;

public class ConwayCube {

	private Set<Vec> active;
	
	public ConwayCube(List<String> initialState) {
		active = new HashSet<>(1024);
		for (int y = 0; y < initialState.size(); y++) {
			String line = initialState.get(y);
			for (int x = 0; x < line.length(); x++) {
				if (line.charAt(x) == '#') {
					active.add(new Vec(x, y, 0, 0));
				}
			}
		}
	}
	
	public void oneTick() {
		Set<Vec> newActive = new HashSet<>(active.size());
		
		// scan all currently active nodes
		// keep inactive neighbors around for loop below
		Set<Vec> inactiveNeighbors = new HashSet<>(active.size() * 80);
		for (Vec active : this.active) {
			
			int numActiveNeighbors = 0;
			for (Vec neighbor : active.iterateNeighbors()) {
				if (this.active.contains(neighbor)) {
					numActiveNeighbors++;
				} else {
					inactiveNeighbors.add(neighbor);
				}
			}
			
			if (numActiveNeighbors == 2 || numActiveNeighbors == 3) {
				newActive.add(active);
			}
		}
		
		// scan inactive that are next to active nodes
		for (Vec inactive : inactiveNeighbors) {
			
			int numActiveNeighbors = 0;
			for (Vec neighbor : inactive.iterateNeighbors()) {
				if (this.active.contains(neighbor)) {
					numActiveNeighbors++;
				}
			}
			
			if (numActiveNeighbors == 3) {
				newActive.add(inactive);
			}
		}
		
		this.active = newActive;
	}
	
	public int countActive() {
		return active.size();
	}
	
	public static void main(String[] args) {
		ConwayCube cube = new ConwayCube(Util.readInputFile("/day17/input.txt"));
		for (int i = 0; i < 6; i++) {
			cube.oneTick();
		}
		System.out.println(cube.countActive());
	}
	
}
