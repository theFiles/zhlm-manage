package com.rogrand.sys.domain;

import com.rogrand.core.domain.Base;


public class RoleMenu
  extends Base
{
  private String srm_id;
  private String sr_id;
  private String sm_id;

  public RoleMenu() {
    putComment("tableComment", "角色菜单表");
    putComment("srm_id", "角色菜单主键");
    putComment("sr_id", "角色ID");
    putComment("sm_id", "菜单ID");
    putComment("sap_id", "系统ID");
  }

  public RoleMenu(Long srm_id) {
    this("srm_id", srm_id);
  }

  public RoleMenu(String srm_id) {
    this("srm_id", srm_id);
  }

  public RoleMenu(String mode, Object value) {
    this();
    init(mode, value);
  }

  public void setSrm_id(String srm_id) {
    putField("srm_id");
    this.srm_id = srm_id;
  }

  public void setSr_id(String sr_id) {
    putField("sr_id");
    this.sr_id = sr_id;
  }

  public void setSm_id(String sm_id) {
    putField("sm_id");
    this.sm_id = sm_id;
  }

  public String getSrm_id() {
    return this.srm_id;
  }

  public String getSr_id() {
    return this.sr_id;
  }

  public String getSm_id() {
    return this.sm_id;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\sys\domain\RoleMenu.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */