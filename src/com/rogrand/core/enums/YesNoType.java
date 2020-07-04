package com.rogrand.core.enums;







public enum YesNoType
  implements BaseEnum<Integer>
{
  YES(Integer.valueOf(1), "是"), NO(Integer.valueOf(0), "否");

  private String desc;
  private Integer code;

  YesNoType(Integer code, String desc) {
    this.code = code;
    this.desc = desc;
  }

  public Integer getCode() {
    return this.code;
  }

  public String getDesc() {
    return this.desc;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\enums\YesNoType.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */