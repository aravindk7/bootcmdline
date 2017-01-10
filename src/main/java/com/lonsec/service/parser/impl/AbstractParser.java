package com.lonsec.service.parser.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.lonsec.dao.FundDao;

/**
 * Base class to inject common beans
 * 
 * @author Aravind
 *
 */
public abstract class AbstractParser {

	@Autowired
	protected FundDao fundDao;
}
