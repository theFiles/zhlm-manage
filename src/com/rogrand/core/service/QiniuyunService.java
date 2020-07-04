package com.rogrand.core.service;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;


@Service
public class QiniuyunService
{
  @Value("${qiniu.ak}")
  private String accessKey;
  @Value("${qiniu.sk}")
  private String secretKey;
  @Value("${qiniu.bucket}")
  private String bucket;
  @Value("${qiniu.cdn}")
  private String cdn;

  public String uploadFile(String filePath, String key) {
    Configuration cfg = new Configuration(Zone.zone2());

    UploadManager uploadManager = new UploadManager(cfg);

    String localFilePath = filePath;

    Auth auth = Auth.create(this.accessKey, this.secretKey);
    String upToken = auth.uploadToken(this.bucket);
    try {
      Response response = uploadManager.put(localFilePath, key, upToken);

      DefaultPutRet putRet = (DefaultPutRet)(new Gson()).fromJson(response.bodyString(), DefaultPutRet.class);
      System.out.println(putRet.key);
      System.out.println(putRet.hash);
      key = putRet.key;
    } catch (QiniuException ex) {
      Response r = ex.response;
      System.err.println(r.toString());
      try {
        System.err.println(r.bodyString());
      } catch (QiniuException ex2) {}
    }


    return key;
  }


  public String uploadByest(byte[] bytes, String key) {
    Configuration cfg = new Configuration(Zone.zone2());

    UploadManager uploadManager = new UploadManager(cfg);

    try {
      Auth auth = Auth.create(this.accessKey, this.secretKey);
      String upToken = auth.uploadToken(this.bucket);
      try {
        Response response = uploadManager.put(bytes, key, upToken);

        DefaultPutRet putRet = (DefaultPutRet)(new Gson()).fromJson(response.bodyString(), DefaultPutRet.class);
        System.out.println(putRet.key);
        System.out.println(putRet.hash);
        key = putRet.key;
      } catch (QiniuException ex) {
        Response r = ex.response;
        System.err.println(r.toString());
        try {
          System.err.println(r.bodyString());
        } catch (QiniuException ex2) {}
      }

    }
    catch (Exception ex) {}


    return key;
  }


  public String uploadStream(InputStream inputStream, String key) {
    Configuration cfg = new Configuration(Zone.zone2());

    UploadManager uploadManager = new UploadManager(cfg);

    try {
      Auth auth = Auth.create(this.accessKey, this.secretKey);
      String upToken = auth.uploadToken(this.bucket);
      try {
        Response response = uploadManager.put(inputStream, key, upToken, null, null);

        DefaultPutRet putRet = (DefaultPutRet)(new Gson()).fromJson(response.bodyString(), DefaultPutRet.class);
        System.out.println(putRet.key);
        System.out.println(putRet.hash);
        key = putRet.key;
      } catch (QiniuException ex) {
        Response r = ex.response;
        System.err.println(r.toString());
        try {
          System.err.println(r.bodyString());
        } catch (QiniuException ex2) {}
      }

    }
    catch (Exception ex) {}


    return key;
  }

  public String convertImageUrl(String key) {
    if (StringUtils.isNotEmpty(key)) {
      return this.cdn + key;
    }
    return null;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\service\QiniuyunService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */