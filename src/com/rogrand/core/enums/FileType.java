package com.rogrand.core.enums;

import org.apache.commons.lang.StringUtils;


public enum FileType
{
  图像("HEAD_IMAGE"),
  图片("PICTURE"),
  视频("VIDEO");
  private String value;

  FileType(String value) {
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
    FileType[] values = values();
    for (int i = 0; i < values.length; i++) {
      FileType enumobj = values[i];
      if (value.equals(enumobj.getValue())) {
        return enumobj.name();
      }
    }
    return null;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\enums\FileType.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */