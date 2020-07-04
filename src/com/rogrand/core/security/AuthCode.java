package com.rogrand.core.security;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;


public class AuthCode
{
  private static String defaultKey = "com.rogrand";






































































  private static Key getKey(byte[] arrBTmp) throws Exception {
    byte[] arrB = new byte[8];

    for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
      arrB[i] = arrBTmp[i];
    }
    return new SecretKeySpec(arrB, "DES");
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\security\AuthCode.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */