package ru.hse.loganalysis.templator.metrics.checks;

import ru.hse.loganalysis.templator.metrics.Metric;
import ru.hse.loganalysis.templator.metrics.Metrics;

/**
 * Decorates two metrics to co
 * Проверяет строки по композитной метрике похожести. Композитная метрика рассчитывается как:<br/>
 * <code>
 * Math.min(s1.length(), s2.length()) < lengthThreshold ? checkMetric(s1, s2, Metrics.LevenshteinDistance, 20) : checkMetric(s1, s2, Metrics.OverlapCoefficient, 80)
 * 
 * </code>
 * 
 * @param s1
 * @param s2
 * @param lengthThreshold
 * @param levensteinThreshold
 * @param overlapThreshold
 * @return
 */
public class TwoChecksComposite implements MetricCheck {
	private final int lengthThreshold;
	private final MetricCheck check1;
	private final MetricCheck check2;
	
	@Override
	public boolean isTrue() {
		if(Math.min(s1.length(), s2.length()) < lengthThreshold){
			return checkMetric(s1, s2, Metrics.LevenshteinDistance, 20);
		} else {
			return checkMetric(s1, s2, Metrics.OverlapCoefficient, 90);
		}
	}

	@Override
	public boolean isFalse() {
		return !isTrue();
	}

}
