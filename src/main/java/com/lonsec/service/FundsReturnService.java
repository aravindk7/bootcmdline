package com.lonsec.service;

import java.util.List;

import com.lonsec.domain.FundPerformance;

/**
 * @author Aravind
 *
 */
public interface FundsReturnService {
	
	public List<FundPerformance> computeReturns();
}
