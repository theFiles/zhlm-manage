package com.rogrand.core.enums;

public class CouponCategory
  implements BaseEnum<String>
{
  private Long code;
  private String desc;

  private CouponCategory(Long code, String desc) {
    this.code = code;
    this.desc = desc;
  }


  public String getCode() {
    return null;
  }


  public String getDesc() {
    return null;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\enums\CouponCategory.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */