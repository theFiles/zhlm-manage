package com.rogrand.auto.controller;

import com.rogrand.auto.domain.Column;
import com.rogrand.auto.domain.Database;
import com.rogrand.auto.domain.Table;
import com.rogrand.auto.service.BuildService;
import com.rogrand.core.controller.BaseController;
import com.rogrand.core.enums.ResponseCode;
import com.rogrand.core.util.BeanUtil;
import com.rogrand.core.util.StringUtil;
import freemarker.ext.beans.BeansWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;


@Controller("buildController")
@RequestMapping({"/auto/*.do"})
public class BuildController
  extends BaseController
{
  private String tempDir = "/WEB-INF/template";
  private String outDir = "/autoBuild";



  @Autowired
  @Qualifier("freeMarkerConfigurer")
  private FreeMarkerConfigurer freeMarkerConfigurer;



  @Autowired
  @Qualifier("buildService")
  private BuildService buildService;




  public void tableList(HttpServletRequest request, HttpServletResponse response) throws Exception {
    Map<String, Object> model = new HashMap<>();
    Database database = this.buildService.getStructure();
    responseJson(response, ResponseCode.SUCCESS, database);
  }









  public void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
    LinkedHashMap<String, String> map = (LinkedHashMap<String, String>)BeanUtil.wrapBean(LinkedHashMap.class, request.getParameter("param"));
    File file = new File(getServletContext().getRealPath(this.tempDir + "/comments.properties"));
    PropertiesConfiguration configuration = new PropertiesConfiguration();
    FileOutputStream outputStream = new FileOutputStream(file);
    for (Map.Entry<String, String> entry : map.entrySet()) {
      configuration.addProperty(entry.getKey(), entry.getValue());
    }
    configuration.save(outputStream, "utf-8");
    responseJson(response, ResponseCode.SUCCESS, null);
  }









  public void create(HttpServletRequest request, HttpServletResponse response) throws Exception {
    List<Table> tableList = BeanUtil.wrapBeanList(Table.class, request.getParameter("tableList"));
    for (Table table : tableList) {
      createDomain(table);
      createSqlmap(table);
      if (table.getBuildCURD() != null && table.getBuildCURD().booleanValue()) {
        createController(table);
        createService(table);
        createView(table);
      }
    }
    responseJson(response, ResponseCode.SUCCESS, null);
  }

  private void createSqlmap(Table table) throws IOException, TemplateException {
    Map<String, Object> model = new HashMap<>();
    boolean hasBlob = false;
    for (Column column : table.getColumnList()) {
      if (column.getIbatisType().equals("BLOB")) {
        hasBlob = true;
        break;
      }
    }
    model.put("now", new Date());
    model.put("hasBlob", Boolean.valueOf(hasBlob));
    model.put("table", table);
    model.put("statics", BeansWrapper.getDefaultInstance().getStaticModels());
    Template template = this.freeMarkerConfigurer.getConfiguration().getTemplate(this.tempDir + "/ibatis_sqlmap.ftl", "utf-8");
    String temp = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
    String dir = this.outDir + "/src/" + table.getPackageDomain().replaceAll("\\.", "/") + "/" + table.getClassDomain() + ".xml";
    File file = new File(getServletContext().getRealPath(dir));
    FileUtils.writeStringToFile(file, temp, "utf-8");
  }

  private void createController(Table table) throws IOException, TemplateException {
    Map<String, Object> model = new HashMap<>();
    model.put("now", new Date());
    model.put("table", table);
    model.put("statics", BeansWrapper.getDefaultInstance().getStaticModels());
    Template template = this.freeMarkerConfigurer.getConfiguration().getTemplate(this.tempDir + "/java_controller.ftl", "utf-8");
    String temp = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
    String dir = this.outDir + "/src/" + table.getPackageController().replaceAll("\\.", "/") + "/" + table.getClassController() + ".java";
    File file = new File(getServletContext().getRealPath(dir));
    FileUtils.writeStringToFile(file, temp, "utf-8");
  }

  private void createDomain(Table table) throws IOException, TemplateException {
    Map<String, Object> model = new HashMap<>();
    model.put("now", new Date());
    model.put("table", table);
    model.put("statics", BeansWrapper.getDefaultInstance().getStaticModels());
    Template template = this.freeMarkerConfigurer.getConfiguration().getTemplate(this.tempDir + "/java_domain.ftl", "utf-8");
    String temp = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
    String dir = this.outDir + "/src/" + table.getPackageDomain().replaceAll("\\.", "/") + "/" + table.getClassDomain() + ".java";
    File file = new File(getServletContext().getRealPath(dir));
    FileUtils.writeStringToFile(file, temp, "utf-8");
  }

  private void createService(Table table) throws IOException, TemplateException {
    Map<String, Object> model = new HashMap<>();
    Template template = this.freeMarkerConfigurer.getConfiguration().getTemplate(this.tempDir + "/java_service.ftl", "utf-8");
    model.put("now", new Date());
    model.put("table", table);
    model.put("statics", BeansWrapper.getDefaultInstance().getStaticModels());
    String temp = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
    String dir = this.outDir + "/src/" + table.getPackageService().replaceAll("\\.", "/") + "/" + table.getClassService() + ".java";
    File file = new File(getServletContext().getRealPath(dir));
    FileUtils.writeStringToFile(file, temp, "utf-8");
  }

  private void createView(Table table) throws IOException, TemplateException {
    Map<String, Object> model = new HashMap<>();
    model.put("now", new Date());
    model.put("table", table);
    model.put("statics", BeansWrapper.getDefaultInstance().getStaticModels());
    String dir = this.outDir + "/page";
    if (!StringUtil.isEmpty(table.getModuleName())) dir = dir + "/" + table.getModuleName();
    if (!StringUtil.isEmpty(table.getSubjectModuleName())) dir = dir + "/" + table.getSubjectModuleName();
    String name = StringUtil.lowerFirstChar(table.getClassDomain());


    String view = dir + "/" + name + ".vue";
    Template template = this.freeMarkerConfigurer.getConfiguration().getTemplate(this.tempDir + "/view_main.ftl", "utf-8");
    String temp = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
    File file = new File(getServletContext().getRealPath(view));
    FileUtils.writeStringToFile(file, temp, "utf-8");


    if ("1".equals(table.getPageType())) {
      view = dir + "/" + name + "_modify.vue";
      template = this.freeMarkerConfigurer.getConfiguration().getTemplate(this.tempDir + "/view_modify.ftl", "utf-8");
      temp = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
      file = new File(getServletContext().getRealPath(view));
      FileUtils.writeStringToFile(file, temp, "utf-8");
    }
  }

  public static void main(String[] args) {}
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\auto\controller\BuildController.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */