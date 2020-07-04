package com.rogrand.sys.domain;

import com.rogrand.core.domain.Base;


public class UserRole
  extends Base
{
  private String sur_id;
  private String su_id;
  private String sr_id;

  public UserRole() {
    putComment("tableComment", "用户角色表");
    putComment("sur_id", "用户角色ID");
    putComment("su_id", "用户ID");
    putComment("sr_id", "角色ID");
  }

  public UserRole(Long sur_id) {
    this("sur_id", sur_id);
  }

  public UserRole(String sur_id) {
    this("sur_id", sur_id);
  }

  public UserRole(String property, Object value) {
    this();
    init(property, value);
  }

  public void setSur_id(String sur_id) {
    putField("sur_id");
    this.sur_id = sur_id;
  }

  public void setSu_id(String su_id) {
    putField("su_id");
    this.su_id = su_id;
  }

  public void setSr_id(String sr_id) {
    putField("sr_id");
    this.sr_id = sr_id;
  }

  public String getSur_id() {
    return this.sur_id;
  }

  public String getSu_id() {
    return this.su_id;
  }

  public String getSr_id() {
    return this.sr_id;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\sys\domain\UserRole.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */