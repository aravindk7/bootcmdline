package com.sample.service;

import static com.sample.util.Util.getDate;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sample.AppTest;
import com.sample.domain.FundPerformance;
import com.sample.service.FundsReportService;
import com.sample.service.impl.FundsReportServiceImpl;
import com.sample.util.Util;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppTest.class)
public class FundsReportServiceTest {

	@Autowired
	FundsReportService fundsReportService;

	private FundsReportServiceImpl service;

	@Before
	public void setUp() {
		service = (FundsReportServiceImpl) fundsReportService;
	}

	@Test
	public void computeOutPerformance() {

		FundPerformance performance = new FundPerformance();

		performance.setExcess(new BigDecimal("1.15"));
		performance = service.outPerformanceCalculator.apply(performance);
		assertEquals("Out Performed", performance.getOutPerformance());

		performance.setExcess(new BigDecimal("-1.15"));
		performance.setOutPerformance(null);
		performance = service.outPerformanceCalculator.apply(performance);
		assertEquals("Under Performed", performance.getOutPerformance());

		performance.setExcess(new BigDecimal("0.3"));
		performance.setOutPerformance(null);
		performance = service.outPerformanceCalculator.apply(performance);
		assertEquals("", performance.getOutPerformance());

	}

	@Test
	public void computeExcess() {

		FundPerformance performance = new FundPerformance();

		performance.setFundReturn(new BigDecimal("1.107845"));
		performance.setBenchmarkReturn(new BigDecimal("-0.04215"));
		performance = service.excessCalculator.apply(performance);
		assertEquals(new BigDecimal("1.15"), performance.getExcess());

		performance.setFundReturn(new BigDecimal("2.5"));
		performance.setBenchmarkReturn(new BigDecimal("1.309476"));
		performance.setExcess(null);
		performance = service.excessCalculator.apply(performance);
		assertEquals(new BigDecimal("1.19"), performance.getExcess());

		performance.setFundReturn(new BigDecimal("2.5"));
		performance.setBenchmarkReturn(new BigDecimal("0"));
		performance.setExcess(null);
		performance = service.excessCalculator.apply(performance);
		assertEquals(new BigDecimal("2.50"), performance.getExcess());

	}

	@Test
	public void computeRank() {
		Map<Date, List<FundPerformance>> groupedByDates = new HashMap<Date, List<FundPerformance>>();
		List<FundPerformance> funds = Arrays
				.asList(new FundPerformance[] { new FundPerformance("1", "fund1", null, new BigDecimal("2.5"), null),
						new FundPerformance("2", "fund3", null, new BigDecimal("0.707635587"), null),
						new FundPerformance("3", "fund2", null, new BigDecimal("0.420467635"), null) });

		groupedByDates.put(new Date(), funds);

		funds = service.computeRank(groupedByDates);
		assertEquals(3, funds.size());
		assertEquals(1, funds.get(0).getRank().intValue());
		assertEquals(2, funds.get(1).getRank().intValue());
		assertEquals(3, funds.get(2).getRank().intValue());
	}

	@Test
	public void testEnrichedData() {
		List<FundPerformance> funds = Arrays.asList(new FundPerformance[] {
				new FundPerformance("MF-1-31705", "Pengana Global Small Companies Fund", getDate("30/11/2016"),
						new BigDecimal("3.874323"), new BigDecimal("-0.127575")),
				new FundPerformance("EF-2-21254", "Vanguard Australian Shares Index ETF", Util.getDate("30/11/2016"),
						new BigDecimal("2.74335"), new BigDecimal("-0.127575")),
				new FundPerformance("MF-1-10685", "Vanguard Wholesale Australian Fixed Interest Index",
						Util.getDate("30/11/2016"), new BigDecimal("-1.470982"), new BigDecimal("-1.460982")),
				new FundPerformance("MF-1-10685", "Vanguard Wholesale Australian Fixed Interest Index",
						Util.getDate("30/11/2016"), new BigDecimal("-1.470982"), new BigDecimal("-1.460982")),
				new FundPerformance("MF-1-10685", "Vanguard Wholesale Australian Fixed Interest Index",
						Util.getDate("30/06/2016"), new BigDecimal("1.286316"), new BigDecimal("1.309476")),
				new FundPerformance("MF-1-9046", "Vanguard Wholesale Emerging Markets Shares Index",
						Util.getDate("30/06/2016"), new BigDecimal("1.217254"), new BigDecimal("-1.809283")),
				new FundPerformance("EF-2-21254", "Vanguard Australian Shares Index ETF", Util.getDate("30/06/2016"),
						new BigDecimal("-2.544124"), new BigDecimal("-1.809283")) });

		Map<Date, List<FundPerformance>> groupedByDates = service.enrichForReport(funds);
		// System.out.println(groupedByDates);
		assertNotNull(groupedByDates);
		assertEquals(2, groupedByDates.size());
	}

}
