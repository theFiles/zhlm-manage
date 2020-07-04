package com.rogrand.core.service;

import com.rogrand.core.domain.PageParam;
import com.rogrand.core.domain.PageResult;
import com.rogrand.core.util.BeanUtil;
import com.rogrand.core.util.StringUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.Map;


public abstract class AbstractPageService
{
  protected Log logger = LogFactory.getLog(getClass());








  public boolean isTableTotal(PageParam pageParam) {
    String refresh = pageParam.getRefresh();
    if (refresh != null && (refresh.equals("1") || refresh.equals("2"))) {

      Map<String, Object> params = new HashMap<>();
      params.putAll((Map<? extends String, ?>)pageParam);

      return BeanUtil.isBlank(params);
    }

    return false;
  }









  protected String getSortAlias(Map sortAlias, String sort) {
    return (sortAlias == null || !sortAlias.containsKey(sort)) ? sort : (String)sortAlias.get(sort);
  }








  protected boolean repeatSort(String columnSort, String defaultSort) {
    return (!StringUtil.isEmpty(columnSort) && !StringUtil.isEmpty(defaultSort) && defaultSort.indexOf(columnSort) != -1);
  }

  public abstract PageResult pageQuery(PageParam paramPageParam) throws Exception;
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\service\AbstractPageService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */