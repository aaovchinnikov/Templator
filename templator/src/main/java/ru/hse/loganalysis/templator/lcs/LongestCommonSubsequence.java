package ru.hse.loganalysis.templator.lcs;

/**
 * Represents longest common subsequence of some strings.<br />
 * Details at <a>https://en.wikipedia.org/wiki/Longest_common_subsequence_problem</a>
 * @author Alexander Ovchinnikov
 *
 */
public interface LongestCommonSubsequence {
	/**
	 * Computes and returns longest common subsequence.
	 * @return
	 */
	String subsequence();
}
