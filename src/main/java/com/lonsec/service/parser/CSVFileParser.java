package com.lonsec.service.parser;

import java.util.List;

import org.apache.commons.csv.CSVRecord;

/**
 * @author Aravind
 *
 */
public interface CSVFileParser {
	
	/**
	 * @param records
	 * @return
	 * @throws Exception
	 */
	public boolean process(List<CSVRecord> records) throws Exception;
}
