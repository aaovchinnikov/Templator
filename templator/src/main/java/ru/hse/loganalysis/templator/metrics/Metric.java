package ru.hse.loganalysis.templator.metrics;

public interface Metric {
	/**
	 * Computes the distance/similarity metric.
	 * @return result of metric computation
	 */
	int compute();
}
