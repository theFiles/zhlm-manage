package com.rogrand.core.enums;






public enum TerminalType
  implements BaseEnum<Long>
{
  ANDROID(Long.valueOf(0L), "Android"), IOS(Long.valueOf(1L), "IOS"), PCWEB(Long.valueOf(3L), "PC-WEB");

  private String desc;
  private Long code;

  TerminalType(Long code, String desc) {
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


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\enums\TerminalType.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */