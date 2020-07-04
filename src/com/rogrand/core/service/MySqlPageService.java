package com.rogrand.core.service;

import com.rogrand.core.dao.SqlDao;
import com.rogrand.core.domain.PageParam;
import com.rogrand.core.domain.PageResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("mysqlPageService")
@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, rollbackFor = {Throwable.class})
public class MySqlPageService
  extends AbstractPageService
{
  @Autowired
  @Qualifier("sqlDao")
  protected SqlDao sqlDao;

  public void setSqlDao(SqlDao sqlDao) {
    this.sqlDao = sqlDao;
  }









  public PageResult pageQuery(PageParam pageParam) throws Exception {
    PageResult pageResult = new PageResult();

    Long page = pageParam.getPage();
    if (page != null && page.longValue() > 0L) {

      Integer rows = pageParam.getRows();
      if (page.longValue() == 1L) {
        Long recordCount = (Long)this.sqlDao.query(pageParam.getCountSql(), pageParam);
        pageResult.setTotal(recordCount);
      }
      String foot = "limit " + ((page.longValue() - 1L) * rows.intValue()) + "," + rows;
      pageParam.setFoot(foot);
    }

    String sort = pageParam.getSort();
    String order = pageParam.getOrder();
    pageParam.setSortOrder(StringUtils.isEmpty(sort) ? "" : ("order by " + sort + " " + (StringUtils.isEmpty(order) ? "" : order)));

    List pageList = this.sqlDao.list(pageParam.getRecordSql(), pageParam);
    pageResult.setRows(pageList);
    this.logger.info("查询时间：" + pageParam.getQueryTime() + "毫秒");
    return pageResult;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\service\MySqlPageService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */