package ru.hse.loganalysis.templator.lcs;

import java.util.Iterator;
import java.util.List;

/**
 * Decorates a list of {@link String Strings} to compute their longest common
 * subsequence
 * 
 * @author sansey
 *
 */
public class SubsequenceForGroup implements LongestCommonSubsequence {
	private final List<String> list;

	/**
	 * @param list
	 */
	public SubsequenceForGroup(List<String> list) {
		this.list = list;
	}

	private void checkListForConstraints() {
		if (this.list == null || this.list.isEmpty()) {
			throw new IllegalArgumentException(
					"List shouldn't be null or empty");
		}
		if (this.list.size() < 2) {
			throw new IllegalArgumentException(
					"List should have at least two elements");
		}		
	}
	
	/**
	 * @implNote Such correct subsequences may be more then one. Implementation
	 * returns one of them but can't determine which one of the set,
	 * because implementation doesn't compute this set.
	 */
	@Override
	public String subsequence() {
		checkListForConstraints();
		Iterator<String> iterator = this.list.iterator();
		boolean first = true;
		String lcs = "";
		while(iterator.hasNext()) {
			if (first) {
				lcs = iterator.next();
				first = false;
			} else {
				String s = iterator.next();
				lcs = new DefaultSubsequence(s, lcs).subsequence();				
			}
		}	
		return lcs;
	}
}
