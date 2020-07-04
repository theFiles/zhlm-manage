package com.rogrand.auto.domain;

import com.rogrand.core.domain.SerializeCloneable;

import java.util.Map;


public class Column
  extends SerializeCloneable
{
  private static final long serialVersionUID = 4761199922482795070L;
  private String name;
  private String annotation;
  private String jdbcType;
  private String nullAble;
  private String pk;
  private Integer length;
  private Integer scale;
  private String type;
  private Map<String, Object> params;
  private String search;
  private String fix;

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

  public String getJdbcType() {
    return this.jdbcType;
  }

  public void setJdbcType(String jdbcType) {
    this.jdbcType = jdbcType;
  }

  public String getJavaType() {
    String ibatisType = getIbatisType();
    if (ibatisType == null) return "String";
    if (ibatisType.equals("VARCHAR")) return "String";
    if (ibatisType.equals("TEXT")) return "String";
    if (ibatisType.equals("CLOB")) return "String";
    if (ibatisType.equals("NUMERIC")) return (this.scale == null || this.scale.intValue() == 0) ? ((this.length.intValue() < 10) ? "Integer" : "Long") : "Double";
    if (ibatisType.equals("TIMESTAMP")) return "Date";
    if (ibatisType.equals("BLOB")) return "byte[]";
    return "String";
  }

  public String getIbatisType() {
    if (this.jdbcType == null) return null;
    if (this.jdbcType.equalsIgnoreCase("CHAR")) return "VARCHAR";
    if (this.jdbcType.equalsIgnoreCase("NCHAR")) return "VARCHAR";
    if (this.jdbcType.equalsIgnoreCase("VARCHAR")) return "VARCHAR";
    if (this.jdbcType.equalsIgnoreCase("VARCHAR2")) return "VARCHAR";
    if (this.jdbcType.equalsIgnoreCase("NVARCHAR2")) return "VARCHAR";

    if (this.jdbcType.equalsIgnoreCase("NUMBER")) return "NUMERIC";
    if (this.jdbcType.equalsIgnoreCase("NUMERIC")) return "NUMERIC";
    if (this.jdbcType.equalsIgnoreCase("DECIMAL")) return "NUMERIC";
    if (this.jdbcType.equalsIgnoreCase("DEC")) return "NUMERIC";
    if (this.jdbcType.equalsIgnoreCase("MONEY")) return "NUMERIC";
    if (this.jdbcType.equalsIgnoreCase("FLOAT")) return "NUMERIC";
    if (this.jdbcType.equalsIgnoreCase("DOUBLE")) return "NUMERIC";
    if (this.jdbcType.equalsIgnoreCase("REAL")) return "NUMERIC";
    if (this.jdbcType.equalsIgnoreCase("INT")) return "NUMERIC";
    if (this.jdbcType.equalsIgnoreCase("INTEGER")) return "NUMERIC";
    if (this.jdbcType.equalsIgnoreCase("BIGINT")) return "NUMERIC";
    if (this.jdbcType.equalsIgnoreCase("TINYINT")) return "NUMERIC";
    if (this.jdbcType.equalsIgnoreCase("SMALLINT")) return "NUMERIC";

    if (this.jdbcType.equalsIgnoreCase("DATE")) return "TIMESTAMP";
    if (this.jdbcType.equalsIgnoreCase("DATETIME")) return "TIMESTAMP";
    if (this.jdbcType.equalsIgnoreCase("TIME")) return "TIMESTAMP";
    if (this.jdbcType.equalsIgnoreCase("TIMESTAMP")) return "TIMESTAMP";
    if (this.jdbcType.equalsIgnoreCase("TIMESTAMP(6)")) return "TIMESTAMP";

    if (this.jdbcType.equalsIgnoreCase("TEXT")) return "TEXT";
    if (this.jdbcType.equalsIgnoreCase("LONGVARCHAR")) return "TEXT";
    if (this.jdbcType.equalsIgnoreCase("CLOB")) return "CLOB";

    if (this.jdbcType.equalsIgnoreCase("LONGVARBINARY")) return "BLOB";
    if (this.jdbcType.equalsIgnoreCase("BLOB")) return "BLOB";
    if (this.jdbcType.equalsIgnoreCase("IMAGE")) return "BLOB";

    return "VARCHAR";
  }











  public String getNullAble() {
    return this.nullAble;
  }

  public void setNullAble(String nullAble) {
    this.nullAble = nullAble;
  }

  public String getPk() {
    return this.pk;
  }

  public void setPk(String pk) {
    this.pk = pk;
  }

  public Integer getLength() {
    return this.length;
  }

  public void setLength(Integer length) {
    this.length = length;
  }

  public Integer getScale() {
    return this.scale;
  }

  public void setScale(Integer scale) {
    this.scale = scale;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Map<String, Object> getParams() {
    return this.params;
  }

  public void setParams(Map<String, Object> params) {
    this.params = params;
  }

  public String getSearch() {
    return this.search;
  }

  public void setSearch(String search) {
    this.search = search;
  }

  public String getFix() {
    return this.fix;
  }

  public void setFix(String fix) {
    this.fix = fix;
  }

  public String getMax() {
    String n = "";
    if (getJavaType().equals("Integer") || getJavaType().equals("Long")) {
      for (int i = 0; i < this.length.intValue(); ) { n = n + "9"; i++; }

    } else if (getJavaType().equals("Double")) {
      int i; for (i = 0; i < this.length.intValue() - this.scale.intValue(); ) { n = n + "9"; i++; }
       n = n + ".";
      for (i = 0; i < this.scale.intValue(); ) { n = n + "9"; i++; }

    }  return n;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\auto\domain\Column.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */