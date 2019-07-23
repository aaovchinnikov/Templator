package ru.hse.loganalysis.templator.templates;

/**
 * Represents one system logs template string. Each template constitutes of
 * constant parts and placeholders for non-constant/changing parts of message.
 * Every placeholder has format "{name}", for example {ip_address}. 
 * For placeholders without name numbers are used, i.e {1}.
 * 
 * @author sansey
 *
 */
public interface Template {
	String template();
}
