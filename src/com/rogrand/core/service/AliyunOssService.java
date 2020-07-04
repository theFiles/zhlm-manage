package com.rogrand.core.service;

import com.aliyun.oss.OSSClient;
import com.rogrand.util.CommonUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;


@Service
public class AliyunOssService
{
  @Value("${aliyun.AccessKeyId}")
  private String AccessKeyId;
  @Value("${aliyun.AccessKeySecret}")
  private String AccessKeySecret;
  @Value("${aliyun.Bucket}")
  private String Bucket;
  @Value("${aliyun.cdn}")
  private String cdn;

  public String uploadFile(String filePaht, String key) {
    String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";

    OSSClient ossClient = new OSSClient(endpoint, this.AccessKeyId, this.AccessKeySecret);

    ossClient.putObject(this.Bucket, key, new File(filePaht));

    ossClient.shutdown();
    return key;
  }








  public String uploadByest(byte[] bytes, String key) {
    String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";

    OSSClient ossClient = new OSSClient(endpoint, this.AccessKeyId, this.AccessKeySecret);

    ossClient.putObject(this.Bucket, key, new ByteArrayInputStream(bytes));

    ossClient.shutdown();
    return key;
  }








  public String uploadStream(InputStream inputStream, String key) {
    String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";

    OSSClient ossClient = new OSSClient(endpoint, this.AccessKeyId, this.AccessKeySecret);

    ossClient.putObject(this.Bucket, key, inputStream);
    return key;
  }








  public String uploadMultipartFile(MultipartFile multipartFile, String type) throws IOException {
    String fileName = multipartFile.getOriginalFilename();
    String suffix = StringUtils.substringAfterLast(fileName, ".");
    if (StringUtils.isEmpty(suffix)) {
      suffix = "jpg";
    }
    String key = type + "/" + CommonUtils.creatOrderNum() + "." + suffix;
    uploadByest(multipartFile.getBytes(), key);
    return key;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\service\AliyunOssService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */