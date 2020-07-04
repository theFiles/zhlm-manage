package com.rogrand.core.enums;

import org.apache.commons.lang.StringUtils;


public enum OrderState
{
  待付款("1"),
  待发货("2"),
  待收货("3"),
  已完成("4"),
  已失效("5"),
  已取消("6"),
  申请退款("7"),
  已退款("8"),
  拒绝退款("9");
  private String value;

  OrderState(String value) {
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
    OrderState[] values = values();
    for (int i = 0; i < values.length; i++) {
      OrderState enumobj = values[i];
      if (value.equals(enumobj.getValue())) {
        return enumobj.name();
      }
    }
    return null;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\enums\OrderState.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */