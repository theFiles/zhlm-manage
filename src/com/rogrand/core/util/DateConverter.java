package com.rogrand.core.util;

import org.apache.commons.beanutils.converters.DateTimeConverter;

import java.util.Date;


class DateConverter
  extends DateTimeConverter
{
  DateConverter() {}

  DateConverter(Object o) {
    super(o);
  }


  protected Object convertToType(Class aClass, Object o) {
    try {
      return super.convertToType(aClass, o);
    } catch (Exception e) {
      return null;
    }
  }


  protected Class getDefaultType() {
    return Date.class;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\cor\\util\DateConverter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */