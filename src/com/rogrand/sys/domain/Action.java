package com.rogrand.sys.domain;

import com.rogrand.core.domain.Base;


public class Action
  extends Base
{
  private String sa_id;
  private String sm_id;
  private String sa_class;
  private String sa_group;

  public Action() {
    putComment("tableComment", "菜单功能表");
    putComment("sa_id", "菜单功能ID");
    putComment("sm_id", "菜单ID");
    putComment("sa_class", "菜单类");
    putComment("sa_group", "功能分组");
  }

  public Action(Long sa_id) {
    this("sa_id", sa_id);
  }

  public Action(String sa_id) {
    this("sa_id", sa_id);
  }

  public Action(String property, Object value) {
    this();
    init(property, value);
  }

  public void setSa_id(String sa_id) {
    putField("sa_id");
    this.sa_id = sa_id;
  }

  public void setSm_id(String sm_id) {
    putField("sm_id");
    this.sm_id = sm_id;
  }

  public void setSa_class(String sa_class) {
    putField("sa_class");
    this.sa_class = sa_class;
  }

  public void setSa_group(String sa_group) {
    putField("sa_group");
    this.sa_group = sa_group;
  }

  public String getSa_id() {
    return this.sa_id;
  }

  public String getSm_id() {
    return this.sm_id;
  }

  public String getSa_class() {
    return this.sa_class;
  }

  public String getSa_group() {
    return this.sa_group;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\sys\domain\Action.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */