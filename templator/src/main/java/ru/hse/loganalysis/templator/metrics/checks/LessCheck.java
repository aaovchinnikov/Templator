package ru.hse.loganalysis.templator.metrics.checks;

import ru.hse.loganalysis.templator.metrics.Metric;

/**
 * Checks if decorated Metric is more then provided threshold value.
 * @author sansey
 */
public class LessCheck implements MetricCheck {
	private final Metric metric;
	private final int threshold;
	
	/**
	 * @param metric
	 * @param threshold
	 */
	public LessCheck(Metric metric, int threshold) {
		this.metric = metric;
		this.threshold = threshold;
	}

	@Override
	public boolean isTrue() {
		return this.metric.value() > this.threshold;
	}

	@Override
	public boolean isFalse() {
		return !isTrue();
	}

}
