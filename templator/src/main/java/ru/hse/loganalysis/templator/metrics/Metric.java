package ru.hse.loganalysis.templator.metrics;

/**
 * Represents integer metric of two Strings
 * @author aaovchinnikov
 *
 */
public interface Metric {
	/**
	 * Returns value of metric
	 * @return value of metric
	 */
	int value(String s1, String s2);
}
