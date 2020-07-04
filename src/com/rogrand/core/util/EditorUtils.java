package com.rogrand.core.util;

import com.rogrand.core.service.MongoDbService;
import com.rogrand.core.system.SystemParameter;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;


public class EditorUtils
{
  private static final Logger logger = Logger.getLogger(EditorUtils.class);











  public static String convertImageUrl(HttpServletRequest request, String imageBaseUrl, String html) {
    if (StringUtils.isNotEmpty(html)) {
      Document doc = Jsoup.parse(html);
      Elements imgs = doc.getElementsByTag("img");
      for (Element img : imgs) {
        String src = img.attr("src");

        if (StringUtils.isNotEmpty(src) && src.startsWith("/")) {

          src = src.substring(src.indexOf("ueditor") - 1);
          String realPath = request.getSession().getServletContext().getRealPath(src);
          FtpUploader.upload(FtpConfig.getIp(), FtpConfig.getPort(), FtpConfig.getUsername(), FtpConfig.getPassword(), new File(realPath), FilenameUtils.getName(src), "/kkmy/upload/" + FilenameUtils.getPath(src));


          img.attr("src", imageBaseUrl + src);


          FileUtils.deleteQuietly(new File(realPath));
        }
      }
      return doc.select("body").html();
    }
    return null;
  }










  public static String convertImageUrl(HttpServletRequest request, String html) {
    String imageBaseUrl = SystemParameter.get("remoteImageUploadPath") + "kkmy/upload/";
    return convertImageUrl(request, imageBaseUrl, html);
  }

  public static void main(String[] args) {
    FileUtils.deleteQuietly(new File("C:\\Users\\Administrator\\Desktop\\test\\activeHtml.zip"));
  }













  public static String convertImageByMongoDb(HttpServletRequest request, String html, MongoDbService mongoDbService) throws IOException {
    if (StringUtils.isNotEmpty(html)) {
      String imageBaseUrl = SystemParameter.get("mongoDbUploadPath");
      Document doc = Jsoup.parse(html);
      Elements imgs = doc.getElementsByTag("img");
      for (Element img : imgs) {
        String src = img.attr("src");

        if (StringUtils.isNotEmpty(src) && src.startsWith("/")) {

          src = src.substring(src.indexOf("ueditor") - 1);
          String extName = "." + StringUtils.substringAfterLast(src, ".");
          String realPath = request.getSession().getServletContext().getRealPath(src);
          String objId = mongoDbService.uploadFile(realPath).toString();
          img.attr("src", imageBaseUrl + objId + extName);


          FileUtils.deleteQuietly(new File(realPath));
        }
      }
      return doc.select("body").html();
    }
    return null;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\cor\\util\EditorUtils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */