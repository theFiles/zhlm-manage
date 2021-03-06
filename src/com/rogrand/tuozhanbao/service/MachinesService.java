package com.rogrand.tuozhanbao.service;

import com.rogrand.core.domain.PageParam;
import com.rogrand.core.domain.PageResult;
import com.rogrand.core.service.BaseService;
import com.rogrand.tuozhanbao.domain.Machines;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


@Service("MachinesService")
@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, rollbackFor = {Throwable.class})
public class MachinesService
  extends BaseService
{
  public Machines query(String id) throws Exception {
    PageParam param = new PageParam();
    param.put("id", id);
    return (Machines)this.sqlDao.query("ape_machines.pageList", param);
  }







  public PageResult pageList(PageParam param) throws Exception {
    param.setCountSql("ape_machines.pageCount");
    param.setRecordSql("ape_machines.pageList");
    PageResult pageResult = this.pageService.pageQuery(param);
    return pageResult;
  }







  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public void create(Machines machines) throws Exception {
    this.sqlDao.create("ape_machines.create", machines);
  }







  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public void update(Machines machines) throws Exception {
    this.sqlDao.update("ape_machines.update", machines);
  }







  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public void delete(String[] ids) throws Exception {
    this.sqlDao.delete("ape_machines.delete", ids);
  }







  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public void delete(String id) throws Exception {
    String[] ids = { id };
    this.sqlDao.delete("ape_machines.delete", ids);
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\tuozhanbao\service\MachinesService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */