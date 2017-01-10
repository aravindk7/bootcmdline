package com.lonsec.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Domain Model for ReturnSeries Data
 * 
 * @author Aravind
 *
 */
public class ReturnSeries extends Domain {

	private String code;

	private Date date;

	private BigDecimal returnPercent;

	public ReturnSeries() {

	}

	public ReturnSeries(String code, Date date, BigDecimal returnPercent) {
		super();
		this.code = code;
		this.date = date;
		this.returnPercent = returnPercent;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public BigDecimal getReturnPercent() {
		return returnPercent;
	}

	public void setReturnPercent(BigDecimal returnPercent) {
		this.returnPercent = returnPercent;
	}
}
