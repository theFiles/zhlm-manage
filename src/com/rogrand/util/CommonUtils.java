package com.rogrand.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtils
{
  public static String getCron(Date date) {
    SimpleDateFormat sdf = new SimpleDateFormat("ss mm HH dd MM ? yyyy");
    return sdf.format(date);
  }






  public static String creatOrderNum() {
    SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    String currTime = outFormat.format(new Date());

    String strRandom = RandomUtil.generateNumber(4);
    String out_trade_no = currTime + strRandom;
    return out_trade_no;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com\rogran\\util\CommonUtils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */