package com.rogrand.sys.domain;

import com.rogrand.core.domain.Base;


public class RoleAction
  extends Base
{
  private String sra_id;
  private String sr_id;
  private String sm_id;
  private String sa_id;

  public RoleAction() {
    putComment("tableComment", "角色动作表");
    putComment("sra_id", "主键");
    putComment("sr_id", "角色ID");
    putComment("sm_id", "菜单ID");
    putComment("sa_id", "功能ID");
  }

  public RoleAction(Long sra_id) {
    this("sra_id", sra_id);
  }

  public RoleAction(String sra_id) {
    this("sra_id", sra_id);
  }

  public RoleAction(String property, Object value) {
    this();
    init(property, value);
  }

  public void setSra_id(String sra_id) {
    putField("sra_id");
    this.sra_id = sra_id;
  }

  public void setSr_id(String sr_id) {
    putField("sr_id");
    this.sr_id = sr_id;
  }

  public void setSm_id(String sm_id) {
    putField("sm_id");
    this.sm_id = sm_id;
  }

  public void setSa_id(String sa_id) {
    putField("sa_id");
    this.sa_id = sa_id;
  }

  public String getSra_id() {
    return this.sra_id;
  }

  public String getSr_id() {
    return this.sr_id;
  }

  public String getSm_id() {
    return this.sm_id;
  }

  public String getSa_id() {
    return this.sa_id;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\sys\domain\RoleAction.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */