package com.rogrand.util;

import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;

import javax.net.ssl.*;
import java.io.*;
import java.net.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpClientUtil {
  protected static org.apache.commons.logging.Log log = LogFactory.getLog(HttpClientUtil.class);
  public static final String GET = "GET";
  public static final String POST = "POST";

  public static void main(String[] args) {
    sendMsgTest();
  }

  public static String http(String url, Map<String, String> params) throws IOException {
    URL u = null;
    HttpURLConnection con = null;

    StringBuffer sb = new StringBuffer();
    if (params != null) {
      for (Map.Entry<String, String> e : params.entrySet()) {
        sb.append(e.getKey());
        sb.append("=");
        sb.append(e.getValue());
        sb.append("&");
      }
      sb.deleteCharAt(sb.length() - 1);
    }
    System.out.println("send_url:" + url + "?" + sb.toString());

    u = new URL(url + "?" + sb.toString());

    URI uri = null;
    try {
      uri = u.toURI();
    } catch (URISyntaxException e) {

      e.printStackTrace();
    }

    if (uri.getScheme().equals("https")) {
      try {
        trustAllHttpsCertificates();
      } catch (Exception e) {

        e.printStackTrace();
      }

      HostnameVerifier hv = new HostnameVerifier() {
          @Override
          public boolean verify(String urlHostName, SSLSession session) {
            System.out.println("Warning: URL Host: " + urlHostName + " vs. " + session.getPeerHost());

            return true;
          }
        };
      HttpsURLConnection.setDefaultHostnameVerifier(hv);
    }

    con = (HttpURLConnection)u.openConnection();
    con.setRequestMethod("POST");
    con.setConnectTimeout(400000);
    con.setReadTimeout(400000);
    con.setDoOutput(true);
    con.setDoInput(true);
    con.setUseCaches(false);
    con.setRequestProperty("Content-Type", "application/json");


    con.connect();


    StringBuffer buffer = new StringBuffer();
    BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
    String temp;
    while ((temp = br.readLine()) != null) {
      buffer.append(temp);
      buffer.append("\n");
    }
    con.disconnect();
    return buffer.toString();
  }


  private static void trustAllHttpsCertificates() throws Exception {
    TrustManager[] trustAllCerts = new TrustManager[1];
    TrustManager tm = new miTM();
    trustAllCerts[0] = tm;
    SSLContext sc = SSLContext.getInstance("SSL");

    sc.init(null, trustAllCerts, null);
    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
  }

  static class miTM
    implements TrustManager, X509TrustManager
  {
    @Override
    public X509Certificate[] getAcceptedIssuers() {
      return null;
    }


    public boolean isServerTrusted(X509Certificate[] certs) {
      return true;
    }


    public boolean isClientTrusted(X509Certificate[] certs) {
      return true;
    }




    @Override
    public void checkServerTrusted(X509Certificate[] certs, String authType) throws CertificateException {}




    @Override
    public void checkClientTrusted(X509Certificate[] certs, String authType) throws CertificateException {}
  }



  private static String sendMsgTest() {
    List<String[]> plist = (List)new ArrayList<>();
    String[] pair1 = { "userid", "982" };
    String[] pair2 = { "account", "yingding" };
    String[] pair3 = { "password", "123456" };
    String[] pair4 = { "mobile", "18370657018" };
    String[] pair5 = { "content", "【神码在线】欢迎注册神码商户，您的验证码为：0000，（此验证码10分钟内有效）。" };

    String[] pair6 = { "sendTime", "" };
    String[] pair7 = { "action", "send" };
    String[] pair8 = { "extno", "" };
    plist.add(pair1);
    plist.add(pair2);
    plist.add(pair3);
    plist.add(pair4);
    plist.add(pair5);
    plist.add(pair6);
    plist.add(pair7);
    plist.add(pair8);
    String url = "http://120.25.147.10:8002/sms.aspx";
    String pairs = getPairs(plist, "UTF-8");
    log.info("pairs===>" + pairs);
    String outStr = doHttp(url, "POST", "UTF-8", pairs, "60000");
    log.info("应答===>" + outStr);
    return outStr;
  }




  public String sendMsg(String mobile, String content) {
    List<String[]> plist = new ArrayList<>();
    String[] pair1 = { "userid", "982" };
    String[] pair2 = { "account", "yingding" };
    String[] pair3 = { "password", "123456" };
    String[] pair4 = { "mobile", mobile };
    String[] pair5 = { "content", content };
    String[] pair6 = { "sendTime", "" };
    String[] pair7 = { "action", "send" };
    String[] pair8 = { "extno", "" };
    plist.add(pair1);
    plist.add(pair2);
    plist.add(pair3);
    plist.add(pair4);
    plist.add(pair5);
    plist.add(pair6);
    plist.add(pair7);
    plist.add(pair8);
    String url = "http://120.25.147.10:8002/sms.aspx";
    String pairs = getPairs(plist, "UTF-8");
    log.info("8002reqPairs===>" + pairs);
    String outStr = doHttp(url, "POST", "UTF-8", pairs, "60000");
    log.info("8002rsp===>" + outStr);
    return outStr;
  }

  public static String getPairs(List<String[]> list, String charSet) {
    if (list == null || list.size() == 0) {
      return null;
    }
    StringBuffer stringBuffer = new StringBuffer();
    for (int i = 0; i < list.size(); i++) {
      String[] nvPairStr = list.get(i);
      try {
        if (i > 0) {
          stringBuffer.append("&");
        }
        stringBuffer.append(URLEncoder.encode(nvPairStr[0], charSet)).append("=").append(URLEncoder.encode(nvPairStr[1], charSet));

      }
      catch (UnsupportedEncodingException e) {
        log.error("exception", e);
        return null;
      }
    }
    return stringBuffer.toString();
  }


  public static String doHttp(String urlStr, String method, String charSet, String postStr, String timeOut) {
    log.debug("url=" + urlStr);
    log.debug("postStr=" + postStr);
    HttpURLConnection httpURLConnection = null;
    try {
      URL url = new URL(urlStr);
      httpURLConnection = (HttpURLConnection)url.openConnection();
      System.setProperty("sun.net.client.defaultConnectTimeout", timeOut);
      System.setProperty("sun.net.client.defaultReadTimeout", timeOut);
      httpURLConnection.setRequestMethod(method.toUpperCase());
      if ("POST".equalsIgnoreCase(method)) {
        httpURLConnection.setDoOutput(true);
        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(httpURLConnection.getOutputStream(), charSet));



        printWriter.write(postStr);
        printWriter.flush();
        printWriter.close();
      }
      InputStream inputStream = httpURLConnection.getInputStream();
      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      int data = 0;
      int statusCode = httpURLConnection.getResponseCode();
      if (statusCode < 200 || statusCode >= 300) {

        log.error("失败返回码[" + statusCode + "]");
        return null;
      }
      while ((data = inputStream.read()) != -1) {
        byteArrayOutputStream.write(data);
      }
      byte[] returnBytes = byteArrayOutputStream.toByteArray();
      String returnStr = new String(returnBytes, charSet);
      return returnStr;
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (null != httpURLConnection)
        httpURLConnection.disconnect();
    }
    return null;
  }


  public static String doHttp2(String urlStr, String method, String charSet, String postStr, String timeOut) {
    log.debug("url=" + urlStr);
    log.debug("postStr=" + postStr);
    HttpURLConnection httpURLConnection = null;
    try {
      URL url = new URL(urlStr);
      httpURLConnection = (HttpURLConnection)url.openConnection();
      System.setProperty("sun.net.client.defaultConnectTimeout", timeOut);
      System.setProperty("sun.net.client.defaultReadTimeout", timeOut);
      httpURLConnection.setRequestMethod(method.toUpperCase());
      httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
      if ("POST".equalsIgnoreCase(method)) {
        httpURLConnection.setDoOutput(true);
        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(httpURLConnection.getOutputStream(), charSet));



        printWriter.write(postStr);
        printWriter.flush();
        printWriter.close();
      }
      InputStream inputStream = httpURLConnection.getInputStream();
      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      int data = 0;
      int statusCode = httpURLConnection.getResponseCode();
      if (statusCode < 200 || statusCode >= 300) {

        log.error("失败返回码[" + statusCode + "]");
        return null;
      }
      while ((data = inputStream.read()) != -1) {
        byteArrayOutputStream.write(data);
      }
      byte[] returnBytes = byteArrayOutputStream.toByteArray();
      String returnStr = new String(returnBytes, charSet);
      return returnStr;
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (null != httpURLConnection)
        httpURLConnection.disconnect();
    }
    return null;
  }






































































































































  public static String sendXml(String url, String xmlData) {
    DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
    HttpPost httppost = new HttpPost(url);
    httppost.addHeader("Content-Type", "text/xml");
    StringBuffer sb = new StringBuffer();
    try {
      StringEntity myEntity = new StringEntity(xmlData, "UTF-8");
      httppost.setEntity((HttpEntity)myEntity);
      HttpResponse response = defaultHttpClient.execute((HttpUriRequest)httppost);
      HttpEntity resEntity = response.getEntity();
      InputStreamReader reader = new InputStreamReader(resEntity.getContent(), "UTF-8");

      char[] buff = new char[1024];
      int length = 0;
      while ((length = reader.read(buff)) != -1) {
        sb.append(new String(buff, 0, length));
      }
      return sb.toString();
    } catch (Exception e) {
      e.printStackTrace();
      defaultHttpClient.getConnectionManager().shutdown();
      return sb.toString();
    } finally {
      defaultHttpClient.getConnectionManager().shutdown();
    }
  }






















































  public static String sendPost(String url, String json) {
    CloseableHttpClient client = HttpClients.createDefault();

    try {
      String charset = "UTF-8";

      URI uri = null;
      try {
        uri = new URI(url);
      } catch (URISyntaxException e) {

        e.printStackTrace();
      }
      int port = uri.getPort();
      if (port == -1) {
        port = 80;
      }

      HttpHost target = new HttpHost(uri.getHost(), port, "http");

      HttpPost request = new HttpPost(uri);

      CloseableHttpResponse response = null;

      try {
        StringEntity se = new StringEntity(json, charset);
        request.setEntity((HttpEntity)se);
        se.setContentType("application/json");

        response = client.execute(target, (HttpRequest)request);

        BufferedReader reader = null;
        try {
          reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));

          StringBuilder result = new StringBuilder();
          String message = null;
          while ((message = reader.readLine()) != null) {
            result.append(message).append(System.getProperty("line.separator"));
          }

          String resultStr = result.toString();
          resultStr = resultStr.trim();

          resultStr = resultStr.replace("\n", "");
          resultStr = resultStr.replace("\r", "");
          resultStr = resultStr.replace("\t", "");
          return resultStr;
        } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
        } catch (IllegalStateException e) {
          e.printStackTrace();
        } catch (IOException e) {
          e.printStackTrace();
        } finally {
          try {
            if (reader != null) {
              reader.close();
            }
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      } catch (ClientProtocolException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      } finally {
        if (response != null) {
          try {
            response.close();
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      }
    } finally {

      try {
        client.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return null;
  }










  public static String sendPostStr(String url, String param) {
    PrintWriter out = null;
    BufferedReader in = null;
    String result = "";
    try {
      URL realUrl = new URL(url);

      URLConnection conn = realUrl.openConnection();
      conn.setConnectTimeout(60000);
      conn.setReadTimeout(60000);

      conn.setRequestProperty("accept", "*/*");
      conn.setRequestProperty("connection", "Keep-Alive");
      conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");


      conn.setDoOutput(true);
      conn.setDoInput(true);

      out = new PrintWriter(conn.getOutputStream());

      out.print(param);

      out.flush();

      in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));

      String line;
      while ((line = in.readLine()) != null) {
        result = result + line;
      }
    } catch (Exception e) {
      System.out.println("发送 POST 请求出现异常！" + e);
      e.printStackTrace();
    } finally {


      try {
        if (out != null) {
          out.close();
        }
        if (in != null) {
          in.close();
        }
      }
      catch (IOException ex) {
        ex.printStackTrace();
      }
    }
    return result;
  }




  public static String createLinkString(Map<String, String> params) {
    List<String> keys = new ArrayList<>(params.keySet());


    String prestr = "";

    for (int i = 0; i < keys.size(); i++) {
      String key = keys.get(i);
      Object value = params.get(key);

      if (i == keys.size() - 1) {
        prestr = prestr + key + "=" + value;
      } else {
        prestr = prestr + key + "=" + value + "&";
      }
    }

    return prestr.trim();
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com\rogran\\util\HttpClientUtil.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */