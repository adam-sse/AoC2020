package day22;

import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import util.Util;

public class RecursiveCombat {

	private static final boolean LOG = false;
	
	private Deque<Integer> playerDeck;
	
	private Deque<Integer> crabDeck;
	
	private Deque<Integer> winner;
	
	private Set<Configuration> seenConfigurations = new HashSet<>();
	
	private static int nextId = 1;
	
	private int gameId;
	
	private int round = 1;
	
	public RecursiveCombat(List<List<String>> input) {
		playerDeck = readDeck(input.get(0));
		crabDeck = readDeck(input.get(1));
		
		gameId = nextId++;
	}
	
	public RecursiveCombat(Deque<Integer> player, int numPlayer, Deque<Integer> crab, int numCrab) {
		this.playerDeck = copyDeck(player, numPlayer);
		this.crabDeck = copyDeck(crab, numCrab);
		
		gameId = nextId++;
	}
	
	private static Deque<Integer> copyDeck(Deque<Integer> deck, int numCards) {
		Deque<Integer> result = new LinkedList<>();
		int count = 0;
		for (Iterator<Integer> it = deck.iterator(); it.hasNext() && count < numCards; count++) {
			result.add(it.next());
		}
		return result;
	}
	
	private static Deque<Integer> readDeck(List<String> input) {
		Deque<Integer> result = new LinkedList<>();
		
		for (int i = 1; i < input.size(); i++) {
			result.add(Integer.parseInt(input.get(i)));
		}
		
		return result;
	}
	
	private static class Configuration {
		
		private Deque<Integer> playerDeck;
		
		private Deque<Integer> crabDeck;

		public Configuration(Deque<Integer> playerDeck, Deque<Integer> crabDeck) {
			this.playerDeck = playerDeck;
			this.crabDeck = crabDeck;
		}

		@Override
		public int hashCode() {
			return Objects.hash(crabDeck, playerDeck);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (!(obj instanceof Configuration)) {
				return false;
			}
			Configuration other = (Configuration) obj;
			return Objects.equals(crabDeck, other.crabDeck) && Objects.equals(playerDeck, other.playerDeck);
		}
		
	}
	
	private void singleRound() {
		log("-- Round " + round + " (Game " + gameId + ") --");
		log("Player1's deck: " + playerDeck);
		log("Player2's deck: " + crabDeck);
		
		Configuration current = new Configuration(playerDeck, crabDeck);
		if (seenConfigurations.contains(current)) {
			winner = playerDeck;
			return;
		}
		seenConfigurations.add(current);
		
		int playerCard = playerDeck.poll();
		log("Player 1 plays " + playerCard);
		int crabCard = crabDeck.poll();
		log("Player 2 plays " + crabCard);
		
		int winnerCard;
		int loserCard;
		
		Deque<Integer> winner;
		if (playerCard <= playerDeck.size() && crabCard <= crabDeck.size()) {
			log("Playing a sub-game to determine the winner...");
			log();
			
			RecursiveCombat rc = new RecursiveCombat(playerDeck, playerCard, crabDeck, crabCard);
			rc.gameId = gameId + 1;
			rc.playUntilVictory();
			
			log("...anyway, back to game " + gameId + ".");
			if (rc.winner == rc.playerDeck) {
				log("Player 1 wins round " + round + " of game " + gameId);
				winner = playerDeck;
				winnerCard = playerCard;
				loserCard = crabCard;
			} else {
				log("Player 2 wins round " + round + " of game " + gameId);
				winner = crabDeck;
				winnerCard = crabCard;
				loserCard = playerCard;
			}
			
		} else {
			if (playerCard > crabCard) {
				log("Player 1 wins round " + round + " of game " + gameId);
				winner = playerDeck;
				winnerCard = playerCard;
				loserCard = crabCard;
				
			} else {
				log("Player 2 wins round " + round + " of game " + gameId);
				winner = crabDeck;
				winnerCard = crabCard;
				loserCard = playerCard;
			}
		}
		
		winner.add(winnerCard);
		winner.add(loserCard);
		
		if (crabDeck.isEmpty()) {
			log("The winner of game " + gameId + " is player 1!");
			this.winner = playerDeck;
			
		} else if (playerDeck.isEmpty()) {
			log("The winner of game " + gameId + " is player 2!");
			this.winner = crabDeck;
		}
		
		round++;
		log();
	}
	
	public void playUntilVictory() {
		log("=== Game " + gameId + " ===");
		log();
		
		while (winner == null) {
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
	
	private static void log(String line) {
		if (LOG) {
			System.out.println(line);
		}
	}
	private static void log() {
		if (LOG) {
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		RecursiveCombat combat = new RecursiveCombat(Util.readGroupedLineInputFile("/day22/input.txt"));
		combat.playUntilVictory();
		System.out.println(combat.calculateScore());
	}
	
}
