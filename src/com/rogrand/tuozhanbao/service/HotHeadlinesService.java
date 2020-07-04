package com.rogrand.tuozhanbao.service;

import com.rogrand.core.domain.PageParam;
import com.rogrand.core.domain.PageResult;
import com.rogrand.core.service.BaseService;
import com.rogrand.tuozhanbao.domain.HotHeadlines;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


@Service("HotHeadlinesService")
@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, rollbackFor = {Throwable.class})
public class HotHeadlinesService
  extends BaseService
{
  public HotHeadlines query(String id) throws Exception {
    PageParam param = new PageParam();
    param.put("id", id);
    return (HotHeadlines)this.sqlDao.query("ape_hot_headlines.pageList", param);
  }







  public PageResult pageList(PageParam param) throws Exception {
    param.setCountSql("ape_hot_headlines.pageCount");
    param.setRecordSql("ape_hot_headlines.pageList");
    PageResult pageResult = this.pageService.pageQuery(param);
    return pageResult;
  }







  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public void create(HotHeadlines hotHeadlines) throws Exception {
    this.sqlDao.create("ape_hot_headlines.create", hotHeadlines);
  }







  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public void update(HotHeadlines hotHeadlines) throws Exception {
    this.sqlDao.update("ape_hot_headlines.update", hotHeadlines);
  }







  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public void delete(String[] ids) throws Exception {
    this.sqlDao.delete("ape_hot_headlines.delete", ids);
  }







  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public void delete(String id) throws Exception {
    String[] ids = { id };
    this.sqlDao.delete("ape_hot_headlines.delete", ids);
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\tuozhanbao\service\HotHeadlinesService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */