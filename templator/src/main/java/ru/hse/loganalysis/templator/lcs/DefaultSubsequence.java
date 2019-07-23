package ru.hse.loganalysis.templator.lcs;

/**
 * Decorates to {@link String String} to compute their longest common.
 * Implementation is based on dynamic programming approach. 
 * @author sansey
 *
 */
public class DefaultSubsequence implements LongestCommonSubsequence {
	private final String s1;
	private final String s2;
	
	/**
	 * Constructor.
	 * @param s1
	 * @param s2
	 */
	public DefaultSubsequence(String s1, String s2) {
		this.s1 = s1;
		this.s2 = s2;
	}

	/**
	 * @implNote Implementation was copied as is, without proper deep understanding the code.
	 */
	@Override
	public String subsequence() {
		// number of lines of each file
		int M = this.s1.length();
		int N = this.s2.length();

		// opt[i][j] = length of LCS of x[i..M] and y[j..N]
		int[][] opt = new int[M + 1][N + 1];

		// compute length of LCS and all subproblems via dynamic programming
		for (int i = M - 1; i >= 0; i--) {
			for (int j = N - 1; j >= 0; j--) {
				if (this.s1.charAt(i) == this.s2.charAt(j)) {
					opt[i][j] = opt[i + 1][j + 1] + 1;
				} else {
					opt[i][j] = Math.max(opt[i + 1][j], opt[i][j + 1]);
				}
			}
		}

		// recover LCS itself
		int i = 0, j = 0;
		StringBuilder sb = new StringBuilder();
		while (i < M && j < N) {
			if (this.s1.charAt(i) == this.s2.charAt(j)) {
				sb.append(s1.charAt(i));
				i++;
				j++;
			} else if (opt[i + 1][j] >= opt[i][j + 1]) {
				i++;
			} else {
				j++;
			}
		}

		return sb.toString();
	}

}
