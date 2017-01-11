package com.lonsec.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.lonsec.dao.FundDao;
import com.lonsec.domain.FundPerformance;
import com.lonsec.service.FundsReportService;

/**
 * @author Aravind
 *
 */
@Service
public class FundsReportServiceImpl implements FundsReportService {

	@Autowired
	private FundDao fundDao;

	@Value("#{${lessthan.excess}}")
	private BigDecimal lessThanValue;

	@Value("#{${greaterthan.excess}}")
	private BigDecimal greaterThanValue;

	@Value("${lessthan.outPerformanceText}")
	private String lessThanText;

	@Value("${greaterthan.outPerformanceText}")
	private String greaterThanText;

	@Value("${default.outPerformanceText}")
	private String defaultText;

	public Function<FundPerformance, FundPerformance> excessCalculator = fund -> {
		BigDecimal excess = fund.getFundReturn().subtract(fund.getBenchmarkReturn());
		excess = excess.setScale(2, BigDecimal.ROUND_HALF_DOWN);
		fund.setExcess(excess);
		return fund;
	};

	public Function<FundPerformance, FundPerformance> outPerformanceCalculator = fund -> {

		BigDecimal excess = fund.getExcess();
		int lessthan = excess.compareTo(lessThanValue);
		int greaterthan = excess.compareTo(greaterThanValue);

		if (lessthan < 0 && greaterthan < 0) {
			fund.setOutPerformance(lessThanText);
		} else if (lessthan > 0 && greaterthan > 0) {
			fund.setOutPerformance(greaterThanText);
		} else {
			fund.setOutPerformance(defaultText);
		}
		return fund;
	};

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lonsec.service.FundsReportService#computeReturns()
	 */
	@Override
	public List<FundPerformance> computeReturns() {

		List<FundPerformance> funds = fundDao.loadFundPerformanceData();

		if (funds == null || funds.size() == 0) {
			return null;
		}

		Map<Date, List<FundPerformance>> groupedByDates = enrichForReport(funds);

		List<FundPerformance> computedFunds = computeRank(groupedByDates);

		return computedFunds;
	}

	/**
	 * Compute the missing fields by applying business logic
	 * 
	 * @param funds
	 * @return
	 */
	public Map<Date, List<FundPerformance>> enrichForReport(List<FundPerformance> funds) {
		Map<Date, List<FundPerformance>> groupedByDates = funds.stream()
				.map(excessCalculator.andThen(outPerformanceCalculator))
				.sorted(Comparator.comparing(FundPerformance::getDate).thenComparing(FundPerformance::getFundReturn)
						.reversed())
				.distinct()
				.collect(Collectors.groupingBy(FundPerformance::getDate, LinkedHashMap::new, Collectors.toList()));

		return groupedByDates;
	}

	/**
	 * Set Rank property in the sorted funds
	 * 
	 * @param groupedByDates
	 * @return
	 */
	public List<FundPerformance> computeRank(Map<Date, List<FundPerformance>> groupedByDates) {
		List<FundPerformance> computedFunds = new ArrayList<FundPerformance>();

		for (Map.Entry<Date, List<FundPerformance>> entry : groupedByDates.entrySet()) {
			// System.out.println(entry.getKey());
			int i = 0;
			for (FundPerformance fund : entry.getValue()) {
				fund.setRank(++i);
				computedFunds.add(fund);
			}
		}

		return computedFunds;
	}

}
