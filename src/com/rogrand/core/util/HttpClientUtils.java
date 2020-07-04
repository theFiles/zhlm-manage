package com.rogrand.core.util;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class HttpClientUtils
{
  public static String sendPost(String url, Map<String, String> map, Map<String, String> headers, String encoding) throws ParseException, IOException {
    CloseableHttpClient client = HttpClients.createDefault();

    HttpPost httpPost = new HttpPost(url);

    String respContent = "";


    List<NameValuePair> nvps = new ArrayList<>();
    if (map != null) {
      for (Map.Entry<String, String> entry : map.entrySet()) {
        nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
      }
    }

    httpPost.setEntity((HttpEntity)new UrlEncodedFormEntity(nvps, encoding));

    System.out.println("请求地址：" + url);
    System.out.println("请求参数：" + nvps.toString());



    httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
    httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
    if (headers != null) {
      for (Map.Entry<String, String> entry : headers.entrySet()) {
        httpPost.setHeader(entry.getKey(), entry.getValue());
      }
    }


    CloseableHttpResponse httpResponse = client.execute((HttpUriRequest)httpPost);

    if (httpResponse.getStatusLine().getStatusCode() == 200) {
      HttpEntity httpEntity = httpResponse.getEntity();
      respContent = EntityUtils.toString(httpEntity, encoding);
      EntityUtils.consume(httpEntity);
    }


    httpResponse.close();

    System.out.print("请求结果：" + respContent);
    return respContent;
  }







  public static String sendPost(String url, String jsonParam) throws Exception {
    return sendPost(url, jsonParam, (Map<String, String>)null, "utf-8");
  }










  public static String sendPost(String url, String jsonParam, Map<String, String> headers, String charset) throws Exception {
    System.out.println("请求地址：" + url);
    System.out.println("请求参数：" + jsonParam);

    CloseableHttpClient client = HttpClients.createDefault();
    HttpPost httpPost = new HttpPost(url);
    String respContent = null;

    StringEntity entity = new StringEntity(jsonParam, charset);
    entity.setContentEncoding(charset);
    entity.setContentType("application/json");
    httpPost.setEntity((HttpEntity)entity);


    if (headers != null) {
      for (Map.Entry<String, String> entry : headers.entrySet()) {
        httpPost.setHeader(entry.getKey(), entry.getValue());
      }
    }


    CloseableHttpResponse closeableHttpResponse = client.execute((HttpUriRequest)httpPost);

    if (closeableHttpResponse.getStatusLine().getStatusCode() == 200) {
      HttpEntity httpEntity = closeableHttpResponse.getEntity();
      respContent = EntityUtils.toString(httpEntity, charset);
      EntityUtils.consume(httpEntity);
    }

    System.out.print("请求结果：" + respContent);
    return respContent;
  }










  public static String sendPost(String url, byte[] bytes, Map<String, String> headers, String charset) throws Exception {
    System.out.println("请求地址：" + url);

    CloseableHttpClient client = HttpClients.createDefault();
    HttpPost httpPost = new HttpPost(url);
    String respContent = null;

    ByteArrayEntity entity = new ByteArrayEntity(bytes);
    entity.setContentEncoding(charset);
    httpPost.setEntity((HttpEntity)entity);


    if (headers != null) {
      for (Map.Entry<String, String> entry : headers.entrySet()) {
        httpPost.setHeader(entry.getKey(), entry.getValue());
      }
    }


    CloseableHttpResponse closeableHttpResponse = client.execute((HttpUriRequest)httpPost);

    if (closeableHttpResponse.getStatusLine().getStatusCode() == 200) {
      HttpEntity httpEntity = closeableHttpResponse.getEntity();
      respContent = EntityUtils.toString(httpEntity, charset);
      EntityUtils.consume(httpEntity);
    }

    System.out.print("请求结果：" + respContent);
    return respContent;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\cor\\util\HttpClientUtils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */