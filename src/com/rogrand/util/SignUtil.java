package com.rogrand.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;

public class SignUtil {
  private static final Logger logger = LoggerFactory.getLogger(SignUtil.class);


  public static String singParam(Map<String, String> paraMap, String appSecert) {
    Map<String, String> tmpMap = paraMap;
    String sign = "";
    try {
      List<Map.Entry<String, String>> infoIds = new ArrayList<>(tmpMap.entrySet());

      Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>()
          {
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
              return ((String)o1.getKey()).toString().compareTo(o2.getKey());
            }
          });

      StringBuilder buf = new StringBuilder();
      for (Map.Entry<String, String> item : infoIds) {
        if (StringUtils.isNotBlank(item.getKey()) && StringUtils.isNotBlank(item.getValue())) {
          String key = item.getKey();
          String val = item.getValue();
          buf.append(key + "=" + val);
          buf.append("&");
        }
      }
      String buff = buf.toString();
      if (!buff.isEmpty()) {
        buff = buff.substring(0, buff.length() - 1);
        System.out.print("请求待签名串：" + buff);

        sign = sign(appSecert.getBytes("utf-8"), buff);
      }
    } catch (Exception e) {
      return null;
    }
    return sign;
  }












  public static String sign(byte[] priKeyText, String plainText) throws Exception {
    try {
      PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decodeBase64(priKeyText));
      KeyFactory keyf = KeyFactory.getInstance("RSA");
      PrivateKey prikey = keyf.generatePrivate(priPKCS8);

      Signature signet = Signature.getInstance("SHA256withRSA");
      signet.initSign(prikey);
      signet.update(plainText.getBytes("UTF-8"));
      return DigestUtil.byte2hex(signet.sign());
    } catch (Exception e) {
      logger.error("RSA 私钥加签失败!");
      throw e;
    }
  }













  public static boolean verify(byte[] pubKeyText, String plainText, String signText) {
    try {
      X509EncodedKeySpec bobPubKeySpec = new X509EncodedKeySpec(Base64.decodeBase64(pubKeyText));

      KeyFactory keyFactory = KeyFactory.getInstance("RSA");

      PublicKey pubKey = keyFactory.generatePublic(bobPubKeySpec);

      byte[] signed = DigestUtil.hex2byte(signText.getBytes("UTF-8"));
      Signature signatureChecker = Signature.getInstance("SHA256withRSA");
      signatureChecker.initVerify(pubKey);
      signatureChecker.update(plainText.getBytes("UTF-8"));

      return signatureChecker.verify(signed);
    } catch (Throwable e) {
      return false;
    }
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com\rogran\\util\SignUtil.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */