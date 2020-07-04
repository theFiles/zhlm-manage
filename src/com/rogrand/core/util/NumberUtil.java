package com.rogrand.core.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;


public class NumberUtil
{
  private static final int DEF_DIV_SCALE = 10;

  public static int parseInt(Object intStr, int defValue) {
    int v = defValue;
    try {
      if (intStr == null) {
        return v;
      }
      v = Integer.parseInt(intStr.toString());
    } catch (Exception e) {}

    return v;
  }







  public static float parseFloat(Object floatStr, long defValue) {
    float v = (float)defValue;
    try {
      if (floatStr == null) {
        return v;
      }
      v = Float.parseFloat(floatStr.toString());
    } catch (Exception e) {}

    return v;
  }







  public static long parseLong(Object longStr, long defValue) {
    long v = defValue;
    try {
      if (longStr == null) {
        return v;
      }
      v = Long.parseLong(longStr.toString());
    } catch (Exception e) {}

    return v;
  }






  public static boolean isFloat(String input) {
    if (StringUtil.isEmpty(input)) {
      return false;
    }
    try {
      Float.parseFloat(input);
      return true;
    } catch (Exception ex) {
      return false;
    }
  }






  public static boolean isDouble(String input) {
    if (StringUtil.isEmpty(input)) {
      return false;
    }

    try {
      Double.parseDouble(input);
      return true;
    } catch (Exception ex) {
      return false;
    }
  }






  public static boolean isDigital(String input) {
    if (StringUtil.isEmpty(input)) {
      return false;
    }
    for (int i = 0; i < input.length(); i++) {
      if (!Character.isDigit(input.charAt(i))) {
        return false;
      }
    }
    return true;
  }






  public static boolean isInt(String input) {
    if (StringUtil.isEmpty(input)) {
      return false;
    }
    try {
      Integer.parseInt(input);
      return true;
    } catch (NumberFormatException ex) {
      return false;
    }
  }







  public static String appendZero(int num, int length) {
    String pattern = "";
    for (int i = 0; i < length; i++) {
      pattern = pattern + "0";
    }
    DecimalFormat df = new DecimalFormat(pattern);
    return df.format(num);
  }







  public static String formatNumber(Number num, String pattern) {
    DecimalFormat df = new DecimalFormat(pattern);
    return df.format(num);
  }







  public static String formatPercent(double num, int digit) {
    NumberFormat format = NumberFormat.getPercentInstance();
    format.setMaximumFractionDigits(digit);
    return format.format(num);
  }






  public static boolean isOdd(int input) {
    return (input % 2 != 0);
  }






  public static boolean isEven(int input) {
    return (input % 2 == 0);
  }







  public static double round(double d, int scale) {
    if (scale < 0) {
      return Math.round(d);
    }
    BigDecimal b = new BigDecimal(Double.toString(d));
    BigDecimal one = new BigDecimal("1");
    double ret = b.divide(one, scale, 4).doubleValue();

    return ret;
  }







  public static BigDecimal round(BigDecimal bd, int size) {
    BigDecimal one = new BigDecimal("1");
    return bd.divide(one, size, 4);
  }









