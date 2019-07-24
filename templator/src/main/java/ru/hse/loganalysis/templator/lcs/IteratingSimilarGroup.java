package ru.hse.loganalysis.templator.lcs;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import ru.hse.loganalysis.templator.metrics.LevenshteinDistance;
import ru.hse.loganalysis.templator.metrics.MinOfTwoStringsLength;
import ru.hse.loganalysis.templator.metrics.OverlapCoefficient;
import ru.hse.loganalysis.templator.metrics.checks.LessCheck;
import ru.hse.loganalysis.templator.metrics.checks.MetricCheck;
import ru.hse.loganalysis.templator.metrics.checks.MoreCheck;
import ru.hse.loganalysis.templator.metrics.checks.TwoChecksComposite;

/**
 * Decorates list of strings. Each call to {@link #group()} returns next group
 * of similar strings from the original list.<br />
 * It's not thread safe implementation.
 * 
 * @author aaovchinnikov
 *
 */
public class IteratingSimilarGroup implements SimilarGroup {
	private final List<String> list;
	private final Iterator<String> outer;
	private final Iterator<String> inner;
	private final MetricCheck check;

	public IteratingSimilarGroup(List<String> list, MetricCheck check) {
		this.list = list;
		this.check = check;
		this.outer = this.list.iterator();
		this.inner = this.list.iterator();
	}

	/**
	 * @return List of similar strings. List contains one element if the string
	 *         doesn't have similars. List is empty if full iteration over original
	 *         collection is done.
	 * @implNote Each call returns next group of similar strings from the original
	 *           list.<br />
	 *           It's assumed (but not proved) that similarity relation is NOT
	 *           transitive. As a consequence we need to make full match
	 *           (any-to-any) during group construction. A one string is taken from
	 *           list and compared to all strings in the list (including itself
	 *           too).
	 */
	@Override
	//TODO переместить параметры расчёта метрик, чтобы можно было создать объект, считающий эту метрику.
	// боремся за возможность конфигурировать получение групп похожих сообщений за счёт разных метрик, передаваемых в конструкторе.
	public List<String> group() {
		List<String> similarStrings = new LinkedList<String>();
		if (this.outer.hasNext()) {
			String currentMessage = this.outer.next();
			while (this.inner.hasNext()) {
				String s = this.inner.next();
/*
				MetricCheck check = new TwoChecksComposite(
						new LessCheck(new LevenshteinDistance(currentMessage, s), 20),
						new MoreCheck(new OverlapCoefficient(currentMessage, s), 90),
						new LessCheck(new MinOfTwoStringsLength(currentMessage, s), LENGTH_THRESHOLD));
*/
				if (this.check.isTrue()) {
					similarStrings.add(s);
				}
			}
		}
		return similarStrings;
	}

}
