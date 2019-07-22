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
	public boolean isTrue() {
		return this.metric.compute() < this.threshold;
	}

	@Override
	public boolean isFalse() {
		return !isTrue();
	}

}
