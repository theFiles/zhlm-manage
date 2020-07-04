package com.rogrand.core.util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;


public class DoubleUtil
  implements Serializable
{
  private static final long serialVersionUID = -3345205828566485102L;
  private static final Integer DEF_DIV_SCALE = Integer.valueOf(2);








  public static Double add(Double value1, Double value2) {
    BigDecimal b1 = new BigDecimal(Double.toString(value1.doubleValue()));
    BigDecimal b2 = new BigDecimal(Double.toString(value2.doubleValue()));
    return Double.valueOf(b1.add(b2).doubleValue());
  }








  public static double sub(Double value1, Double value2) {
    BigDecimal b1 = new BigDecimal(Double.toString(value1.doubleValue()));
    BigDecimal b2 = new BigDecimal(Double.toString(value2.doubleValue()));
    return b1.subtract(b2).doubleValue();
  }








  public static Double mul(Double value1, Double value2) {
    BigDecimal b1 = new BigDecimal(Double.toString(value1.doubleValue()));
    BigDecimal b2 = new BigDecimal(Double.toString(value2.doubleValue()));
    return Double.valueOf(b1.multiply(b2).doubleValue());
  }








  public static Double divide(Double dividend, Double divisor) {
    return divide(dividend, divisor, DEF_DIV_SCALE);
  }









  public static Double divide(Double dividend, Double divisor, Integer scale) {
    if (scale.intValue() < 0) {
      throw new IllegalArgumentException("The scale must be a positive integer or zero");
    }
    BigDecimal b1 = new BigDecimal(Double.toString(dividend.doubleValue()));
    BigDecimal b2 = new BigDecimal(Double.toString(divisor.doubleValue()));
    return Double.valueOf(b1.divide(b2, scale.intValue(), RoundingMode.HALF_UP).doubleValue());
  }








  public static double round(double value, int scale) {
    if (scale < 0) {
      throw new IllegalArgumentException("The scale must be a positive integer or zero");
    }
    BigDecimal b = new BigDecimal(Double.toString(value));
    BigDecimal one = new BigDecimal("1");
    return b.divide(one, scale, RoundingMode.HALF_UP).doubleValue();
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\cor\\util\DoubleUtil.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */