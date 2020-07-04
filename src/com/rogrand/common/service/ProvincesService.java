package com.rogrand.common.service;

import com.rogrand.common.domain.Provinces;
import com.rogrand.core.domain.PageParam;
import com.rogrand.core.domain.PageResult;
import com.rogrand.core.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("ProvincesService")
@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, rollbackFor = {Throwable.class})
public class ProvincesService
  extends BaseService
{
  public Provinces query(String id) throws Exception {
    PageParam param = new PageParam();
    param.put("id", id);
    return (Provinces)this.sqlDao.query("common_provinces.pageList", param);
  }







  public List<Provinces> list(PageParam param) throws Exception {
    return this.sqlDao.list("common_provinces.pageList", param);
  }







  public PageResult pageList(PageParam param) throws Exception {
    param.setCountSql("common_provinces.pageCount");
    param.setRecordSql("common_provinces.pageList");
    return this.pageService.pageQuery(param);
  }







  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public String create(Provinces provinces) throws Exception {
    this.sqlDao.create("common_provinces.create", provinces);
    return "1";
  }







  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public String update(Provinces provinces) throws Exception {
    this.sqlDao.update("common_provinces.update", provinces);
    return "1";
  }







  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public String delete(Provinces provinces) throws Exception {
    this.sqlDao.delete("common_provinces.delete", provinces);
    return "1";
  }







  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public String delete(String id) throws Exception {
    return delete(new Provinces(id));
  }







  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public String delete(String[] ids) throws Exception {
    for (String id : ids) {
      delete(id);
    }
    return "1";
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\common\service\ProvincesService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */