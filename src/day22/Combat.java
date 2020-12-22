package day22;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import util.Util;

public class Combat {

	private Deque<Integer> playerDeck;
	
	private Deque<Integer> crabDeck;
	
	public Combat(List<List<String>> input) {
		playerDeck = readDeck(input.get(0));
		crabDeck = readDeck(input.get(1));
	}
	
	private static Deque<Integer> readDeck(List<String> input) {
		Deque<Integer> result = new LinkedList<>();
		
		for (int i = 1; i < input.size(); i++) {
			result.add(Integer.parseInt(input.get(i)));
		}
		
		return result;
	}
	
	private void singleRound() {
		int playerCard = playerDeck.poll();
		int crabCard = crabDeck.poll();
		
		Deque<Integer> winner;
		if (playerCard > crabCard) {
			winner = playerDeck;
		} else {
			winner = crabDeck;
		}
		
		int winnerCard = Math.max(playerCard, crabCard);
		int loserCard = Math.min(playerCard, crabCard);
		winner.add(winnerCard);
		winner.add(loserCard);
	}
	
	public void playUntilVictory() {
		while (!playerDeck.isEmpty() && !crabDeck.isEmpty()) {
			singleRound();
		}
	}
	
	public long calculateScore() {
		Deque<Integer> winner;
		if (playerDeck.isEmpty()) {
			winner = crabDeck;
		} else {
			winner = playerDeck;
		}
		
		long score = 0;
		int factor = winner.size();
		for (int card : winner) {
			score += factor * card;
			factor--;
		}
		
		return score;
	}
	
	public static void main(String[] args) {
		Combat combat = new Combat(Util.readGroupedLineInputFile("/day22/input.txt"));
		combat.playUntilVictory();
		System.out.println(combat.calculateScore());
	}
	
}
