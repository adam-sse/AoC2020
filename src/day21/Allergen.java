package day21;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Allergen {

	private String name;
	
	private Set<String> possibleIngredients;
	
	public Allergen(String name, String... ingredients) {
		this.name = name;
		this.possibleIngredients = new HashSet<>(Arrays.asList(ingredients));
	}
	
	public String getName() {
		return name;
	}
	
	public void updatePossibleIngredients(String... ingredients) {
		this.possibleIngredients.retainAll(Arrays.asList(ingredients));
	}
	
	public void removePossibleIngredient(String ingredient) {
		this.possibleIngredients.remove(ingredient);
	}
	
	public Set<String> getPossibleIngredients() {
		return Collections.unmodifiableSet(possibleIngredients);
	}
	
	@Override
	public String toString() {
		return name;
	}
	
}
