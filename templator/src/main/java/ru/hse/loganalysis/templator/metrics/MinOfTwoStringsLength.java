package ru.hse.loganalysis.templator.metrics;

/**
 * Decorates two Strings to compute minimum of their length.
 * @author sansey
 *
 */
public class MinOfTwoStringsLength implements Metric {
	private final String s1;
	private final String s2;
	
	/**
	 * @param s1
	 * @param s2
	 */
	public MinOfTwoStringsLength(String s1, String s2) {
		this.s1 = s1;
		this.s2 = s2;
	}

	@Override
	public int compute() {
		return Math.min(s1.length(), s2.length());
	}

}
