package com.rogrand.core.enums;






public enum Gender
  implements BaseEnum<String>
{
  MALE("B", "男"), FEMALE("A", "女");

  private String desc;
  private String code;

  Gender(String code, String desc) {
    this.code = code;
    this.desc = desc;
  }


  public String getCode() {
    return this.code;
  }


  public String getDesc() {
    return this.desc;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\enums\Gender.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */