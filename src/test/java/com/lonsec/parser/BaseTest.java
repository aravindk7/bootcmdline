package com.lonsec.parser;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.input.BOMInputStream;
import org.junit.After;
import org.springframework.beans.factory.annotation.Autowired;

import com.lonsec.service.parser.CSVFileParser;
import com.lonsec.service.parser.CSVFileParserFactory;

public abstract class BaseTest {

	@Autowired
	protected CSVFileParserFactory parserFactory;

	protected CSVFileParser csvFileParser;

	protected static Reader reader;

	protected static CSVParser parser;

	protected List<CSVRecord> getCSVRecords(String filePath) throws IOException {

		try {
			ClassLoader classLoader = getClass().getClassLoader();

			if (parser != null) {
				parser.close();
			}
			if (reader != null) {
				reader.close();
			}
			reader = new InputStreamReader(new BOMInputStream(classLoader.getResourceAsStream(filePath)));
			parser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader().withIgnoreEmptyLines().withIgnoreHeaderCase()
					.withIgnoreSurroundingSpaces());
			return parser.getRecords();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	@After
	public void tearDown() {
		try {
			if (parser != null) {
				parser.close();
			}
			if (reader != null) {
				reader.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
