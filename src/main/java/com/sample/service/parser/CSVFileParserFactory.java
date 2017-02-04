package com.sample.service.parser;

import com.sample.domain.CSVType;

/**
 * Factory to load Parser based on File Types
 * 
 * @author Aravind
 *
 */
public interface CSVFileParserFactory {

	/**
	 * Return Parser based on File Type
	 * 
	 * @param csvType
	 * @return Parser Types
	 */
	public CSVFileParser getParser(CSVType csvType);
}
