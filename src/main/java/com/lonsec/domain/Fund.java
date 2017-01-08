package com.lonsec.domain;

/**
 * @author Aravind
 *
 */
public class Fund {

	private String fundCode;

	private String fundName;

	private String benchmarkCode;

	public String getFundCode() {
		return fundCode;
	}

	public void setFundCode(String fundCode) {
		this.fundCode = fundCode;
	}

	public String getFundName() {
		return fundName;
	}

	public void setFundName(String fundName) {
		this.fundName = fundName;
	}

	public String getBenchmarkCode() {
		return benchmarkCode;
	}

	public void setBenchmarkCode(String benchmarkCode) {
		this.benchmarkCode = benchmarkCode;
	}
}
