package com.lonsec.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lonsec.dao.FundDao;
import com.lonsec.domain.Benchmark;
import com.lonsec.domain.Fund;
import com.lonsec.domain.ReturnSeries;

@Repository
public class FundDaoImpl implements FundDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void insertFunds(List<Fund> funds) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insertBenchmarks(List<Benchmark> benchmarks) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insertReturnSeries(List<ReturnSeries> returnSeries) {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional(readOnly = true)
	public void loadMonthlyData() {
		// TODO Auto-generated method stub

	}

}
