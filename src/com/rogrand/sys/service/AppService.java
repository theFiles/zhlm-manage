package com.rogrand.sys.service;

import com.rogrand.core.domain.PageParam;
import com.rogrand.core.domain.PageResult;
import com.rogrand.core.service.BaseService;
import com.rogrand.sys.domain.App;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("sysAppService")
@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, rollbackFor = {Throwable.class})
public class AppService
  extends BaseService
{
  public App query(String pk) throws Exception {
    return query(new App(pk));
  }








  public App query(Long pk) throws Exception {
    return query(new App(pk));
  }









  public App query(App param) throws Exception {
    return (App)this.sqlDao.query("sys_app.query", param);
  }








  public List<App> list(App param) throws Exception {
    return this.sqlDao.list("sys_app.query", param);
  }








  public PageResult pageList(PageParam param) throws Exception {
    param.setCountSql("sys_app.pageCount");
    param.setRecordSql("sys_app.pageList");
    return this.pageService.pageQuery(param);
  }








  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public String create(App param) throws Exception {
    this.sqlDao.create("sys_app.create", param);
    return "1";
  }








  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public String update(App param) throws Exception {
    this.sqlDao.update("sys_app.update", param);
    return "1";
  }








  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public String delete(App param) throws Exception {
    this.sqlDao.delete("sys_app.delete", param);
    return "1";
  }









  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public String delete(String pk) throws Exception {
    return delete(new App(pk));
  }









  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public String delete(String[] pks) throws Exception {
    for (String pk : pks) {
      delete(pk);
    }
    return "1";
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\sys\service\AppService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */