package com.rogrand.core.enums;






public enum MerchantType
  implements BaseEnum<Long>
{
  PHARMACY(Long.valueOf(1L), "药店"), COMMUNITY_CLINIC(Long.valueOf(2L), "社区诊所");

  private String desc;
  private Long code;

  MerchantType(Long code, String desc) {
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


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\enums\MerchantType.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */