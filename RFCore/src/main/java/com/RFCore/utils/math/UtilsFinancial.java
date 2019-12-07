package com.RFCore.utils.math;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * 
 * @author diego
 *
 */
public class UtilsFinancial {

	/**
	 * Method to calculate net cash value (Valor actual neto)
	 * If the return value is less than or equal to zero, the investment is inadvisable.
	 * 
	 * @param initialOutlay
	 * @param netFlows
	 * @param interests
	 * @param scale
	 * @param roundingMode
	 * @return
	 */
	public static BigDecimal calculateVAN(BigDecimal initialOutlay, BigDecimal[] netFlows, BigDecimal interests,
			int scale, RoundingMode roundingMode) {
		BigDecimal sumNetFlows = BigDecimal.ZERO;

		if (interests == null) {
			interests = BigDecimal.ZERO;
		}

		if (initialOutlay == null) {
			initialOutlay = BigDecimal.ZERO;
		}

		if (netFlows != null) {
			for (int i = 0; i < netFlows.length; i++) {
				sumNetFlows = sumNetFlows
						.add(netFlows[i].divide(BigDecimal.ONE.add(interests).pow(i + 1), MathContext.DECIMAL128)
								.setScale(scale, roundingMode));
			}
		}
		return initialOutlay.negate().add(sumNetFlows);
	}
}
