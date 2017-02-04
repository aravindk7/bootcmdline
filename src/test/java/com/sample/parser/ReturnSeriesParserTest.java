package com.sample.parser;

import static com.sample.util.Util.toDateString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.apache.commons.csv.CSVRecord;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sample.AppTest;
import com.sample.domain.CSVType;
import com.sample.domain.ReturnSeries;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppTest.class)
public class ReturnSeriesParserTest extends BaseTest {
	
	private List<ReturnSeries> returns;

	@Before
	public void setup() {
		csvFileParser = parserFactory.getParser(CSVType.RETURNSERIES);
	}

	/**
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void processFundSeries() throws Exception {
		List<CSVRecord> records = getCSVRecords("test/parser/FundReturnSeries.csv");
		returns = (List<ReturnSeries>) csvFileParser.process(records);
		assertEquals(42, returns.size());
		ReturnSeries returnSeries = returns.get(0);
		assertEquals("MF-1-4220", returnSeries.getCode());
		assertEquals("30/06/2016", toDateString(returnSeries.getDate()));
		assertEquals("-4.229084292", returnSeries.getReturnPercent().toPlainString());
		assertNotNull(returnSeries);
	}

	@Test
	public void insertFundSeries() throws Exception {
		csvFileParser.insert(returns);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void processBenchmarkSeries() throws Exception {
		List<CSVRecord> records = getCSVRecords("test/parser/BenchReturnSeries.csv");
		returns = (List<ReturnSeries>) csvFileParser.process(records);
		assertEquals(14, returns.size());
		ReturnSeries returnSeries = returns.get(0);
		assertEquals("BM-18", returnSeries.getCode());
		assertEquals("31/05/2016", toDateString(returnSeries.getDate()));
		assertEquals("1.240472701", returnSeries.getReturnPercent().toPlainString());
		assertNotNull(returnSeries);
	}
	
	@Test
	public void insertBenchmarkSeries() throws Exception {
		csvFileParser.insert(returns);
	}
}
