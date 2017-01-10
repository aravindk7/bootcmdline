package com.lonsec.service;

import java.util.List;

import com.lonsec.domain.FundPerformance;

/**
 * Calculate the Monthly Returns
 * 
 * @author Aravind
 *
 */
public interface FundsReturnService {

	/**
	 * Compute the Domain model for Reports
	 * 
	 * @return list of report records in order
	 */
	public List<FundPerformance> computeReturns();

	/**
	 * Enrich the data for Report and populate columns based on business rules
	 * 
	 * @param funds
	 * @return
	 */
	public List<FundPerformance> enrichForReport(List<FundPerformance> funds);
}
