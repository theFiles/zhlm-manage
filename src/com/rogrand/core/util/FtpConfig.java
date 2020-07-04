package com.rogrand.core.util;

import java.io.IOException;
import java.util.Properties;


public class FtpConfig
{
  private static Properties prop = new Properties();

  static {
    try {
      prop.load(FtpConfig.class.getResourceAsStream("/ftp.properties"));
    } catch (IOException e) {
      throw new RuntimeException("加载FTP账号配置信息出错：" + e.getMessage(), e);
    }
  }

  public static void reload(String path) {
    try {
      prop.load(FtpConfig.class.getResourceAsStream(path));
    } catch (IOException e) {
      throw new RuntimeException("重新加载FTP账号配置信息出错：" + e.getMessage(), e);
    }
  }

  public static String getIp() {
    return prop.getProperty("ftp.ip");
  }

  public static String getUsername() {
    return prop.getProperty("ftp.username");
  }

  public static String getPassword() {
    return prop.getProperty("ftp.password");
  }

  public static int getPort() {
    return Integer.parseInt(prop.getProperty("ftp.port"));
  }

  public static String getUrl() {
    return prop.getProperty("url");
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\cor\\util\FtpConfig.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */