package com.rogrand.auto.domain;

import com.rogrand.core.domain.SerializeCloneable;
import com.rogrand.core.util.StringUtil;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.List;


public class Table
  extends SerializeCloneable
{
  private static final long serialVersionUID = -7703109005640698128L;
  private Integer id;
  private String name;
  private String annotation;
  private List<Column> columnList;
  private Boolean buildCURD;
  private String subjectModuleName;
  private String pageType;
  private String multiple;
  private String paging;
  private List<RelevanceTable> relevanceTables;
  private EditTable editTable;

  public String getClassDomain() {
    int index = this.name.indexOf("_");
    String className = (index != -1) ? this.name.substring(1 + index) : this.name;
    String[] arrStr = className.split("_");
    className = "";
    for (String s : arrStr) {
      className = className + StringUtil.upperFirstChar(s.toLowerCase());
    }
    return className;
  }

  public String getClassController() {
    return getClassDomain() + "Controller";
  }

  public String getClassService() {
    return getClassDomain() + "Service";
  }

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAnnotation() {
    return this.annotation;
  }

  public void setAnnotation(String annotation) {
    this.annotation = annotation;
  }

  public List<Column> getColumnList() {
    return this.columnList;
  }

  public void setColumnList(List<Column> columnList) {
    this.columnList = columnList;
  }

  public Boolean getBuildCURD() {
    return this.buildCURD;
  }

  public void setBuildCURD(Boolean buildCURD) {
    this.buildCURD = buildCURD;
  }

  public String getSubjectModuleName() {
    return this.subjectModuleName;
  }

  public void setSubjectModuleName(String subjectModuleName) {
    this.subjectModuleName = subjectModuleName;
  }

  public String getPageType() {
    return this.pageType;
  }

  public void setPageType(String pageType) {
    this.pageType = pageType;
  }

  public String getMultiple() {
    return this.multiple;
  }

  public void setMultiple(String multiple) {
    this.multiple = multiple;
  }

  public String getPaging() {
    return this.paging;
  }

  public void setPaging(String paging) {
    this.paging = paging;
  }

  public List<RelevanceTable> getRelevanceTables() {
    return this.relevanceTables;
  }

  public void setRelevanceTables(List<RelevanceTable> relevanceTables) {
    this.relevanceTables = relevanceTables;
  }

  public EditTable getEditTable() {
    return this.editTable;
  }

  public void setEditTable(EditTable editTable) {
    this.editTable = editTable;
  }






  public String getModuleName() {
    return "";
  }

  @JsonIgnore(true)
  public String getPackageDomain() {
    String packageName = "com.rogrand";
    if (!StringUtil.isEmpty(getModuleName())) packageName = packageName + "." + getModuleName();
    if (!StringUtil.isEmpty(getSubjectModuleName())) packageName = packageName + "." + getSubjectModuleName();
    packageName = packageName + ".domain";
    return packageName;
  }

  @JsonIgnore(true)
  public String getPackageController() {
    String packageName = "com.rogrand";
    if (!StringUtil.isEmpty(getModuleName())) packageName = packageName + "." + getModuleName();
    if (!StringUtil.isEmpty(getSubjectModuleName())) packageName = packageName + "." + getSubjectModuleName();
    packageName = packageName + ".controller";
    return packageName;
  }

  @JsonIgnore(true)
  public String getPackageService() {
    String packageName = "com.rogrand";
    if (!StringUtil.isEmpty(getModuleName())) packageName = packageName + "." + getModuleName();
    if (!StringUtil.isEmpty(getSubjectModuleName())) packageName = packageName + "." + getSubjectModuleName();
    packageName = packageName + ".service";
    return packageName;
  }

  @JsonIgnore(true)
  public String getMappings() {
    String mappings = "";
    if (!StringUtil.isEmpty(getModuleName())) mappings = mappings + "/" + getModuleName();
    if (!StringUtil.isEmpty(getSubjectModuleName())) mappings = mappings + "/" + getSubjectModuleName();
    mappings = mappings + "/" + StringUtil.lowerFirstChar(getClassDomain()) + "/*.do";
    return mappings;
  }

  public String getPk() {
    if (this.columnList != null && this.columnList.size() > 0) {
      for (Column column : this.columnList) {
        if ("Y".equals(column.getPk())) return column.getName();
      }
      return ((Column)this.columnList.get(0)).getName();
    }

    return "";
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\auto\domain\Table.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */