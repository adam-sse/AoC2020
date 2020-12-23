package day23;

import java.util.Deque;
import java.util.LinkedList;

import day23.LinkedCupCircle.Cup;

public class FuckTheCrab {

	private LinkedCupCircle cups;
	
	private Cup currentCup;
	
	public FuckTheCrab(String initial, boolean extended) {
		this.cups = new LinkedCupCircle();
		
		for (int i = 0; i < initial.length(); i++) {
			cups.insertCupAtEnd(initial.charAt(i) - '0');
		}
		if (extended) {
			for (int i = initial.length(); i < 1000000; i++) {
				cups.insertCupAtEnd(i + 1);
			}
		}
		
		this.cups.afterAllAdded();
		currentCup = this.cups.getHead();
	}
	
	private Deque<Cup> pickedUp = new LinkedList<>();
	
	private void move() {
		for (int i = 0; i < 3; i++) {
			pickedUp.push(cups.removeElementAfter(currentCup));
		}
		
		Cup target = currentCup.getNextLowerCup();
		while (target.isCurrentlyRemoved()) {
			target = target.getNextLowerCup();
		}
		
		while (!pickedUp.isEmpty()) {
			cups.insertElementAfter(target, pickedUp.pop());
		}
		
		currentCup = currentCup.getNext();
	}
	
	public void moves(int amount) {
		for (int i = 0; i < amount; i++) {
			move();
		}
	}
	
	public String cupsAfter1ToString() {
		StringBuilder sb = new StringBuilder();
		
		Cup one = cups.searchCup(1);
		Cup i = one.getNext();
		while (i != one) {
			sb.append(i.getValue());
			i = i.getNext();
		}
		
		return sb.toString();
	}
	
	public long multiplyCupsAfter1() {
		Cup one = cups.searchCup(1);
		
		Cup after1 = one.getNext();
		Cup after2 = after1.getNext();
		
		return (long) after1.getValue() * after2.getValue();
	}
	
	public static void main(String[] args) {
		FuckTheCrab ftc = new FuckTheCrab("219347865", false);
		ftc.moves(100);
		System.out.println(ftc.cupsAfter1ToString());
		
		ftc = new FuckTheCrab("219347865", true);
		ftc.moves(10000000);
		System.out.println(ftc.multiplyCupsAfter1());
	}
	
}
