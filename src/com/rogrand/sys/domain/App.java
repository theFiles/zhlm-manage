package com.rogrand.sys.domain;

import com.rogrand.core.domain.Base;


public class App
  extends Base
{
  private String sap_id;
  private String sap_code;
  private String sap_name;

  public App() {
    putComment("tableComment", "应用系统表");
    putComment("sap_id", "应用系统ID");
    putComment("sap_code", "应用系统编号");
    putComment("sap_name", "应用系统名称");
  }

  public App(Long sap_id) {
    this("sap_id", sap_id);
  }

  public App(String sap_id) {
    this("sap_id", sap_id);
  }

  public App(String property, Object value) {
    this();
    init(property, value);
  }




  public void setSap_id(String sap_id) {
    putField("sap_id");
    this.sap_id = sap_id;
  }




  public String getSap_id() {
    return this.sap_id;
  }




  public void setSap_code(String sap_code) {
    putField("sap_code");
    this.sap_code = sap_code;
  }




  public String getSap_code() {
    return this.sap_code;
  }




  public void setSap_name(String sap_name) {
    putField("sap_name");
    this.sap_name = sap_name;
  }




  public String getSap_name() {
    return this.sap_name;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\sys\domain\App.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */