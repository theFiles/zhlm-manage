package com.rogrand.core.util;

import java.io.ByteArrayOutputStream;


public class CodeUtil
{
  private static String hexString = "0123456789abcdef";







  public static String stringToHex(String str) {
    byte[] bytes = str.getBytes();
    StringBuilder sb = new StringBuilder(bytes.length * 2);

    for (int i = 0; i < bytes.length; i++) {
      sb.append(hexString.charAt((bytes[i] & 0xF0) >> 4));
      sb.append(hexString.charAt((bytes[i] & 0xF) >> 0));
    }
    return sb.toString();
  }






  public static String hexToString(String hex) {
    String s = "";
    ByteArrayOutputStream baos = null;
    try {
      baos = new ByteArrayOutputStream(hex.length() / 2);

      for (int i = 0; i < hex.length(); i += 2) {
        baos.write(hexString.indexOf(hex.charAt(i)) << 4 | hexString.indexOf(hex.charAt(i + 1)));
      }
      s = new String(baos.toByteArray());
      baos.close();
    }
    catch (Exception e) {
      e.fillInStackTrace();
    }

    return s;
  }







  public static byte[] hexToByte(String hex) {
    byte[] bts = new byte[hex.length() / 2];
    for (int i = 0; i < bts.length; i++) {
      bts[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
    }

    return bts;
  }







  public static String byteToHex(byte[] b) {
    String s = "";
    for (int i = 0; i < b.length; i++) {
      String hex = Integer.toHexString(b[i] & 0xFF);
      if (hex.length() == 1) {
        hex = '0' + hex;
      }
      s = s + hex;
    }
    return s;
  }






  public static String byteToString(byte[] b) {
    String hex = byteToHex(b);
    return hexToString(hex);
  }






  public static byte[] stringToByte(String s) {
    String hex = stringToHex(s);
    return hexToByte(hex);
  }






  public static String encodeUnicode(String str) {
    StringBuffer out = new StringBuffer();
    for (int i = 0; i < str.length(); i++) {
      char ch = str.charAt(i);
      out.append("\\u");
      String hex = Integer.toHexString(str.charAt(i) & Character.MAX_VALUE);
      for (int j = 0; j < 4 - hex.length(); j++) {
        out.append("0");
      }
      out.append(hex.toLowerCase());
    }
    return new String(out);
  }






  public static String decodeUnicode(String string) {
    int start = 0;
    int end = 0;
    StringBuffer buffer = new StringBuffer();
    while (start > -1) {
      end = string.indexOf("\\u", start + 2);
      String charStr = "";
      if (end == -1) {
        charStr = string.substring(start + 2, string.length());
      } else {
        charStr = string.substring(start + 2, end);
      }
      char letter = (char)Integer.parseInt(charStr, 16);
      buffer.append(Character.toString(letter));
      start = end;
    }
    return buffer.toString();
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\cor\\util\CodeUtil.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */