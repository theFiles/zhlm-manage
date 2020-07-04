package com.rogrand.tuozhanbao.service;

import com.rogrand.core.domain.PageParam;
import com.rogrand.core.domain.PageResult;
import com.rogrand.core.enums.SendState;
import com.rogrand.core.service.BaseService;
import com.rogrand.tuozhanbao.domain.SendRecords;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


@Service("SendRecordsService")
@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, rollbackFor = {Throwable.class})
public class SendRecordsService
  extends BaseService
{
  public SendRecords query(String id) throws Exception {
    PageParam param = new PageParam();
    param.put("id", id);
    return (SendRecords)this.sqlDao.query("ape_send_records.pageList", param);
  }







  public PageResult pageList(PageParam param) throws Exception {
    param.setCountSql("ape_send_records.pageCount");
    param.setRecordSql("ape_send_records.pageList");
    PageResult pageResult = this.pageService.pageQuery(param);
    return pageResult;
  }







  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public void create(SendRecords sendRecords) throws Exception {
    this.sqlDao.create("ape_send_records.create", sendRecords);
  }







  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public void update(SendRecords sendRecords) throws Exception {
    this.sqlDao.update("ape_send_records.update", sendRecords);
  }







  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public void delete(String[] ids) throws Exception {
    this.sqlDao.delete("ape_send_records.delete", ids);
  }







  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public void delete(String id) throws Exception {
    String[] ids = { id };
    this.sqlDao.delete("ape_send_records.delete", ids);
  }






  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public void send(SendRecords sendRecords) throws Exception {
    sendRecords.setState(SendState.已发货.getValue());
    this.sqlDao.update("ape_send_records.update", sendRecords);
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\tuozhanbao\service\SendRecordsService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */