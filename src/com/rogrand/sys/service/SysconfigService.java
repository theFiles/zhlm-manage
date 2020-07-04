package com.rogrand.sys.service;

import com.rogrand.core.domain.PageParam;
import com.rogrand.core.domain.PageResult;
import com.rogrand.core.service.BaseService;
import com.rogrand.core.system.SystemParameter;
import com.rogrand.sys.domain.Sysconfig;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("SysconfigService")
@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, rollbackFor = {Throwable.class})
public class SysconfigService
  extends BaseService
{
  public Sysconfig query(String configid) throws Exception {
    return query(new Sysconfig(configid));
  }








  public Sysconfig query(Sysconfig sysconfig) throws Exception {
    return (Sysconfig)this.sqlDao.query("sys_config.query", sysconfig);
  }







  public Sysconfig query1(String configkey) throws Exception {
    return (Sysconfig)this.sqlDao.query("sys_config.query1", configkey);
  }








  public List<Sysconfig> list(Sysconfig sysconfig) throws Exception {
    return this.sqlDao.list("sys_config.query", sysconfig);
  }







  public List<Sysconfig> list() throws Exception {
    return list(new Sysconfig());
  }







  public PageResult list(PageParam pageParam) throws Exception {
    pageParam.setCountSql("sys_config.pageCount");
    pageParam.setRecordSql("sys_config.pageList");
    return this.pageService.pageQuery(pageParam);
  }








  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public String create(Sysconfig sysconfig) throws Exception {
    this.sqlDao.create("sys_config.create", sysconfig);

    updateConfigCache(sysconfig);

    return "1";
  }








  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public String update(Sysconfig sysconfig) throws Exception {
    this.sqlDao.update("sys_config.update", sysconfig);

    updateConfigCache(sysconfig);

    return "1";
  }








  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public String delete(Sysconfig sysconfig) throws Exception {
    removeConfigCache(sysconfig);

    this.sqlDao.delete("sys_config.delete", sysconfig);

    return "1";
  }








  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public String delete(String configid) throws Exception {
    Sysconfig sysconfig = new Sysconfig(configid);

    removeConfigCache(sysconfig);

    return delete(sysconfig);
  }








  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public String delete(String[] configids) throws Exception {
    for (String configid : configids) {
      delete(configid);
    }
    return "1";
  }








  public Sysconfig queryByKey(String configkey) throws Exception {
    return (Sysconfig)this.sqlDao.query("sys_config.queryByKey", configkey);
  }







  private void updateConfigCache(Sysconfig sysconfig) throws Exception {
    Long id = sysconfig.getConfigid();
    String key = sysconfig.getConfigkey();
    String value = sysconfig.getConfigvalue();
    if (StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(value)) {
      SystemParameter.set(sysconfig.getConfigkey(), sysconfig.getConfigvalue());
    } else if (id != null) {
      Sysconfig record = query(String.valueOf(id));
      if (record != null) {
        SystemParameter.set(record.getConfigkey(), record.getConfigvalue());
      }
    }
  }







  private void removeConfigCache(Sysconfig sysconfig) throws Exception {
    Long id = sysconfig.getConfigid();
    String key = sysconfig.getConfigkey();
    if (StringUtils.isNotEmpty(key)) {
      SystemParameter.remove(sysconfig.getConfigkey());
    } else if (id != null) {
      Sysconfig record = query(String.valueOf(id));
      if (record != null)
        SystemParameter.remove(record.getConfigkey());
    }
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\sys\service\SysconfigService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */