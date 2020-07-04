package com.rogrand.core.enums;

import org.apache.commons.lang.StringUtils;


public enum ConfigType
{
  客服电话("KF"),
  关于我们("US"),
  用户协议("XY"),
  反馈意见字数("ZS"),
  超级邀请码("INVITE_NUM"),
  订单有效时间("ORDER_TIME"),
  退款协议("REFUND"),
  拓展宝URL("TZB_URL");
  private String value;

  ConfigType(String value) {
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
    ConfigType[] values = values();
    for (int i = 0; i < values.length; i++) {
      ConfigType enumobj = values[i];
      if (value.equals(enumobj.getValue())) {
        return enumobj.name();
      }
    }
    return null;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\enums\ConfigType.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */