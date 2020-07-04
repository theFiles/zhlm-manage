package com.rogrand.core.enums;

import org.apache.commons.lang.StringUtils;


public enum SendState
{
  已提交("1"),
  已发货("2"),
  已完成("3");
  private String value;

  SendState(String value) {
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
    SendState[] values = values();
    for (int i = 0; i < values.length; i++) {
      SendState enumobj = values[i];
      if (value.equals(enumobj.getValue())) {
        return enumobj.name();
      }
    }
    return null;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\enums\SendState.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */