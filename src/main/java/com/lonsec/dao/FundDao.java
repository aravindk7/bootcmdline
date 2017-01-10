package com.lonsec.dao;

import java.util.List;

import com.lonsec.domain.Benchmark;
import com.lonsec.domain.Fund;
import com.lonsec.domain.FundPerformance;
import com.lonsec.domain.ReturnSeries;

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
