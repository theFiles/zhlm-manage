package com.rogrand.core.enums;






public enum SmsSendState
  implements BaseEnum<Long>
{
  VALID(Long.valueOf(0L), "待发送"), AUDIT(Long.valueOf(1L), "成功"), CUSTOM(Long.valueOf(2L), "失败");

  private String desc;
  private Long code;

  SmsSendState(Long code, String desc) {
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


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\enums\SmsSendState.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */