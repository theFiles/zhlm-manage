package com.rogrand.core.enums;







public enum AgeEnum
  implements BaseEnum<String>
{
  AGE0("0", "16岁以下"),
  AGE1("1", "16-24岁"),
  AGE2("2", "25-30岁"),
  AGE3("3", "31-40岁"),
  AGE4("4", "41-50岁"),
  AGE5("5", "51-60岁"),
  AGE6("6", "61-70岁"),
  AGE7("7", "71-80岁");

  private String code;
  private String desc;

  AgeEnum(String code, String desc) {
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


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\enums\AgeEnum.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */