package com.rogrand.core.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class GeneratorHtml
{
  private Configuration config = null;

  public static GeneratorHtml instance() {
    GeneratorHtml generatorHtml = new GeneratorHtml();
    return generatorHtml;
  }






  private boolean creatDirs(String path) {
    File aFile = new File(path);
    if (!aFile.exists()) {
      return aFile.mkdirs();
    }
    return true;
  }













  public void geneHtmlFile(String templateFileName, String templateFilePath, Map contextMap, String htmlFilePath, String htmlFileName) {
    try {
      Template t = getFreeMarkerCFG(templateFilePath).getTemplate(templateFileName);

      creatDirs(htmlFilePath);
      File afile = new File(htmlFilePath + "/" + htmlFileName);
      Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(afile)));

      t.process(contextMap, out);
      out.flush();
      out.close();
    } catch (TemplateException e) {
      System.out.print(e.getMessage());
    } catch (IOException e) {
      System.out.print(e.getMessage());
    } catch (Exception e) {
      System.out.print(e.getMessage());
    }
  }











  private Configuration getFreeMarkerCFG(String templateFilePath) throws Exception {
    if (null == this.config) {

      this.config = new Configuration();
      try {
        this.config.setDirectoryForTemplateLoading(new File(templateFilePath));
      }
      catch (Exception ex) {
        throw ex;
      }
    }
    return this.config;
  }


  public static void main(String[] args) {
    GeneratorHtml generatorHtml = new GeneratorHtml();
    Map<String, Object> contextMap = new HashMap<>();
    contextMap.put("title", "asd");
    contextMap.put("shareImageUrl", "aaaaa");
    contextMap.put("contentText", "bbbb");
    contextMap.put("title", "asd");
    generatorHtml.geneHtmlFile("activity_html.ftl", "/WEB-INF/view/activeinfo/", contextMap, "/template", "bb.html");
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\cor\\util\GeneratorHtml.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */