package com.rogrand.sys.domain;

import com.rogrand.core.domain.Base;
import org.springframework.data.annotation.Transient;

import java.util.List;


public class Org
  extends Base
{
  private String so_id;
  private String sap_id;
  private String so_name;
  private String so_parentid;
  private Integer so_order;
  private String so_code;
  private String so_contact;
  private String so_email;
  private String so_post;
  private String so_purview;
  private String so_parentname;
  private String so_child;
  private String so_used;
  @Transient
  private List<Org> child_orgs;

  public Org() {
    putComment("tableComment", "机构表");
    putComment("so_id", "机构ID");
    putComment("so_name", "机构名称机构部门表");
    putComment("so_parentid", "上级机构ID");
    putComment("so_order", "机构排序");
    putComment("so_code", "机构编码");
    putComment("so_contact", "联系电话");
    putComment("so_email", "电子邮箱");
    putComment("so_post", "邮政编码");
    putComment("so_purview", "机构权限");
  }


  public Org(Long so_id) {
    this("so_id", so_id);
  }

  public Org(String so_id) {
    this("so_id", so_id);
  }

  public Org(String property, Object value) {
    this();
    init(property, value);
  }

  public void setSo_id(String so_id) {
    putField("so_id");
    this.so_id = so_id;
  }

  public void setSo_name(String so_name) {
    putField("so_name");
    this.so_name = so_name;
  }

  public void setSo_parentid(String so_parentid) {
    putField("so_parentid");
    this.so_parentid = so_parentid;
  }

  public void setSo_order(Integer so_order) {
    putField("so_order");
    this.so_order = so_order;
  }

  public void setSo_code(String so_code) {
    putField("so_code");
    this.so_code = so_code;
  }

  public void setSo_contact(String so_contact) {
    putField("so_contact");
    this.so_contact = so_contact;
  }

  public void setSo_email(String so_email) {
    putField("so_email");
    this.so_email = so_email;
  }

  public void setSo_post(String so_post) {
    putField("so_post");
    this.so_post = so_post;
  }

  public void setSo_purview(String so_purview) {
    putField("so_purview");
    this.so_purview = so_purview;
  }

  public void setSo_child(String so_child) {
    putField("so_child");
    this.so_child = so_child;
  }

  public void setSo_used(String so_used) {
    putField("so_used");
    this.so_used = so_used;
  }

  public String getSo_used() {
    return this.so_used;
  }

  public String getSo_id() {
    return this.so_id;
  }

  public String getSo_name() {
    return this.so_name;
  }

  public String getSo_parentid() {
    return this.so_parentid;
  }

  public Integer getSo_order() {
    return this.so_order;
  }

  public String getSo_code() {
    return this.so_code;
  }

  public String getSo_contact() {
    return this.so_contact;
  }

  public String getSo_email() {
    return this.so_email;
  }

  public String getSo_post() {
    return this.so_post;
  }

  public String getSo_purview() {
    return this.so_purview;
  }

  public String getSo_child() {
    return this.so_child;
  }

  public String getSo_parentname() {
    return this.so_parentname;
  }

  public void setSo_parentname(String so_parentname) {
    putField("so_parentname");
    this.so_parentname = so_parentname;
  }

  public String getSap_id() {
    return this.sap_id;
  }

  public void setSap_id(String sap_id) {
    putField("sap_id");
    this.sap_id = sap_id;
  }

  public List<Org> getChild_orgs() {
    return this.child_orgs;
  }

  public void setChild_orgs(List<Org> child_orgs) {
    this.child_orgs = child_orgs;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\sys\domain\Org.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */