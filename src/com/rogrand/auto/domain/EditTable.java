package com.rogrand.auto.domain;

import com.rogrand.core.util.StringUtil;

import java.util.List;


public class EditTable
{
  private String table;
  private String column;
  private List<Column> columnList;

  public String getTable() {
    return this.table;
  }

  public void setTable(String table) {
    this.table = table;
  }

  public String getColumn() {
    return this.column;
  }

  public void setColumn(String column) {
    this.column = column;
  }

  public List<Column> getColumnList() {
    return this.columnList;
  }

  public void setColumnList(List<Column> columnList) {
    this.columnList = columnList;
  }

  public String getClassDomain() {
    int index = this.table.indexOf("_");
    String className = (index != -1) ? this.table.substring(1 + index) : this.table;
    String[] arrStr = className.split("_");
    className = "";
    for (String s : arrStr) {
      className = className + StringUtil.upperFirstChar(s.toLowerCase());
    }
    return className;
  }

  public String getClassService() {
    return getClassDomain() + "Service";
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\auto\domain\EditTable.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */