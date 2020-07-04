package com.rogrand.core.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MD5
{
  private static Log logger = LogFactory.getLog(MD5.class);
  public static String getEncrypt(String input) {
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      md.update(input.getBytes());
      byte[] bb = md.digest();
      String hs = "", tmp = "";
      for (byte e : bb) {
        tmp = Integer.toHexString(e & 0xFF);
        hs = (tmp.length() == 1) ? (hs + "0" + tmp) : (hs + tmp);
      }
      return hs;
    } catch (NoSuchAlgorithmException e) {
      logger.error(e.getMessage());
      return "";
    }
  }

  public static void main(String[] args) {}
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\security\MD5.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */