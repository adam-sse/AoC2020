package day19;

import java.util.List;
import java.util.Map;

/**
 * Special rule for "late-binding" for recursive rules. This rule
 * is indirectly referenced by {@link #poolIndex} in the map of
 * all rules ({@link #pool}).
 */
public class RecursiveRule extends Rule {

	private int poolIndex;
	
	private Map<Integer, Rule> pool;

	public RecursiveRule(int poolIndex, Map<Integer, Rule> pool) {
		this.poolIndex = poolIndex;
		this.pool = pool;
	}

	@Override
	public List<String> match(String message) {
		return pool.get(poolIndex).match(message);
	}
	
}
