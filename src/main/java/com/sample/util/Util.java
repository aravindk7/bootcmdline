package com.sample.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.function.Predicate;

import org.apache.commons.lang3.StringUtils;

/**
 * Utility Class for Generic Methods
 * 
 * @author Aravind
 *
 */
public class Util {

	private Util() {

	}

	private static String FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
	private static String FORMAT_DD_MM_YYYY = "dd/MM/yyyy";

	/**
	 *  Predicate to check the String value is not blank
	 */
	public static Predicate<Object> nonNull = Objects::nonNull;

	/**
	 * Predicate to check the String value is not blank
	 */
	public static Predicate<String> stringNotBlank = StringUtils::isNotBlank;

	/**
	 * Check if the String is Numeric
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isNumeric(String s) {
		if (StringUtils.isBlank(s)) {
			return false;
		}
		return s.matches("[-+]?\\d*\\.?\\d+");
	}

	/**
	 * Convert String to Date if the pattern is valid
	 * 
	 * @param s
	 * @return
	 */
	public static Date getDate(String s) {
		String pattern = getDateFormat(s);
		if (pattern == null) {
			return null;
		}

		try {
			return new SimpleDateFormat(pattern).parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Convert String to Date if the pattern is valid
	 * 
	 * @param s
	 * @return
	 */
	public static String toDateString(Date d) {

		return new SimpleDateFormat(FORMAT_DD_MM_YYYY).format(d);

	}

	/**
	 * Get the date pattern of the string
	 * 
	 * @param s
	 * @return
	 */
	private static String getDateFormat(String s) {
		if (s.matches("\\d{4}-\\d{2}-\\d{2}")) {
			return FORMAT_YYYY_MM_DD;
		}

		if (s.matches("\\d{2}/\\d{2}/\\d{4}")) {
			return FORMAT_DD_MM_YYYY;
		}

		return null;
	}
}
