package com.lonsec.service.parser.impl;

import static com.lonsec.util.Util.stringNotBlank;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVRecord;
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

	private Function<CSVRecord, Benchmark> populateBenchmarkFn = record -> {
		Benchmark benchmark = new Benchmark();
		String benchmarkCode = record.get(0);
		String benchmarkName = record.get(1);

		benchmark.setBenchmarkCode(benchmarkCode);
		benchmark.setBenchmarkName(benchmarkName);

		return benchmark;
	};

	@Override
	public boolean process(List<CSVRecord> records) throws Exception {

		// List<Benchmark> benchmarks = new ArrayList<Benchmark>();

		List<Benchmark> benchmarks = records.stream().filter(r -> r.size() == 2).map(populateBenchmarkFn)
				.filter(b -> stringNotBlank.test(b.getBenchmarkCode()))
				.filter(b -> stringNotBlank.test(b.getBenchmarkName())).collect(Collectors.toList());

		if (benchmarks.size() == 0) {
			return false;
		}

		int insertCount = fundDao.insertBenchmarks(benchmarks);

		return insertCount == benchmarks.size();
	}

}
