package com.lonsec.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Domain Model for FundPerformance Data
 * 
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

	public FundPerformance() {

	}

	public FundPerformance(String fundName, Date date, BigDecimal fundReturn, BigDecimal benchmarkReturn) {
		super();
		this.fundName = fundName;
		this.date = date;
		this.fundReturn = fundReturn;
		this.benchmarkReturn = benchmarkReturn;
	}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((fundName == null) ? 0 : fundName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FundPerformance other = (FundPerformance) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (fundName == null) {
			if (other.fundName != null)
				return false;
		} else if (!fundName.equals(other.fundName))
			return false;
		return true;
	}
}
