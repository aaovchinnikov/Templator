package ru.hse.loganalysis.templator.metrics;

import ru.hse.loganalysis.templator.lcs.DefaultSubsequence;

/**
 * Decorates to strings to copmute their overlap coefficient metric.
 * @author Alexander Ovchinnikov
 */
public class OverlapCoefficient implements Metric {
	/**
	 * @implNote Computations are based on computing the longest 
	 * common subsequence of strings provided in constructor.<br />
	 * The shorter string is used as a base in division.
	 */
	@Override
	public int value(String s1, String s2) {
		String lcs = new DefaultSubsequence(s1, s2).subsequence();
		int base = Math.min(s1.length(), s2.length());
		return base == 0 ? 0 : (int) lcs.length() * 100 / base;
	}
}
