package com.lonsec.parser;

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
import com.lonsec.domain.Fund;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppTest.class)
public class FundParserTest extends BaseTest {

	private List<Fund> funds;

	@Before
	public void setup() {
		csvFileParser = parserFactory.getParser(CSVType.FUND);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void process() throws Exception {
		List<CSVRecord> records = getCSVRecords("test/parser/fund.csv");
		funds = (List<Fund>) csvFileParser.process(records);
		assertEquals(6, funds.size());
		Fund fund = funds.get(0);
		assertNotNull(fund);
	}

	@Test
	public void insert() throws Exception {
		csvFileParser.insert(funds);
	}

}
