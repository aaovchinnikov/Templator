package ru.hse.loganalysis.templator.metrics.checks;

import ru.hse.loganalysis.templator.metrics.Metric;

/**
 * Checks if decorated Metric is less then provided threshold value.
 * @author sansey
 */
public class MoreCheck implements MetricCheck {
	private final Metric metric;
	private final int threshold;
	
	/**
	 * @param metric
	 * @param threshold
	 */
	public MoreCheck(Metric metric, int threshold) {
		this.metric = metric;
		this.threshold = threshold;
	}

	@Override
	public boolean isTrue(String s1, String s2) {
		return this.metric.value(s1,s2) > this.threshold;
	}

	@Override
	public boolean isFalse(String s1, String s2) {
		return !isTrue(s1,s2);
	}

}
