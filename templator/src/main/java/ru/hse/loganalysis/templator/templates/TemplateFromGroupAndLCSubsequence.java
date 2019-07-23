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
	private boolean hasAnyStringMoreSymbols(List<StringBuilder> sbList) {
		for(StringBuilder sb: sbList){
			if(sb.length() != 0){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Checks if passed symbol is first in all passed StringBuilders 
	 * @param ch - symbol to search
	 * @param sbList - list of {@link StringBuilder StringBuilders} to search the symbol in
	 * @return
	 */
	private boolean isSymbolFirst(char ch, List<StringBuilder> sbList) {
		for(StringBuilder sb: sbList){
			if(sb.charAt(0) != ch){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Deletes/truncates the first symbol form all passed StringBuiders
	 * @param sbList
	 */
	private void deleteFirstSymbol(List<StringBuilder> sbList) {
		for(StringBuilder sb: sbList){
			sb.delete(0, 1);
		}
	}
	
	/**
	 * Deletes/truncates all symbols from the beginning of StringBuilder to passed symbol <strong>ch</strong> for all passed StringBuilders
	 * @param ch
	 * @param sbList
	 */
	private static void deleteFirstDifferentSymbols(char ch, List<StringBuilder> sbList) {
		int i;
		for(StringBuilder sb: sbList){
			i=0;
			while (ch != sb.charAt(i) && i < sb.length()) {
				i++;
			}
			sb.delete(0, i);
		}
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
	
	private void replaceUnnamedPlaceholdersWithNumbered(StringBuilder template) {
		int index=0;
		int indexesCount=0;
		
		index=template.indexOf("{&}");
		while(index!=-1){
			template.replace(index+1, index+2, String.valueOf(indexesCount));
			indexesCount++;
			index=template.indexOf("{&}", index);	
		}
	}
	
	/**
	 * @implNote Computation of longest common subsequence is costly, that's why
	 *           it should be reused, not each time computed.
	 */
	@Override
	public String template() {
		StringBuilder template = getInitialTemplateCandidate();
		replaceUnnamedPlaceholdersWithNumbered(template);
		return template.toString();
	}

}
