package com.rogrand.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Digest
{
  private static final String ENCODING = "UTF-8";

  public static String hmacSign(String aValue) {
    try {
      byte[] input = aValue.getBytes();
      MessageDigest md = MessageDigest.getInstance("MD5");
      System.out.println(md.digest(input));
      return toHex(md.digest(input));
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
      return null;
    }
  }






  public static String toHex(byte[] input) {
    if (input == null)
      return null;
    StringBuffer output = new StringBuffer(input.length * 2);
    for (int i = 0; i < input.length; i++) {
      int current = input[i] & 0xFF;
      if (current < 16)
        output.append("0");
      output.append(Integer.toString(current, 16));
    }

    return output.toString();
  }







  public static String hmacSign(String aValue, String aKey) {
    return hmacSign(aValue, aKey, "UTF-8");
  }








  public static String hmacSign(String aValue, String aKey, String encoding) {
    byte[] arrayOfByte1, arrayOfByte2, k_ipad = new byte[64];
    byte[] k_opad = new byte[64];


    try {
      arrayOfByte1 = aKey.getBytes(encoding);
      arrayOfByte2 = aValue.getBytes(encoding);
    } catch (UnsupportedEncodingException e) {
      arrayOfByte1 = aKey.getBytes();
      arrayOfByte2 = aValue.getBytes();
    }
    Arrays.fill(k_ipad, arrayOfByte1.length, 64, (byte)54);
    Arrays.fill(k_opad, arrayOfByte1.length, 64, (byte)92);
    for (int i = 0; i < arrayOfByte1.length; i++) {
      k_ipad[i] = (byte)(arrayOfByte1[i] ^ 0x36);
      k_opad[i] = (byte)(arrayOfByte1[i] ^ 0x5C);
    }

    MessageDigest md = null;
    try {
      md = MessageDigest.getInstance("MD5");
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
      return null;
    }
    md.update(k_ipad);
    md.update(arrayOfByte2);
    byte[] dg = md.digest();
    md.reset();
    md.update(k_opad);
    md.update(dg, 0, 16);
    dg = md.digest();
    return toHex(dg);
  }


  public static void main(String[] args) {
    String key = "D10001674445100124348800.14511aaa18612563245http://112.74.176.237:8063/qpapi/openUnionpayResultForYiMingShenZhou.actionhttp://112.74.176.237:8063/qpapi/openUnionpayResultForYiMingShenZhou.actionhttp://112.74.176.237:8063/qpapi/openUnionpayResultForYiMingShenZhou.action";
    String s = hmacSign(key);
    System.out.println(s);
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com\rogran\\util\Digest.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */