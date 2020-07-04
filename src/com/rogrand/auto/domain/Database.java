package com.rogrand.auto.domain;

import com.rogrand.core.domain.SerializeCloneable;

import java.util.List;


public class Database
  extends SerializeCloneable
{
  private static final long serialVersionUID = 4506825349291946162L;
  private String driverClassName;
  private String url;
  private String username;
  private String password;
  private List<Table> tableList;

  public String getDriverClassName() {
    return this.driverClassName;
  }

  public void setDriverClassName(String driverClassName) {
    this.driverClassName = driverClassName;
  }

  public String getUrl() {
    return this.url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getUsername() {
    return this.username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<Table> getTableList() {
    return this.tableList;
  }

  public void setTableList(List<Table> tableList) {
    this.tableList = tableList;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\auto\domain\Database.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */