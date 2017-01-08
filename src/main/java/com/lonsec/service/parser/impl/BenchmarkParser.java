package com.lonsec.service.parser.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.lonsec.domain.Benchmark;
import com.lonsec.service.parser.CSVFileParser;

/**
 * @author Aravind
 *
 */
@Service("BENCHMARK")
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BenchmarkParser extends AbstractParser implements CSVFileParser {

	@Override
	public boolean process(List<CSVRecord> records) throws Exception {

		List<Benchmark> benchmarks = new ArrayList<Benchmark>();

		records.stream().forEach(record -> {
			Benchmark benchmark = new Benchmark();

			String benchmarkCode = record.get(0);
			String benchmarkName = record.get(1);

			boolean isValid = true;

			if (!StringUtils.isNotBlank(benchmarkCode)) {
				benchmark.setBenchmarkCode(benchmarkCode);
			} else {
				isValid = false;
			}

			if (!StringUtils.isNotBlank(benchmarkName)) {
				benchmark.setBenchmarkName(benchmarkName);
			} else {
				isValid = false;
			}

			if (isValid) {
				benchmarks.add(benchmark);
			}
		});

		if (benchmarks.size() == 0) {
			return false;
		}
		
		int insertCount = fundDao.insertBenchmarks(benchmarks);

		return insertCount == benchmarks.size();
	}

}
