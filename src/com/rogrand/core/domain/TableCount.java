package com.rogrand.core.domain;








public class TableCount
{
  private String table_name;
  private Integer record_count;

  public TableCount() {}

  public TableCount(String table_name, Integer record_count) {
    this.table_name = table_name;
    this.record_count = record_count;
  }

  public String getTable_name() {
    return this.table_name;
  }

  public void setTable_name(String table_name) {
    this.table_name = table_name;
  }

  public Integer getRecord_count() {
    return this.record_count;
  }

  public void setRecord_count(Integer record_count) {
    this.record_count = record_count;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\domain\TableCount.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */