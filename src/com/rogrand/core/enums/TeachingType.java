package com.rogrand.core.enums;

import org.apache.commons.lang.StringUtils;


public enum TeachingType
{
  视频("1"),
  文章("2");
  private String value;

  TeachingType(String value) {
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
    TeachingType[] values = values();
    for (int i = 0; i < values.length; i++) {
      TeachingType enumobj = values[i];
      if (value.equals(enumobj.getValue())) {
        return enumobj.name();
      }
    }
    return null;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\enums\TeachingType.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */