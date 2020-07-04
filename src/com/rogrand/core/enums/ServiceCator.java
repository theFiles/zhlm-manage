package com.rogrand.core.enums;






public enum ServiceCator
  implements BaseEnum<Long>
{
  IMMEDIATE(Long.valueOf(1L), "买药服务"), BOOKING(Long.valueOf(2L), "问诊服务");

  private String desc;
  private Long code;

  ServiceCator(Long code, String desc) {
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


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\enums\ServiceCator.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */