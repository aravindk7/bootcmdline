package com.lonsec.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.lonsec.dao.FundDao;
import com.lonsec.domain.FundPerformance;
import com.lonsec.service.FundsReturnService;

/**
 * @author Aravind
 *
 */
@Service
public class FundsReturnServiceImpl implements FundsReturnService {

	@Autowired
	private FundDao fundDao;

	@Value("#{${lessthan.excess}}")
	private BigDecimal lessThanValue;

	@Value("#{${greaterthan.excess}}")
	private BigDecimal greaterThanValue;

	@Value("#{${lessthan.outPerformanceText}}")
	private String lessThanText;

	@Value("#{${greaterthan.outPerformanceText}}")
	private String greaterThanText;

	@Override
	public void computeReturns() {

		List<FundPerformance> funds = fundDao.loadFundPerformanceData();

		funds.stream().forEach(fund -> {
			BigDecimal excess = fund.getFundReturn().subtract(fund.getBenchmarkReturn());
			excess = excess.setScale(2, BigDecimal.ROUND_HALF_DOWN);
			fund.setExcess(excess);

			int lessthan = excess.compareTo(lessThanValue);
			int greaterthan = excess.compareTo(greaterThanValue);

			if (lessthan < 0 && greaterthan < 0) {
				fund.setOutPerformance(lessThanText);
			}
			if (lessthan > 0 && greaterthan > 0) {
				fund.setOutPerformance(greaterThanText);
			}

		});

		Map<Date, List<FundPerformance>> dates = funds.stream()
				.collect(Collectors.groupingBy(FundPerformance::getDate));
	}

}
