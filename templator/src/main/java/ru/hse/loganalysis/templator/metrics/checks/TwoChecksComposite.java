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
	public boolean isTrue(String s1, String s2) {
		if (this.condition.isTrue(s1, s2)) {
			return this.check1.isTrue(s1, s2);
		} else {
			return this.check2.isTrue(s1, s2);
		}
	}

	@Override
	public boolean isFalse(String s1, String s2) {
		return !isTrue(s1, s2);
	}
}