  public static String formatMoneyCN(double lower) {
    if (lower == 0.0D) {
      return "零";
    }
    if (Math.abs(lower) > 1.0E12D) {
      return "金额太大！";
    }
    String money = "";
    boolean isNegative = false;
    if (lower < 0.0D) {
      lower = Math.abs(lower);
      isNegative = true;
    }
    String num = "零壹贰叁肆伍陆柒捌玖";
    String[] unit = { "元", "拾", "佰", "仟", "万", "拾万", "佰万", "仟万", "亿", "拾亿", "佰亿", "仟亿" };

    String s = String.valueOf(lower);
    int a = s.indexOf("+");
    int e = s.indexOf("E");

    if (e != -1) {
      int index = 0;
      if (a == -1) {
        index = Integer.parseInt(s.substring(e + 1));
      } else {
        index = Integer.parseInt(s.substring(a + 1));
      }
      String sub1 = s.substring(0, e);
      int dot = sub1.indexOf(".");

      if (dot == -1) {
        for (int m = 1; m <= index; m++) {
          s = sub1 + "0";
        }
      } else {
        String sub11 = sub1.substring(0, dot);
        String sub12 = sub1.substring(dot + 1);
        if (index >= sub12.length()) {
          int m = index - sub12.length();
          for (int n = 1; n <= m; n++) {
            sub12 = sub12 + "0";
          }
        } else {
          sub12 = sub12.substring(0, index) + "." + sub12.substring(index);
        }

        s = sub11 + sub12;
      }
    }
    int sdot = s.indexOf(".");
    String beforeDot = "";
    String afterDot = "";

    if (sdot != -1) {
      beforeDot = s.substring(0, sdot);
      afterDot = s.substring(sdot + 1);
    } else {
      beforeDot = s;
    }
    int bl = beforeDot.length();
    boolean zero = false;
    int z = 0;

    for (int j = 0, k = bl - 1; j <= bl - 1; j++, k--) {
      int number = Integer.parseInt(String.valueOf(beforeDot.charAt(j)));
      if (number == 0) {
        zero = true;
        z++;
      } else {
        zero = false;
        z = 0;
      }
      if (zero && z == 1) {
        money = money + "零";
      } else if (!zero &&
        !zero) {
        money = money + num.substring(number, number + 1) + unit[k];
      }
    }

    for (int i = 1; i <= 2; i++) {
      String ss = "";
      if (i == 1) {
        ss = "万";
      } else {
        ss = "亿";
      }
      int last = money.lastIndexOf(ss);
      if (last != -1) {
        String moneySub1 = money.substring(0, last);
        String moneySub2 = money.substring(last, money.length());
        int last2 = moneySub1.indexOf(ss);
        while (last2 != -1) {
          moneySub1 = moneySub1.substring(0, last2) + moneySub1.substring(last2 + 1, moneySub1.length());


          last2 = moneySub1.indexOf(ss);
        }
        money = moneySub1 + moneySub2;
      }
    }

    int yuan = money.indexOf("元");

    if (yuan == -1) {
      int zi = money.lastIndexOf("零");

      if (zi == money.length() - 1) {
        money = money.substring(0, money.length() - 1) + "元";
      }
    }

    if (!afterDot.equals("")) {
      int al = afterDot.length();
      if (al > 2) {
        afterDot = afterDot.substring(0, 2);
        al = afterDot.length();
      }

      if (!afterDot.equals("0") && !afterDot.equals("00"))
      {
        for (int m = 0; m < al; m++) {
          int number = Integer.parseInt(String.valueOf(afterDot.charAt(m)));

          if (number != 0 && m == 0) {
            money = money + num.substring(number, number + 1) + "角";
          } else if (number != 0 && m == 1) {
            money = money + num.substring(number, number + 1) + "分";
          } else if (number == 0 && m == 0) {
            money = money + "零";
          }
        }
      }
    }

    if (money.indexOf("角") == -1 && money.indexOf("分") == -1) {
      money = money + "整";
    }


    if (Math.abs(lower) < 1.0D) {
      if (money.startsWith("元")) {
        money = money.substring(1);
      }
      if (money.startsWith("零")) {
        money = money.substring(1);
      }
    }

    if (isNegative) {
      money = "负" + money;
    }
    return money;
  }







  public static float add(float v1, float v2) {
    BigDecimal b1 = new BigDecimal(Float.toString(v1));
    BigDecimal b2 = new BigDecimal(Float.toString(v2));
    return b1.add(b2).floatValue();
  }







  public static float sub(float v1, float v2) {
    BigDecimal b1 = new BigDecimal(Float.toString(v1));
    BigDecimal b2 = new BigDecimal(Float.toString(v2));
    return b1.subtract(b2).floatValue();
  }







  public static float mul(float v1, float v2) {
    BigDecimal b1 = new BigDecimal(Float.toString(v1));
    BigDecimal b2 = new BigDecimal(Float.toString(v2));
    return b1.multiply(b2).floatValue();
  }







  public static float mul(float v1, float v2, float v3) {
    BigDecimal b1 = new BigDecimal(Float.toString(v1));
    BigDecimal b2 = new BigDecimal(Float.toString(v2));
    BigDecimal b3 = new BigDecimal(Float.toString(v3));
    return b1.multiply(b2).multiply(b3).floatValue();
  }







  public static float div(float v1, float v2) {
    return div(v1, v2, 10);
  }








  public static float div(float v1, float v2, int scale) {
    if (scale < 0) {
      scale = 0;
    }
    BigDecimal b1 = new BigDecimal(Float.toString(v1));
    BigDecimal b2 = new BigDecimal(Float.toString(v2));
    return b1.divide(b2, scale, 4).floatValue();
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\cor\\util\NumberUtil.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */