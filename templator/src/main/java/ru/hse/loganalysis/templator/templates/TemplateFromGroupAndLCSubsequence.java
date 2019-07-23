package ru.hse.loganalysis.templator.templates;

import java.util.ArrayList;
import java.util.List;

/**
 * Decorates list of {@link String Strings} and longest common subsequence for
 * this list to compute the template. <br />
 * For computed placeholders numbers are used instead of names, i.e {1}, {2},
 * etc.
 * 
 * @author sansey
 *
 */
public class TemplateFromGroupAndLCSubsequence implements Template {
	private final List<String> list;
	private final String lcs;
	
	/**
	 * Constructor
	 * @param list
	 * @param lcs
	 */
	public TemplateFromGroupAndLCSubsequence(List<String> list, String lcs) {
		this.list = list;
		this.lcs = lcs;
	}

	/**
	 * Creates and returns list of {@link StringBuilder StringBuilders} for each String in {@link #list} 
	 * @return
	 */
	private List<StringBuilder> getStringBuilders() {
		List<StringBuilder> sbList = new ArrayList<StringBuilder>();
		StringBuilder sb;		
		for(String s: this.list){
			sb = new StringBuilder(s);
			sbList.add(sb);
		}
		return sbList;
	}
	
	/**
	 * Returns true, if any string of the {@link #list} contains at least one symbol
	 * @param sbList - list of StringBuilders of the strings in the {@link #list}
	 * @return true, if any string of the {@link #list} contains at least one symbol, or false instead
	 */
	private static boolean hasAnyStringMoreSymbols(List<StringBuilder> sbList) {
		for(StringBuilder sb: sbList){
			if(sb.length() != 0){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @param similarStrings
	 * @param lcSequence
	 * @return
	 */
	private StringBuilder getInitialTemplateCandidate() {
		StringBuilder sb = new StringBuilder();
		List<StringBuilder> sbList = getStringBuilders();
		int i = 0;
		char ch;
		boolean anyStringHasMoreSymbols = hasAnyStringMoreSymbols(sbList);
		
		while (i < this.lcs.length() && anyStringHasMoreSymbols) {
			ch = this.lcs.charAt(i);
			if(isSymbolFirst(ch, sbList)){
				sb.append(ch);
				i++;
				deleteFirstSymbol(sbList);
			} else {
				sb.append("{&}");
				deleteFirstDifferentSymbols(ch, sbList);
			}
			anyStringHasMoreSymbols = hasAnyStringMoreSymbols(sbList);
		}
		if(i==this.lcs.length() && anyStringHasMoreSymbols){
			sb.append("{&}");
		}
		return sb;
	}
	
	/**
	 * @implNote Computation of longest common subsequence is costly, that's why
	 *           it should be reused, not each time computed.
	 */
	@Override
	public String template() {
		StringBuilder template = getInitialTemplateCandidate());
		replaceUnnamedPlaceholdersWithNumbered(template);
		return template.toString();
	}

}
