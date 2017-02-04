package com.sample.dao.impl;

import static com.sample.util.Util.getDate;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sample.AppTest;
import com.sample.dao.FundDao;
import com.sample.domain.Benchmark;
import com.sample.domain.Fund;
import com.sample.domain.FundPerformance;
import com.sample.domain.ReturnSeries;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppTest.class)
public class FundDaoTest {
	@Autowired
	private FundDao fundDao;

	List<Fund> funds = null;

	List<Benchmark> benchmarks = null;

	List<ReturnSeries> returns = null;

	@Before 
	public void setUp() {

		funds = Arrays.asList(
				new Fund[] { new Fund("MF-1-10685", "Vanguard Wholesale Australian Fixed Interest Index", "BM-18"),
						new Fund("MF-1-4220", "Goldman Sachs Emerging Leaders Fund", "BM-672") });

		benchmarks = Arrays
				.asList(new Benchmark[] { new Benchmark("BM-18", "Bloomberg AusBond Composite 0+ Year Index AUD"),
						new Benchmark("BM-672", "S&P/ASX Mid Small TR Index AUD") });
		
		returns = Arrays.asList(
				new ReturnSeries[] { new ReturnSeries("MF-1-10685", getDate("2016-06-30"), new BigDecimal("-1.470981688")),
						new ReturnSeries("MF-1-4220", getDate("2016-11-30"), new BigDecimal("-4.229084292")) });
	}

	@Test
	public void insertFunds() {
		int result = fundDao.insertFunds(funds);
		assertEquals(2, result);
	}

	@Test
	public void insertBenchmarks() {
		int result = fundDao.insertBenchmarks(benchmarks);
		assertEquals(2, result);
	}

	@Test
	public void insertReturnSeries() {
		int result = fundDao.insertReturnSeries(returns);
		assertEquals(2, result);
	}

	@Test
	public void loadFundPerformanceData() {
		List<FundPerformance> fundReturns = fundDao.loadFundPerformanceData();
		assertNotNull(fundReturns);
	}

}
