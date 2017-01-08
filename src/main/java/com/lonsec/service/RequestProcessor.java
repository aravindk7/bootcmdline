package com.lonsec.service;

/**
 * @author Aravind
 *
 */
public interface RequestProcessor {
	
	/**
	 * @param path
	 */
	void processInputFiles(String path);
	
	/**
	 * 
	 */
	void generateMonthlyReport();
}
