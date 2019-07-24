package ru.hse.loganalysis.templator.metrics;

/**
 * Decorates two Strings to compute minimum of their length.
 * @author sansey
 *
 */
public class MinOfTwoStringsLength implements Metric {
	@Override
	public int value(String s1, String s2) {
		return Math.min(s1.length(), s2.length());
	}
}
