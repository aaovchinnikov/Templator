package ru.hse.loganalysis.templator.metrics.checks;

/**
 * Decorates three MetricChecks. Computes result of first metric check if
 * provided condition is true, else computes the second.
 * 
 * @author sansey
 */
public class TwoChecksComposite implements MetricCheck {
	private final MetricCheck check1;
	private final MetricCheck check2;
	private final MetricCheck condition;

	/**
	 * @param check1
	 * @param check2
	 * @param condition
	 */
	public TwoChecksComposite(MetricCheck check1, MetricCheck check2,
			MetricCheck condition) {
		this.check1 = check1;
		this.check2 = check2;
		this.condition = condition;
	}

	@Override
	public boolean isTrue() {
		if (this.condition.isTrue()) {
			return this.check1.isTrue();
		} else {
			return this.check2.isTrue();
		}
	}

	@Override
	public boolean isFalse() {
		return !isTrue();
	}
}
