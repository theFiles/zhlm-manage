package com.rogrand.sys.domain;

import com.rogrand.core.domain.Base;

import java.util.List;


public class Menu
  extends Base
{
  private String sm_id;
  private String sap_id;
  private String sm_name;
  private String sm_parentid;
  private String sm_icon;
  private String sm_type;
  private String sm_action;
  private Long sm_order;
  private String sm_content;
  private String sm_admin;
  private String sm_child;
  private String sm_parentname;
  private String sap_name;
  private List<Menu> child_menus;

  public Menu() {}

  public Menu(String sm_id) {
    this("sm_id", sm_id);
  }

  public Menu(String property, Object value) {
    this();
    init(property, value);
  }




  public void setSm_id(String sm_id) {
    putField("sm_id");
    this.sm_id = sm_id;
  }




  public String getSm_id() {
    return this.sm_id;
  }




  public void setSm_name(String sm_name) {
    putField("sm_name");
    this.sm_name = sm_name;
  }




  public String getSm_name() {
    return this.sm_name;
  }




  public void setSm_parentid(String sm_parentid) {
    putField("sm_parentid");
    this.sm_parentid = sm_parentid;
  }




  public String getSm_parentid() {
    return this.sm_parentid;
  }




  public void setSm_icon(String sm_icon) {
    putField("sm_icon");
    this.sm_icon = sm_icon;
  }




  public String getSm_icon() {
    return this.sm_icon;
  }




  public void setSm_type(String sm_type) {
    putField("sm_type");
    this.sm_type = sm_type;
  }




  public String getSm_type() {
    return this.sm_type;
  }




  public void setSm_action(String sm_action) {
    putField("sm_action");
    this.sm_action = sm_action;
  }




  public String getSm_action() {
    return this.sm_action;
  }




  public void setSm_order(Long sm_order) {
    putField("sm_order");
    this.sm_order = sm_order;
  }




  public Long getSm_order() {
    return this.sm_order;
  }




  public void setSm_content(String sm_content) {
    putField("sm_content");
    this.sm_content = sm_content;
  }




  public String getSm_content() {
    return this.sm_content;
  }




  public void setSm_admin(String sm_admin) {
    putField("sm_admin");
    this.sm_admin = sm_admin;
  }




  public String getSm_admin() {
    return this.sm_admin;
  }




  public void setSap_id(String sap_id) {
    putField("sap_id");
    this.sap_id = sap_id;
  }




  public String getSap_id() {
    return this.sap_id;
  }


  public String getSm_child() {
    return this.sm_child;
  }

  public void setSm_child(String sm_child) {
    putField("sm_child");
    this.sm_child = sm_child;
  }

  public String getSm_parentname() {
    return this.sm_parentname;
  }

  public void setSm_parentname(String sm_parentname) {
    putField("sm_parentname");
    this.sm_parentname = sm_parentname;
  }

  public String getSap_name() {
    return this.sap_name;
  }

  public void setSap_name(String sap_name) {
    putField("sap_name");
    this.sap_name = sap_name;
  }

  public List<Menu> getChild_menus() {
    return this.child_menus;
  }

  public void setChild_menus(List<Menu> child_menus) {
    this.child_menus = child_menus;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\sys\domain\Menu.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */