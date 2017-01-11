package com.lonsec.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lonsec.dao.FundDao;
import com.lonsec.domain.Benchmark;
import com.lonsec.domain.Fund;
import com.lonsec.domain.FundPerformance;
import com.lonsec.domain.ReturnSeries;

@Repository
@Transactional
public class FundDaoImpl implements FundDao {

	private final static String INSERT_FUND = " INSERT INTO FUND "
			+ "(FundCode, FundName, BenchMarkCode) VALUES (?, ?, ?) ";

	private final static String INSERT_BENCHMARK = " INSERT INTO BENCHMARK "
			+ "(BenchMarkCode, BenchmarkName) VALUES (?, ?) ";

	private final static String INSERT_RETURNSERIES = " INSERT INTO RETURNSERIES "
			+ "(code, Date, ReturnPerc) VALUES (?, ?, ?) ";

	private final static String SELECT_FUNDS_DATA = "SELECT  F.FUNDCODE AS FUNDCODE, F.FUNDNAME AS FUNDNAME, FR.DATE AS DATE, "
			+ " FR.RETURNPERC AS FUNDRETURN, BR.RETURNPERC AS BENCHMARKRETURN " + " FROM RETURNSERIES FR JOIN FUND F "
			+ " ON F.FUNDCODE = FR.CODE " + " LEFT OUTER JOIN RETURNSERIES BR " + " ON F.BENCHMARKCODE = BR.CODE "
			+ " AND FR.DATE = BR.DATE " + " ORDER BY FR.DATE DESC, FR.RETURNPERC  DESC ";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lonsec.dao.FundDao#insertFunds(java.util.List)
	 */
	@Override
	public int insertFunds(List<Fund> funds) {
		int[] updatedRecords = jdbcTemplate.batchUpdate(INSERT_FUND, new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Fund fund = funds.get(i);
				ps.setString(1, fund.getFundCode());
				ps.setString(2, fund.getFundName());
				ps.setString(3, fund.getBenchmarkCode());
			}

			@Override
			public int getBatchSize() {
				return funds.size();
			}

		});

		return getSuccessfulInsertCount(updatedRecords);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lonsec.dao.FundDao#insertBenchmarks(java.util.List)
	 */
	@Override
	public int insertBenchmarks(List<Benchmark> benchmarks) {
		int[] updatedRecords = jdbcTemplate.batchUpdate(INSERT_BENCHMARK, new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Benchmark benchmark = benchmarks.get(i);
				ps.setString(1, benchmark.getBenchmarkCode());
				ps.setString(2, benchmark.getBenchmarkName());
			}

			@Override
			public int getBatchSize() {
				return benchmarks.size();
			}

		});

		return getSuccessfulInsertCount(updatedRecords);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lonsec.dao.FundDao#insertReturnSeries(java.util.List)
	 */
	@Override
	public int insertReturnSeries(List<ReturnSeries> returns) {
		int[] updatedRecords = jdbcTemplate.batchUpdate(INSERT_RETURNSERIES, new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ReturnSeries returnSeries = returns.get(i);
				ps.setString(1, returnSeries.getCode());
				ps.setDate(2, new java.sql.Date(returnSeries.getDate().getTime()));
				ps.setDouble(3, returnSeries.getReturnPercent().doubleValue());
			}

			@Override
			public int getBatchSize() {
				return returns.size();
			}

		});

		return getSuccessfulInsertCount(updatedRecords);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lonsec.dao.FundDao#loadFundPerformanceData()
	 */
	@Override
	@Transactional(readOnly = true)
	public List<FundPerformance> loadFundPerformanceData() {
		List<FundPerformance> funds = jdbcTemplate.query(SELECT_FUNDS_DATA, new RowMapper<FundPerformance>() {

			@Override
			public FundPerformance mapRow(ResultSet rs, int rowNum) throws SQLException {
				FundPerformance FundPerformance = new FundPerformance();
				FundPerformance.setFundCode(rs.getString("FUNDCODE"));
				FundPerformance.setFundName(rs.getString("FUNDNAME"));
				FundPerformance.setDate(new Date(rs.getDate("DATE").getTime()));
				FundPerformance.setFundReturn(rs.getBigDecimal("FUNDRETURN"));
				FundPerformance.setBenchmarkReturn(rs.getBigDecimal("BENCHMARKRETURN"));
				return FundPerformance;
			}

		});

		return funds;

	}

	/**
	 * Get the count of successful inserts
	 * 
	 * @param updatedRecords
	 * @return
	 */
	private int getSuccessfulInsertCount(int[] updatedRecords) {
		int totalCount = 0;
		for (int i : updatedRecords) {
			if (i == 0) {
				System.out.println("Record Not Updated");
			} else {
				totalCount++;
			}
		}

		return totalCount;
	}
}
