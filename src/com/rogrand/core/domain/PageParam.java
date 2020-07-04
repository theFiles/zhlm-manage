package com.rogrand.core.domain;

import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.List;


public class PageParam
  extends HashMap<String, Object>
{
  private static final long serialVersionUID = 2940669879993201874L;
  public List<String> propertyList;
  public String[] searchContents;

  public List<String> getPropertyList() {
    return this.propertyList;
  }

  public void setPropertyList(List<String> propertyList) {
    this.propertyList = propertyList;
  }

  public String getCountSql() {
    return (String)get("countSql");
  }

  public void setCountSql(String countSql) {
    put("countSql", countSql);
  }

  public String getRecordSql() {
    return (String)get("recordSql");
  }

  public void setRecordSql(String recordSql) {
    put("recordSql", recordSql);
  }

  public String getDefaultSort() {
    return (String)get("defaultSort");
  }

  public void setDefaultSort(String defaultSort) {
    put("defaultSort", defaultSort);
  }

  public String getSort() {
    return (String)get("sort");
  }

  public void setSort(String sort) {
    put("sort", sort);
  }

  public String getOrder() {
    return (String)get("order");
  }

  public void setOrder(String order) {
    put("order", order);
  }

  public String getHead() {
    return (String)get("head");
  }

  public void setHead(String head) {
    put("head", head);
  }

  public String getFoot() {
    return (String)get("foot");
  }

  public void setFoot(String foot) {
    put("foot", foot);
  }

  public String getSortOrder() {
    return (String)get("sortOrder");
  }

  public void setSortOrder(String sortOrder) {
    put("sortOrder", sortOrder);
  }

  public String getRefresh() {
    return (String)get("refresh");
  }

  public void setRefresh(String refresh) {
    put("refresh", refresh);
  }

  public void setTime(String time) {
    put("time", time);
  }

  public String getTime() {
    return (String)get("time");
  }

  public Long getQueryTime() {
    Object queryTime = get("queryTime");
    if (queryTime == null) return Long.valueOf(0L);
    try {
      return Long.valueOf(Long.parseLong(queryTime.toString()));
    } catch (NumberFormatException e) {
      return Long.valueOf(0L);
    }
  }

  public void setQueryTime(Long queryTime) {
    put("queryTime", queryTime);
  }

  public Long getPage() {
    String page = (String)get("page");
    if (StringUtils.isNotEmpty(page)) {
      return Long.valueOf(Long.parseLong(page));
    }
    return null;
  }


  public void setPage(Long page) {
    put("page", page);
  }

  public Integer getRows() {
    String rows = (String)get("rows");
    if (rows != null) {
      return Integer.valueOf(Integer.parseInt(rows));
    }
    return Integer.valueOf(20);
  }


  public void setRows(Integer rows) {
    put("rows", rows);
  }

  public Long getRecordCount() {
    Object count = get("recordCount");
    if (count == null) return Long.valueOf(0L);
    try {
      return Long.valueOf(Long.parseLong(count.toString()));
    } catch (NumberFormatException e) {
      return Long.valueOf(0L);
    }
  }

  public void setRecordCount(Long recordCount) {
    put("recordCount", recordCount);
  }

  public Long getTotalPage() {
    Long count = getRecordCount();
    Integer rows = getRows();
    return Long.valueOf((count.longValue() > 0L && rows.intValue() > 0) ? (int)Math.ceil(count.longValue() / rows.intValue()) : 0L);
  }

  public void setTotalPage(Long totalPage) {
    put("totalPage", totalPage);
  }

  public Long getBegin() {
    if (getRecordCount().longValue() == 0L) return Long.valueOf(0L);
    return Long.valueOf(1L + (getPage().longValue() - 1L) * getRows().intValue());
  }

  public void setBegin(Long begin) {
    put("begin", begin);
  }

  public Long getEnd() {
    if (getRecordCount().longValue() == 0L) return Long.valueOf(0L);
    Long end = Long.valueOf(getPage().longValue() * getRows().intValue());
    return (end.longValue() > getRecordCount().longValue()) ? getRecordCount() : end;
  }

  public void setEnd(Long end) {
    put("end", end);
  }

  public void reset() {
    setRows(getRows());
    setPage(getPage());
    setRecordCount(getRecordCount());
    setTotalPage(getTotalPage());
    setBegin(getBegin());
    setEnd(getEnd());
    setQueryTime(getQueryTime());

    if (getSort() != null && getSort().length() > 0 && (
      getOrder() == null || (!getOrder().equals("asc") && !getOrder().equals("desc")))) {
      setOrder("asc");
    }
  }





  public String[] getSearchContents() {
    return this.searchContents;
  }

  public void setSearchContents(String[] searchContents) {
    put("searchContents", searchContents);
    this.searchContents = searchContents;
  }

  public int getTotalPageIndex() {
    return (getRecordCount().longValue() > 0L && getRows().intValue() > 0) ? (int)Math.ceil(getRecordCount().longValue() / getRows().intValue()) : 0;
  }

  public void setTotalPageIndex(Integer totalPageIndex) {
    put("totalPageIndex", totalPageIndex);
  }

  public Long getStartPage() {
    if (getRecordCount().longValue() == 0L) return Long.valueOf(0L);
    if (getPage().longValue() - 10L > 0L) return Long.valueOf(getPage().longValue() - 10L);
    return Long.valueOf(1L);
  }

  public void setStartPage(Long startPage) {
    put("startPage", startPage);
  }

  public Long getEndPage() {
    if (getRecordCount().longValue() == 0L) return Long.valueOf(0L);
    if (getPage().longValue() + 9L > getTotalPage().longValue()) return getTotalPage();
    return Long.valueOf(getPage().longValue() + 9L);
  }

  public void setEndPage(Long endPage) {
    put("endPage", endPage);
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\domain\PageParam.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */