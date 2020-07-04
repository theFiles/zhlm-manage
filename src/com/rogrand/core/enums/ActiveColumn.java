package com.rogrand.core.enums;







public enum ActiveColumn
  implements BaseEnum<String>
{
  FAVOUR("1", "优惠活动");
  private String desc;
  private String code;

  ActiveColumn(String code, String desc) {
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


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\enums\ActiveColumn.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */