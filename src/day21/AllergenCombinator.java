package day21;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import util.Util;

public class AllergenCombinator {

	private Map<String, Allergen> allergens;
	
	private Set<String> ingredients;
	
	private List<String[]> ingredientLines;
	
	private static final Pattern LINE_PATTERN = Pattern.compile(
			"(?<ingredients>[a-z ]+) \\(contains (?<allergens>[a-z, ]+)\\)");
	
	public AllergenCombinator(List<String> lines) {
		this.allergens = new HashMap<>(lines.size());
		this.ingredients = new HashSet<>();
		this.ingredientLines = new ArrayList<>(lines.size());
		
		for (String line : lines) {
			Matcher m = LINE_PATTERN.matcher(line);
			m.matches();
			
			String[] ingredients = m.group("ingredients").split(" ");
			this.ingredientLines.add(ingredients);
			this.ingredients.addAll(Arrays.asList(ingredients));
			
			for (String allergenName : m.group("allergens").split(",")) {
				allergenName = allergenName.trim();
				Allergen allergen= allergens.get(allergenName);
				if (allergen != null) {
					allergen.updatePossibleIngredients(ingredients);
					
				} else {
					allergens.put(allergenName, new Allergen(allergenName, ingredients));
				}
			}
		}
	}
	
	// ingredient -> possible allergens
	private Map<String, List<Allergen>> getPossibleAllergens() {
		Map<String, List<Allergen>> result = new HashMap<>();
		for (String ingredient : ingredients) {
			result.put(ingredient, new LinkedList<>());
		}
		
		for (Allergen allergen : this.allergens.values()) {
			for (String ingredient : allergen.getPossibleIngredients()) {
				result.get(ingredient).add(allergen);
			}
		}
		
		return result;
	}
	
	public Set<String> getNoPossibleAllergens() {
		return getPossibleAllergens().entrySet().stream()
				.filter(e -> e.getValue().isEmpty())
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
	}
	
	public int countIngredient(String ingredient) {
		int count = 0;
		for (String[] line : ingredientLines) {
			for (String element : line) {
				if (element.equals(ingredient)) {
					count++;
				}
			}
		}
		return count;
	}
	
	// ingredient -> allergen
	public Map<String, String> matchAllergenToIngredient() {
		Map<String, String> result = new HashMap<>(allergens.size());
		
		while (result.size() < allergens.size()) {
			
			for (Allergen allergen : allergens.values()) {
				if (allergen.getPossibleIngredients().size() == 1) {
					String ingredient = allergen.getPossibleIngredients().iterator().next();
					
					result.put(ingredient, allergen.getName());
					
					for (Allergen other : allergens.values()) {
						other.removePossibleIngredient(ingredient);
					}
				}
			}
			
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		List<String> input = Util.readInputFile("/day21/input.txt");
		AllergenCombinator comb = new AllergenCombinator(input);
		
		System.out.println(comb.getNoPossibleAllergens().stream()
				.mapToInt(comb::countIngredient)
				.sum());
		
		System.out.println(comb.matchAllergenToIngredient().entrySet().stream()
			.sorted((e1, e2) -> e1.getValue().compareTo(e2.getValue()))
			.map(Map.Entry::getKey)
			.reduce((i1, i2) -> i1 + "," + i2).get());
	}
	
}
