package com.rogrand.core.enums;






public enum ResponseCode
  implements BaseEnum<String>
{
  SUCCESS("0000", "操作成功"),
  SYS_ERR("1000", "操作错误"),
  PARAM_IS_ERROR("1001", "参数错误"),
  NOT_LOGIN("1002", "未登陆"),
  LOGIN_OUT_TIME("1003", "登陆超时"),
  NO_ACCESS("1003", "没有权限"),
  AUTH_CODE_ERR1("1004", "验证码错误"),
  NO_MENU("1005", "没有可用菜单");

  private String code;
  private String desc;

  ResponseCode(String code, String desc) {
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


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\enums\ResponseCode.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */