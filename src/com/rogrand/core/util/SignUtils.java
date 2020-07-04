package com.rogrand.core.util;

import org.apache.commons.lang.StringUtils;

import java.net.URLEncoder;
import java.util.*;


public class SignUtils
{
  public static String singParam(Map<String, String> paraMap, boolean urlEncode, boolean keyToLower, String appSecert) {
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
          if (urlEncode) {
            val = URLEncoder.encode(val, "utf-8");
          }
          if (keyToLower) {
            buf.append(key.toLowerCase() + "=" + val);
          } else {
            buf.append(key + "=" + val);
          }
          buf.append("&");
        }
      }
      String buff = buf.toString();
      if (!buff.isEmpty()) {
        buff = buff.substring(0, buff.length() - 1);
        buff = buff + "&key=" + appSecert;
        System.out.print(buff);
        sign = MD5Util.MD5(buff).toUpperCase();
      }
    } catch (Exception e) {
      return null;
    }
    return sign;
  }







  public static String singParam(Map<String, String> paraMap, String appSecert) {
    String sign = singParam(paraMap, false, false, appSecert);
    return sign;
  }








  public static String getSign(Map<String, String> paramMap, String appSecret) throws Exception {
    SortedMap<String, Object> smap = new TreeMap<String, Object>(paramMap);
    StringBuffer stringBuffer = new StringBuffer();
    for (Map.Entry<String, Object> m : smap.entrySet()) {
      Object value = m.getValue();
      if (value == null)
        value = "";
      stringBuffer.append(m.getKey()).append("=").append(value).append("&");
    }

    String argPreSign = stringBuffer.append("key=").append(appSecret).toString();
    System.out.print("签名字符串：" + argPreSign + "\n");
    String signStr = MD5Util.MD5(argPreSign).toUpperCase();
    System.out.print("签名结果：" + signStr + "\n");
    return signStr;
  }



  public static void main(String[] args) {
    Map<String, String> paraMap = new HashMap<>();
    paraMap.put("total_fee", "200");
    paraMap.put("appid", "wxd678efh567hg6787");
    paraMap.put("body", "腾讯充值中心-QQ会员充值");
    paraMap.put("out_trade_no", "20150806125346");
    String url = singParam(paraMap, false, false, "111");
    System.out.println(url);
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\cor\\util\SignUtils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */