package com.lonsec.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

/**
 * Utility Class for generic methods 
 * @author Aravind
 *
 */
public class Util {
	
	private Util() {
		
	}
	
	private static String FORMAT_YYYY_MM_DD = "YYYY-MM-DD";
	private static String FORMAT_DD_MM_YYYY="DD/MM/YYYY";
	
	/**
	 * Check if the String is Numeric
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
	 * Get the date pattern of the string
	 * @param s
	 * @return
	 */
	private static String getDateFormat(String s) {
		if(s.matches("\\d{4}-\\d{2}-\\d{2}")) {
			return FORMAT_YYYY_MM_DD;
		}
		
		if (s.matches("\\d{2}/\\d{2}/\\d{4}")) {
			return FORMAT_DD_MM_YYYY;
		}
		
		return null;
	}
}
