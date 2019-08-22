package com.RFCore.test.utils.financial;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Test;

import com.RFCore.utils.financial.UtilsFinancial;

/**
 * 
 * @author diego
 *
 */
public class VANTest {

	@Test
	public void test() {
		BigDecimal result = UtilsFinancial.calculateVAN(new BigDecimal("70"), new BigDecimal[] {new BigDecimal("15"), new BigDecimal("60")}, new BigDecimal("0.05"), 2, RoundingMode.HALF_UP);
		assertTrue(result.doubleValue() == -1.29);
	}
}
