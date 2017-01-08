package com.lonsec.dao;

import java.util.List;

import com.lonsec.domain.Benchmark;
import com.lonsec.domain.Fund;
import com.lonsec.domain.ReturnSeries;

/**
 * @author Aravind
 *
 */
public interface FundDao {
	
	/**
	 * @param funds
	 */
	public void insertFunds(List<Fund> funds);
	
	/**
	 * @param benchmarks
	 */
	public void insertBenchmarks(List<Benchmark> benchmarks);
	
	/**
	 * @param returnSeries
	 */
	public void insertReturnSeries(List<ReturnSeries> returnSeries);
	
	/**
	 * 
	 */
	public void loadMonthlyData();
}
