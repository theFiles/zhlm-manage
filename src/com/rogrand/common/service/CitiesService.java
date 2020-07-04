package com.rogrand.common.service;

import com.rogrand.common.domain.Cities;
import com.rogrand.core.domain.PageParam;
import com.rogrand.core.domain.PageResult;
import com.rogrand.core.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("CitiesService")
@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, rollbackFor = {Throwable.class})
public class CitiesService
  extends BaseService
{
  public Cities query(String id) throws Exception {
    PageParam param = new PageParam();
    param.put("id", id);
    return (Cities)this.sqlDao.query("common_cities.pageList", param);
  }







  public List<Cities> list(PageParam param) throws Exception {
    return this.sqlDao.list("common_cities.pageList", param);
  }







  public PageResult pageList(PageParam param) throws Exception {
    param.setCountSql("common_cities.pageCount");
    param.setRecordSql("common_cities.pageList");
    return this.pageService.pageQuery(param);
  }







  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public String create(Cities cities) throws Exception {
    this.sqlDao.create("common_cities.create", cities);
    return "1";
  }







  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public String update(Cities cities) throws Exception {
    this.sqlDao.update("common_cities.update", cities);
    return "1";
  }







  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public String delete(Cities cities) throws Exception {
    this.sqlDao.delete("common_cities.delete", cities);
    return "1";
  }







  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public String delete(String id) throws Exception {
    return delete(new Cities(id));
  }







  @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {Throwable.class})
  public String delete(String[] ids) throws Exception {
    for (String id : ids) {
      delete(id);
    }
    return "1";
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\common\service\CitiesService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */