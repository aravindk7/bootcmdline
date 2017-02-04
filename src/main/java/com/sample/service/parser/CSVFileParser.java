package com.sample.service.parser;

import java.util.List;

import org.apache.commons.csv.CSVRecord;

import com.sample.domain.Domain;

/**
 * Generic Interface to process Input File
 * 
 * @author Aravind
 *
 */
public interface CSVFileParser {

	/**
	 * Process CSV Records and construct list of domain models
	 * 
	 * @param records
	 * @return
	 * @throws Exception
	 */
	public List<? extends Domain> process(List<CSVRecord> records) throws Exception;

	/**
	 * Delegate to call Dao to insert records
	 * 
	 * @param <E>
	 * @param records
	 * @return
	 * @throws Exception
	 */
	public boolean insert(List<? extends Domain> records) throws Exception;
}
