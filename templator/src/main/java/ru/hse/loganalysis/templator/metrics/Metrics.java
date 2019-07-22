package ru.hse.loganalysis.templator.metrics;

import ru.hse.loganalysis.templator.lcs.StringComparison;

public class Metrics {

	public static final int LevenshteinDistance = 1 << 0;
	public static final int OverlapCoefficient = 1 << 1;

	/**
	 * 
	 * @param s1
	 * @param s2
	 * @param metric
	 * @param metricThreshold
	 *            значение метрики, после которого строки начинают считаться
	 *            похожими
	 * @return
	 */
	public static boolean checkMetric(String s1, String s2, int metric, int metricThreshold) {
		switch (metric) {
		case LevenshteinDistance:
			return computeLevenshteinDistance(s1, s2) < metricThreshold;

		case OverlapCoefficient:
			int coef = computeOverlapCoefficient(s1, s2);
			return coef > metricThreshold;

		}
		return false;
	}
	
	/**
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
	public static boolean checkCompositeMetric(String s1, String s2, int lengthThreshold, int levensteinThreshold, int overlapThreshold){
		if(Math.min(s1.length(), s2.length()) < lengthThreshold){
			return checkMetric(s1, s2, Metrics.LevenshteinDistance, 20);
		} else {
			return checkMetric(s1, s2, Metrics.OverlapCoefficient, 90);
		}
	}

	public static int computeSimilarityMetric(int metric, String s1, String s2) {
		switch (metric) {
		case LevenshteinDistance:
			return computeLevenshteinDistance(s1, s2);

		case OverlapCoefficient:
			return computeOverlapCoefficient(s1, s2);
			
			default: throw new IllegalArgumentException("Unknown metric! Use static named metrics in Metrics class");
		}
	}

	private static int computeLevenshteinDistance(String s1, String s2) {
		int m = s1.length(), n = s2.length();
		int[] D1;
		int[] D2 = new int[n + 1];

		for (int i = 0; i <= n; i++)
			D2[i] = i;

		for (int i = 1; i <= m; i++) {
			D1 = D2;
			D2 = new int[n + 1];
			for (int j = 0; j <= n; j++) {
				if (j == 0)
					D2[j] = i;
				else {
					int cost = (s1.charAt(i - 1) != s2.charAt(j - 1)) ? 1 : 0;
					if (D2[j - 1] < D1[j] && D2[j - 1] < D1[j - 1] + cost)
						D2[j] = D2[j - 1] + 1;
					else if (D1[j] < D1[j - 1] + cost)
						D2[j] = D1[j] + 1;
					else
						D2[j] = D1[j - 1] + cost;
				}
			}
		}
		return D2[n];
	}

	private static int computeOverlapCoefficient(String s1, String s2) {
		String lcs = StringComparison.computeLCSubsequence(s1, s2);
		int base = Math.min(s1.length(), s2.length());

		return base == 0 ? 0 : (int) lcs.length() * 100 / base;
	}

}
