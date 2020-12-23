package day23;

public class LinkedCupCircle {

	private int size;
	
	private Cup head;
	
	private Cup tail;
	
	private Cup currentSearchPtr;
	
	public void insertCupAtEnd(int value) {
		Cup newElement = new Cup(value);
		
		if (head == null) {
			head = new Cup(value);
			tail = head;
		} else {
			tail.setNext(newElement);
			tail = newElement;
		}
		tail.setNext(head);
		
		size++;
	}
	
	public void afterAllAdded() {
		tail = null; // not needed anymore
		currentSearchPtr = head;
		
		Cup e = head;
		for (int i = 0; i < getSize(); i++) {
			int lowerValue = e.getValue() - 1;
			if (lowerValue == 0) {
				lowerValue = getSize();
			}
			e.nextLowerCup = searchCup(lowerValue);
			
			e = e.getNext();
		}
	}
	
	public int getSize() {
		return size;
	}
	
	public Cup getHead() {
		return head;
	}
	
	public Cup searchCup(int value) {
		int i = 0;
		while (currentSearchPtr.value != value && i < getSize()) {
			currentSearchPtr = currentSearchPtr.getNext();
			i++;
		}
		return currentSearchPtr.value == value ? currentSearchPtr : null;
	}
	
	public Cup removeElementAfter(Cup before) {
		Cup toRemove = before.getNext();
		before.setNext(toRemove.getNext());
		if (toRemove == head) {
			head = before.getNext();
		}
		toRemove.currentlyRemoved = true;
		return toRemove;
	}
	
	public void insertElementAfter(Cup before, Cup toInsert) {
		toInsert.setNext(before.getNext());
		before.setNext(toInsert);
		toInsert.currentlyRemoved = false;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		if (head != null) {
			sb.append(head.getValue());
			Cup e = head.getNext();
			while (e != head) {
				sb.append(", ").append(e.getValue());
				e = e.getNext();
			}
		}
		sb.append("]");
		
		return sb.toString();
	}
	
	public static final class Cup {
		
		private int value;
		
		private Cup next;
		
		private boolean currentlyRemoved;
		
		private Cup nextLowerCup;
		
		private Cup(int value) {
			this.value = value;
		}
		
		private void setNext(Cup next) {
			this.next = next;
		}
		
		public Cup getNext() {
			return next;
		}
		
		public int getValue() {
			return value;
		}
		
		public Cup getNextLowerCup() {
			return nextLowerCup;
		}
		
		public boolean isCurrentlyRemoved() {
			return currentlyRemoved;
		}
		
	}
	
}
