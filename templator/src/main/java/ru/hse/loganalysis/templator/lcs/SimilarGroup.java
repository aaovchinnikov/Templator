package ru.hse.loganalysis.templator.lcs;

import java.util.List;

/**
 * Represents a group of similar strings
 * @author aaovchinnikov
 *
 */
public interface SimilarGroup {
	/**
	 * @return List of similar strings
	 */
	List<String> group();
}
