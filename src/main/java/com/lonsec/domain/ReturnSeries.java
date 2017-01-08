package com.lonsec.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Aravind
 *
 */
public class ReturnSeries {
	
	private String code;
	
	private Date date;
	
	private BigDecimal returnPercent;

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
