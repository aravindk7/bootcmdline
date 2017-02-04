package com.sample.util;

import static com.sample.util.Util.nonNull;
import static com.sample.util.Util.stringNotBlank;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;

import com.sample.util.Util;

public class UtilTest {

	@Test
	public void isNumericTest() {
		assertTrue(Util.isNumeric("8.067664911"));
		assertTrue(Util.isNumeric("-0.457860098"));
		assertFalse(Util.isNumeric("tes"));
	}

	@Test
	public void toDateStringTest() {
		
	}

	@Test
	public void objectNotNullPredicateTest() {
		assertFalse(nonNull.test(null));
		assertTrue(nonNull.test(new BigDecimal(0)));
	}

	@Test
	public void stringNotBlankPredicateTest() {
		assertFalse(stringNotBlank.test(null));
		assertFalse(stringNotBlank.test(""));
		assertTrue(stringNotBlank.test("    test      "));
		assertTrue(stringNotBlank.test("test"));
	}

}
