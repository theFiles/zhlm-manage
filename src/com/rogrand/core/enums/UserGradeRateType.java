package com.rogrand.core.enums;

import org.apache.commons.lang.StringUtils;


public enum UserGradeRateType
{
  V1("0.0008"),
  V2("0.00085"),
  V3("0.0009"),
  V4("0.00095"),
  V5("0.0010"),
  V6("0.00105"),
  V7("0.0011"),
  V8("0.00115"),
  V9("0.0012");
  private String value;

  UserGradeRateType(String value) {
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
    UserGradeRateType[] values = values();
    for (int i = 0; i < values.length; i++) {
      UserGradeRateType enumobj = values[i];
      if (value.equals(enumobj.getValue())) {
        return enumobj.name();
      }
    }
    return null;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\enums\UserGradeRateType.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */