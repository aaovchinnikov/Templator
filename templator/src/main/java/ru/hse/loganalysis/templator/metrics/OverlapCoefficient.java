package ru.hse.loganalysis.templator.metrics;

import ru.hse.loganalysis.templator.lcs.DefaultSubsequence;

/**
 * Decorates to strings to copmute their overlap coefficient metric.
 * @author Alexander Ovchinnikov
 */
public class OverlapCoefficient implements Metric {
	private final String s1;
	private final String s2;

	/**
	 * Constructor.
	 * @param s1
	 * @param s2
	 */
	public OverlapCoefficient(String s1, String s2) {
		this.s1 = s1;
		this.s2 = s2;
	}

	/**
	 * @implNote Computations are based on computing the longest 
	 * common subsequence of strings provided in constructor.<br />
	 * The shorter string is used as a base in division.
	 */
	@Override
	public int value() {
		String lcs = new DefaultSubsequence(this.s1, this.s2).subsequence();
		int base = Math.min(s1.length(), s2.length());
		return base == 0 ? 0 : (int) lcs.length() * 100 / base;
	}
}
