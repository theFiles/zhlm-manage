package com.rogrand.tuozhanbao.enums;

import org.apache.commons.lang.StringUtils;


public enum UserGrade
{
  V1("V1"),
  V2("V2"),
  V3("V3"),
  V4("V4"),
  V5("V5"),
  V6("V6"),
  V7("V7"),
  V8("V8"),
  V9("V9");
  private String value;

  UserGrade(String value) {
    this.value = value;
  }



  public String getValue() {
    return this.value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public static String getEnumName(String value) {
    if (StringUtils.isEmpty(value)) return null;
    UserGrade[] values = values();
    for (int i = 0; i < values.length; i++) {
      UserGrade enumobj = values[i];
      if (value.equals(enumobj.getValue())) {
        return enumobj.name();
      }
    }
    return null;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\tuozhanbao\enums\UserGrade.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */