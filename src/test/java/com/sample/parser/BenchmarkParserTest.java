package com.sample.parser;

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
import com.sample.domain.Benchmark;
import com.sample.domain.CSVType;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppTest.class)
public class BenchmarkParserTest extends BaseTest {
	
	private List<Benchmark> benchamarks;
	
	@Before
	public void setup() {
		csvFileParser = parserFactory.getParser(CSVType.BENCHMARK);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void process() throws Exception {
		List<CSVRecord> records = getCSVRecords("test/parser/benchmark.csv");
		benchamarks = (List<Benchmark>) csvFileParser.process(records);
		assertEquals(2, benchamarks.size());
		Benchmark benchmark = benchamarks.get(0);
		assertNotNull(benchmark); 
	}

	@Test
	public void insert() throws Exception {
		csvFileParser.insert(benchamarks);
	}

}
