package com.lonsec.service.parser;

import com.lonsec.domain.CSVType;

/**
 * @author Aravind
 *
 */
public interface CSVFileParserFactory {
	
	/**
	 * @param csvType
	 * @return
	 */
	public CSVFileParser getParser(CSVType csvType);
}
