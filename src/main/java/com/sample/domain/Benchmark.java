package com.sample.domain;

/**
 * Domain Model for Benchmark Data
 * @author Aravind
 *
 */
public class Benchmark extends Domain {
	private String benchmarkCode;

	private String benchmarkName;

	public Benchmark() {

	}

	public Benchmark(String benchmarkCode, String benchmarkName) {
		super();
		this.benchmarkCode = benchmarkCode;
		this.benchmarkName = benchmarkName;
	}

	public String getBenchmarkCode() {
		return benchmarkCode;
	}

	public void setBenchmarkCode(String benchmarkCode) {
		this.benchmarkCode = benchmarkCode;
	}

	public String getBenchmarkName() {
		return benchmarkName;
	}

	public void setBenchmarkName(String benchmarkName) {
		this.benchmarkName = benchmarkName;
	}
}
