package com.rogrand.tuozhanbao.service;

import com.rogrand.core.domain.PageParam;
import com.rogrand.core.domain.PageResult;
import com.rogrand.core.service.BaseService;
import com.rogrand.tuozhanbao.domain.IncomeRecords;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


@Service("IncomeRecordsService")
@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, rollbackFor = {Throwable.class})
public class IncomeRecordsService
  extends BaseService
{
  public IncomeRecords query(String id) throws Exception {
    PageParam param = new PageParam();
    param.put("id", id);
    return (IncomeRecords)this.sqlDao.query("ape_income_records.pageList", param);
  }







  public PageResult pageList(PageParam param) throws Exception {
    param.setCountSql("ape_income_records.pageCount");
    param.setRecordSql("ape_income_records.pageList");
    PageResult pageResult = this.pageService.pageQuery(param);
    return pageResult;
  }







  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public void create(IncomeRecords incomeRecords) throws Exception {
    this.sqlDao.create("ape_income_records.create", incomeRecords);
  }







  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public void update(IncomeRecords incomeRecords) throws Exception {
    this.sqlDao.update("ape_income_records.update", incomeRecords);
  }







  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public void delete(String[] ids) throws Exception {
    this.sqlDao.delete("ape_income_records.delete", ids);
  }







  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public void delete(String id) throws Exception {
    String[] ids = { id };
    this.sqlDao.delete("ape_income_records.delete", ids);
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\tuozhanbao\service\IncomeRecordsService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */