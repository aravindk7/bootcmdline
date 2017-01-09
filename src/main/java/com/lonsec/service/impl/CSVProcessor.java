package com.lonsec.service.impl;

import static com.lonsec.util.Util.toDateString;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.input.BOMInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.lonsec.domain.CSVType;
import com.lonsec.domain.FundPerformance;
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

	@Value("${csvheader.monthtlyReport}")
	private List<String> csvOutputFileHeader;

	@Value("${csv.fileName}")
	private String fileName;

	@Autowired
	private CSVFileParserFactory parserFactory;

	private static final String NEW_LINE_SEPARATOR = "\n";

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

	public void writeCSV(List<FundPerformance> funds) {
		FileWriter fileWriter = null;
		CSVPrinter csvFilePrinter = null;
		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);

		try {
			fileWriter = new FileWriter(fileName);
			csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);
			csvFilePrinter.printRecord(csvOutputFileHeader);

			for (FundPerformance fund : funds) {
				List<String> record = new ArrayList<String>();
				record.add(fund.getFundName());
				record.add(toDateString(fund.getDate()));
				record.add(fund.getExcess().toPlainString());
				record.add(fund.getOutPerformance());
				record.add(fund.getFundReturn().toPlainString());
				record.add(fund.getRank().toString());
				csvFilePrinter.printRecord(record);
			}
		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
				csvFilePrinter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter/csvPrinter !!!");
				e.printStackTrace();
			}
		}
	}

}
