package com.lonsec.service.parser.impl;

import static com.lonsec.util.Util.getDate;
import static com.lonsec.util.Util.isNumeric;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.lonsec.domain.ReturnSeries;
import com.lonsec.service.parser.CSVFileParser;

/**
 * @author Aravind
 *
 */
@Service("RETURNSERIES")
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FundSeriesParser extends AbstractParser implements CSVFileParser {

	@Override
	public boolean process(List<CSVRecord> records) throws Exception {

		List<ReturnSeries> returns = new ArrayList<ReturnSeries>();

		records.stream().forEach(record -> {
			ReturnSeries returnSeries = new ReturnSeries();

			String code = record.get(0);
			String strDate = record.get(1);
			String strPercent = record.get(2);

			boolean isValid = true;

			if (!StringUtils.isNotBlank(code)) {
				returnSeries.setCode(code);
			} else {
				isValid = false;
			}

			Date date = getDate(strDate);

			if (date != null) {
				returnSeries.setDate(date);
			} else {
				isValid = false;
			}

			if (isNumeric(strPercent)) {
				returnSeries.setReturnPercent(new BigDecimal(strPercent));
			} else {
				isValid = false;
			}

			if (isValid) {
				returns.add(returnSeries);
			}
		});
		
		if (returns.size() ==  0) {
			return false;
		}
		
		int insertCount = fundDao.insertReturnSeries(returns);
		
		return insertCount == returns.size();
	}

}
