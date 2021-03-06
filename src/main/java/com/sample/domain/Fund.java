package com.sample.domain;

/**
 * Domain Model for Fund Data
 * 
 * @author Aravind
 *
 */
public class Fund extends Domain {

	private String fundCode;

	private String fundName;

	private String benchmarkCode;

	public Fund() {

	}

	public Fund(String fundCode, String fundName, String benchmarkCode) {
		super();
		this.fundCode = fundCode;
		this.fundName = fundName;
		this.benchmarkCode = benchmarkCode;
	}

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
