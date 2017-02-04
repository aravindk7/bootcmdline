package com.sample.service;

import java.util.List;

import com.sample.domain.FundPerformance;

/**
 * Calculate the Monthly Returns
 * 
 * @author Aravind
 *
 */
public interface FundsReportService {

	/**
	 * Compute the Domain model for Reports
	 * 
	 * @return list of report records in order
	 */
	public List<FundPerformance> computeReturns();

}
