package com.rogrand.core.jms.simplejms;

import java.io.Serializable;


public class WebNode
  implements Serializable
{
  private static final long serialVersionUID = -3290325399607255390L;
  private int id;
  private String protocol;
  private String host;
  private int port;
  private String app;

  public String toString() {
    return this.protocol + "://" + this.host + ":" + this.port + "/" + this.app;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getPort() {
    return this.port;
  }

  public void setPort(int port) {
    this.port = port;
  }

  public String getProtocol() {
    return this.protocol;
  }

  public void setProtocol(String protocol) {
    this.protocol = protocol;
  }

  public String getApp() {
    return this.app;
  }

  public void setApp(String app) {
    this.app = app;
  }

  public String getHost() {
    return this.host;
  }

  public void setHost(String host) {
    this.host = host;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\jms\simplejms\WebNode.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */