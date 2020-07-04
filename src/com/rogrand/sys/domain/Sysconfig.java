package com.rogrand.sys.domain;

import com.rogrand.core.annotation.FieldAnnotation;
import com.rogrand.core.domain.Base;

import java.util.Date;


public class Sysconfig
  extends Base
{
  private static final long serialVersionUID = -9190650837389559744L;
  @FieldAnnotation(comment = "配置ID", exp = false, imp = false, empty = true, len = 10, pk = true)
  private Long configid;
  @FieldAnnotation(comment = "配置KEY", exp = true, imp = true, empty = true, len = 30)
  private String configkey;
  @FieldAnnotation(comment = "配置值", exp = true, imp = true, empty = true, len = 10)
  private String configvalue;
  @FieldAnnotation(comment = "配置描述", exp = true, imp = true, empty = true, len = 200)
  private String configdesc;
  @FieldAnnotation(comment = "配置时间", exp = true, imp = true, empty = true)
  private Date configaddtime;
  @FieldAnnotation(comment = "配置类型 0-用户配置 1-系统配置", exp = true, imp = true, empty = true)
  private Long configtype;

  public Sysconfig() {}

  public Sysconfig(String configid) {
    this("configid", configid);
  }

  public Sysconfig(String property, Object value) {
    this();
    init(property, value);
  }





  public Long getConfigid() {
    return this.configid;
  }





  public void setConfigid(Long configid) {
    putField("configid");
    this.configid = configid;
  }





  public String getConfigkey() {
    return this.configkey;
  }





  public void setConfigkey(String configkey) {
    putField("configkey");
    this.configkey = configkey;
  }





  public String getConfigvalue() {
    return this.configvalue;
  }





  public void setConfigvalue(String configvalue) {
    putField("configvalue");
    this.configvalue = configvalue;
  }





  public String getConfigdesc() {
    return this.configdesc;
  }





  public void setConfigdesc(String configdesc) {
    putField("configdesc");
    this.configdesc = configdesc;
  }





  public Date getConfigaddtime() {
    return this.configaddtime;
  }





  public void setConfigaddtime(Date configaddtime) {
    putField("configaddtime");
    this.configaddtime = configaddtime;
  }




  public Long getConfigtype() {
    return this.configtype;
  }





  public void setConfigtype(Long configtype) {
    putField("configtype");
    this.configtype = configtype;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\sys\domain\Sysconfig.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */