package ru.hse.loganalysis.templator.metrics.checks;

public interface MetricCheck {
	/**
	 * @implSpec implementations should guarantee that isFalse == !(isTrue) and the reverse (i.e isTrue == !(isFalse))
	 * @return
	 */
	boolean isTrue(final String s1, final String s2);
	/**
	 * @implSpec implementations should guarantee that isFalse == !(isTrue) and the reverse (i.e isTrue == !(isFalse))
	 * @return
	 */
	boolean isFalse(final String s1, final String s2);
}
