package com.rogrand.tuozhanbao.service;

import com.rogrand.core.domain.PageParam;
import com.rogrand.core.domain.PageResult;
import com.rogrand.core.service.BaseService;
import com.rogrand.tuozhanbao.domain.TradeRecords;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


@Service("TradeRecordsService")
@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, rollbackFor = {Throwable.class})
public class TradeRecordsService
  extends BaseService
{
  public TradeRecords query(String id) throws Exception {
    PageParam param = new PageParam();
    param.put("id", id);
    return (TradeRecords)this.sqlDao.query("ape_trade_records.pageList", param);
  }







  public PageResult pageList(PageParam param) throws Exception {
    param.setCountSql("ape_trade_records.pageCount");
    param.setRecordSql("ape_trade_records.pageList");
    PageResult pageResult = this.pageService.pageQuery(param);
    return pageResult;
  }







  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public void create(TradeRecords tradeRecords) throws Exception {
    this.sqlDao.create("ape_trade_records.create", tradeRecords);
  }







  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public void update(TradeRecords tradeRecords) throws Exception {
    this.sqlDao.update("ape_trade_records.update", tradeRecords);
  }







  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public void delete(String[] ids) throws Exception {
    this.sqlDao.delete("ape_trade_records.delete", ids);
  }







  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public void delete(String id) throws Exception {
    String[] ids = { id };
    this.sqlDao.delete("ape_trade_records.delete", ids);
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\tuozhanbao\service\TradeRecordsService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */