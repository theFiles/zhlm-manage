package com.rogrand.core.util;

import java.security.MessageDigest;

public class MD5Util
{
  public static final String MD5(String s) {
    char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };



    try {
      byte[] btInput = s.getBytes();

      MessageDigest mdInst = MessageDigest.getInstance("MD5");

      mdInst.update(btInput);

      byte[] md = mdInst.digest();

      int j = md.length;
      char[] str = new char[j * 2];
      int k = 0;
      for (int i = 0; i < j; i++) {
        byte byte0 = md[i];
        str[k++] = hexDigits[byte0 >>> 4 & 0xF];
        str[k++] = hexDigits[byte0 & 0xF];
      }
      return new String(str);
    }
    catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
  public static void main(String[] args) {
    System.out.print(MD5("password"));
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\cor\\util\MD5Util.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */