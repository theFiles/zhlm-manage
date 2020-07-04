package com.rogrand.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES
{
  public static final String ALGORITHM_AEPP = "AES/ECB/PKCS5Padding";

  public static byte[] encrypt(byte[] content, byte[] password, String algorithm) {
    if (content == null || password == null)
      return null;
    try {
      Cipher cipher = null;
      if (algorithm.endsWith("PKCS7Padding")) {
        cipher = Cipher.getInstance(algorithm, "BC");
      } else {
        cipher = Cipher.getInstance(algorithm);
      }
      cipher.init(1, new SecretKeySpec(password, "AES"));
      return cipher.doFinal(content);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }












  public static byte[] decrypt(byte[] content, byte[] password, String algorithm) {
    if (content == null || password == null)
      return null;
    try {
      Cipher cipher = null;
      if (algorithm.endsWith("PKCS7Padding")) {
        cipher = Cipher.getInstance(algorithm, "BC");
      } else {
        cipher = Cipher.getInstance(algorithm);
      }
      cipher.init(2, new SecretKeySpec(password, "AES"));
      byte[] bytes = cipher.doFinal(content);
      return bytes;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }














  public static byte[] encrypt(byte[] content, byte[] password, byte[] ivStr, String algorithm) {
    if (content == null || password == null)
      return null;
    try {
      Cipher cipher = null;
      if (algorithm.endsWith("PKCS7Padding")) {
        cipher = Cipher.getInstance(algorithm, "BC");
      } else {
        cipher = Cipher.getInstance(algorithm);
      }
      IvParameterSpec iv = new IvParameterSpec(ivStr);
      cipher.init(1, new SecretKeySpec(password, "AES"), iv);
      return cipher.doFinal(content);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }














  public static byte[] decrypt(byte[] content, byte[] password, byte[] ivStr, String algorithm) {
    if (content == null || password == null)
      return null;
    try {
      Cipher cipher = null;
      if (algorithm.endsWith("PKCS7Padding")) {
        cipher = Cipher.getInstance(algorithm, "BC");
      } else {
        cipher = Cipher.getInstance(algorithm);
      }
      IvParameterSpec iv = new IvParameterSpec(ivStr);
      cipher.init(2, new SecretKeySpec(password, "AES"), iv);
      byte[] bytes = cipher.doFinal(content);
      return bytes;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com\rogran\\util\AES.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */