package com.rogrand.core.enums;







public enum ActiveType
  implements BaseEnum<String>
{
  APPLY("APPLY", "报名活动", "/template/apply_active.html");
  private String template;
  private String desc;
  private String code;

  ActiveType(String code, String desc, String template) {
    this.code = code;
    this.desc = desc;
    this.template = template;
  }


  public String getCode() {
    return this.code;
  }


  public String getDesc() {
    return this.desc;
  }

  public String getTemplate() {
    return this.template;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\enums\ActiveType.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */