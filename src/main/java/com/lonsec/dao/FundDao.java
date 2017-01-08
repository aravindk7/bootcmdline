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
	 * @param funds
	 */
	public int insertFunds(List<Fund> funds);
	
	/**
	 * @param benchmarks
	 */
	public int insertBenchmarks(List<Benchmark> benchmarks);
	
	/**
	 * @param returnSeries
	 */
	public int insertReturnSeries(List<ReturnSeries> returns);
	
	/**
	 * 
	 */
	public List<FundPerformance> loadFundPerformanceData();
}
