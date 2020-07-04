package com.rogrand.tuozhanbao.service;

import com.rogrand.core.domain.PageParam;
import com.rogrand.core.domain.PageResult;
import com.rogrand.core.service.BaseService;
import com.rogrand.tuozhanbao.domain.ActivateRecords;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


@Service("ActivateRecordsService")
@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, rollbackFor = {Throwable.class})
public class ActivateRecordsService
  extends BaseService
{
  public ActivateRecords query(String id) throws Exception {
    PageParam param = new PageParam();
    param.put("id", id);
    return (ActivateRecords)this.sqlDao.query("ape_activate_records.pageList", param);
  }







  public PageResult pageList(PageParam param) throws Exception {
    param.setCountSql("ape_activate_records.pageCount");
    param.setRecordSql("ape_activate_records.pageList");
    PageResult pageResult = this.pageService.pageQuery(param);
    return pageResult;
  }







  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public void create(ActivateRecords activateRecords) throws Exception {
    this.sqlDao.create("ape_activate_records.create", activateRecords);
  }







  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public void update(ActivateRecords activateRecords) throws Exception {
    this.sqlDao.update("ape_activate_records.update", activateRecords);
  }







  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public void delete(String[] ids) throws Exception {
    this.sqlDao.delete("ape_activate_records.delete", ids);
  }







  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public void delete(String id) throws Exception {
    String[] ids = { id };
    this.sqlDao.delete("ape_activate_records.delete", ids);
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\tuozhanbao\service\ActivateRecordsService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */