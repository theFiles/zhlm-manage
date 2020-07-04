package com.rogrand.core.enums;






public enum SmsType
  implements BaseEnum<Long>
{
  VALID(Long.valueOf(1L), "验证码短信"), AUDIT(Long.valueOf(2L), "审核提示短信"), CUSTOM(Long.valueOf(3L), "用户自定义短信"), NOTICE(Long.valueOf(4L), "群发通知");

  private String desc;
  private Long code;

  SmsType(Long code, String desc) {
    this.code = code;
    this.desc = desc;
  }


  public Long getCode() {
    return this.code;
  }


  public String getDesc() {
    return this.desc;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\enums\SmsType.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */