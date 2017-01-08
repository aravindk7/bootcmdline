package com.lonsec.service.parser.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.lonsec.dao.FundDao;

public abstract class AbstractParser {
	@Autowired
	protected FundDao fundDao;
}
