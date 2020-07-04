package com.rogrand.core.util;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeSocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class HttpClientUtil
{
  public static String sendGetRequest(String reqURL, String decodeCharset) {
    long responseLength = 0L;
    String responseContent = null;
    DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
    HttpGet httpGet = new HttpGet(reqURL);
    try {
      HttpResponse response = defaultHttpClient.execute((HttpUriRequest)httpGet);
      HttpEntity entity = response.getEntity();
      if (null != entity) {
        responseLength = entity.getContentLength();
        responseContent = EntityUtils.toString(entity, (decodeCharset == null) ? "UTF-8" : decodeCharset);
        EntityUtils.consume(entity);
      }
      System.out.println("请求地址: " + httpGet.getURI());
      System.out.println("响应状态: " + response.getStatusLine());
      System.out.println("响应长度: " + responseLength);
      System.out.println("响应内容: " + responseContent);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      defaultHttpClient.getConnectionManager().shutdown();
    }
    return responseContent;
  }









  public static String sendPostRequest(String reqURL, String sendData, boolean isEncoder) {
    return sendPostRequest(reqURL, sendData, isEncoder, null, null);
  }













  public static String sendPostRequest(String reqURL, String sendData, boolean isEncoder, String encodeCharset, String decodeCharset) {
    String responseContent = null;
    DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
    HttpPost httpPost = new HttpPost(reqURL);
    httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
    try {
      if (isEncoder) {
        List<NameValuePair> formParams = new ArrayList<>();
        for (String str : sendData.split("&")) {
          formParams.add(new BasicNameValuePair(str.substring(0, str.indexOf("=")), str.substring(str.indexOf("=") + 1)));
        }
        httpPost.setEntity((HttpEntity)new StringEntity(URLEncodedUtils.format(formParams, (encodeCharset == null) ? "UTF-8" : encodeCharset)));
      } else {
        httpPost.setEntity((HttpEntity)new StringEntity(sendData));
      }
      HttpResponse response = defaultHttpClient.execute((HttpUriRequest)httpPost);
      HttpEntity entity = response.getEntity();
      if (null != entity) {
        responseContent = EntityUtils.toString(entity, (decodeCharset == null) ? "UTF-8" : decodeCharset);
        EntityUtils.consume(entity);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      defaultHttpClient.getConnectionManager().shutdown();
    }
    return responseContent;
  }












  public static String sendPostRequest(String reqURL, Map<String, String> params, String encodeCharset, String decodeCharset) {
    System.out.println("请求地址：" + reqURL);
    System.out.println("请求参数：" + JSON.toJSONString(params));
    String responseContent = null;
    DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
    HttpPost httpPost = new HttpPost(reqURL);
    List<NameValuePair> formParams = new ArrayList<>();
    for (Map.Entry<String, String> entry : params.entrySet()) {
      formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
    }
    try {
      httpPost.setEntity((HttpEntity)new UrlEncodedFormEntity(formParams, (encodeCharset == null) ? "UTF-8" : encodeCharset));
      HttpResponse response = defaultHttpClient.execute((HttpUriRequest)httpPost);
      HttpEntity entity = response.getEntity();
      if (null != entity) {
        responseContent = EntityUtils.toString(entity, (decodeCharset == null) ? "UTF-8" : decodeCharset);
        EntityUtils.consume(entity);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      defaultHttpClient.getConnectionManager().shutdown();
    }
    System.out.println("返回结果：" + responseContent);
    return responseContent;
  }










  public static String sendPostRequestJson(String reqURL, String json, String encodeCharset, String decodeCharset) {
    String responseContent = null;
    DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
    HttpPost httpPost = new HttpPost(reqURL);

    httpPost.addHeader("Authorization", "your token");
    httpPost.addHeader("Content-Type", "application/json");
    httpPost.addHeader("User-Agent", "imgfornote");
    try {
      httpPost.setEntity((HttpEntity)new StringEntity(json, (encodeCharset == null) ? "UTF-8" : encodeCharset));
      HttpResponse response = defaultHttpClient.execute((HttpUriRequest)httpPost);
      HttpEntity entity = response.getEntity();
      if (null != entity) {
        responseContent = EntityUtils.toString(entity, (decodeCharset == null) ? "UTF-8" : decodeCharset);
        EntityUtils.consume(entity);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      defaultHttpClient.getConnectionManager().shutdown();
    }
    return responseContent;
  }








  public static String sendPostSSLRequest(String reqURL, Map<String, String> params) {
    return sendPostSSLRequest(reqURL, params, null, null);
  }












  public static String sendPostSSLRequest(String reqURL, Map<String, String> params, String encodeCharset, String decodeCharset) {
    String responseContent = "";
    DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
    X509TrustManager xtm = new X509TrustManager() {
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {}

        public X509Certificate[] getAcceptedIssuers() { return null; } public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
      };
    try {
      SSLContext ctx = SSLContext.getInstance("TLS");
      ctx.init(null, new TrustManager[] { xtm }, null);
      SSLSocketFactory socketFactory = new SSLSocketFactory(ctx);
      defaultHttpClient.getConnectionManager().getSchemeRegistry().register(new Scheme("https", 443, (SchemeSocketFactory)socketFactory));
      HttpPost httpPost = new HttpPost(reqURL);
      List<NameValuePair> formParams = new ArrayList<>();
      for (Map.Entry<String, String> entry : params.entrySet()) {
        formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
      }
      httpPost.setEntity((HttpEntity)new UrlEncodedFormEntity(formParams, (encodeCharset == null) ? "UTF-8" : encodeCharset));
      HttpResponse response = defaultHttpClient.execute((HttpUriRequest)httpPost);
      HttpEntity entity = response.getEntity();
      if (null != entity) {
        responseContent = EntityUtils.toString(entity, (decodeCharset == null) ? "UTF-8" : decodeCharset);
        EntityUtils.consume(entity);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      defaultHttpClient.getConnectionManager().shutdown();
    }
    return responseContent;
  }










  public static String sendPostRequestByJava(String reqURL, Map<String, String> params) {
    StringBuilder sendData = new StringBuilder();
    for (Map.Entry<String, String> entry : params.entrySet()) {
      sendData.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
    }
    if (sendData.length() > 0) {
      sendData.setLength(sendData.length() - 1);
    }
    return sendPostRequestByJava(reqURL, sendData.toString());
  }










  public static String sendPostRequestByJava(String reqURL, String sendData) {
    HttpURLConnection httpURLConnection = null;
    OutputStream out = null;
    InputStream in = null;
    int httpStatusCode = 0;
    try {
      URL sendUrl = new URL(reqURL);
      httpURLConnection = (HttpURLConnection)sendUrl.openConnection();
      httpURLConnection.setRequestMethod("POST");
      httpURLConnection.setDoOutput(true);
      httpURLConnection.setUseCaches(false);
      httpURLConnection.setConnectTimeout(30000);
      httpURLConnection.setReadTimeout(30000);
      out = httpURLConnection.getOutputStream();
      out.write(sendData.toString().getBytes());

      out.flush();

      httpStatusCode = httpURLConnection.getResponseCode();


      in = httpURLConnection.getInputStream();
      byte[] byteDatas = new byte[in.available()];
      in.read(byteDatas);
      return new String(byteDatas) + "`" + httpStatusCode;
    } catch (Exception e) {
      e.printStackTrace();
      return "Failed`" + httpStatusCode;
    } finally {
      if (out != null) {
        try {
          out.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
      if (in != null) {
        try {
          in.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
      if (httpURLConnection != null) {
        httpURLConnection.disconnect();
        httpURLConnection = null;
      }
    }
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\cor\\util\HttpClientUtil.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */