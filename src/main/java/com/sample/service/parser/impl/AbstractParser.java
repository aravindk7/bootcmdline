package com.sample.service.parser.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.sample.dao.FundDao;

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
