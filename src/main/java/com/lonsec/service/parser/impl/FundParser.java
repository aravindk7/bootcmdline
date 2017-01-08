package com.lonsec.service.parser.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.lonsec.domain.Fund;
import com.lonsec.service.parser.CSVFileParser;

/**
 * @author Aravind
 *
 */
@Service("FUND")
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FundParser extends AbstractParser implements CSVFileParser {

	@Override
	public boolean process(List<CSVRecord> records) throws Exception {

		List<Fund> funds = new ArrayList<Fund>();

		records.stream().forEach(record -> {
			Fund fund = new Fund();

			String fundCode = record.get(0);
			String fundName = record.get(1);
			String benchmarkCode = record.get(2);

			boolean isValid = true;

			if (!StringUtils.isNotBlank(fundCode)) {
				fund.setFundCode(fundCode);
			} else {
				isValid = false;
			}

			if (!StringUtils.isNotBlank(fundName)) {
				fund.setFundName(fundName);
			} else {
				isValid = false;
			}
			
			if (!StringUtils.isNotBlank(benchmarkCode)) {
				fund.setBenchmarkCode(benchmarkCode);
			} else {
				isValid = false;
			}

			if (isValid) {
				funds.add(fund);
			}
		});
		
		if (funds.size() ==  0) {
			return false;
		}
		
		int insertCount = fundDao.insertFunds(funds);

		return insertCount == funds.size();
	}

}
