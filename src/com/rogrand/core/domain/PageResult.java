package com.rogrand.core.domain;

import java.util.List;


public class PageResult
{
  private List<Object> rows;
  private Long total;

  public List<Object> getRows() {
    return this.rows;
  }

  public void setRows(List<Object> rows) {
    this.rows = rows;
  }

  public Long getTotal() {
    return this.total;
  }

  public void setTotal(Long total) {
    this.total = total;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\domain\PageResult.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */