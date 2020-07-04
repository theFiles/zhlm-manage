package com.rogrand.util;

import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;


public class HttpUtils
{
  public static String sendGet(String url, String param, String contentType) {
    String result = "";
    BufferedReader in = null;
    try {
      String urlName = url + "?" + param;
      URL realUrl = new URL(urlName);
      URLConnection conn = realUrl.openConnection();

      conn.setRequestProperty("accept", "*/*");
      conn.setRequestProperty("connection", "Keep-Alive");
      if (StringUtils.isNotEmpty(contentType)) {
        conn.setRequestProperty("Content-type", contentType);
      }
      conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");

      conn.connect();

      Map<String, List<String>> map = conn.getHeaderFields();

      for (String key : map.keySet()) {
        System.out.println(key + "--->" + map.get(key));
      }

      in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
      String line;
      while ((line = in.readLine()) != null) {
        result = result + line;
      }
      System.out.print("响应数据--->" + result);
    } catch (Exception e) {
      System.out.println("发送GET请求出现异常！" + e);
      e.printStackTrace();
    } finally {


      try {
        if (in != null) {
          in.close();
        }
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }
    return result;
  }







  public static String sendPost(String url, String param, String contentType) {
    PrintWriter out = null;
    BufferedReader in = null;
    String result = "";
    try {
      URL realUrl = new URL(url);

      URLConnection conn = realUrl.openConnection();

      conn.setRequestProperty("accept", "*/*");
      conn.setRequestProperty("connection", "Keep-Alive");
      if (StringUtils.isNotEmpty(contentType)) {
        conn.setRequestProperty("Content-type", contentType);
      }
      conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");

      conn.setDoOutput(true);
      conn.setDoInput(true);

      out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(), "utf-8"));

      System.out.print("请求数据--->" + param);
      out.print(param);

      out.flush();

      Map<String, List<String>> map = conn.getHeaderFields();

      for (String key : map.keySet()) {
        System.out.println(key + "--->" + map.get(key));
      }

      in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
      String line;
      while ((line = in.readLine()) != null) {
        result = result + line;
      }
      System.out.print("响应数据--->" + result);
    } catch (Exception e) {
      System.out.println("发送POST请求出现异常！" + e);
      e.printStackTrace();
    } finally {


      try {
        if (out != null) {
          out.close();
        }
        if (in != null) {
          in.close();
        }
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }
    return result;
  }







  public static String sendPost(String url, Map<String, String> param, String contentType) {
    String params = "";
    for (Map.Entry<String, String> entry : param.entrySet()) {
      String key = entry.getKey();
      String value = entry.getValue();
      params = params + key + "=" + value + "&";
    }
    String result = sendPost(url, params, contentType);
    return result;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com\rogran\\util\HttpUtils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */