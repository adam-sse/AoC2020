package day21;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class AllergenCombinatorTest {

	@Test
	public void noPossibleAllergensExample() {
		AllergenCombinator comb = new AllergenCombinator(Arrays.asList(
				"mxmxvkd kfcds sqjhc nhms (contains dairy, fish)",
				"trh fvjkl sbzzf mxmxvkd (contains dairy)",
				"sqjhc fvjkl (contains soy)",
				"sqjhc mxmxvkd sbzzf (contains fish)"
				));
		
		assertEquals(new HashSet<>(Arrays.asList("kfcds", "nhms", "sbzzf", "trh")),
				comb.getNoPossibleAllergens());
	}
	
	@Test
	public void countIngredient() {
		AllergenCombinator comb = new AllergenCombinator(Arrays.asList(
				"mxmxvkd kfcds sqjhc nhms (contains dairy, fish)",
				"trh fvjkl sbzzf mxmxvkd (contains dairy)",
				"sqjhc fvjkl (contains soy)",
				"sqjhc mxmxvkd sbzzf (contains fish)"
				));
		
		assertEquals(1, comb.countIngredient("kfcds"));
		assertEquals(1, comb.countIngredient("nhms"));
		assertEquals(2, comb.countIngredient("sbzzf"));
		assertEquals(1, comb.countIngredient("trh"));
	}
	
	@Test
	public void matchAllergenToIngredient() {
		AllergenCombinator comb = new AllergenCombinator(Arrays.asList(
				"mxmxvkd kfcds sqjhc nhms (contains dairy, fish)",
				"trh fvjkl sbzzf mxmxvkd (contains dairy)",
				"sqjhc fvjkl (contains soy)",
				"sqjhc mxmxvkd sbzzf (contains fish)"
				));
		
		Map<String, String> expected = new HashMap<>();
		expected.put("mxmxvkd", "dairy");
		expected.put("sqjhc", "fish");
		expected.put("fvjkl", "soy");
		
		assertEquals(expected, comb.matchAllergenToIngredient());
	}
	
}
