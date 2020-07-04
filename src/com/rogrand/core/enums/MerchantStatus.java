package com.rogrand.core.enums;






public enum MerchantStatus
  implements BaseEnum<Long>
{
  INIT(Long.valueOf(1L), "待审核"), PASSED(Long.valueOf(2L), "已认证"), REJECT(Long.valueOf(3L), "已驳回");

  private String desc;
  private Long code;

  MerchantStatus(Long code, String desc) {
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


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\enums\MerchantStatus.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */