package com.lonsec.service.parser.impl;

import static com.lonsec.util.Util.stringNotBlank;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.lonsec.domain.Domain;
import com.lonsec.domain.Fund;
import com.lonsec.service.parser.CSVFileParser;

/**
 * Parser to process Fund data in CSV files.
 * 
 * @author Aravind
 *
 */
@Service("FUND")
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FundParser extends AbstractParser implements CSVFileParser {

	private Function<CSVRecord, Fund> populateFundFn = record -> {
		Fund fund = new Fund();
		String fundCode = record.get(0);
		String fundName = record.get(1);
		String benchmarkCode = record.get(2);

		fund.setFundCode(fundCode);
		fund.setFundName(fundName);
		fund.setBenchmarkCode(benchmarkCode);

		return fund;
	};

	@Override
	public List<? extends Domain> process(List<CSVRecord> records) throws Exception {

		List<Fund> funds = records.stream().filter(r -> r.size() == 3).map(populateFundFn)
				.filter(f -> stringNotBlank.test(f.getFundCode())).filter(b -> stringNotBlank.test(b.getFundName()))
				.filter(b -> stringNotBlank.test(b.getBenchmarkCode())).collect(Collectors.toList());

		return funds;
	}

	@Override
	public boolean insert(List<? extends Domain> records) throws Exception {
		@SuppressWarnings("unchecked")
		List<Fund> funds = (List<Fund>) records;

		if (funds == null || funds.size() == 0) {
			return false;
		}

		int insertCount = fundDao.insertFunds(funds);

		return insertCount == funds.size();
	}

}
