package com.rogrand.core.enums;

import org.apache.commons.lang.StringUtils;


public enum TradeState
{
  成功("1"),
  失败("2"),
  初始化("3");
  private String value;

  TradeState(String value) {
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
    TradeState[] values = values();
    for (int i = 0; i < values.length; i++) {
      TradeState enumobj = values[i];
      if (value.equals(enumobj.getValue())) {
        return enumobj.name();
      }
    }
    return null;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\enums\TradeState.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */