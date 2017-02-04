package com.sample.dao;

import java.util.List;

import com.sample.domain.Benchmark;
import com.sample.domain.Fund;
import com.sample.domain.FundPerformance;
import com.sample.domain.ReturnSeries;

/**
 * @author Aravind
 *
 */
public interface FundDao {

	/**
	 * Insert the list of Funds
	 * 
	 * @param funds
	 *            list of funds
	 * @return count of successful inserts
	 */
	public int insertFunds(List<Fund> funds);

	/**
	 * Insert the list of Benchmarks
	 * 
	 * @param benchmarks
	 *            list of benchmarks
	 * @return count of successful inserts
	 */
	public int insertBenchmarks(List<Benchmark> benchmarks);

	/**
	 * Insert the list of ReturnSeries
	 * 
	 * @param returns
	 *            list of returns
	 * @return count of successful inserts
	 */
	public int insertReturnSeries(List<ReturnSeries> returns);

	/**
	 * Load the list of Fund Data to Generate Monthly Report
	 * @return list of FundPerformance
	 */
	public List<FundPerformance> loadFundPerformanceData();
}
