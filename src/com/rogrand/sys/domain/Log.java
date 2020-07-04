package com.rogrand.sys.domain;

import com.rogrand.core.annotation.FieldAnnotation;
import com.rogrand.core.domain.Base;

import java.util.Date;


public class Log
  extends Base
{
  @FieldAnnotation(comment = "日志id", exp = false, imp = false, empty = true, len = 64, pk = true)
  private String sl_id;
  @FieldAnnotation(comment = "日志时间", exp = true, imp = true, empty = true)
  private Date sl_date;
  @FieldAnnotation(comment = "登录账号", exp = true, imp = true, empty = true, len = 50)
  private String sl_user_code;
  @FieldAnnotation(comment = "用户名称", exp = true, imp = true, empty = true, len = 50)
  private String sl_user_name;
  @FieldAnnotation(comment = "机构名称", exp = true, imp = true, empty = true, len = 50)
  private String sl_org_name;
  @FieldAnnotation(comment = "ip地址", exp = true, imp = true, empty = true, len = 200)
  private String sl_ip;
  @FieldAnnotation(comment = "类名称", exp = true, imp = true, empty = true, len = 200)
  private String sl_class;
  @FieldAnnotation(comment = "方法名称", exp = true, imp = true, empty = true, len = 100)
  private String sl_method;
  @FieldAnnotation(comment = "方法描述", exp = true, imp = true, empty = true, len = 200)
  private String sl_description;
  @FieldAnnotation(comment = "日志内容", exp = true, imp = true, empty = true, len = 65535)
  private String sl_content;

  public Log() {}

  public Log(String sl_id) {
    this("sl_id", sl_id);
  }

  public Log(String property, Object value) {
    this();
    init(property, value);
  }

  public String getSl_id() {
    return this.sl_id;
  }

  public void setSl_id(String sl_id) {
    putField("sl_id");
    this.sl_id = sl_id;
  }

  public Date getSl_date() {
    return this.sl_date;
  }

  public void setSl_date(Date sl_date) {
    putField("sl_date");
    this.sl_date = sl_date;
  }

  public String getSl_user_code() {
    return this.sl_user_code;
  }

  public void setSl_user_code(String sl_user_code) {
    putField("sl_user_code");
    this.sl_user_code = sl_user_code;
  }

  public String getSl_user_name() {
    return this.sl_user_name;
  }

  public void setSl_user_name(String sl_user_name) {
    putField("sl_user_name");
    this.sl_user_name = sl_user_name;
  }

  public String getSl_org_name() {
    return this.sl_org_name;
  }

  public void setSl_org_name(String sl_org_name) {
    putField("sl_org_name");
    this.sl_org_name = sl_org_name;
  }

  public String getSl_ip() {
    return this.sl_ip;
  }

  public void setSl_ip(String sl_ip) {
    putField("sl_ip");
    this.sl_ip = sl_ip;
  }

  public String getSl_class() {
    return this.sl_class;
  }

  public void setSl_class(String sl_class) {
    putField("sl_class");
    this.sl_class = sl_class;
  }

  public String getSl_method() {
    return this.sl_method;
  }

  public void setSl_method(String sl_method) {
    putField("sl_method");
    this.sl_method = sl_method;
  }

  public String getSl_description() {
    return this.sl_description;
  }

  public void setSl_description(String sl_description) {
    putField("sl_description");
    this.sl_description = sl_description;
  }

  public String getSl_content() {
    return this.sl_content;
  }

  public void setSl_content(String sl_content) {
    putField("sl_content");
    this.sl_content = sl_content;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\sys\domain\Log.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */