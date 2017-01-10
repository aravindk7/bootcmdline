package com.lonsec.parser;

import static com.lonsec.util.Util.toDateString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.apache.commons.csv.CSVRecord;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lonsec.AppTest;
import com.lonsec.domain.CSVType;
import com.lonsec.domain.ReturnSeries;

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
	public void process() throws Exception {
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
	public void insert() throws Exception {
		csvFileParser.insert(returns);
	}

}
