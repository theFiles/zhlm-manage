package com.rogrand.core.enums;

import org.apache.commons.lang.StringUtils;


public enum UserGradeType
{
  V1("1"),
  V2("2"),
  V3("3"),
  V4("4"),
  V5("5"),
  V6("6"),
  V7("7"),
  V8("8"),
  V9("9");
  private String value;

  UserGradeType(String value) {
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
    UserGradeType[] values = values();
    for (int i = 0; i < values.length; i++) {
      UserGradeType enumobj = values[i];
      if (value.equals(enumobj.getValue())) {
        return enumobj.name();
      }
    }
    return null;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\enums\UserGradeType.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */