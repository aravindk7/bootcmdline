package com.lonsec.service;

import java.util.List;

import com.lonsec.domain.FundPerformance;

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
