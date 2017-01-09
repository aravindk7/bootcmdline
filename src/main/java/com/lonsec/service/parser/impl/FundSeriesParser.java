package com.lonsec.service.parser.impl;

import static com.lonsec.util.Util.getDate;
import static com.lonsec.util.Util.isNumeric;
import static com.lonsec.util.Util.nonNull;
import static com.lonsec.util.Util.stringNotBlank;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVRecord;
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

	private Function<CSVRecord, ReturnSeries> populateReturnSeriesFn = record -> {
		ReturnSeries returnSeries = new ReturnSeries();
		String code = record.get(0);
		String strDate = record.get(1);
		String strPercent = record.get(2);

		returnSeries.setCode(code);
		Date date = getDate(strDate);
		returnSeries.setDate(date);

		if (isNumeric(strPercent)) {
			returnSeries.setReturnPercent(new BigDecimal(strPercent));
		}
		returnSeries.setDate(date);

		return returnSeries;
	};

	@Override
	public boolean process(List<CSVRecord> records) throws Exception {

		// List<ReturnSeries> returns = new ArrayList<ReturnSeries>();

		List<ReturnSeries> returns = records.stream().map(populateReturnSeriesFn)
				.filter(r -> stringNotBlank.test(r.getCode())).filter(r -> nonNull.test(r.getDate()))
				.filter(r -> nonNull.test(r.getReturnPercent())).collect(Collectors.toList());

		if (returns.size() == 0) {
			return false;
		}

		int insertCount = fundDao.insertReturnSeries(returns);

		return insertCount == returns.size();
	}

}
