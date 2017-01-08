package com.lonsec.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.input.BOMInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.lonsec.domain.CSVType;
import com.lonsec.service.parser.CSVFileParser;
import com.lonsec.service.parser.CSVFileParserFactory;

/**
 * @author Aravind
 *
 */
@Component
public class CSVProcessor {

	@Value("#{${csvheader.fund}}")
	private Map<String, Integer> fundHeaders;

	@Value("#{${csvheader.benchmark}}")
	private Map<String, Integer> benchmarkHeaders;

	@Value("#{${csvheader.returnSeries}}")
	private Map<String, Integer> returnSeriesHeaders;

	@Autowired
	private CSVFileParserFactory parserFactory;

	public void pareCSV(File file) throws Exception {

		final Reader reader = new InputStreamReader(new BOMInputStream(new FileInputStream(file)));
		final CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader().withIgnoreEmptyLines()
				.withIgnoreHeaderCase().withIgnoreSurroundingSpaces());
		
		try {
			Map<String, Integer> map = parser.getHeaderMap();

			CSVFileParser fileParser = null;

			if (fundHeaders.equals(map)) {
				fileParser = parserFactory.getParser(CSVType.FUND);
			}
			if (benchmarkHeaders.equals(map)) {
				fileParser = parserFactory.getParser(CSVType.BENCHMARK);
			}
			if (returnSeriesHeaders.equals(map)) {
				fileParser = parserFactory.getParser(CSVType.RETURNSERIES);
			}

			if (fileParser != null) {
				List<CSVRecord> records = parser.getRecords();
				fileParser.process(records);
			}
		} finally {
			reader.close();
			parser.close();
		}
	}
	
	public void writeCSV() {
		
	}

}
