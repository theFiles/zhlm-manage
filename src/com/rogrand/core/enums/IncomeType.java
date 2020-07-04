package com.rogrand.core.enums;

import org.apache.commons.lang.StringUtils;


public enum IncomeType
{
  激活pos机("1"),
  刷卡("2"),
  提现("3");
  private String value;

  IncomeType(String value) {
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
    IncomeType[] values = values();
    for (int i = 0; i < values.length; i++) {
      IncomeType enumobj = values[i];
      if (value.equals(enumobj.getValue())) {
        return enumobj.name();
      }
    }
    return null;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\enums\IncomeType.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */