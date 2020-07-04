package com.rogrand.core.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.DateFormatSymbols;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class DateFormat
  extends SimpleDateFormat
{
  private Log logger = LogFactory.getLog(getClass());


  public DateFormat() {}

  public DateFormat(String pattern) {
    super(pattern);
  }

  public DateFormat(String pattern, Locale locale) {
    super(pattern, locale);
  }

  public DateFormat(String pattern, DateFormatSymbols formatSymbols) {
    super(pattern, formatSymbols);
  }



  public Date parse(String source) {
    if (source == null || source.length() < 8) return null;
    String dateTime = source.replaceAll("/", "-");

    if (dateTime.indexOf('-') == -1) {
      try {
        long time = Long.parseLong(dateTime);
        return new Date(time);
      }
      catch (NumberFormatException e) {
        this.logger.error(e.getMessage());
        return null;
      }
    }

    String pattern = "yyyy-MM-dd";
    int count = (dateTime.split(":")).length;
    if (count == 2) { pattern = pattern + " HH:mm"; }
    else if (count == 3) { pattern = pattern + " HH:mm:ss"; }
     applyPattern(pattern);
    try {
      return super.parse(dateTime);
    }
    catch (ParseException e) {
      this.logger.error(e.getMessage());
      return null;
    }
  }



  public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition pos) {
    StringBuffer stringBuffer = super.format(date, toAppendTo, pos);
    String str = stringBuffer.toString();
    if (str.endsWith(" 00:00:00") || str.endsWith(" 00:00") || str.endsWith(" 00")) {
      return new StringBuffer(str.substring(0, 10));
    }

    return new StringBuffer(str);
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\cor\\util\DateFormat.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */