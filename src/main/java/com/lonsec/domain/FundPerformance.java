package com.lonsec.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Aravind
 *
 */
public class FundPerformance {

	private String fundName;

	private Date date;

	private BigDecimal fundReturn;

	private BigDecimal benchmarkReturn;

	private BigDecimal excess;

	private String outPerformance;

	private Integer rank;

	public String getFundName() {
		return fundName;
	}

	public void setFundName(String fundName) {
		this.fundName = fundName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public BigDecimal getFundReturn() {
		return fundReturn;
	}

	public void setFundReturn(BigDecimal fundReturn) {
		this.fundReturn = fundReturn;
	}

	public BigDecimal getBenchmarkReturn() {
		return benchmarkReturn;
	}

	public void setBenchmarkReturn(BigDecimal benchmarkReturn) {
		this.benchmarkReturn = benchmarkReturn;
	}

	public BigDecimal getExcess() {
		return excess;
	}

	public void setExcess(BigDecimal excess) {
		this.excess = excess;
	}

	public String getOutPerformance() {
		return outPerformance;
	}

	public void setOutPerformance(String outPerformance) {
		this.outPerformance = outPerformance;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	@Override
	public String toString() {
		return "FundPerformance [fundName=" + fundName + ", date=" + date + ", fundReturn=" + fundReturn
				+ ", benchmarkReturn=" + benchmarkReturn + ", excess=" + excess + ", outPerformance=" + outPerformance
				+ ", rank=" + rank + "]";
	}
}
