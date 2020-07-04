package com.rogrand.core.enums;







public enum UploadType
{
  TEMP(0, "temp", "临时文件"),
  USER_PIC(1, "userpic", "用户头像"),
  IMAGE(2, "images", "图片"),
  AUDIO(3, "audio", "音频");

  private int code;
  private String name;
  private String desc;

  UploadType(int code, String name, String desc) {
    this.code = code;
    this.name = name;
    this.desc = desc;
  }

  public int getCode() {
    return this.code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDesc() {
    return this.desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\enums\UploadType.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */