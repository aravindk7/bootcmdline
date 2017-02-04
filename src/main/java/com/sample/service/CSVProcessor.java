package com.sample.service;

import java.io.File;
import java.util.List;

import com.sample.domain.FundPerformance;

/**
 * @author Aravind
 *
 */
public interface CSVProcessor {

	/**
	 * @param file
	 * @throws Exception
	 */
	public void pareCSV(File file) throws Exception;

	/**
	 * @param funds
	 */
	public void writeCSV(List<FundPerformance> funds);

}
