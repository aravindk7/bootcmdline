package com.lonsec.service;

/**
 * Delegate class to process user input
 * 
 * @author Aravind
 *
 */
public interface RequestProcessor {

	/**
	 * Process the Input File and insert the records in database
	 * 
	 * @param path
	 *            file or Folder Path. If folder path is input, the nall the CSV
	 *            files in the folder will be processed. Files are validated and
	 *            processed
	 */
	void processInputFiles(String path);

	/**
	 * Generate Monthly CSV Report File
	 */
	void generateMonthlyReport();
}
