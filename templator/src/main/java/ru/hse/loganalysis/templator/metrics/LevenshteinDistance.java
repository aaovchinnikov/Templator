package ru.hse.loganalysis.templator.metrics;

/**
 * Decorates to strings to compute their Levenshtein distance metric.
 * @author Alexander Ovchinnikov
 *
 */
public class LevenshteinDistance implements Metric {
	private final String s1;
	private final String s2;
	
	/**
	 * Constructor
	 * @param s1
	 * @param s2
	 */
	public LevenshteinDistance(String s1, String s2) {
		this.s1 = s1;
		this.s2 = s2;
	}

	/**
	 * @implNote Implementation is copied from Wikipedia as is, 
	 * without deep understanding.
	 */
	@Override
	public int compute() {
		int m = this.s1.length(); 
		int n = this.s2.length();
		int[] D1;
		int[] D2 = new int[n + 1];
		for (int i = 0; i <= n; i++) {
			D2[i] = i;
		}
		for (int i = 1; i <= m; i++) {
			D1 = D2;
			D2 = new int[n + 1];
			for (int j = 0; j <= n; j++) {
				if (j == 0)
					D2[j] = i;
				else {
					int cost = (this.s1.charAt(i - 1) != this.s2.charAt(j - 1)) ? 1 : 0;
					if (D2[j - 1] < D1[j] && D2[j - 1] < D1[j - 1] + cost)
						D2[j] = D2[j - 1] + 1;
					else if (D1[j] < D1[j - 1] + cost)
						D2[j] = D1[j] + 1;
					else
						D2[j] = D1[j - 1] + cost;
				}
			}
		}
		return D2[n];
	}
}