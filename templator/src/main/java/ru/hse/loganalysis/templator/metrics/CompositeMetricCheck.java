package ru.hse.loganalysis.templator.metrics;

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
public class TwoMetricComposite implements Metric {
	private final int lengthThreshold;
	private final Metric metric1;
	private final Metric metric2;
	
	
	@Override
	public int compute() {
		// TODO Auto-generated method stub
		return 0;
	}

}
