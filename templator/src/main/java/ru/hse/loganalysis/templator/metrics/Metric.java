package ru.hse.loganalysis.templator.metrics;

/**
 * Represents integer metric of any kind
 * @author aaovchinnikov
 *
 */
public interface Metric {
	/**
	 * Returns value of metric
	 * @return value of metric
	 */
	int value();
}
